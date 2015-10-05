package com.codejock.utilities.audio;

import java.io.*;
import javax.sound.sampled.*;

public class PlayThread extends Thread
{
		byte tempBuffer[] = new byte[10000];
		
		protected AudioSourceData sourcedata;
		
		public PlayThread( AudioSourceData data )
		{
			this.sourcedata = data;
		}
		
		public void run() 
		{
			//AudioListener listener = new AudioListener();
			try
			{
				sourcedata.play();
				//sourcedata.load();
			}
			catch ( Exception e )
			{
				e.printStackTrace();
				System.exit(0);
			}//end catch
		}//end run
}//end inner class PlayThread