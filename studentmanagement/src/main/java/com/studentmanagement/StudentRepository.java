package com.studentmanagement;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Students, Long> {
    List<Students> findByPaid(boolean published);
    static List<Students> findByFirstNameContaining(String firstname) {
        // TODO Auto-generated method stub
        return null;
    }
}
