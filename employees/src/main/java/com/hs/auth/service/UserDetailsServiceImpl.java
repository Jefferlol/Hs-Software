package com.hs.auth.service;

import com.hs.employees.entity.UserActivo;
import com.hs.employees.repository.UserActivoRepository;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserActivoRepository userRepo;

    public UserDetailsServiceImpl(UserActivoRepository userRepo) {
        this.userRepo = userRepo;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserActivo user = userRepo.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Usuario no encontrado"));

        String role = user.getEmpleado().getCargo().toUpperCase(); // e.g. ADMIN, VENDEDOR

        return new User(user.getUsername(), user.getPassword(),
                List.of(new SimpleGrantedAuthority("ROLE_" + role)));
    }
}
