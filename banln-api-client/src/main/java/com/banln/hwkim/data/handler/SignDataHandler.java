package com.banln.hwkim.data.handler;

import com.banln.hwkim.data.entity.UserEntity;

import java.util.List;

public interface SignDataHandler {
    UserEntity saveUserEntity(String uid, String password, String name, List<String> roles);
    UserEntity getByUid(String uid);
}
