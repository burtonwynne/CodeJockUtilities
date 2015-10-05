package com.codejock.utilities.states;

import java.util.*;



public class StatesUtil
{
	private static HashMap<String, String> states;



	static
	{
		states = new HashMap();
		states.put( "AL", "ALABAMA" );
		states.put( "AK", "ALASKA" );
		states.put( "AS", "AMERICAN SAMOA" );
		states.put( "AZ", "ARIZONA" );
		states.put( "AR", "ARKANSAS" );
		states.put( "CA", "CALIFORNIA" );
		states.put( "CO", "COLORADO" );
		states.put( "CT", "CONNECTICUT" );
		states.put( "DE", "DELAWARE" );
		states.put( "DC", "DISTRICT OF COLUMBIA" );
		states.put( "FM", "FEDERATED STATES OF MICRONESIA" );
		states.put( "FL", "FLORIDA" );
		states.put( "GA", "GEORGIA" );
		states.put( "GU", "GUAM" );
		states.put( "HI", "HAWAII" );
		states.put( "ID", "IDAHO" );
		states.put( "IL", "ILLINOIS" );
		states.put( "IN", "INDIANA" );
		states.put( "IA", "IOWA" );
		states.put( "KS", "KANSAS" );
		states.put( "KY", "KENTUCKY" );
		states.put( "LA", "LOUISIANA" );
		states.put( "ME", "MAINE" );
		states.put( "MH", "MARSHALL ISLANDS" );
		states.put( "MD", "MARYLAND" );
		states.put( "MA", "MASSACHUSETTS" );
		states.put( "MI", "MICHIGAN" );
		states.put( "MN", "MINNESOTA" );
		states.put( "MS", "MISSISSIPPI" );
		states.put( "MO", "MISSOURI" );
		states.put( "MT", "MONTANA" );
		states.put( "NE", "NEBRASKA" );
		states.put( "NV", "NEVADA" );
		states.put( "NH", "NEW HAMPSHIRE" );
		states.put( "NJ", "NEW JERSEY" );
		states.put( "NM", "NEW MEXICO" );
		states.put( "NY", "NEW YORK" );
		states.put( "NC", "NORTH CAROLINA" );
		states.put( "ND", "NORTH DAKOTA" );
		states.put( "MP", "NORTHERN MARIANA ISLANDS" );
		states.put( "OH", "OHIO" );
		states.put( "OK", "OKLAHOMA" );
		states.put( "OR", "OREGON" );
		states.put( "PW", "PALAU" );
		states.put( "PA", "PENNSYLVANIA" );
		states.put( "PR", "PUERTO RICO" );
		states.put( "RI", "RHODE ISLAND" );
		states.put( "SC", "SOUTH CAROLINA" );
		states.put( "SD", "SOUTH DAKOTA" );
		states.put( "TN", "TENNESSEE" );
		states.put( "TX", "TEXAS" );
		states.put( "UT", "UTAH" );
		states.put( "VT", "VERMONT" );
		states.put( "VI", "VIRGIN ISLANDS" );
		states.put( "VA", "VIRGINIA" );
		states.put( "WA", "WASHINGTON" );
		states.put( "WV", "WEST VIRGINIA" );
		states.put( "WI", "WISCONSIN" );
		states.put( "WY", "WYOMING" );
	}

	public static String getStateName( String abbr )
	{
		return states.get( abbr.toUpperCase().trim());
	}


}