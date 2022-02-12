package com.example.hanghae99_mini2.service;

import com.example.hanghae99_mini2.dto.DulplicateResponseDto;
import com.example.hanghae99_mini2.dto.SignupRequestDto;
import com.example.hanghae99_mini2.model.User;
import com.example.hanghae99_mini2.repository.UserRepository;
import com.example.hanghae99_mini2.validation.SignupValidation;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService {
    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    public void registerUser(SignupRequestDto requestDto) {

        if (SignupValidation.validationSignupInput(requestDto)) {
            String username = requestDto.getUsername();
            if (userRepository.existsByUsername(username)) {
                throw new IllegalArgumentException("중복된 username 입니다.");
            }
            // 패스워드 암호화
            String password = passwordEncoder.encode(requestDto.getPassword());
            String email = requestDto.getEmail();

            User user = new User(username, email, password);
            userRepository.save(user);
        }
    }

    //username 중복확인
    public DulplicateResponseDto duplicateUsername(String username) {
        return new DulplicateResponseDto(userRepository.existsByUsername(username));
    }

    public DulplicateResponseDto duplicateEmail(String email) {
        return new DulplicateResponseDto(userRepository.existsByEmail(email));
    }

    public User readUser(String username) {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Can't find " + username));
        return user;
    }
}