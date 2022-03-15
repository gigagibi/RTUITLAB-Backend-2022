package rtuitlab.deliveries.services;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import rtuitlab.deliveries.entities.UserEntity;
import rtuitlab.deliveries.repositories.UserRepository;

import java.util.Collections;

@Service
@AllArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = userRepository.getByUsername(username);
        return new org.springframework.security.core.userdetails.User(username, userEntity.getPassword(), Collections.singletonList(new SimpleGrantedAuthority(userEntity.getRole().getName())));
    }
}
