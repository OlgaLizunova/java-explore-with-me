package ru.practicum.main.user.dto.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import ru.practicum.main.user.dto.UserDto;
import ru.practicum.main.user.dto.UserShortDto;
import ru.practicum.main.user.model.User;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDto toUserDto(User user);

    @Mapping(target = "id", ignore = true)
    User toUser(UserShortDto userShotDto);

    List<UserDto> toDtoList(List<User> users);
}
