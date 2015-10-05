package com.codejock.utilities.audio;

import java.io.*;

import javax.sound.sampled.*;

class PlayClip extends PlayThread
{
	protected Mixer mixy;

	public PlayClip( AudioFormat audioFormat, SourceDataLine sourceDataLine, AudioInputStream audioInputStream, Mixer mrdj ) throws UnsupportedAudioFileException, LineUnavailableException, IOException
	{

		////super(  audioFormat, sourceDataLine, audioInputStream );
		super(new AudioSourceData(mrdj, audioInputStream));
		mixy = mrdj;
	}

	public void run()
	{
		/*
		System.out.println( "Playing Clip..." );
		AudioListener listener = new AudioListener();
		try
		{
			//sourceDataLine.addLineListener( listener );
			//sourceDataLine.open(audioFormat);
			//sourceDataLine.start();
			DataLine.Info info = new DataLine.Info(
         Clip.class, audioFormat, ( (int)audioInputStream.getFrameLength() * audioFormat.getFrameSize() ) );
     		Clip clip = (Clip) mixy.getLine(info);
     		clip.open( audioInputStream );
     		clip.loop(3);

 			clip.start();

 			int cnt;

			/ *while((cnt = audioInputStream.read(
					 tempBuffer,0,tempBuffer.length)) != -1 )
			{
				System.out.println( "cnt:" + cnt);
				if( cnt > 0 )
				{
					sourceDataLine.write( tempBuffer, 0, cnt);
				}
				//sleep( 100 );
			}* /

			System.out.println( "Finished..." );
			//sourceDataLine.drain();
			//sourceDataLine.close();
		}
		catch (Exception e)
		{
			e.printStackTrace();
			System.exit(0);
		}//end catch
		*/
	}//end ru


}