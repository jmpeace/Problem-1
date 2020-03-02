package problem1.persist;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;
import java.util.Set;

import problem1.model.Clazz;
import problem1.model.Student;

public class DATABASE {
	
	//MAIN DATA STORAGE
	private static Map<String,Student> students = new LinkedHashMap<String,Student>();
	private static Map<String,Clazz> classes  = new LinkedHashMap<String,Clazz>();
	
	//RELATIONSHIPS
	private static Map<Clazz,Set<Student>> studentsByClass = new HashMap<Clazz,Set<Student>>();
	private static Map<Student,Set<Clazz>> classesByStudent = new HashMap<Student,Set<Clazz>>();
	
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
			Set<Student> studentsByClassL = studentsByClass.get(clazz);
			Set<Clazz> classesByStudentL = classesByStudent.get(student);
			
			if(studentsByClassL==null)
				studentsByClassL = new LinkedHashSet<Student>();
			studentsByClassL.add(student);

			if(classesByStudentL==null)
				classesByStudentL = new LinkedHashSet<Clazz>();
			classesByStudentL.add(clazz);
			
			studentsByClass.put(clazz, studentsByClassL);
			classesByStudent.put(student, classesByStudentL);
			
			student.setClassesByStudent();
		}
		
		clazz.setStudentsByClass();
	}


	public static void addStudent(Student student) throws Exception {
	
		String id = student.getStudentId();
		Student exists = students.get(id);
		if(exists!=null) throw new Exception("Student exists");
		
		//Create New
		students.put(id, student);
		
		//Assign Classes
		String classesAssig = student.getClassesAssig();
		if(classesAssig!=null)
		{
			String[] classesAssigA = classesAssig.split(",");
			if(classesAssig!=null&&classes.size()>0)
			for(String classAssig:classesAssigA)
			{
				Clazz clazz = classes.get(classAssig);
				
				if(clazz!=null)
				addStudentsClasses(clazz, student);
			}
		}
	}

	public static void addClass(Clazz clazz) throws Exception {
		String id = clazz.getCode();
		Clazz exists = classes.get(id);
		if(exists!=null) throw new Exception("Class exists");
		
		//Create New
		classes.put(id, clazz);
		
		//Assign Students
		String studentsAssig = clazz.getStudentsAssig();
		if(studentsAssig!=null)
		{
			String[] studentsAssigA = studentsAssig.split(",");
			if(studentsAssig!=null&&classes.size()>0)
			for(String studentAssig:studentsAssigA)
			{
				Student student = students.get(studentAssig);
				
				if(student!=null)
				addStudentsClasses(clazz, student);
			}
		}
	}
	
	public static void removeStudent(String studentId) {
		
		Student student = students.get(studentId);
		
		//remove from classes
		Set<Clazz> classes = classesByStudent.get(student);
		for(Clazz clazz:classes)
		{
			studentsByClass.get(clazz).remove(student);
			clazz.setStudentsByClass();
		}
				
		students.remove(studentId);
	}
	
	public static void removeClass(String classCode) {

		Clazz clazz = classes.get(classCode);
		
		//remove from students
		Set<Student> students = studentsByClass.get(clazz);
		for(Student student:students)
		{
			classesByStudent.get(student).remove(clazz);
			student.setClassesByStudent();
		}
				
		classes.remove(classCode);
	}


	
	public static Map<String, Student> getStudents() {
		return students;
	}

	public static Map<String, Clazz> getClasses() {
		return classes;
	}

	public static Set<Clazz> getClassesByStudent(Student student) {
		return classesByStudent.get(student);
	}

	public static Set<Student> getStudentsByClass(Clazz clazz) {
		return studentsByClass.get(clazz);
	}


	






}
