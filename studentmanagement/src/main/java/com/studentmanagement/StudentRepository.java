package com.studentmanagement;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Students, Long> {
    List<Students> findByPaid(boolean paid);
    List<Students> findByCourseContaining(String course);
     
}
