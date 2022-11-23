package com.wormtrader.dao;
/********************************************************************
* @(#)ExecDAO.java 1.00 20120607
* Copyright © 2012-2013 by Richard T. Salamone, Jr. All rights reserved.
*
* ExecDAO: Interface for execution data access objects.
*
* @author Rick Salamone
* @version 1.00
* 20120607 rts created
* 20130312 rts finished version which marshalls all current access requirements
* 20130315 rts added exportTo which will let one DAO copy records from another
* 20130411 rts use USD for prices
* 20140329 rts added fireImportBegin() & fireImportDone()
*******************************************************/
import com.wormtrader.dao.ExecutionEvent;
import com.wormtrader.dao.ExecutionListener;
import com.shanebow.util.SBLog;
import java.util.Vector;
import java.util.List;

public abstract class ExecDAO
	{
	public static final String ALL_DATES = "All Dates";
	public static final String ALL_SYMBOLS = "All Symbols";
	public static final String ALL_TYPES = "All Types";
	public static final String ALL_REASONS = "All Reasons";

	private static final Vector<ExecutionListener> m_listeners
													= new Vector<ExecutionListener>();
	public static synchronized void addExecutionListener( ExecutionListener el )
		{
		if ( !m_listeners.contains( el ))
			m_listeners.add( el );
		}
	public synchronized void removeExecutionListener( ExecutionListener el )
		{
		m_listeners.remove( el );
		}
	@SuppressWarnings("unchecked")
	// private  // package private now - called by DAO
	protected final void fireExecutionEvent(SBExecution aExec, int actionID)
		{
		Vector<ExecutionListener> listeners;
		synchronized (this)
			{
			listeners = (Vector<ExecutionListener>)m_listeners.clone();
			}
		ExecutionEvent evt = new ExecutionEvent(aExec, actionID);
		int numListeners = listeners.size();
		for (int i = 0; i < numListeners; i++ )
			listeners.get(i).executionChanged(evt);
		}

	public final void fireImportBegin(SBExecution aExec) {
		fireExecutionEvent(aExec, ExecutionEvent.IMPORT_BEGIN);
		}

	public final void fireImportDone(SBExecution aExec) {
		fireExecutionEvent(aExec, ExecutionEvent.IMPORT_DONE);
		}

	protected String fAccount;
	public final String getAccount() { return fAccount; }
	public void setAccount(String aAccount) { fAccount = aAccount; }

	public final SBExecution insert(SBExecution exec)
		{
		return insert(exec.getID(), exec.execid(), exec.getSymbol(),
				   exec.localSymbol(), exec.yyyymmdd(), exec.hhmmss(), exec.exchange(),
				   exec.getOptDesc(), exec.type(), exec.getQty(), exec.getSide(),
				   exec.getPrice(), exec.getFees(), exec.getReason());
		}

	public final String side(int aSignedQty)
		{
		return (aSignedQty > 0)? "BOT" : "SLD";
		}

	/**
	* @returns a list of executions from "this" DAO for the specified date that are
	* not in the specified DAO. In other words this will return a list of executions
	* that need to be imported
	*/
	public final List<SBExecution> notIn(ExecDAO aOther, String yyyymmdd)
		{
		Vector<SBExecution> exports = new Vector<SBExecution>();
		for (SBExecution exec : fetchExecs(ALL_SYMBOLS, yyyymmdd, yyyymmdd))
			if ( aOther.get(exec.getID(), exec.getSymbol()) == null ) // not in aOther
				{
				SBExecution exported = exec; // aOther.insert(exec);
				exports.add(exported);
				log("exported to " + aOther + exec);
				}
		exports.trimToSize();
		return exports;
		}

	public final boolean commit(SBExecution aExec)
		{
		return commit(aExec, ExecutionEvent.EXEC_MODIFIED);
		}

	abstract public boolean commit(SBExecution aExec, int aExecEvent);
	abstract public boolean delete(SBExecution aExec);
	abstract public void disconnect();
	abstract public List<String> distinctDates(String aSymbol);
	abstract public List<String> distinctSymbols();
	abstract public List<String> distinctSymbols(String aReason); // only tab warnings
	abstract public List<String> distinctReasons();
	abstract public boolean expire(SBExecution aExec);
	abstract public List<SBExecution> fetch(String yyyymmdd, String symbol,
	                                        String type, String reason);
	abstract public List<SBExecution> fetchExecs(String aSymbol, String aDate1, String aDate2);
	abstract public SBExecution get(long permid, String symbol);
	abstract public SBExecution insert(long permid, String execid, String symbol,
		String localSymbol, String yyyymmdd, String hhmmss, String exchange,
		String optdesc, String type, int qty, String side, USD price,
		USD fees, String strategy);
	abstract public boolean split(SBExecution aExec, int[] qty);

	protected final void log(String fmt, Object... args)
		{
		String msg = toString() + "." + String.format(fmt, args);
		SBLog.write(msg);
//		System.out.println(msg);
		}
	}
