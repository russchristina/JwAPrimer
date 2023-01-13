package com.revature;

import java.util.Arrays;
import java.util.List;

public class Repository {

	
	/**
	 * This method doesn't actually pull from a real data source. It just supplies
	 * dummy data.
	 * 
	 * @return dummy data
	 */
	public List<String> findAll(){
		return Arrays.asList("Christina", "Richard", "Jasdhir", "Rory", "Matt");
	}
}
