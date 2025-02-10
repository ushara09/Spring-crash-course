package com.luv2code.cruddemo;

import com.luv2code.cruddemo.dao.StudentDAO;
import com.luv2code.cruddemo.entity.Student;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.List;

@SpringBootApplication
public class CruddemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(CruddemoApplication.class, args);
	}

	@Bean
	public CommandLineRunner commandLineRunner(StudentDAO studentDAO){ //execute after the spring beans have been loaded
		return runner -> {
//			createStudent(studentDAO);
			createMultipleStudents(studentDAO);
//			readStudent(studentDAO);
//			queryForStudents(studentDAO);
//			queryForStudentsByLastName(studentDAO);
//			updateStudent(studentDAO);
//			deleteStudent(studentDAO);
//			deleteStudentByLastName(studentDAO);
//			deleteAllStudents(studentDAO);
		};
	}

	private void deleteAllStudents(StudentDAO studentDAO) {
		studentDAO.deleteAll();
	}

	private void deleteStudent(StudentDAO studentDAO) {
		studentDAO.delete(1);
	}

	private void deleteStudentByLastName(StudentDAO studentDAO){
		studentDAO.deleteByLastName();
	}

	private void updateStudent(StudentDAO studentDAO) {
		//get the student
		Student student = studentDAO.findById(1);
		//update the student
		student.setLastName("Tester");
		studentDAO.update(student);

		System.out.println("Updated Student - "+student);
	}

	private void queryForStudentsByLastName(StudentDAO studentDAO) {
		List<Student> studentList = studentDAO.findByLastName("Beta");
		for (Student student : studentList) {
			System.out.println("Student Data of student ID : " + student.getId() + "\n" + student);
		}
	}

	private void queryForStudents(StudentDAO studentDAO) {
		//get list of students
		List<Student> studentList = studentDAO.findAll();
		//display list of students
//		for(Student student: studentList){
//			System.out.println("Student Data of student ID : "+student.getId() + "\n" +student);
//		}
		studentList.stream().forEach(student -> System.out.println("Student Data of student ID : "+student.getId() + "\n" +student));
	}

	private void readStudent(StudentDAO studentDAO) {

		//create student
		Student student = new Student("Shamal","Rupa","shamal@test.com");
		//save student
		studentDAO.save(student);
		//retrieve student
		Student savedStudent = studentDAO.findById(student.getId());
		System.out.println("Saved Student Data : "+savedStudent.toString());
	}


	private void createMultipleStudents(StudentDAO studentDAO) {
		System.out.println("Creating 3 student objects...");
		Student tempStudent1 = new Student("Kamal","Lee","lee@gmail.com");
		Student tempStudent2 = new Student("Alpha","Beta","beta@gmail.com");
		Student tempStudent3 = new Student("Nirmal","Pere","pere@gmail.com");

		System.out.println("Saving the students...");
		studentDAO.save(tempStudent1);
		studentDAO.save(tempStudent2);
		studentDAO.save(tempStudent3);
	}

	private void createStudent(StudentDAO studentDAO) {
		//create student object
		System.out.println("Creating new student object...");
		Student tempStudent = new Student("Bob","Builder","bob@gmail.com");

		//save student object
		System.out.println("Saving the student...");
		studentDAO.save(tempStudent);

		//display the id of the saved student
		System.out.println("Saved Student ID : "+tempStudent.getId());
	}

}
