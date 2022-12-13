package com.hefnawy.DoneByToday.security;

import com.hefnawy.DoneByToday.exceptions.NoSuchUserException;
import com.hefnawy.DoneByToday.user_feature.data_access.UserDao;
import com.hefnawy.DoneByToday.user_feature.data_objects.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class MyUserDetailsService implements UserDetailsService {

    @Autowired
    UserDao userDao;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

            Optional<User> optionalUser = userDao.findByUserName(username);
            optionalUser.orElseThrow(() -> new NoSuchUserException("No such user, Idiot"));
            return optionalUser.map(MyUserDetails::new).get();
        }

}
