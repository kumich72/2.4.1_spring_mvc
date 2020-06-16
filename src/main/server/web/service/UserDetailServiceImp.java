package web.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import web.model.Role;
import web.model.User;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserDetailServiceImp implements UserDetailsService {
    @Autowired
    private IUserService userService;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        User user = userService.findByUsername(userName);
        if (user == null) {
            throw new UsernameNotFoundException("User not found");
        }
        List<Role> roles = userService.getRolesByUser(user);
        UserDetails userDetails =
                new org.springframework.security.core.userdetails.User(userName,
                        user.getPassword(),
                        roles);

        return userDetails;
    }
}
