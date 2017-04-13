package ua.jdroidcoder.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.jdroidcoder.persistent.dto.UserDto;
import ua.jdroidcoder.persistent.dto.UserProfileDto;
import ua.jdroidcoder.persistent.entity.UserEntity;
import ua.jdroidcoder.persistent.entity.UserProfileEntity;
import ua.jdroidcoder.persistent.repository.UserRepository;
import ua.jdroidcoder.service.UserService;

/**
 * Created by jdroidcoder on 07.04.17.
 */
@Service
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public boolean register(UserDto userDto) {
        try {
            return (userRepository.save(userDto.clone()) != null);
        } catch (Exception e) {
             return false;
        }
    }

    @Override
    public UserProfileDto login(UserDto userDto) {
        return  userRepository.findUserByEmail(userDto.getEmail()).getUserProfileEntity().clone();
    }

    @Override
    public UserProfileDto setDataForUser(UserProfileDto userDto) {
        UserEntity userEntity = userRepository.findUserByEmail(userDto.getEmail());
        if(userEntity.getUserProfileEntity()==null){
            userEntity.setUserProfileEntity(new UserProfileEntity());
        }
        userEntity.setUserProfileEntity(userDto.clone());
        userRepository.save(userEntity);
        return userDto;
    }
}
