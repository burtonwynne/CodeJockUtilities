package com.codejock.utilities.threads;

public abstract class Task 
{
	//Job theJob;
	public abstract void doTask() throws InterruptedException;
	
	/*public void setJob( Job aJob )
	{
		theJob = aJob;
	}
	
	public Job getJob()
	{
		return theJob;
	}*/
	
}