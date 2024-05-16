package by.kolesnik.students.service;

import by.kolesnik.students.entity.User;
import by.kolesnik.students.model.ExtendedUserDetails;
import by.kolesnik.students.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public ExtendedUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        final Optional<User> optionalUser = userRepository.findByUsername(username);

        if (optionalUser.isEmpty()) {
            throw new UsernameNotFoundException("Пользователь не найден: %s".formatted(username));
        }

        return ExtendedUserDetails.create(
                optionalUser.get()
        );
    }
}
