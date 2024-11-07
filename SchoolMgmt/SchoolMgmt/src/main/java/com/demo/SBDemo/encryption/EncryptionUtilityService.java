package com.demo.SBDemo.encryption;

import org.springframework.security.crypto.password.PasswordEncoder;

public interface EncryptionUtilityService {

	public PasswordEncoder passwordEncoder();
	
}
