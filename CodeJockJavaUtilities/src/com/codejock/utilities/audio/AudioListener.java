package com.codejock.utilities.audio;

import java.io.*;
import javax.sound.sampled.*;

class AudioListener implements LineListener
{
	AudioSourceData sourcedata;
	
	public AudioListener( AudioSourceData data )
	{
		sourcedata = data;
	}
	public void update( LineEvent ev )
	{
		LineEvent.Type type = ev.getType();
		if( LineEvent.Type.STOP.equals( type ) )
		{
			System.out.println( "The Audio Clip Stopped Playing" );
			try
			{
				//sourcedata.load();
			}
			catch( Exception e )
			{
				e.printStackTrace( System.out );	
			}
		}
	}
		
}