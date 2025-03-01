package com.studentapp.controller;

import com.studentapp.model.Student;
import com.studentapp.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/students")
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping("/")
    public String home(Model model) {
        List<Student> students = studentService.getAllStudents();
        model.addAttribute("students", students);
        return "index";
    }

    @PostMapping("/add")
    public ResponseEntity<String> addStudent(@RequestBody Student student) {
        Student savedStudent = studentService.addStudent(student);
        return ResponseEntity.ok("Student added successfully with ID: " + savedStudent.getId());
    }

    @PutMapping("/toggleAttendance/{id}")
    public ResponseEntity<String> toggleAttendance(@PathVariable Long id) {
        String status = studentService.toggleAttendance(id);
        return status != null 
                ? ResponseEntity.ok("Attendance updated: " + status) 
                : ResponseEntity.notFound().build();
    }
}
