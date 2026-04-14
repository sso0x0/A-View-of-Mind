package kr.ac.hywoman.park.login.service;

import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kr.ac.hywoman.park.login.domain.User;
import kr.ac.hywoman.park.login.dto.AddUserRequest;
import kr.ac.hywoman.park.login.dto.EditUserRequest;
import kr.ac.hywoman.park.login.repository.UserRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class UserService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;
    
    // 아이디 찾기
    @Transactional(readOnly = true)
    public String findUsername(String name, String phone, String email) {
        User user = userRepository
            .findByNameAndPhonenumberAndEmail(name, phone, email)
            .orElseThrow(() -> new IllegalArgumentException("일치하는 계정이 없습니다."));

        return user.getUsername();
    }

    // 회원가입
    public void save(AddUserRequest request) {

        if (userRepository.existsByUsername(request.getUsername())) {
            throw new IllegalArgumentException("이미 사용 중인 아이디입니다.");
        }
        if (userRepository.existsByEmail(request.getEmail())) {
            throw new IllegalArgumentException("이미 사용 중인 이메일입니다.");
        }
        if (userRepository.existsByPhonenumber(request.getPhonenumber())) {
            throw new IllegalArgumentException("이미 등록된 휴대전화번호입니다.");
        }

        User user = User.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
                .email(request.getEmail())
                .name(request.getName())
                .phonenumber(request.getPhonenumber())
                .build();

        userRepository.save(user);
    }

    // 회원정보 수정
    public User updateUser(Long userId, EditUserRequest request) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalArgumentException("사용자를 찾을 수 없습니다."));

        // 이메일 중복 검사 (본인 제외)
        if (request.getEmail() != null &&
            userRepository.existsByEmailAndIdNot(request.getEmail(), userId)) {
            throw new IllegalArgumentException("이미 사용 중인 이메일입니다.");
        }

        // 휴대전화번호 중복 검사 (본인 제외)
        if (request.getPhonenumber() != null &&
            userRepository.existsByPhonenumberAndIdNot(request.getPhonenumber(), userId)) {
            throw new IllegalArgumentException("이미 등록된 휴대전화번호입니다.");
        }
        // 기타 정보 수정
        user.setName(request.getName());
        user.setEmail(request.getEmail());
        user.setPhonenumber(request.getPhonenumber());

        return user;
    }
}
