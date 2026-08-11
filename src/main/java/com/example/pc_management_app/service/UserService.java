package com.example.pc_management_app.service;

import java.time.LocalDateTime;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.pc_management_app.dto.UserRegisterForm;
import com.example.pc_management_app.entity.AuthUser;
import com.example.pc_management_app.exception.DuplicateUsernameException;
import com.example.pc_management_app.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class UserService {
	
	private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    
    /**
     * ユーザーを登録する。
     * @throws DuplicateUsernameException ユーザー名が既に使われている場合
     */
	public void save(UserRegisterForm form) {
		
		 if (userRepository.findByUsername(form.getUsername()).isPresent()) {
	            throw new DuplicateUsernameException("このユーザー名は既に使われています");
	        }
		AuthUser user = AuthUser.builder()
                .username(form.getUsername())
                .password(passwordEncoder.encode(form.getPassword()))
                .createdAt(LocalDateTime.now())
                .build();

		userRepository.save(user);
	}
}
