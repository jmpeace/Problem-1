package problem1.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class Util {
	
	private static final  String responseInError = "Undefined error.";
	private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();
	
	public static void printAsJson(HttpServletResponse response,Object toRespond) throws IOException {
		
		if(toRespond==null) toRespond = responseInError;
		
		//PRINT JSON
		PrintWriter out = response.getWriter();
		response.setContentType("application/json;charset=UTF-8");
		response.setCharacterEncoding("UTF-8");
		out.print(gson.toJson(toRespond));
		out.flush();
	}

}
