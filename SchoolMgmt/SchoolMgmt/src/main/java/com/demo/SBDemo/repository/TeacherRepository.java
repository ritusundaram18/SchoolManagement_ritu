package com.demo.SBDemo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
//import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.demo.SBDemo.entity.Student;
import com.demo.SBDemo.entity.Teacher;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Integer> {

	@Query(value = "select * from teacher_tb t left join login_tb l on t.login_id = l.id"
			+ " where l.username=? and l.login_type=?", nativeQuery = true)
	public Teacher fetchTeacherUsingCredentials(String username, String loginType);

	public Optional<Teacher> findById(int id);

}
