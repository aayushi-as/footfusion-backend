package com.project.footfusionbackend.security.service;

import com.project.footfusionbackend.model.User;
import com.project.footfusionbackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String emailId) throws UsernameNotFoundException {

        Optional<User> user = userRepository.findByEmailId(emailId);
        user.orElseThrow(() -> new UsernameNotFoundException("Not found"));

        return user.map(UserDetailsImpl::new).get();

//        return new org.springframework.security.core.userdetails.User(emailId, user.get().getPassword(), new ArrayList<>());
    }
}
