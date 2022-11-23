package com.wormtrader.dao;
/********************************************************************
* @(#)USD.java	1.00 20100718
* Copyright © 2010 by Richard T. Salamone, Jr. All rights reserved.
*
* USDRenderer: Renders a USD, US Dollars object in a JTable.
*
* @author Rick Salamone
* @version 1.00
* 20100718 rts created
*******************************************************/
import com.wormtrader.dao.USD;
import java.awt.Color;
import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

public class USDRenderer extends JLabel
	implements TableCellRenderer
	{
	// common constructor code
		{
		setOpaque(true);
		setHorizontalAlignment(RIGHT);
		setFont(new JTextField().getFont());
		}

	public Component getTableCellRendererComponent(
                            JTable table, Object usd,
                            boolean isSelected, boolean hasFocus,
                            int row, int column)
		{
		Color fg, bg;
		if ( isSelected )
			{
			fg = table.getSelectionForeground();
			bg = table.getSelectionBackground();
			}
		else
			{
			fg = table.getForeground();
			bg = table.getBackground();
			}
		setText( (((USD)usd).cents()==0)? "" : usd.toString());
		setForeground( fg );
		setBackground( bg );
		return this;
		}
	}
