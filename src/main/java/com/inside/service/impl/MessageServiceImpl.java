package com.inside.service.impl;

import com.inside.dto.MessageDTO;
import com.inside.exception.EntityNotFoundException;
import com.inside.mapper.MessageMapper;
import com.inside.model.User;
import com.inside.repository.MessageRepository;
import com.inside.service.MessageService;
import com.inside.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class MessageServiceImpl implements MessageService {

    private final MessageRepository repository;
    private final MessageMapper mapper;
    private final UserService userService;

    @Override
    public MessageDTO save(MessageDTO dto) {
        final var entity = mapper.toEntity(dto);
        User user = userService.findByName(dto.getSender())
                .orElseThrow(() -> new EntityNotFoundException(dto.getSender(), User.class));
        entity.setSender(user);

        repository.save(entity);

        return mapper.toDto(entity);
    }
}
