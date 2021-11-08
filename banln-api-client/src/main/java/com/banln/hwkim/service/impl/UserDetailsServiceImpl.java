package com.banln.hwkim.service.impl;

import com.banln.hwkim.data.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.NoSuchElementException;

@RequiredArgsConstructor
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private final Logger logger = LoggerFactory.getLogger(UserDetailsServiceImpl.class);
    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String userPk) {
        logger.info("[loadUserByUsername] loadUserByUsername 수행. userPk : {}", userPk);
        return userRepository.findById(Long.valueOf(userPk)).orElseThrow(NoSuchElementException::new);
    }
}
