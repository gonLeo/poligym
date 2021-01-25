package com.poligym.service;

import java.util.List;
import java.util.Optional;

import com.poligym.models.Users;
import com.poligym.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class JwtUserDetailsService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    public JwtUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Optional<Users> user = userRepository.findByEmail(email);

        if (user.isPresent()) {
            Users userEntity = user.get();
            List<GrantedAuthority> authorityListAdmin = AuthorityUtils.createAuthorityList("ROLE_USER", "ROLE_ADMIN");

            return new org.springframework.security.core.userdetails.User(userEntity.getEmail(),
                    userEntity.getPassword(), authorityListAdmin);
        } else {
            throw new UsernameNotFoundException("User not found.");
        }
    }

}
