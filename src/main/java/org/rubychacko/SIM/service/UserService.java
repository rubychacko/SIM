package org.rubychacko.SIM.service;


import org.rubychacko.SIM.model.User;
import org.rubychacko.SIM.security.UserDTO;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

public interface UserService extends UserDetailsService {
   public UserDetails loadUserByUsername(String userName);
   public void createUser(UserDTO userDTO);
   public User findUserByEmail(String email);
   public User findUserByName(String name);
}

