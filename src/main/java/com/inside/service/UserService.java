package com.inside.service;

import com.inside.dto.UserDTO;
import com.inside.model.User;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public interface UserService {

    UserDTO save(UserDTO userDTO);

    Optional<User> findByName(String name);
}
