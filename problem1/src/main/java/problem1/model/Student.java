package problem1.model;

import java.util.List;
import java.util.StringJoiner;

import problem1.persist.DATABASE;

public class Student {
	
	private String studentId;
	private String lastName;
	private String firstName;
	private String classesAssig;
	
	private transient List<Clazz> classes;
	
	public Student(String studentId, String lastName, String firstName) {
		super();
		this.studentId = studentId;
		this.lastName = lastName;
		this.firstName = firstName;
	}
	
	public String getStudentId() {
		return studentId;
	}
	public void setStudentId(String studentId) {
		this.studentId = studentId;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public List<Clazz> getClasses() {
		return classes;
	}

	public void setClasses(List<Clazz> classes) {
		this.classes = classes;
	}

	public String getClassesAssig() {
		return classesAssig;
	}


	public void setClassesByStudent()
	{
		classes=DATABASE.getClassesByStudent(this);
		
		StringJoiner joiner = new StringJoiner(",");
		if(classes!=null) {
		for(Clazz clazz:classes)
			joiner.add(clazz.getCode());
		classesAssig = joiner.toString();}
	}
	
	
	
	
	
	
	

}
