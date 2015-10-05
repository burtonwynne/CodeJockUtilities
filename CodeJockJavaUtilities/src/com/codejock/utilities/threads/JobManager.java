package com.codejock.utilities.threads;


import java.util.Vector;
import java.util.Date;
import java.util.Enumeration;


public class JobManager extends Thread
{
	protected int task_thread_limit = 5;
	protected int task_count = 0;
	protected Vector tasks;
	protected Vector jobs;
	protected boolean sooth = true;
	protected int sleeptime = 1000;
	protected long lifetime = 5 * 60 * 1000;

	{
		tasks = new Vector();
		jobs = new Vector();
	}
	public void addTask( Task task )
	{
		tasks.add( task );
	}

	public void run()
	{
			//System.out.println( "Here!" );
			while( sooth )
			{

					//System.out.println( "# of Jobs:" + jobs.size() );
					Enumeration enumation = jobs.elements();
					Job theJob;
					checkJobs();

					while( enumation.hasMoreElements() )
					{

						//System.out.println( "Doing the Job..." );
						theJob = (Job) enumation.nextElement();
						try
						{
							theJob.join( sleeptime );
						}
						catch( InterruptedException ie )
						{

						}

						theJob = null;
					}

				while( task_thread_limit > task_count && tasks.size() > 0 )
				{
					Job myJob = new Job( (Task) tasks.remove( 0 ) );
					//System.out.println( "Adding new Job. " );
					jobs.add( myJob );
					//System.out.println( "Starting the job..." );
					myJob.start();

					try
					{
						myJob.join( sleeptime );
					}
					catch( InterruptedException ie )
					{

					}
					myJob = null;
					task_count++;
				}
			}
			try
			{
				int count = jobs.size();
				if( count == 0 )
				{
					sleep( sleeptime );
				}
				else
				{
					sleep( sleeptime + count );
				}

			}
			catch( InterruptedException ie )
			{

			}

	}

	public void setLifeTime( long life )
	{
		lifetime = life;
	}

	public long getLifeTime()
	{
		return lifetime;
	}

	public void setTaskThreadLimit( int size )
	{
		task_thread_limit = size;
	}

	public int getTaskThreadLimit()
	{
		return task_thread_limit;
	}


	public void setSleepTime( int sleep )
	{
		sleeptime = sleep;
	}

	public int getSleepTime()
	{
		return sleeptime;
	}

	protected void checkJobs()
	{
		Enumeration enumation = jobs.elements();
		Date now = new Date();
		long diff;
		Job theJob;
		for( int i = jobs.size() - 1; i > -1 ; i-- )
		{
			theJob = (Job) jobs.elementAt( i );
			diff = now.getTime() - theJob.getCreationDate().getTime();
			//System.out.println( "Difference: " + diff + " LifeTime: " + lifetime );
			if( diff > lifetime || theJob.isDone() )
			{
				//jobs.setElementAt( null, i );
				jobs.removeElementAt( i );
				//jobs.trimToSize();
				//try
				//{
					//System.out.println( "Stopping the Job..." );
					theJob.interrupt();
					theJob = null;
					task_count--;
					//System.out.println( "Task Count: " + task_count );
				//}
				//catch( InterruptedException ie )
				//{

				//}
				//System.out.println( "Job Removed." );
			}
		}
	}
}