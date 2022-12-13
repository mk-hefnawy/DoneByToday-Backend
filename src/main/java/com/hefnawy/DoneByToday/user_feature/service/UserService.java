package com.hefnawy.DoneByToday.user_feature.service;

import com.hefnawy.DoneByToday.exceptions.NoSuchUserException;
import com.hefnawy.DoneByToday.user_feature.data_access.UserDao;
import com.hefnawy.DoneByToday.user_feature.data_objects.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class UserService implements IUserService{

    @Autowired
    UserDao userDao;

    @Override
    public User addUser(User user) {
        user.setPassword(new BCryptPasswordEncoder().encode(user.getPassword()));
        return userDao.save(user);
    }

    @Override
    public User getUserByUserName(String userName) throws NoSuchUserException{
        Optional<User> optionalUser = userDao.findByUserName(userName);
        optionalUser.orElseThrow(() -> new NoSuchUserException("No such user, Idiot"));
        return optionalUser.get();
    }
}
