package com.wormtrader.dao;
/********************************************************************
* @(#)USDSpinner.java 1.00 20120527
* Copyright © 2012 by Richard T. Salamone, Jr. All rights reserved.
*
* USDSpinner: A spinner style editor for USD values. Implementation
* note - the textfield is owned and maintained by the model. Since the
* model will never be shared, this is the most expedious approach.
*
* @author Rick Salamone
* @version 1.00
* 20120527 rts created
* 20120809 rts uses SBFormat.parseDollarString to fix probs with Double.parseDouble
*******************************************************/
import com.wormtrader.dao.USD;
import com.shanebow.ui.SBDialog;
import com.shanebow.util.SBFormat;
import javax.swing.*;

public final class USDSpinner
	extends JSpinner
	{
	public USDSpinner() { super(new USDModel()); }

	public void set(USD aUSD) { ((USDModel)getModel()).set(aUSD); }
	public void setCents(int aCents) { ((USDModel)getModel()).set(aCents); }

	public int getCents() { return ((USDModel)getModel()).getCents(); }
	public String getText() { return ((JTextField)getEditor()).getText(); }

	protected JComponent createEditor(SpinnerModel model)
		{
		return ((USDModel)model).getEditor();
		}

	/** commitEdit() - Ignore since model auto-commits everything */
	@Override public void commitEdit() {}
	}

final class USDModel
	extends AbstractSpinnerModel
	{
	private int fCents;
	private final JTextField fEditor = new JTextField();

	public USDModel()
		{
		super();
		fEditor.setHorizontalAlignment(JTextField.RIGHT);
		}

	public void set(USD aUSD)
		{
		set(aUSD.cents());
		}

	public void set(int aCents)
		{
		if ( fCents == aCents )
			return;
		fCents = aCents;
		fEditor.setText(formatted(fCents));
		fireStateChanged();
		}

	private String formatted(int cents)
		{
		return (cents == 0)? "" : SBFormat.toDollarString(cents);
		}

	public JTextField getEditor() { return fEditor; }

	public int getCents()
		{
		String text = fEditor.getText().trim();
		try { return SBFormat.parseDollarString(text); }
		catch (Exception e) { return fCents; }
		}

	/**
	* getValue() returns the current value of the model as
	* a String - first we try to parse the user's input and
	* use that if it is parsable - cause we don't want to
	* change things out from under his editing.
	*/
	public String getValue()
		{
		String text = fEditor.getText().trim();
		try { SBFormat.parseDollarString(text); return text; }
		catch (Exception e) { return formatted(fCents); }
		}

	public String getNextValue() { return formatted(getCents()+1); }
	public String getPreviousValue() { return formatted(getCents()-1); }

	public void setValue(Object x) throws IllegalArgumentException
		{
		if (x  instanceof USD)
			set(((USD)x).cents());
		else
			{
			try { set( SBFormat.parseDollarString(x.toString())); }
			catch (Exception e) { SBDialog.inputError(x.toString() + " is an invalid amount"); }
			}
		}
	}
