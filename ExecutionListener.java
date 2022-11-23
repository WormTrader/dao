package com.wormtrader.dao;

public interface ExecutionListener extends java.util.EventListener
	{
	public abstract void executionChanged(ExecutionEvent e);
	}
