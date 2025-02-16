package com.luv2code.demo.rest;

import com.luv2code.demo.entity.Student;
import jakarta.annotation.PostConstruct;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api")
public class StudentRestController {

    private List<Student> theStudents;

    @PostConstruct
    public void loadData(){
        theStudents = new ArrayList<>();

        theStudents.add(new Student("Thimz", "Geek"));
        theStudents.add(new Student("Sulthan", "Moulana"));
        theStudents.add(new Student("Mary", "Smith"));
        theStudents.add(new Student("John", "Cena"));
    }

    // define endpoint for "/students" to return list of students

    @GetMapping("/students")
    public List<Student> getStudents(){
        return theStudents;
    }

    @GetMapping("/students/{studentId}")
    public Student getStudent(@PathVariable int studentId) {

        if (studentId >= theStudents.size() || studentId < 0) {
            throw new StudentNotFoundException("Student Id not found - " + studentId);
        }
        return theStudents.get(studentId);
    }

    //Add an exception handler using @ExceptionHandler

    @ExceptionHandler
    public ResponseEntity<StudentErrorResponse> handleException(StudentNotFoundException exception){

        //create a studentResponseError
        StudentErrorResponse studentErrorResponse = new StudentErrorResponse();

        studentErrorResponse.setStatus(HttpStatus.NOT_FOUND.value());
        studentErrorResponse.setMessage(exception.getMessage());
        studentErrorResponse.setTimeStamp(System.currentTimeMillis());

        //return ResponseEntity
        return new ResponseEntity<>(studentErrorResponse, HttpStatus.NOT_FOUND);
    }

    //add another exception handler to catch any exception (catch all)

    @ExceptionHandler
    public ResponseEntity<StudentErrorResponse> handleException(Exception e){

        //create a studentResponseError
        StudentErrorResponse studentErrorResponse = new StudentErrorResponse();

        studentErrorResponse.setStatus(HttpStatus.BAD_REQUEST.value());
        studentErrorResponse.setMessage(e.getMessage());
        studentErrorResponse.setTimeStamp(System.currentTimeMillis());

        //return ResponseEntity
        return new ResponseEntity<>(studentErrorResponse, HttpStatus.BAD_REQUEST);
    }
}
