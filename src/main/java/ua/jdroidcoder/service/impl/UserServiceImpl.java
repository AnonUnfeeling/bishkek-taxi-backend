package ua.jdroidcoder.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.jdroidcoder.persistent.dto.UserDto;
import ua.jdroidcoder.persistent.dto.UserProfileDto;
import ua.jdroidcoder.persistent.entity.UserEntity;
import ua.jdroidcoder.persistent.entity.UserProfileEntity;
import ua.jdroidcoder.persistent.repository.UserProfileRepository;
import ua.jdroidcoder.persistent.repository.UserRepository;
import ua.jdroidcoder.service.UserService;

/**
 * Created by jdroidcoder on 07.04.17.
 */
@Service
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;
    private UserProfileRepository userProfileEntity;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, UserProfileRepository userProfileEntity) {
        this.userRepository = userRepository;
        this.userProfileEntity = userProfileEntity;
    }

    @Override
    public boolean register(UserDto userDto) {
        try {
            return (userRepository.save(convertUserDtoToEntity(userDto)) != null);
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public UserProfileDto login(UserDto userDto) {
        return convertUserProfileEntityToDto(userProfileEntity.findUserProfileByEmail(
                userRepository.findUserByEmail(userDto.getEmail()).getEmail()));
    }

    @Override
    public UserProfileDto setDataForUser(UserProfileDto userDto) {
        return convertUserProfileEntityToDto(userProfileEntity.save(convertUserProfileDtoToEntity(userDto)));
    }

    private UserEntity convertUserDtoToEntity(UserDto userDto) {
        return userDto.clone();
    }

    private UserDto convertUserEntityToDto(UserEntity userEntity) {
        return userEntity.clone();
    }

    private UserProfileDto convertUserProfileEntityToDto(UserProfileEntity userProfileEntity) {
        return userProfileEntity.clone();
    }

    private UserProfileEntity convertUserProfileDtoToEntity(UserProfileDto userProfileEntity) {
        return userProfileEntity.clone();
    }
}
