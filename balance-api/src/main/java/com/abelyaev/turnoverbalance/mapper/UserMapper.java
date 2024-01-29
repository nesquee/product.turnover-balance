package com.abelyaev.turnoverbalance.mapper;

import com.abelyaev.turnoverbalance.model.dto.authdto.UserDto;
import com.abelyaev.turnoverbalance.model.entity.UserEntity;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDto map(UserEntity userEntity);

    @InheritInverseConfiguration
    UserEntity map(UserDto dto);
}
