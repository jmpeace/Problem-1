package problem1.servlet;

import java.io.BufferedReader;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import problem1.model.Student;
import problem1.persist.DATABASE;

@WebServlet(name = "Students", urlPatterns = { "/Students/*" })
public class Students extends HttpServlet {

	private static final long serialVersionUID = 8703538427440037537L;

	//READ
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String pathInfo = request.getPathInfo();
		if (pathInfo == null || pathInfo.equals("/")) {

			Util.printAsJson(response, DATABASE.getStudents().values());
			return;
		} else {
			String studentId = pathInfo.split("/")[1];
			Util.printAsJson(response, DATABASE.getStudents().get(studentId));
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

			Student student = (Student) Util.fromJson(payload, Student.class);
			try {
				DATABASE.addStudent(student);
			} catch (Exception e) {
				e.printStackTrace();
				response.sendError(HttpServletResponse.SC_BAD_REQUEST);
				return;
			}
			Util.printAsJson(response, student.getStudentId());
			
		} else {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST);
			return;
		}
	}
	
	//UPDATE

}
