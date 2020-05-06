package resources;

import java.util.ArrayList;
import java.util.List;

import pojo.AddPlaceAPI;
import pojo.Location;

public class TestDataBuild{

	public static AddPlaceAPI addPlaceMethod(String name, String language, String address) {
			
	AddPlaceAPI addPlaceObj=new AddPlaceAPI();
	
	addPlaceObj.setAccuracy(10);
	addPlaceObj.setAddress(address);
	addPlaceObj.setLanguage(language);
	addPlaceObj.setName(name);
	addPlaceObj.setPhone_number("+91 9090909090");
	addPlaceObj.setWebsite("www.apitestingexample.com");
	
	
	//Setup types array list
	List<String> arr= new ArrayList<String>();
	arr.add("shoe park");
	arr.add("sandle park");
	addPlaceObj.setTypes(arr);
	
	
	//Setup Location
	Location loc=new Location();
	loc.setLat(123.23232);
	loc.setLng(232323.23232);
	addPlaceObj.setLocation(loc);
	
	return addPlaceObj;
	
	}

	public static String DeleteAPIpayload(String place_ID) {
		return "{\r\n" + 
				"    \"place_id\":\""+place_ID+"\"\r\n" + 
				"}\r\n" + 
				"";
	}

}
