package com.example.project6.util;

import com.example.project6.model.User;
import com.example.project6.model.UserDto;
import com.example.project6.model.UserDto2;
import org.mapstruct.Context;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface SimpleSourceDestinationMapper {
    UserDto sourceToDestination(User source, @Context CycleAvoidingMappingContext context);
    User destinationToSource(UserDto userDto);
}
