package com.wormtrader.dao;
/********************************************************************
* @(#)SBExecution.java 1.00 2007????
* Copyright © 2007-2013 by Richard T. Salamone, Jr. All rights reserved.
*
* SBExecution: Interface that describes an execution.
* Concrete implementations for real and simulated executions.
*
* @author Rick Salamone
* @version 1.00
* 2007???? rts created
* 20121018 rts added getQtyString()
* 20130310 rts added setFees(), setPrice(), SetQty()
* 20130315 rts added execid(), type(), localSymbol(), exchange()
* 20130326 rts added remaining accessor methods for db execs
* 20130411 rts prices use USD
*******************************************************/

public interface SBExecution
	{
	public boolean equals( SBExecution e );
	public long    getID();
	public String  execid();
	public int     getOrderID();
	public String  type();
	public String  localSymbol();
	public String  exchange();
	public String  getDesc();
	public USD     getFees();
	public String  getOptDesc();
	public USD     getPrice();
	public int     getQty();  // signed quantity
	/**
	* @return the quantity of this execution as a String. If the
	*         execution is composed of multiple partial executions
	*         then return the total quantity followed by a colon
	*         then a csv of the partial quantities
	*         e.g. -200: -100,-100
	*/
	public String  getQtyString();
	public String  getSide();
	public String  getSymbol();
	public String  getReason();
	public long    getTime();
	public String  yyyymmdd();
	public String  hhmmss();
	public boolean isOpposing( SBExecution e );
	public boolean isOpposingIgnoreQty( SBExecution e );

	public void setFees(int cents);
	public void setPrice(int cents);
	public void setQty(int aSignedQty);
	public boolean setReason(String s);
	public void setDate(String yyyymmdd);
	public void setHour(String hhmmss);
	public void setType(String aType);
	public void setSymbol(String aSymbol);
	public void setLocalSymbol(String aLocalSymbol);
	public void setExchange(String aExchange);
	public void setSide(String aSide);
	public void setOptDesc(String aOptDesc);
	public void setExecID(String aExecID);
	public void setOrderID(int aOrderID);
	}
