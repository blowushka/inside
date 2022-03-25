package com.inside.controller;

import com.inside.dto.UserDTO;
import com.inside.service.security.JWTEntity;
import com.inside.service.security.JWTTokenProvider;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@Tag(name = "Authorize", description = "The Authorize API")
@RequiredArgsConstructor
@RequestMapping("/authorize")
public class AuthorizeController {

    private final JWTTokenProvider provider;

    /**
     * Authorize and generate JWT token for user
     * @param userDTO User DTO which one contains username and password
     * @return JSON object with JWT token
     */
    @PostMapping
    @Operation(summary = "Authorize user")
    public JWTEntity getJWT(@RequestBody UserDTO userDTO){
        return provider.generateToken(userDTO);
    }
}
