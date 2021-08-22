package com.studentmanagement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin
@RequestMapping("/management")
public class StudentController {
    
    @Autowired
    StudentRepository studentRepository;

    @GetMapping("/students")
    public ResponseEntity<List<Students>> getAllStudents (@RequestParam(required = false) String firstname){
        try{
            List<Students> students = new ArrayList<Students>();

            if (firstname.isEmpty())
                studentRepository.findAll().forEach(students::add);
            else
                StudentRepository.findByFirstNameContaining(firstname).forEach(students::add);

            if (students.isEmpty()) {
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }

            return new ResponseEntity<>(students, HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @GetMapping("/students/{id}")
    public ResponseEntity <Students> getStudentsById(@PathVariable("id") long id){
        Optional<Students> studentData = studentRepository.findById(id);

        if(studentData.isPresent()){
            return new ResponseEntity<>(studentData.get(), HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/students")
    public ResponseEntity <Students> createStudent(@RequestBody Students students){
        try{
            Students _students = studentRepository.save(new Students(students.getFirstname(), students.getLastname(), students.getGender(), students.getCourse(), false));
            return new ResponseEntity<>(_students, HttpStatus.CREATED);
        }catch(Exception e){
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/students/{id}")
    public ResponseEntity <Students> updateStudents(@PathVariable("id") long id, @RequestBody Students students){
        Optional<Students> studentData = studentRepository.findById(id);

        if(studentData.isPresent()){
            Students _students = studentData.get();
            _students.setFirstname(students.getFirstname());
            _students.setLastname(students.getLastname());
            _students.setGender(students.getGender());
            _students.setPaid(students.isPaid());
            return new ResponseEntity<>(studentRepository.save(_students), HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/student/{id}")
    public ResponseEntity <HttpStatus> deleteStudent(@PathVariable("id") long id){
        try{
            studentRepository.deleteById(id);
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/students")
    public ResponseEntity <HttpStatus> deleteAllStudents(){
        try{
            studentRepository.deleteAll();
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }catch(Exception e){
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/tutorials/published")
    public ResponseEntity<List<Students>> findByPaid(){
        try {
            List<Students> students = studentRepository.findByPaid(true);

            if(students.isEmpty()){
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            }
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception e){
           return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    
}
