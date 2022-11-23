package com.wormtrader.dao;
/********************************************************************
* @(#)SortableTable.java 1.00 20101104
* Copyright © 2010-2013 by Richard T. Salamone, Jr. All rights reserved.
*
* SortableTable: Extends JTable to support sorting and persistent
* configuration.
*
* Sorting: If the the model implements the SortableTableModel interface,
* SortableTable will add a mouse listener to the column headers that calls the
* model's sort method.
*
* @author Rick Salamone
* @version 2.00
* 20101104 rts created from various apo apps
* 20110212 rts added Qty cell type
* 20110212 rts added makeConfigurable()
* 20110507 rts added setEditor()
* 20110513 rts added support for sorting
* 20130411 rts remove datafield stuff - just sorting here
*******************************************************/
import com.shanebow.ui.LAF;
import java.awt.Component;
import java.awt.Font;
import java.awt.event.InputEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.*;
import javax.swing.table.*;

public class SortableTable
	extends JTable
	{
//common contructor code
		{
		setFillsViewportHeight( true );

		// Set up sorting on column headers
		if ( getModel() instanceof SortableTableModel )
			{
			System.out.println("SortableTable: " + getClass().getSimpleName()
				+ "'s model implements SortableTableModel" );
			JTableHeader header = getTableHeader();
			header.setUpdateTableInRealTime(true);
			header.setReorderingAllowed(false);
			header.addMouseListener(new MouseAdapter() // to handle sorts
				{
				public void mouseClicked(MouseEvent e)
					{
					TableColumnModel colModel = getColumnModel();
					int columnModelIndex = colModel.getColumnIndexAtX(e.getX());
					int sortColumn = colModel.getColumn(columnModelIndex).getModelIndex();
					int shiftPressed = e.getModifiers()&InputEvent.SHIFT_MASK;
					boolean ascending = (shiftPressed == 0);
					SortableTableModel model = (SortableTableModel)getModel();
					model.sort(sortColumn, ascending);
/*********
					@TODO: it would be nice if the selection were restored after the sort
					if ( wasSelected != null )
						{
						int index = model.indexOf(wasSelected);
						if ( index >= 0 )
							{
							setRowSelectionInterval(index,index);
							scrollRectToVisible( getCellRect(index, 0, true));
							}
						}
*********/
					} 
				});
			}
		}

	public SortableTable(TableModel tm)
		{
		super(tm);
		}

	@Override public String toString() { return getClass().getSimpleName() + ": Unnamed"; }

/*******
	public void makeConfigurable()
		{
		if ( this instanceof ConfigurableTable )
			LAF.addPreferencesEditor(new TablePreferencesEditor((ConfigurableTable)this));
		else throw new IllegalStateException("Table doesn't implement Configurable");
		}
*******/
	}
