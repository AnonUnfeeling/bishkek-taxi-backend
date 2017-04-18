package ua.jdroidcoder.service;

import ua.jdroidcoder.persistent.dto.UserDto;
import ua.jdroidcoder.persistent.dto.UserProfileDto;

/**
 * Created by jdroidcoder on 07.04.17.
 */
public interface UserService {
    boolean register(UserDto userDto);

    UserProfileDto login(UserDto userDto);

    UserProfileDto setDataForUser(UserProfileDto userDto);

    void editBalance(String userEmail, int balance);

    UserProfileDto getProfile(String email);
}
