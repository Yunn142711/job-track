package com.jobtrack;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.jobtrack.entity.User;
import com.jobtrack.repository.UserRepository;

import jakarta.servlet.http.HttpSession;

@RestController
public class AuthController {

    private final UserRepository userRepository;

    public AuthController(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // ログイン
    @PostMapping("/auth/login")
    public String login(
            @RequestBody LoginRequest request,
            HttpSession session) {

        User user = userRepository.findByUsername(request.username());

        if (user != null &&
                user.getPassword().equals(request.password())) {

            session.setAttribute("userId", user.getId());
            session.setAttribute("username", user.getUsername());

            return "OK";
        }

        return "ERROR";
    }

    // 新規登録
    @PostMapping("/auth/register")
    public String register(
            @RequestBody RegisterRequest request) {

        if (request.username() == null ||
                request.username().isBlank() ||
                request.password() == null ||
                request.password().isBlank()) {

            return "EMPTY";
        }

        // 同じユーザー名があるかチェック
        User existingUser =
                userRepository.findByUsername(request.username());

        if (existingUser != null) {
            return "EXISTS";
        }

        // 新しいユーザーを作成
        User user = new User();

        user.setUsername(request.username());
        user.setPassword(request.password());

        userRepository.save(user);

        return "OK";
    }

    // ログアウト
    @PostMapping("/auth/logout")
    public String logout(HttpSession session) {

        session.invalidate();

        return "OK";
    }

    public record LoginRequest(
            String username,
            String password) {
    }

    public record RegisterRequest(
            String username,
            String password) {
    }
}