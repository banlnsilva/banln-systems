package com.banln.hwkim.data.repository;

import com.banln.hwkim.data.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    UserEntity getByUid(String uid);
}
