package com.mpakbaz.mycvserver.repository;

import com.mpakbaz.mycvserver.domain.User;
import com.mpakbaz.mycvserver.domain.enums.UserType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    User findByEmail(String email);
//    List<User> findByUserTypeAndReceiveNotifications(UserType userType, boolean receiveNotificationsEnabled);
//    User findByActiveCode(String activeCode);

}