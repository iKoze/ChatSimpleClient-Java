package de.floriware.utils;

import java.awt.Label;
import java.util.Timer;
import java.util.TimerTask;

public class StatusLine
{
	protected Label statusline;
	public String default_message = "";
	public long default_timeout = 5000;
	protected Timer resetter;
	
	protected void init()
	{
		resetter = new Timer();
		statusline.setText(default_message);
	}
	
	public StatusLine(Label statusline)
	{
		this.statusline = statusline;
		init();
	}
	
	public StatusLine(Label statusline, String default_message)
	{
		this.statusline = statusline;
		this.default_message = default_message;
		init();
	}
	
	public StatusLine(Label statusline, String default_message, long default_timeout)
	{
		this.statusline = statusline;
		this.default_message = default_message;
		this.default_timeout = default_timeout;
		init();
	}
	
	public void notify(String message)
	{
		statusline.setText(message);
		reset(default_timeout);
	}
	
	public void notify(String message, long timeout)
	{
		statusline.setText(message);
		reset(timeout);
	}
	
	protected void reset(long timeout)
	{
		TimerTask reset = new TimerTask()
		{
			public void run()
			{
				statusline.setText(default_message);
			}
		};

		try
		{
			resetter.schedule(reset, timeout);
		}
		catch (Exception e)
		{
			
		}
	}
}
