package ua.jdroidcoder.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ua.jdroidcoder.persistent.dto.UserCoordinateDto;
import ua.jdroidcoder.persistent.dto.UserDto;
import ua.jdroidcoder.persistent.dto.UserProfileDto;
import ua.jdroidcoder.persistent.entity.UserCoordinateEntity;
import ua.jdroidcoder.service.UserCoordinateService;
import ua.jdroidcoder.service.UserService;

import javax.validation.Valid;

/**
 * Created by jdroidcoder on 07.04.17.
 */
@RestController
public class UserController {
    private UserService userService;
    private UserCoordinateService userCoordinateService;

    @Autowired
    public UserController(UserService userService, UserCoordinateService userCoordinateService) {
        this.userService = userService;
        this.userCoordinateService = userCoordinateService;
    }

    @PostMapping("register")
    private ResponseEntity register(@Valid UserDto userDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(bindingResult.getFieldErrors().get(0).getDefaultMessage());
        }
        return ResponseEntity.ok(userService.register(userDto));
    }

    @PostMapping("setDataToProfile")
    private ResponseEntity setDataToProfile(UserProfileDto userDto) {
        return ResponseEntity.ok(userService.setDataForUser(userDto));
    }

    @PostMapping("login")
    private ResponseEntity login(@Valid UserDto userDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(bindingResult.getFieldErrors().get(0).getDefaultMessage());
        }
        return ResponseEntity.ok(userService.login(userDto));
    }

    @PostMapping("setCoordinate")
    private void setCoordinate(UserCoordinateDto userDto) {
        userCoordinateService.setCoordinate(userDto);
    }

    @PostMapping("getUserCoordinate")
    private ResponseEntity getUserCoordinate(String userPhone) {
        return ResponseEntity.ok(userCoordinateService.getCoordinate(userPhone));
    }

    @GetMapping("editBalance")
    private ResponseEntity editBalance(String userEmail, int balance) {
        userService.editBalance(userEmail, balance);
        return ResponseEntity.ok("");
    }

    @PostMapping("getProfile")
    private ResponseEntity getProfile(String email) {
        return ResponseEntity.ok(userService.getProfile(email));
    }
}

