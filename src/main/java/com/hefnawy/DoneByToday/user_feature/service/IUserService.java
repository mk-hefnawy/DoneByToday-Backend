package com.hefnawy.DoneByToday.user_feature.service;

import com.hefnawy.DoneByToday.exceptions.NoSuchUserException;
import com.hefnawy.DoneByToday.user_feature.data_objects.User;
import org.springframework.stereotype.Service;

@Service
public interface IUserService {

    User addUser(User user);
    User getUserByUserName(String userName) throws NoSuchUserException;

}
