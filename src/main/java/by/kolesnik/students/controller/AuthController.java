package by.kolesnik.students.controller;

import by.kolesnik.students.dto.LoginRequestDto;
import by.kolesnik.students.dto.RegisterRequestDto;
import by.kolesnik.students.dto.TokenResponseDto;
import by.kolesnik.students.entity.User;
import by.kolesnik.students.repository.UserRepository;
import by.kolesnik.students.util.JwtUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthenticationProvider authenticationProvider;
    private final JwtUtils jwtUtils;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    @PostMapping("/login")
    public TokenResponseDto login(@RequestBody LoginRequestDto dto) {
        final UsernamePasswordAuthenticationToken authenticationToken
                = new UsernamePasswordAuthenticationToken(dto.getUsername(), dto.getPassword());

        final Authentication authentication = authenticationProvider.authenticate(authenticationToken);

        SecurityContextHolder.getContext().setAuthentication(authentication);

        final Date now = new Date();
        final Date expiryDate = Date.from(Instant.now().plus(7, ChronoUnit.DAYS));

        final String token = jwtUtils.generateToken(dto.getUsername(), now, expiryDate);
        return new TokenResponseDto(token);
    }

    @PostMapping("/registration")
    public TokenResponseDto register(@RequestBody RegisterRequestDto dto) {
        // создание пользователя
        final User user = new User();
        user.setUsername(dto.getUsername());
        final String passwordHash = passwordEncoder.encode(dto.getPassword());
        user.setPassword(passwordHash);
        userRepository.save(user);

        final UsernamePasswordAuthenticationToken authenticationToken
                = new UsernamePasswordAuthenticationToken(dto.getUsername(), dto.getPassword());

        final Authentication authentication = authenticationProvider.authenticate(authenticationToken);

        SecurityContextHolder.getContext().setAuthentication(authentication);

        final Date now = new Date();
        final Date expiryDate = Date.from(Instant.now().plus(7, ChronoUnit.DAYS));

        final String token = jwtUtils.generateToken(dto.getUsername(), now, expiryDate);
        return new TokenResponseDto(token);
    }
}
