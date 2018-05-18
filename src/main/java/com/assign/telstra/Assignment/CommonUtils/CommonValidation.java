package com.assign.telstra.Assignment.commonUtils;

public class CommonValidation {
	public static boolean isNullOrEmpty(String name)
	{
		boolean check = false;
		if(name != null && !name.isEmpty())
		{
			check=true;
		}
		return check;
	}

	public static boolean isNumeric(String s) {  
	    return s != null && s.matches("[-+]?\\d*\\.?\\d+");  
	}  
	


}
