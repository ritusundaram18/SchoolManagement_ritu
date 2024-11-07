package com.demo.SBDemo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.demo.SBDemo.entity.Student;

@Repository
public interface StudentRepository extends JpaRepository<Student, Integer> {

	@Query(value = "select * from student_tb s left join login_tb l on s.login_id = l.id"
			+ " where username=? and login_type=?", nativeQuery = true)
	public Student fetchStudentUsingCredentials(String username, String loginType);

	public Student findByName(String name);

	public Student findByAddress(String address);
}