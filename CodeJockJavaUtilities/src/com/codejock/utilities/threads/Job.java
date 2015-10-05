package com.codejock.utilities.threads;

import java.util.Date;

public class Job extends Thread
{
	protected Task tsk;
	protected Date birthdate;
	//protected JobManager boss;
	protected boolean is_done = false;
	
	{
		birthdate = new Date();
	}
	
	
	
	public Job( Task task )
	{
		tsk = task;
		//boss = manager;
		
	}
	
	public void run()
	{
		try
		{
			sleep( 1000 );
			//doNothing
			tsk.doTask();
			done();
		}
		catch( InterruptedException ie )
		{
			System.out.println( "Interrupted!" );
		}
	}
	
	public Task getTask()
	{
		return tsk;
	}
	
	public Date getCreationDate()
	{
		return birthdate;	
	}
	
	protected void done()
	{
		is_done = true;	
	}
	
	public boolean isDone()
	{
		return is_done;
	}
}



