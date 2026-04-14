package kr.ac.hywoman.park.login.service;

import java.time.LocalDateTime;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import kr.ac.hywoman.park.login.domain.User;
import kr.ac.hywoman.park.login.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserDetailService implements UserDetailsService {

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {

        if (username == null || username.trim().isEmpty()) {
            log.warn("로그인 시도 실패: username 비어있음");
            throw new UsernameNotFoundException("아이디를 입력해주세요.");
        }

        log.info("로그인 시도 username = {}", username);

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> {
                    log.warn("로그인 실패 - 사용자 없음: {}", username);
                    return new UsernameNotFoundException("사용자를 찾을 수 없습니다.");
                });

        log.info("로그인 사용자 조회 성공: {}", username);

        return user;
    }
}
