package com.wormtrader.dao;
/********************************************************************
* @(#)USD.java	1.00 20100718
* Copyright © 2010-2013 by Richard T. Salamone, Jr. All rights reserved.
*
* USD: United States Dollars; represented internally as an integer
* holding the number of cents.
*
* @author Rick Salamone
* @version 1.00
* 20100718 rts created
* 20130411 rts added ctors for String and double
* 20130411 rts implements Comparable
* 20140402 rts added add(USD)
*******************************************************/
import com.shanebow.util.SBFormat;

public final class USD
	implements Comparable<USD>
	{
	private int m_cents;

	public USD() { this(0); }
	public USD(int cents) { m_cents = cents; }
	public USD(double dollarsAndCents) { this(SBFormat.parseDollarString(dollarsAndCents)); }
	public USD(String dollarsAndCents) { this(SBFormat.parseDollarString(dollarsAndCents)); }
	public USD(int dollars, int cents) { this(cents + 100*dollars); }

	public int cents() { return m_cents; }
	public boolean set( int cents )
		{
		if ( cents == m_cents )
			return false;
		m_cents = cents;
		return true;
		}

	public USD add(USD aOther)
		{
		m_cents += aOther.m_cents;
		return this;
		}

	@Override public String toString()
		{
//		return (m_cents == 0) ? "" : SBFormat.toDollarString(m_cents);
		return SBFormat.toDollarString(m_cents);
		}

	public boolean equals(int aCents) { return m_cents == aCents; }

	@Override public int compareTo(USD other) { return m_cents - other.m_cents; }

	@Override public boolean equals(Object other)
		{
		if ( other == null ) return false;
		if ( other instanceof USD )
			return ((USD)other).m_cents == this.m_cents;
		else if ( other instanceof Number )
			return ((Number)other).intValue() == this.m_cents;
		else return false;
		}
	}
