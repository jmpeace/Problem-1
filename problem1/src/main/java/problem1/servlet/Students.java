package problem1.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import problem1.persist.DATABASE;

@WebServlet(name = "Students", urlPatterns = {"/Students/*"})
public class Students extends HttpServlet {

	private static final long serialVersionUID = 8703538427440037537L;

	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String pathInfo = request.getPathInfo();
		if(pathInfo == null || pathInfo.equals("/")){

			Util.printAsJson(response, DATABASE.getStudents().values());
			return;
		}else
		{
			String studentId = pathInfo.split("/")[1];
			Util.printAsJson(response, DATABASE.getStudents().get(studentId));
			return;
		}
	}

	

}
