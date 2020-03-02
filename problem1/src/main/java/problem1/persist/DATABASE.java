package problem1.persist;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import problem1.model.Clazz;
import problem1.model.Student;

public class DATABASE {
	
	private static Map<String,Student> students = new LinkedHashMap<String,Student>();
	private static Map<String,Clazz> classes  = new LinkedHashMap<String,Clazz>();
	
	private static Map<Clazz,List<Student>> studentsByClass = new HashMap<Clazz,List<Student>>();
	private static Map<Student,List<Clazz>> classesByStudent = new HashMap<Student,List<Clazz>>();
	
	static  //test data
	{ 
		
		Clazz c001 = new Clazz("C101","DATABASES","CLASS ABOUT DATABASES");
		Clazz c002 = new Clazz("C102","C++","CLASS ABOUT C++");
		Clazz c003 = new Clazz("C103","JAVA","CLASS ABOUT JAVA");
		
		classes.put("C101",c001);
		classes.put("C102",c002);
		classes.put("C103",c003);
		
		Student a001 = new Student("E01","PAZ","JOSE");
		Student a002 = new Student("E02", "CLAROS", "OSCAR");
		Student a003 = new Student("E03", "PEREZ", "CLAUDIA");
		Student a004 = new Student("E04", "GOMEZ", "ANDREA");
		
		students.put("E01",a001);		
		students.put("E02",a002);
		students.put("E03",a003);
		students.put("E04",a004);
		
		addStudentsClasses(c001,a001,a003);
		addStudentsClasses(c002,a002,a003,a004);
		addStudentsClasses(c003,a003,a004);
		
	}

	
	/**
	 * Method to add students to the database, keeps in sync local collections and lists
	 * @param clazz
	 * @param students
	 */
	private static void addStudentsClasses(Clazz clazz, Student... students) {
		
		for(Student student:students)
		{
			List<Student> studentsByClassL = studentsByClass.get(clazz);
			List<Clazz> classesByStudentL = classesByStudent.get(student);
			
			if(studentsByClassL==null)
				studentsByClassL = new ArrayList<Student>();
			studentsByClassL.add(student);

			if(classesByStudentL==null)
				classesByStudentL = new ArrayList<Clazz>();
			classesByStudentL.add(clazz);
			
			studentsByClass.put(clazz, studentsByClassL);
			classesByStudent.put(student, classesByStudentL);
			
			student.setClassesByStudent();
		}
		
		clazz.setStudentsByClass();
	}

	public static Map<String, Student> getStudents() {
		return students;
	}

	public static Map<String, Clazz> getClasses() {
		return classes;
	}

	public static List<Clazz> getClassesByStudent(Student student) {
		return classesByStudent.get(student);
	}

	public static List<Student> getStudentsByClass(Clazz clazz) {
		return studentsByClass.get(clazz);
	}

}
