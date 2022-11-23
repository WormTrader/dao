package com.wormtrader.dao;
/********************************************************************
* @(#)ConfigurableTable.java 1.00 20110212
* Copyright (c) 2011-2013 by Richard T. Salamone, Jr. All rights reserved.
*
* ConfigurableTable: Interface for a table that may be configured
* via a TablePreferencesEditor.
*
* @author Rick Salamone
* @version 4.00
* 20110212 rts created
* 20130401 rts removed all dao references - now a stand alone module
*******************************************************/

public interface ConfigurableTable
	{
	/**
	* @return an array of columns that may be displayed in the table
	*/
	Object[] getAvailableFields();

	/**
	* @return a String that holds the prefix for the property names
	*         that will be configured. Should begin with "usr."
	*/
	String getPropertyPrefix();

	/**
	* Apply the configuration settings to the table. Called when
	* the user selects "Apply" from the preferences editor.
	* @param int[] chosenIndicies - an array of indices into the
	*    array of available columns returned by getAvailableColumns()
	* Note that this array of indicies is also saved in the properties
	*/
	void configure(int[] chosenIndicies);
	}
