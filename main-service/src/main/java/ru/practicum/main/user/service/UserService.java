package ru.practicum.main.user.service;

import ru.practicum.main.user.dto.UserDto;
import ru.practicum.main.user.dto.UserShortDto;

import java.util.Collection;
import java.util.List;

public interface UserService {
    UserDto addUserAdmin(UserShortDto newUserDto);

    Collection<UserDto> getAllUsersAdmin(List<Long> ids, int from, int size);

    void deleteUserByIdAdmin(Long id);
}
