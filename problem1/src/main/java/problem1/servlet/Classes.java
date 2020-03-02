package problem1.servlet;

import java.io.BufferedReader;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import problem1.model.Clazz;
import problem1.persist.DATABASE;

@WebServlet(name = "Classes", urlPatterns = {"/Classes/*"})
public class Classes extends HttpServlet {

	private static final long serialVersionUID = 8703538427440037537L;
	
	//READ
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String pathInfo = request.getPathInfo();

		if(pathInfo == null || pathInfo.equals("/")){

			Util.printAsJson(response, DATABASE.getClasses().values());
			return;
		}else
		{
			String code = pathInfo.split("/")[1];
			Util.printAsJson(response, DATABASE.getClasses().get(code));
			return;
		}
		
	}
	
	//CREATE
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String pathInfo = request.getPathInfo();
		if (pathInfo == null || pathInfo.equals("/")) {

			StringBuilder buffer = new StringBuilder();
			BufferedReader reader = request.getReader();
			String line;
			while ((line = reader.readLine()) != null) {
				buffer.append(line);
			}
			String payload = buffer.toString();

			Clazz clazz = (Clazz) Util.fromJson(payload, Clazz.class);
			try {
				DATABASE.addClass(clazz);
			} catch (Exception e) {
				e.printStackTrace();
				response.sendError(HttpServletResponse.SC_BAD_REQUEST);
				return;
			}
			Util.printAsJson(response, DATABASE.getClasses().get(clazz.getCode()));
			
		} else {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST);
			return;
		}
	}

	

}
