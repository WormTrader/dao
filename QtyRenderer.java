package com.wormtrader.dao;
/********************************************************************
* @(#)QtyRenderer.java	1.00 20100718
* Copyright © 2010 by Richard T. Salamone, Jr. All rights reserved.
*
* QtyRenderer: Renders a Qty, which is an Integer holding a number,
* using a blue background for a positive and red for a negative.
*
* @author Rick Salamone
* @version 1.00
* 20100718 rts created
*******************************************************/
import java.awt.Color;
import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

public class QtyRenderer extends JLabel
	implements TableCellRenderer
	{
	// Common constructor code
		{
		setOpaque(true);
		setHorizontalAlignment(RIGHT);
		setFont(new JTextField().getFont());
		setForeground( Color.WHITE );
		}

	public Component getTableCellRendererComponent(
                            JTable table, Object quantity,
                            boolean isSelected, boolean hasFocus,
                            int row, int column)
		{
		String text;
		Color bg;
		int qty = ((Number)quantity).intValue();
		if ( qty == 0 )
			{
			text = "";
			bg = isSelected?	table.getSelectionBackground()
			               : table.getBackground();
			}
		else
			{
			text = "" + qty;
			bg = (qty > 0)? Color.BLUE : Color.RED;
			if ( isSelected ) bg = bg.darker();
			}
		setText( text );
		setBackground( bg );
		return this;
		}
	}
