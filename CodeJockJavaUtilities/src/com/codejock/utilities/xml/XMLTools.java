package com.codejock.utilities.xml;

import org.w3c.dom.*;
import org.apache.xerces.parsers.DOMParser;
import java.util.*;
import javax.xml.transform.TransformerException;

public class XMLTools
{
	public static String getText( Node nd )
	{
		Node child;
		String str = null;
		NodeList nl = nd.getChildNodes();
		for( int j = 0; j < nl.getLength(); j++ )
		{
			child = nl.item( j );
			if( child.getNodeType() == Node.TEXT_NODE )
			{
				str = child.getNodeValue();
				break;
			}

		}
		return str;
	}

	public static void hackNodes( Node nd, int tabs ) throws Exception
	{
		Node child;
		NodeList nl = nd.getChildNodes();
		for( int j = 0; j < nl.getLength(); j++ )
		{
			for( int k = 0; k < tabs; k++ )
			{
				System.out.print( "\t" );
			}
			child = nl.item( j );
			if( child.getNodeType() == Node.TEXT_NODE )
			{
				System.out.println( "Value:" + child.getNodeValue()  );
			}
			else
			{
				System.out.println( "Name:" + child.getNodeName() );
			}
			if( child.hasChildNodes() )
			{
				hackNodes( child, tabs + 1 );
			}
		}

	}

}