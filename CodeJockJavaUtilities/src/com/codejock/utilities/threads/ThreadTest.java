package com.codejock.utilities.threads;

import java.util.Vector;

public class ThreadTest
{
	
	public static void main( String argv[] )
	{
		doNothingThread dnt = new doNothingThread( Thread.currentThread() );
		Vector v = new Vector();
		System.out.println( "Starting Do Nothing Thread!" );
		v.add( dnt );
		dnt.start();
		System.out.println( "Joining the Do Nothing Thread." );
		try
		{
		
			dnt.join( 10000 );
		}
		catch( InterruptedException ie )
		{
			
		}		
		//dnt.stop(); //depricated
		//dnt.interrupt();
		dnt = null;
		System.out.println( "Finished!" );
		try
		{
			Thread.currentThread().sleep( 5000 );
		}
		catch( InterruptedException ie )
		{
			
		}
	}


}

class doNothingThread extends Thread
{
	Thread mainThread;
	
	public doNothingThread( Thread thrd )
	{
		mainThread = thrd;
		System.out.println( "Created New Do Nothing Thread!" );
		
	}
	
	public void run()
	{
		try
		{
			sleep( 1000 );
			while( true )
			{
				//doNothing
				System.out.println( "Doing Nothing..." );
				sleep( 1000 );
			}
		}
		catch( InterruptedException ie )
		{
			
		}
	}
}