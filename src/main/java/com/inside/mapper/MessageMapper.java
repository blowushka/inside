package com.inside.mapper;

import com.inside.dto.MessageDTO;
import com.inside.model.Message;
import com.inside.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface MessageMapper extends EntityMapper<MessageDTO, Message>{

    @Override
    @Mapping(target = "sender", ignore = true)
    Message toEntity(final MessageDTO dto);

    default String mapSender(User user) {
        return user.getName();
    }
}
