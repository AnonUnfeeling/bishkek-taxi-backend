package ua.jdroidcoder.service.impl;

import com.mysql.jdbc.exceptions.MySQLIntegrityConstraintViolationException;
import com.mysql.jdbc.exceptions.MySQLNonTransientException;
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
//            System.out.println(userRepository.findUserByEmail(userDto.getEmail()));
//            if(userRepository.findUserByEmail(userDto.getEmail()) == null){
                userRepository.save(userDto.clone());
                return true;
//            }else {
//                return false;
//            }
//            return userRepository.findUserByEmail(userDto.getEmail()) == null;
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public UserProfileDto login(UserDto userDto) {
        try {
            return userRepository.findUserByEmail(userDto.getEmail()).getUserProfileEntity().clone();
        }catch (Exception e){
            return null;
        }
    }

    @Override
    public UserProfileDto setDataForUser(UserProfileDto userDto) {
        try {
            UserEntity userEntity = userRepository.findUserByEmail(userDto.getEmail());
            if (userEntity.getUserProfileEntity() == null) {
                userEntity.setUserProfileEntity(new UserProfileEntity());
            }
            String phone = userEntity.getUserProfileEntity().getPhone();
            userEntity.getUserProfileEntity().setEmail(userDto.getEmail());
            userEntity.getUserProfileEntity().setFirtName(userDto.getFirstName());
            userEntity.getUserProfileEntity().setLastName(userDto.getLastName());
            userEntity.getUserProfileEntity().setPhone(userDto.getPhone());
            userEntity.getUserProfileEntity().setBalance(userEntity.getUserProfileEntity().getBalance());
            List<OrdersEntity> list = (List<OrdersEntity>) orderRepository.findOrderByUserPhone(phone);
            for (int i = 0; i < list.size(); i++) {
                OrdersEntity order = list.get(i);
                order.setUserPhone(userDto.getPhone());
                orderRepository.save(order);
            }
            userRepository.save(userEntity);
            return userDto;
        }catch (Exception e){
            userRepository.delete(userRepository.findUserByEmail(userDto.getEmail()).getId());
            return null;
        }
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
