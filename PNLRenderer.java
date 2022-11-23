package com.wormtrader.dao;
/********************************************************************
* @(#)PNL.java	1.00 20100718
* Copyright © 2010-2012 by Richard T. Salamone, Jr. All rights reserved.
*
* PNLRenderer: Renders a PNL, which is an Integer holding the number
* of cents, using a blue background for a profit and red for a loss.
*
* @author Rick Salamone
* @version 1.00
* 20100718 rts created
*******************************************************/
import com.shanebow.util.SBFormat;
import java.awt.Color;
import java.awt.Component;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.JTable;
import javax.swing.table.TableCellRenderer;

public class PNLRenderer extends JLabel
	implements TableCellRenderer
	{
		{
		setOpaque(true);
		setHorizontalAlignment(RIGHT);
		setFont(new JTextField().getFont());
		setForeground( Color.WHITE );
		}

	public Component getTableCellRendererComponent(
                            JTable table, Object profit,
                            boolean isSelected, boolean hasFocus,
                            int row, int column)
		{
		String text;
		Color bg;
		int pnl = (profit == null)? 0
		        : (profit instanceof Integer) ? ((Integer)profit).intValue()
		        : (profit instanceof PNL) ? ((PNL)profit).intValue()
		        : 0;
		if ( pnl == 0 )
			{
			text = "";
			bg = isSelected?	table.getSelectionBackground()
			               : table.getBackground();
			}
		else
			{
			text = SBFormat.toDollarString( pnl );
			bg = (pnl > 0)? Color.BLUE : Color.RED;
			if ( isSelected ) bg = bg.darker();
			}
		setText(text);
		setBackground(bg);
		return this;
		}
	}
