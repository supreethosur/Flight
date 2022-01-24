package com.example.util;

public class QueryConstants {

	public static final String  GET_FLIGHTS= "select ft from Flight ft "
			+ "  where  ft.scheduleType  in (:scheduleType,'Daily') and  ft.isActive=1 " ;
	

	public static final String  GET_Active_FLIGHTS= "select ft from Flight ft "
			+ "  where ft.isActive=1 " ;
}
