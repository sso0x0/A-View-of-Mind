package kr.ac.hywoman.park.login.controller;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import kr.ac.hywoman.park.login.domain.User;
import kr.ac.hywoman.park.login.dto.EditUserRequest;
import kr.ac.hywoman.park.login.service.UserService;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
@RequestMapping("/user")
public class UserController {

    private final UserService userService;

    // 회원정보 수정 페이지
    @GetMapping("/edit")
    public String editForm(
            @AuthenticationPrincipal User user,
            Model model
    ) {
        model.addAttribute("user", user);
        return "edit";
    }

    // 회원정보 수정 처리
    @PostMapping("/edit")
    public String editSubmit(
            @AuthenticationPrincipal User user,
            EditUserRequest request,
            RedirectAttributes ra
    ) {
        try {
            User updatedUser = userService.updateUser(user.getId(), request);

            Authentication newAuth =
                    new UsernamePasswordAuthenticationToken(
                            updatedUser,
                            null,
                            user.getAuthorities()
                    );

            SecurityContextHolder.getContext().setAuthentication(newAuth);

            ra.addFlashAttribute("editSuccess", true);

            // edit 페이지로 다시 이동
            return "redirect:/user/edit";

        } catch (IllegalArgumentException e) {
            ra.addFlashAttribute("errorMessage", e.getMessage());
            return "redirect:/user/edit";
        }
    }
}
