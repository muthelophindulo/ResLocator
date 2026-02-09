package com.RL.auth;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    public UserDetailsServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
        try{
            UserEntity userEntity = userRepository.findByUsername(username)
                    .orElseThrow( () -> new UsernameNotFoundException(
                            "user not found" + username
                    ));

            return org.springframework.security.core.userdetails.User
                    .withUsername(userEntity.getUsername())
                    .password(userEntity.getPassword())
                    .roles(userEntity.getRole())
                    .build();
        }catch (Exception e){
            throw new UsernameNotFoundException("user not found " + username);
        }
    }

}
