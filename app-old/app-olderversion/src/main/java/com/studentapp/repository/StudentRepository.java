package com.studentapp.repository;

import com.studentapp.model.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface StudentRepository extends JpaRepository<Student, Long> {

    // Fetch students by exact name match (case-sensitive)
    List<Student> findByName(String name);

    // Fetch students by attendance status
    List<Student> findByAttendance(boolean attendance);

    // Update attendance status for a student
    @Modifying
    @Transactional
    @Query("UPDATE Student s SET s.attendance = :status WHERE s.id = :id")
    int updateAttendance(Long id, boolean status); // Return int for affected rows
}
