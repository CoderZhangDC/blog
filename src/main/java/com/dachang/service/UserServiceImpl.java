package com.dachang.service;

import com.dachang.dao.UserRepository;
import com.dachang.pojo.User;
import com.dachang.util.MD5Utils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author : xsh
 * @create : 2020-02-08 - 0:14
 * @describe:
 */
@Service
public class UserServiceImpl implements UserService{

    @Autowired
    private UserRepository userRepository;

    @Override
    public User checkUser(String username, String password) {
        User user=userRepository.findByUsernameAndPassword(username, MD5Utils.code(password));
        return user;
    }
}
