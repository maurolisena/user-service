package com.mlisena.user.application;

import com.mlisena.user.domain.model.User;
import com.mlisena.user.utils.PasswordManager;
import org.springframework.stereotype.Service;

@Service
public class UserManager {

    public void encryptPassword(User user) {
        user.setPassword(PasswordManager.hashPassword(user.getPassword()));
    }

}
