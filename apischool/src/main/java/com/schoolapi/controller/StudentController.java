package com.schoolapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.schoolapi.model.Student;
import com.schoolapi.service.StudentService;

import java.util.List;

@RestController
@RequestMapping("/api/students") // Updated to match the path in ApiService
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping
    public List<Student> getAllStudents() {
        return studentService.getAllStudents();
    }

    @GetMapping("/{admNo}")
    public Student getStudentByAdmNo(@PathVariable Integer admNo) {
        return studentService.getStudentById(admNo);
    }

    @PostMapping
    public Student saveStudent(@RequestBody Student student) {
        return studentService.saveStudent(student);
    }

    @DeleteMapping("/{id}")
    public void deleteStudent(@PathVariable Integer id) {
        studentService.deleteStudent(id);
    }
}
