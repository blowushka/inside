package com.inside.config;

import com.inside.dto.UserDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@RequiredArgsConstructor
@Configuration
public class TestConfig {

    @Bean
    public UserDTO userDTO() {
        return new UserDTO();
    }
}
