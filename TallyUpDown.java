package com.wormtrader.dao;
/********************************************************************
* @(#)TallyUpDown.java 1.00 20120905
* Copyright © 2012 by Richard T. Salamone, Jr. All rights reserved.
*
* TallyUpDown: Collects and tallies the up and down changes for a particular
* measurement. For instance, the change in price five minutes after each hit
* from a query. The contructor takes a title as it's argument which is later
* used in reporting the result.
*
* To add results to the tally call
*   - delta(endValue, startValue)
* This calculates and returns the change(delta) of the two values and updates
* the tally.
*
* To get the cumulative results you can use the following:
*   - toString() returns a vanilla String summary of # up, # down, & percents
*   - html() returns a prettier HTML summary
*   - percentUp() returns the percent that were up (any amount)
*   
* @author Rick Salamone
* @version 1.00
* 20120905 rts created
* 20120916 rts decoupled from HistoryManager's QueryWorkor
*******************************************************/
import com.shanebow.util.SBFormat;

public class TallyUpDown
	{
	static final char UP_ARROW='\u2191';
	static final char DN_ARROW='\u2193';

	private String title;
	private int numTotal;
	private int numUp;
	private int numDn;
	private int sumUp;
	private int sumDn;

	public TallyUpDown(String aTitle) { title = aTitle; }

	public final int delta(int endValue, int startValue)
		{
		int change = endValue - startValue;
		++numTotal;
		if (change > 0)
			{ ++numUp; sumUp += change; }
		else if (change < 0)
			{ ++numDn; sumDn += change; }
		return change;
		}

	public String toString()
		{
		int exp = expect();
		return title + " " + percent(numUp) + UP_ARROW
		             + " " + percent(numDn) + DN_ARROW
+ " " + numTotal + " hits";
//		             + " avgs:" + average(sumUp,numUp) + average(sumDn,numDn)
//		             + ((exp ==0)? "" : SBFormat.toDollarString(exp));
		}

	public String html()
		{
		int exp = expect();
		String expStr = (exp ==0)? "" : " <b><font color="
                                   + ((exp>0)? "blue" : "red")
		                              + ">" + SBFormat.toDollarString(exp)
		                              + "</font></b>";
		return title + " " + numUp + htmlPercent(numUp) + UP_ARROW
		             + " " + numDn + htmlPercent(numDn) + DN_ARROW
		             + " avgs:" + average(sumUp,numUp) + average(sumDn,numDn)
		             + expStr;
		}

	private String average(int sum, int num)
		{
		return (num==0)? " --"
		               : " " + SBFormat.toDollarString(sum/num);
		}

	private int expect()
		{
		return (numTotal == 0)? 0 : (sumUp + sumDn)/numTotal;
		}

	private String percent(int num)
		{
		return (numTotal==0)? "--%" : "" + (100 * num/numTotal) + "%";
		}

	private String htmlPercent(int num)
		{
		String pc = (numTotal==0)? "--" : "" + (100 * num/numTotal);
		return "(<b>" + pc + "%</b>)";
		}

	public int upPercent() { return (numTotal==0)? 0 : (100 * numUp/numTotal); }
	}
