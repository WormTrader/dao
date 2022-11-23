package com.wormtrader.dao;
/********************************************************************
* @(#)IntSpinner.java 1.00 20121028
* Copyright © 2012 by Richard T. Salamone, Jr. All rights reserved.
*
* IntSpinner: A spinner editor for int values.
*
* @author Rick Salamone
* @version 1.00
* 20121028 rts created
*******************************************************/
import javax.swing.*;

public final class IntSpinner
	extends JSpinner
	{
	public IntSpinner() { this(0, 0, 100, 1); }
	public IntSpinner(int initialValue, int min, int max, int step)
		{
		super(new SpinnerNumberModel(initialValue, min, max, step));
		}

	public void set(int value) { setValue(value); }
	public int get() { return ((Integer)getValue()).intValue(); }
	}

