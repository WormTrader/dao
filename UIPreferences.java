package com.wormtrader.dao;
/********************************************************************
* @(#)UIPreferences.java 1.00 20130503
* Copyright © 2012-2013 by Richard T. Salamone, Jr. All rights reserved.
*
* UIPreferences: Allows user to configure general UI parameters.
*
* @author Rick Salamone
* @version 1.00
* 20130503 rts created
*******************************************************/
import com.shanebow.ui.PreferencesEditor;
import com.shanebow.ui.layout.LabeledPairPanel;
import com.shanebow.ui.themes.SBThemePicker;
import java.awt.Dimension;
import javax.swing.*;

public final class UIPreferences
	implements PreferencesEditor
	{
	private UIPreferencesPanel fUIPreferencesPanel;

	/**
	* Return a GUI component which allows the user to edit
	* this set of related preferences.
	*/  
	@Override public JComponent getComponent()
		{
		if (fUIPreferencesPanel == null)
			fUIPreferencesPanel = new UIPreferencesPanel();
		return fUIPreferencesPanel;
		}

	/**
	* The name of the tab in which this PreferencesEditor will be placed.
	*/
	@Override public String getTitle() { return "UI"; }

	/**
	* The mnemonic to appear in the tab name.
	*/
	@Override public int getMnemonic() { return 'U'; }
  
	/**
	* Store the related preferences as they are currently displayed,
	* overwriting all corresponding settings.
	*/
	@Override public void savePreferences() { fUIPreferencesPanel.save(); }

	/**
	* Reset the related preferences to their default values, but only as 
	* presented in the GUI, without affecting stored preference values.
	*/
	@Override public void matchGuiToDefaultPreferences() {}

	private final class UIPreferencesPanel
		extends LabeledPairPanel
		{
		public UIPreferencesPanel()
			{
			super();
			SBThemePicker themePicker = new SBThemePicker();
			addRow( themePicker.toString(), themePicker );
			setPreferredSize(new Dimension(320, 125));
	//		populate();
			}

		/*******
		public void populate()
			{
			SBProperties props = SBProperties.getInstance();
			}
		*******/

		public void save()
			{
		//	SBProperties props = SBProperties.getInstance();
			}
		}
	}
