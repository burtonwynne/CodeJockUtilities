package com.codejock.utilities.audio;

import java.io.*;
import javax.sound.sampled.*;

public final class AudioTools
{
	public static Mixer.Info[] getMixersInfo()
	{
		Mixer.Info[] mixerInfo = AudioSystem.getMixerInfo();
		return mixerInfo;
	}
	
	public static Mixer getMixer( String name ) throws Exception
	{
		Mixer.Info[] mixerInfo = getMixersInfo();
		
		for( int cnt = 0; cnt < mixerInfo.length; cnt++ )
		{
				System.out.println( mixerInfo[ cnt ].getName() );
			if( mixerInfo[ cnt ].getName().indexOf( name ) != -1 )
			{
				return AudioSystem.getMixer( mixerInfo[ cnt ] ); 
			}
		}
		//else it was not found and return null
		return null;	
	}
	
	public static AudioFormat getFormat( File audiofile ) throws IOException, UnsupportedAudioFileException
	{
		AudioFormat audioFormat;
		AudioInputStream audioInputStream;
		audioInputStream = AudioSystem.getAudioInputStream( audiofile );
		audioFormat = audioInputStream.getFormat();
		audioInputStream.close();
		return audioFormat;
	}
	
	public static DataLine.Info getSourceDateLineInfo( AudioFormat aformat )
	{
					return new DataLine.Info( SourceDataLine.class, aformat );	
	}
	
	public static AudioSourceData getAudioSourceData( Mixer mixer, File audiofile ) throws 
		UnsupportedAudioFileException, LineUnavailableException, IOException
	{
		return new AudioSourceData( mixer, audiofile );
	}
	
	
	
	

}