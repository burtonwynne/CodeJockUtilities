package com.codejock.utilities.audio.test;

import com.codejock.utilities.audio.*;

import javax.sound.sampled.*;

import java.io.*;


public class AudioTest
{

	public static void main( String argv[] ) 
	{
		try
		{
			File audiofile = new File( "content/Josie_And_The_Pussycats.wav" );
			Mixer mixer = AudioTools.getMixer( "Java" );
			AudioSourceData data = AudioTools.getAudioSourceData( mixer, audiofile );
			PlayThread player = new PlayThread( data );
			player.start();
			player.join();
			System.out.println( "Finished" );
			
		}
		catch( Exception e )
		{
			e.printStackTrace( System.out );
		}
	}

}