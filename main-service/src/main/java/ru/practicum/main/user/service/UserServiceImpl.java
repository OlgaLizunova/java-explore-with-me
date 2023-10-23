package ru.practicum.main.user.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.practicum.main.exception.NotFoundException;
import ru.practicum.main.user.dto.UserDto;
import ru.practicum.main.user.dto.UserShortDto;
import ru.practicum.main.user.dto.mapper.UserMapper;
import ru.practicum.main.user.model.User;
import ru.practicum.main.user.repository.UserRepository;
import ru.practicum.main.utils.PageRequestUtil;

import java.util.List;


@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Transactional()
    @Override
    public UserDto addUserAdmin(UserShortDto newUserDto) {
        User newUser = userMapper.toUser(newUserDto);
        User addedUser = userRepository.save(newUser);
        log.info("Was add user={}", addedUser);
        return userMapper.toUserDto(addedUser);
    }

    @Transactional(readOnly = true)
    @Override
    public List<UserDto> getAllUsersAdmin(List<Long> ids, int from, int size) {
        Pageable pageable = PageRequestUtil.of(from, size);
        List<User> users;
        if (ids == null || ids.isEmpty()) {
            users = userRepository.findAll(pageable).getContent();
        } else {
            users = userRepository.findAllByIdIn(ids, pageable);
        }
        log.info("Returned all {} users", users.size());
        return userMapper.toDtoList(users);
    }

    @Transactional
    @Override
    public void deleteUserByIdAdmin(Long id) {
        findUserById(id);
        userRepository.deleteById(id);
        log.info("Deleted user with id={}", id);
    }

    private User findUserById(long userId) {
        return userRepository.findById(userId).orElseThrow(() ->
                new NotFoundException(String.format("User with id=%d was not found", userId)));
    }
}
