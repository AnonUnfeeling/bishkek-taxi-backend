package ua.jdroidcoder.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.jdroidcoder.persistent.dto.UserDto;
import ua.jdroidcoder.persistent.dto.UserProfileDto;
import ua.jdroidcoder.persistent.entity.OrdersEntity;
import ua.jdroidcoder.persistent.entity.UserEntity;
import ua.jdroidcoder.persistent.entity.UserProfileEntity;
import ua.jdroidcoder.persistent.repository.OrderRepository;
import ua.jdroidcoder.persistent.repository.UserRepository;
import ua.jdroidcoder.service.UserService;

import java.util.List;

/**
 * Created by jdroidcoder on 07.04.17.
 */
@Service
public class UserServiceImpl implements UserService {
    private UserRepository userRepository;
    private OrderRepository orderRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, OrderRepository orderRepository) {
        this.userRepository = userRepository;
        this.orderRepository = orderRepository;
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
        return userRepository.findUserByEmail(userDto.getEmail()).getUserProfileEntity().clone();
    }

    @Override
    public UserProfileDto setDataForUser(UserProfileDto userDto) {
        UserEntity userEntity = userRepository.findUserByEmail(userDto.getEmail());
        if (userEntity.getUserProfileEntity() == null) {
            userEntity.setUserProfileEntity(new UserProfileEntity());
        }
        String phone = userEntity.getUserProfileEntity().getPhone();
        userEntity.getUserProfileEntity().setEmail(userDto.getEmail());
        userEntity.getUserProfileEntity().setFirtName(userDto.getFirstName());
        userEntity.getUserProfileEntity().setLastName(userDto.getLastName());
        userEntity.getUserProfileEntity().setPhone(userDto.getPhone());
        userEntity.getUserProfileEntity().setBalance(userDto.getBalance());
        List<OrdersEntity> list = (List<OrdersEntity>) orderRepository.findOrderByUserPhone(phone);
        for (int i = 0; i < list.size(); i++) {
            OrdersEntity order = list.get(i);
            order.setUserPhone(userDto.getPhone());
            orderRepository.save(order);
        }
        userRepository.save(userEntity);
        return userDto;
    }

    @Override
    public void editBalance(String userPhone, int balance) {
        UserEntity userEntity = userRepository.findUserByEmail(userPhone);
        if (userEntity.getUserProfileEntity() == null) {
            userEntity.setUserProfileEntity(new UserProfileEntity());
        }
        userEntity.getUserProfileEntity().setBalance(userEntity.getUserProfileEntity().getBalance() + balance);
        userRepository.save(userEntity);
    }

    @Override
    public UserProfileDto getProfile(String email) {
        return userRepository.findUserByEmail(email).getUserProfileEntity().clone();
    }
}
