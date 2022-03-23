package com.inside.service.impl;

import com.inside.dto.UserDTO;
import com.inside.mapper.UserMapper;
import com.inside.model.User;
import com.inside.repository.UserRepository;
import com.inside.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserMapper mapper;
    private final UserRepository repository;

    @Override
    public UserDTO save(UserDTO dto) {
        final var entity = mapper.toEntity(dto);
        repository.save(entity);

        return mapper.toDto(entity);
    }

    @Override
    public Optional<User> findByName(String name) {
        return repository.findByName(name);
    }
}
