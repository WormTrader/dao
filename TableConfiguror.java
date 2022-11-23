package com.wormtrader.dao;
/********************************************************************
* @(#)TableConfiguror.java 1.00 20091111
* Copyright © 2011-2013 by Richard T. Salamone, Jr. All rights reserved.
*
* TableConfiguror: Allows editing/saving/restoring user preferences for
* a table including columns to include, the column order, and the font size.
*
* @author Rick Salamone
* @version 1.00
* 20110715 rts last known modification date
*******************************************************/
import com.shanebow.dao.FieldMeta;
import com.shanebow.ui.LAF;
import com.shanebow.ui.PreferencesEditor;
import com.shanebow.ui.SublistChooser;
import com.shanebow.util.SBProperties;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;

public final class TableConfiguror
	extends JPanel
	implements PreferencesEditor
	{
	private final SublistChooser fColumnChooser;
	private final ConfigurableTable fTable;
//	private final JCheckBox chkAuto;

	public TableConfiguror( ConfigurableTable aTable )
		{
		super();
		setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));

		fTable = aTable;
		SBProperties props = SBProperties.getInstance();
		String propertyPrefix = fTable.getPropertyPrefix();

		Object[] fieldChoices = fTable.getAvailableFields();
		fColumnChooser = new SublistChooser( fieldChoices, fieldChoices.length, 8, 120 );
		LAF.titled(fColumnChooser, "Select & Order Columns");

		int[] fields = props.getIntArray(propertyPrefix + "fields");
		Object[] chosen = new Object[fields.length];
		for ( int i = 0; i < fields.length; i++ )
			chosen[i] = fieldChoices[fields[i]];
		fColumnChooser.chooseItems ( chosen );
		add( fColumnChooser );

		// Column Resizing
/**********
		if ( fTable instanceof JTable )
			{
			boolean autoResize = props.getBoolean(propertyPrefix+"col.fit", false);
			((JTable)fTable).setAutoResizeMode(autoResize? JTable.AUTO_RESIZE_ALL_COLUMNS
			                                    : JTable.AUTO_RESIZE_OFF);
			chkAuto = new JCheckBox("Fit Columns", autoResize);
			JPanel p = new JPanel(); p.add(chkAuto);
			add( LAF.titled(p, "Column Auto Resize" ));
			}
		else chkAuto = null;
**********/
		add( fontPanel() );
LAF.addPreferencesEditor(this);
fTable.configure(fields);
		}

	public int getTableFontSize()
		{
		return SBProperties.getInstance().getInt(fTable.getPropertyPrefix() + "font.size", 12);
		}

	private JSlider fontPanel()
		{
		int size = getTableFontSize();
		JSlider sizeSlider = new JSlider(JSlider.HORIZONTAL, 5, 25, size);
		LAF.titled(sizeSlider, "Font Size");
		sizeSlider.setMajorTickSpacing(5); // sets numbers for big tick marks
		sizeSlider.setMinorTickSpacing(1);  // smaller tick marks
		sizeSlider.setPaintTicks(true);     // display the ticks
		sizeSlider.setPaintLabels(true);    // show the numbers
		sizeSlider.setToolTipText("Font point size");
		sizeSlider.addChangeListener(new ChangeListener()
			{
			public void stateChanged(ChangeEvent e)
				{
				int fontSize = ((JSlider)e.getSource()).getValue();
				setTableFontSize(fontSize);
				}
			});
setTableFontSize(size);
		return sizeSlider;
		}

	private void setTableFontSize(int size)
		{
		((JTable)fTable).setFont(new Font(Font.SANS_SERIF, Font.PLAIN, size));
		SBProperties.set(fTable.getPropertyPrefix() + "font.size",  "" + size);
		}

	/**
	* Return a GUI component which allows the user to edit
	* this set of related preferences.
	*/  
	public JComponent getComponent() { return this; }

	/**
	* The name of the tab in which this <tt>TableConfiguror</tt>
	* will be placed. 
	*/
	public String getTitle()
		{
		return SBProperties.get(fTable.getPropertyPrefix() + "title");
		}

	/**
	* The mnemonic to appear in the tab name.
	*
	* <P>Must match a letter appearing in {@link #getTitle}.
	* Use constants defined in <tt>KeyEvent</tt>, for example <tt>KeyEvent.VK_A</tt>.
	*/
	public int getMnemonic()
		{
		return (int)SBProperties.get(fTable.getPropertyPrefix() + "mnemonic")
		            .toUpperCase().charAt(0);
		}
  
	/**
	* Store the related preferences as they are currently displayed,
	* overwriting all corresponding settings.
	*/
	public void savePreferences()
		{
		String csv = "";
		Object[] fieldChoices = fTable.getAvailableFields();
		Object[] chosen = fColumnChooser.getChosen();
		int[] fields = new int[chosen.length];
		for ( int i = 0; i < chosen.length; i++ )
			{
			fields[i] = findIndex(fieldChoices, chosen[i]);
			csv += ((i!=0)?",":"") + fields[i];
			}
		String propertyPrefix = fTable.getPropertyPrefix();
		SBProperties.set(propertyPrefix + "fields", csv);

/**********
		if ( fTable instanceof JTable )
			{
			boolean auto = chkAuto.isSelected();
			SBProperties.set(propertyPrefix+"col.fit", auto );
			((JTable)fTable).setAutoResizeMode(auto? JTable.AUTO_RESIZE_ALL_COLUMNS
		                             : JTable.AUTO_RESIZE_OFF);
			}
**********/
		fTable.configure(fields);
		}

	private int findIndex(Object[] choices, Object chosen)
		{
		for (int i = 0; i < choices.length; i++)
			if (choices[i].equals(chosen)) return i;
		return -1;
		}

	/**
	* Reset the related preferences to their default values, but only as 
	* presented in the GUI, without affecting stored preference values.
	*
	* <P>This method may not apply in all cases. For example, if the item 
	* represents a config which has no meaningful default value (such as a
	* mail server name), the desired behavior may be to only allow a manual
	* change. In such a case, implement this method as a no-operation. 
	*/
	public void matchGuiToDefaultPreferences()
		{
		}
	}
