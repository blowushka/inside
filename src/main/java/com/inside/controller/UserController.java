package com.inside.controller;


import com.inside.dto.UserDTO;
import com.inside.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@Tag(name = "User", description = "The User API")
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @PutMapping
    @Operation(summary = "Create user")
    public UserDTO create(@RequestBody final UserDTO userDTO) {
        log.debug("Create user");
        return userService.save(userDTO);
    }

}
