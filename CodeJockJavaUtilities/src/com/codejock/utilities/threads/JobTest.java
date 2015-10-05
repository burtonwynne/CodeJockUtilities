package com.codejock.utilities.threads;

class JobTest 
{
	public static void main( String argv[] )
	{
		JobManager manager = new JobManager();
		for( int i = 1; i < 21; i++ )
		{
			System.out.println( "Adding Task # " + i );
			manager.addTask( new TaskTest( i ) );
		}
		manager.setLifeTime( 10000 );
		//manager.setTaskThreadLimit( 20 );
		System.out.println( "Starting the Manager..." );
		manager.start();
	}
}

class TaskTest extends Task
{
	
	protected int id;
	
	public TaskTest( int id )
	{
		this.id = id;
	}
	
	public void doTask() throws InterruptedException
	{
		while( true )
		{
			
			
				System.out.println( "Task Test #" + id );
				Thread.currentThread().sleep( 1000 );
		}
	}
}