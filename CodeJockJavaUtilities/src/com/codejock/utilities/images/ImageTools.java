package com.codejock.utilities.images;

import java.awt.image.*;
import java.awt.*;
import com.sun.image.codec.jpeg.*;
import java.io.*;
import java.awt.geom.AffineTransform;
//import com.sun.media.jai.codec.*;
import javax.imageio.*;

/**
 *
 *
 */
public class ImageTools
{

	private static Button dummy_comp;
	//private static Graphics2D graphics_util;

	static
	{
		dummy_comp = new Button();
		//graphics_util = (Graphics2D) dummy_comp.getGraphics()
	}
	private ImageTools(){}


	public static void addWaterMark(String image_path,
		String watermark_path, String output_file)
	{
		addWaterMark(image_path, watermark_path, output_file, 0, 0);
	}
	public static void addWaterMark(String image_path,
		String watermark_path, String output_file, int x, int y)
	{
		try
		{
			BufferedImage img = getBufferedImage(image_path);
			BufferedImage watermark_image
				= getBufferedImage(watermark_path);
			Graphics2D workspace = img.createGraphics();
			workspace.drawImage(watermark_image, x, y, dummy_comp);
			//workspace.dispose();
			writeBufferedImage(img, output_file);
			//Frame Frame = new Frame();
			//Frame.
		}
		catch(Exception e)
		{
			e.printStackTrace(System.out);
		}
	}

	public static BufferedImage createWaterMark(BufferedImage orig_image,
		BufferedImage watermark_image, int x, int y)
	{
		try
		{
				BufferedImage thirdimage = orig_image.getSubimage(0, 0, orig_image.getWidth(), orig_image.getHeight());
				Graphics2D workspace = thirdimage.createGraphics();
				workspace.drawImage(watermark_image, x, y, dummy_comp);
				return thirdimage;
		}
		catch(Exception e)
		{
			e.printStackTrace(System.out);
			return null;
		}
	}


	public static BufferedImage getBufferedImage(String image_path)
		throws IOException
	{
		File image_file = new File(image_path);
		return getBufferedImage(image_file);
	}

	public static BufferedImage getBufferedImage(File image_file)
		throws IOException
	{
		BufferedImage img = null;
		img = ImageIO.read(image_file);
		return img;
	}

	/*public static BufferedImage getBufferedImageFromPNG(File image_file)
		throws IOException, ImageFormatException
	{
		BufferedImage img = null;
		//graphics_util.
		FileInputStream fis = new FileInputStream(image_file);
		PNGDecodeParam png = new PNGDecodeParam();
		ImageDecoderImpl decoder = new ImageDecoderImpl(fis, png );
		RenderedImage ri = decoder.decodeAsRenderedImage();
		img = (BufferedImage) ri;
		fis.close();
		return img;
	}*/

	/*protected BufferedImage convertToBufferedImage(RenderedImage img)
	{
		ColorModel cm = img.getColorModel();
		Raster ras = img.getData();
		WriteableRaster wr = ras.createCompatibleWritableRaster();

	}*/

	public static void writeBufferedImage(BufferedImage img, String output_file)
		throws IOException
	{
		writeBufferedImage(img, new File(output_file));


	}

	public static void writeBufferedImage(BufferedImage img, File output_file)
		throws IOException
	{
		FileOutputStream fos = new FileOutputStream(output_file);
		ImageIO.write(img, "jpeg", output_file);
		fos.close();


	}

	public static BufferedImage scale(double scale, BufferedImage srcImg)
	{
	   if (scale == 1)
	   {
			return srcImg;
	  	}
	   AffineTransformOp op =
	     new AffineTransformOp(
	        AffineTransform.getScaleInstance(
	                    scale, scale), null);

	        return op.filter(srcImg, null);
	}



}