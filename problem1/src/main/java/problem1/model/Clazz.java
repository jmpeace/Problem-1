package problem1.model;

import java.util.List;
import java.util.Set;
import java.util.StringJoiner;

import problem1.persist.DATABASE;

public class Clazz {
	
	private String code;
	private String title;
	private String description;
	private String studentsAssig;
	
	transient private Set<Student> students;
	
	public Clazz(String code, String title, String description) {
		super();
		this.code = code;
		this.title = title;
		this.description = description;
	}
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Set<Student> getStudents() {
		return students;
	}
	
	public String getStudentsAssig() {
		return studentsAssig;
	}

	public void setStudentsByClass()
	{
		students = DATABASE.getStudentsByClass(this);
		
		StringJoiner joiner = new StringJoiner(",");
		if(students!=null) {
		for(Student student:students)
			joiner.add(student.getStudentId());
		studentsAssig = joiner.toString();}
	}
	
	
	
	
	
	
}
