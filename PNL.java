package com.wormtrader.dao;
/********************************************************************
* @(#)PNL.java	1.00 20100718
* Copyright © 2010-2012 by Richard T. Salamone, Jr. All rights reserved.
*
* PNL:
*
* @version 1.00
* @author Rick Salamone
* 20100718 rts created
*******************************************************/
import com.shanebow.util.SBFormat;

public final class PNL
	{
	private int m_cents;
	public PNL() { this(0); }
	public PNL(int cents) { m_cents = cents; }
	public PNL(int dollars, int cents) { this(cents + 100*dollars); }

	public void set(int cents) { m_cents = cents; }
	public int intValue() { return m_cents; }

	public String toString()
		{
		return (m_cents == 0) ? "" : SBFormat.toDollarString(m_cents);
		}
	}
