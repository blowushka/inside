package com.inside.mapper;

import com.inside.dto.UserDTO;
import com.inside.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = EncodedPasswordMapper.class)
public interface UserMapper extends EntityMapper<UserDTO, User> {
    @Mapping(target = "password", qualifiedBy = EncodedMapping.class)
    @Override
    User toEntity(final UserDTO dto);
}
