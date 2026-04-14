package kr.ac.hywoman.park.login.controller;

import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import kr.ac.hywoman.park.login.domain.FindAccountRequest;
import kr.ac.hywoman.park.login.dto.AddUserRequest;
import kr.ac.hywoman.park.login.service.UserService;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class UserApiController {

    private final UserService userService;

    /* =========================
       회원가입
    ========================= */
    @PostMapping("/user")
    public ResponseEntity<?> signup(@ModelAttribute AddUserRequest request) {
        try {
            userService.save(request);
            return ResponseEntity.ok().build(); // 성공

        } catch (IllegalArgumentException e) {
            // 아이디 / 이메일 / 전화번호 중복
            return ResponseEntity
                    .badRequest()
                    .body(Map.of("message", e.getMessage()));

        } catch (Exception e) {
            // 서버 오류
            return ResponseEntity
                    .internalServerError()
                    .body(Map.of("message", "서버 오류로 회원가입에 실패했습니다."));
        }
    }

    /* =========================
       로그아웃
    ========================= */
    @GetMapping("/logout")
    public String logout(
            HttpServletRequest request,
            HttpServletResponse response) {

        new SecurityContextLogoutHandler().logout(
                request,
                response,
                SecurityContextHolder.getContext().getAuthentication());

        return "redirect:/login";
    }

    /* =========================
       아이디 찾기
    ========================= */
    @PostMapping("/api/user/find-id")
    public ResponseEntity<?> findId(@RequestBody FindAccountRequest req) {
        try {
            String username = userService.findUsername(
                    req.getName(),
                    req.getPhonenumber(),
                    req.getEmail()
            );

            return ResponseEntity.ok(
                    Map.of("username", username)
            );

        } catch (IllegalArgumentException e) {
            // 일치하는 계정 없음
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)   // ⭐ 중요
                    .body(Map.of("message", e.getMessage()));

        } catch (Exception e) {
            // 서버 오류
            return ResponseEntity
                    .internalServerError()
                    .body(Map.of("message", "아이디 찾기 중 오류가 발생했습니다."));
        }
    }
}
