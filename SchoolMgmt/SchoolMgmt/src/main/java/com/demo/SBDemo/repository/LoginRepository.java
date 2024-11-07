package com.demo.SBDemo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.demo.SBDemo.entity.Login;

@Repository
public interface LoginRepository extends JpaRepository<Login, Integer> {

	public Optional<Login> findByUsernameAndLoginType(String username, String loginType);

	public Optional<Login> findByUsername(String username);

}
