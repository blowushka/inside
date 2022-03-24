package com.inside.service;

import com.inside.dto.UserDTO;
import com.inside.model.User;
import org.springframework.stereotype.Service;

import java.util.Optional;

/**
 * This interface allows us to add new User to DB as well as opportunity to get exact User from DB by his name
 */
@Service
public interface UserService {

    /**
     * Save new user to DB
     * @param userDTO JSON object which one store information about user name and his password
     * @return UserDTO
     */
    UserDTO save(UserDTO userDTO);

    /**
     * Find user by his name
     * @param name string name helps us find this user in DB
     */
    Optional<User> findByName(String name);
}
