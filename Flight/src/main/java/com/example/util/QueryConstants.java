package com.example.util;

public class QueryConstants {

	public static final String  GET_FLIGHTS= "select ft from Flight ft "
			+ "  where  ft.scheduleType  in (:scheduleType,'D') and  ft.isActive=1 " ;
}
