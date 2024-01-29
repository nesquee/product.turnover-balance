package com.abelyaev.balance.mapper;

import com.abelyaev.balance.model.dto.authdto.UserDto;
import com.abelyaev.balance.model.entity.UserEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDto map(UserEntity userEntity);

    @InheritInverseConfiguration
    UserEntity map(UserDto dto);
}
