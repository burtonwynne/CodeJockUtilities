package com.codejock.utilities.audio;

import java.io.*;
import javax.sound.sampled.*;

public class AudioSourceData
{

	private AudioFormat audioFormat;
	private SourceDataLine sourceDataLine;
	private AudioInputStream audioInputStream;
	private Mixer mixer;
	private File audiofile;
	private DataLine.Info dataLineInfo;
	private boolean stop = false;


	protected AudioSourceData(Mixer mixer, File audiofile)
		throws UnsupportedAudioFileException, LineUnavailableException, IOException
	{
		this.mixer = mixer;
		this.audiofile = audiofile;
		audioInputStream = AudioSystem.getAudioInputStream(audiofile);
		load();

	}

	protected AudioSourceData(Mixer mixer, InputStream inputStream)
			throws UnsupportedAudioFileException, LineUnavailableException, IOException
		{
			this.mixer = mixer;
			audioInputStream = AudioSystem.getAudioInputStream(inputStream);
			load();

		}

	public AudioInputStream getStream()
	{
		return audioInputStream;
	}

	public void load()
		throws UnsupportedAudioFileException, LineUnavailableException, IOException
	{

		audioFormat = audioInputStream.getFormat();
		dataLineInfo = new DataLine.Info(SourceDataLine.class, audioFormat);
		sourceDataLine = (SourceDataLine) mixer.getLine(dataLineInfo);
	}

	public SourceDataLine getSourceDataLine()
	{
		return sourceDataLine;
	}

	public AudioFormat getAudioFormat()
	{
		return audioFormat;
	}

	public DataLine.Info getDataLineInfo()
	{
		return dataLineInfo;
	}

	public void stop()
	{
		stop = true;
	}

	/*public void addListener(LineListener listener)
	{
		sourceDataLine.addLineListener(listener);
	}*/

	public void play() throws LineUnavailableException
	{
		sourceDataLine.open(audioFormat);
		//AudioListener listener = new AudioListener(this);
		sourceDataLine.start();
		byte buff[] = new byte[10000];
		int cnt;
		try
		{
			cnt = audioInputStream.read(buff, 0, buff.length);
			while((cnt != -1) /*&& !stop*/)

			{
				if(cnt > 0)
				{
					sourceDataLine.write(buff, 0, cnt);
					cnt = audioInputStream.read(buff, 0, buff.length);
				}
			}
			audioInputStream.close();
			sourceDataLine.drain();
			sourceDataLine.close();
		}
		catch(IOException ioe)
		{

		}

	}










}