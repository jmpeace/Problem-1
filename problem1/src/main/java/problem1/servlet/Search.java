package problem1.servlet;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections4.MapUtils;

import problem1.persist.DATABASE;

@WebServlet(name = "Search", urlPatterns = {"/Search/*"})
public class Search extends HttpServlet {
	private static final long serialVersionUID = 1L;
       

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String pathInfo = request.getPathInfo();

		if(pathInfo == null || pathInfo.equals("/")){
			response.sendError(HttpServletResponse.SC_BAD_REQUEST);
			return;
		}
		String[] splits = pathInfo.split("/");
		if(splits.length != 2) {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST);
			return;
		}
		
		String entity = splits[1];
		Map<String, String[]> parameters = request.getParameterMap();
		MapUtils.debugPrint(System.out, "Map as String", parameters);
		
		if("Classes".equals(entity))
		{
			Util.printAsJson(response,DATABASE.getClassesSearch(parameters));
			return;
		}
		else
		if("Students".equals(entity))
		{
			Util.printAsJson(response,DATABASE.getStudentsSearch(parameters));
			return;
		}
	}

	

}
