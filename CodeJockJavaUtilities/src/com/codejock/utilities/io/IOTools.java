package com.codejock.utilities.io;

import java.io.*;


public final class IOTools
{
	public static void copyFile( File source, File dest_dir, String rel_path )
	{
		File dest = new File( dest_dir, rel_path );
		createDirectory( dest.getPath() );
		int length = 1000;
		int read;
		try
		{
			FileInputStream in = new FileInputStream( source );
			FileOutputStream out = new FileOutputStream( dest );
			byte[] data = new byte[ 1000 ];
			while( ( read = in.read(  data, 0, length ) ) != -1 )
			{
				out.write( data, 0, read );
			}
			in.close();
			out.close();	
		}
		catch( IOException ioe )
		{
			
		}
	}
	
	public static void createDirectory( String path )
	{
		int ndx = path.lastIndexOf( File.separator );
		System.out.println( "Path:" + path.substring( 0, ndx ) );
		File parent_dir = new File( path.substring( 0, ndx ) );
		parent_dir.mkdirs();
	}
	
	
	public static String getFileName( File theFile )
	{
			String filename = "";
			String path = theFile.getAbsolutePath();
		if( path == null || path.length() == 0 )
		{
			return filename;
		}
		else
		{
			int ndx = path.lastIndexOf( File.separator );
			filename = path.substring( ndx + 1, path.length() );
			return filename;
		}
	}
	
	public static String getRelativePath( File parent, File child )
	{
		String parent_str = parent.getAbsolutePath();
		int parent_lnth = parent_str.length();
		String child_str = child.getAbsolutePath();
		if(  child_str.startsWith( parent_str ) )
		{
				return child_str.substring( parent_lnth, child_str.length() );
		}
		else
		{
			return null;
		}	
	}
	
	public static String getFileExtention( String path )
	{
		String ext = "";
		if( path == null || path.length() == 0 )
		{
			return ext;
		}
		else
		{
			int ndx = path.lastIndexOf( '.' );
			ext = path.substring( ndx + 1, path.length() );
			return ext;
		}
	}
}