package resources;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Properties;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.filter.log.RequestLoggingFilter;
import io.restassured.filter.log.ResponseLoggingFilter;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;

public class UtilClass {

	public static RequestSpecification req;
	ResponseSpecification resspec;
	
	public RequestSpecification requestSpecMethod() throws IOException {	
	
		if(req==null)
		{
		PrintStream log=new PrintStream(new FileOutputStream("logging.txt"));
		req=new RequestSpecBuilder().setBaseUri(globalValues("baseURL")).setContentType(ContentType.JSON).addQueryParam("key", "qaclick123")
				.addFilter(RequestLoggingFilter.logRequestTo(log))
				.addFilter(ResponseLoggingFilter.logResponseTo(log))				
				.build();
		return req;
		}
		return req;
	}
	
	public ResponseSpecification responseSpecMethod() {
		resspec=new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
		return resspec;
	}

	public static String globalValues(String key) throws IOException {
		
		Properties prop=new Properties();
		FileInputStream fis=new FileInputStream("C:\\Work\\API Automation\\MavenFramework\\ProjectFramework\\src\\test\\java\\resources\\global.properties");
		prop.load(fis);
		return prop.getProperty(key);				
	}
	
	
	public String parseResponse(Response response, String keyValue) {
		
		String res= response.asString();
		JsonPath js=new JsonPath(res);
	    return js.get(keyValue).toString();
	   
	}
	
}



