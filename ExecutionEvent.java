package com.wormtrader.dao;
/********************************************************************
* @(#)ExecutionRecord.java 1.00 2007????
* Copyright © 2007-2014 by Richard T. Salamone, Jr. All rights reserved.
*
* ExecutionEvent: The object sent to ExecutionListeners when an
* important event happens in the life cycle of an SBExecution.
*
* @author Rick Salamone
* @version 1.00
* 2007???? rts created
* 20140329 rts added IMPORT_BEGIN, IMPORT_DONE
*******************************************************/
import com.wormtrader.dao.SBExecution;

public class ExecutionEvent extends java.util.EventObject
	{
	public static final int EXEC_DELETED=1;
	public static final int EXEC_EXPIRED=2;
	public static final int EXEC_MODIFIED=3;
	public static final int EXEC_DIVIDED=4;
	public static final int EXEC_NEW_REASON=5;
	public static final int EXEC_ADDED=6;
	public static final int IMPORT_BEGIN=7; // this is last exec of an import
	public static final int IMPORT_DONE=8; // this is last exec of an import

	private int eventID;

	public ExecutionEvent(SBExecution src, int id)
		{
		super(src);
		eventID = id;
		}
	public int getActionID() { return eventID; }
	public SBExecution getExecution() { return (SBExecution)getSource(); }
	}
