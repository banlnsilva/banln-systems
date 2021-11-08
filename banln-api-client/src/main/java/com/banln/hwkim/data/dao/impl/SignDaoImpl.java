package com.banln.hwkim.data.dao.impl;

import com.banln.hwkim.data.dao.SignDao;
import com.banln.hwkim.data.entity.UserEntity;
import com.banln.hwkim.data.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SignDaoImpl implements SignDao {
    private final Logger logger = LoggerFactory.getLogger(SignDaoImpl.class);

    @Autowired
    private UserRepository userRepository;

//    @Autowired
//    public SignDAOImpl(UserRepository userRepository) {
//        this.userRepository = userRepository;
//    }

    @Override
    public UserEntity getByUid(String uid) {
        return userRepository.getByUid(uid);
    }

    @Override
    public UserEntity save(UserEntity userEntity) {
        return userRepository.save(userEntity);
    }
}
