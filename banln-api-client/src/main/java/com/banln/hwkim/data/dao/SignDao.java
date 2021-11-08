package com.banln.hwkim.data.dao;

import com.banln.hwkim.data.entity.UserEntity;

public interface SignDao {
    UserEntity getByUid(String uid);
    UserEntity save(UserEntity userEntity);
}
