package com.stockflow.shared.mappers;

import com.stockflow.api.responses.user.UserAuthResponseDTO;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface AuthMapper {

    UserAuthResponseDTO toResponse(String login, String token);
}
