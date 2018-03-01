package com.myutility;

public class TimerModel {

	private int timerid;
	private String timername;
	private String timertime;
	private String timerdays;
	private boolean timeractive;
	
	
	public TimerModel(int timerid, String timername, String timertime, String timerdays, boolean timeractive)
	{
		this.timerid = timerid;
		this.timeractive = timeractive;
		this.timerdays = timerdays;
		this.timername = timername;
		this.timertime = timertime;
	}
	
	public void setTimerId(int timerid)
	{
		this.timerid = timerid;
	}
			
	public void setTimerName(String timername)
	{
		this.timername = timername;
	}
	public void setTimerTime(String timertime)
	{
		this.timertime = timertime;
	}
	
	public void setTimerDays(String timerdays)
	{
		this.timerdays = timerdays;
	}
	public void setTimerActive(boolean timeractive)
	{
		this.timeractive = timeractive;
	}
	
	
	public int getTimerId()
	{
		return timerid;
	}
	
	public String getTimerName()
	{
		return timername;
	}
	public String getTimerTime()
	{
		return timertime;
	}	
	public String getTimerDays()
	{
		return timerdays;
	}
	public boolean getTimerActive()
	{
		return timeractive;
	}
	
	
}
