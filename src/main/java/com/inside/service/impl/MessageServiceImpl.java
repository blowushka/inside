package com.inside.service.impl;

import com.inside.dto.MessageDTO;
import com.inside.exception.EntityNotFoundException;
import com.inside.mapper.MessageMapper;
import com.inside.model.Message;
import com.inside.model.User;
import com.inside.repository.MessageRepository;
import com.inside.service.MessageService;
import com.inside.service.UserService;
import com.inside.service.security.JWTTokenProvider;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class MessageServiceImpl implements MessageService {

    private final MessageRepository repository;
    private final MessageMapper mapper;
    private final UserService userService;
    private final JWTTokenProvider provider;


    @Override
    public MessageDTO save(String bearerToken, MessageDTO dto) {

        provider.verifyToken(dto.getSender(), bearerToken);

        final var entity = mapper.toEntity(dto);
        User user = userService.findByName(dto.getSender())
                .orElseThrow(() -> new EntityNotFoundException(dto.getSender(), User.class));
        entity.setSender(user);

        repository.save(entity);

        return mapper.toDto(entity);
    }

    @Override
    public List<MessageDTO> getHistory(String bearerToken, String userName, int amount) {

        provider.verifyToken(userName, bearerToken);

        User user = userService.findByName(userName)
                .orElseThrow(() -> new EntityNotFoundException(userName, User.class));

        Pageable pageable = PageRequest.of(0, amount, Sort.Direction.DESC, "createdAt");
        List<Message> messages = repository.findBySender(user, pageable);

       return messages.stream()
                .map(mapper::toDto)
                .toList();
    }
}
