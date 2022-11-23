package com.wormtrader.dao;
/********************************************************************
* @(#)HitListener.java 1.00 20130314
* Copyright © 2013 by Richard T. Salamone, Jr. All rights reserved.
*
* HitListener:
*
* @author Rick Salamone
* @version 1.00
* 20130314 rts created
* 20130410 rts Added hhmm param to hitSelected
*******************************************************/

public interface HitListener extends java.util.EventListener
	{
	public abstract void hitSelected(String symbol, String yyyymmdd, String hhmm);
	}
