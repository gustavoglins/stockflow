package com.stockflow.shared.mappers;

import com.stockflow.api.requests.user.UserSignupRequestDTO;
import com.stockflow.api.responses.user.UserResponseDTO;
import com.stockflow.domain.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING, unmappedTargetPolicy = ReportingPolicy.ERROR)
public interface UserMapper {

    @Mapping(target = "role", ignore = true)
    @Mapping(target = "authorities", ignore = true)
    @Mapping(target = "productList", ignore = true)
    User toEntity(UserSignupRequestDTO userSignupRequestDTO);

    UserResponseDTO toResponse(User user);

    List<UserResponseDTO> toResponseList(List<User> userList);
}
