package com.studentapp.service;

import com.studentapp.model.Student;
import com.studentapp.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public List<Student> getAllStudents() {
        return studentRepository.findAll();
    }

    public Student addStudent(Student student) {
    if (student.getName() == null || student.getName().trim().isEmpty()) {
        throw new IllegalArgumentException("Student name cannot be empty");
    }
    student.setAttendance(false); // Default attendance is false
    return studentRepository.save(student);
}

    public Student toggleAttendance(Long id) {
        return studentRepository.findById(id)
                .map(student -> {
                    student.setAttendance(!student.isAttendance());  // Toggle attendance
                    return studentRepository.save(student);
                })
                .orElse(null);
    }
}
