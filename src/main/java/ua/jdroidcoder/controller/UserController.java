package ua.jdroidcoder.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ua.jdroidcoder.persistent.dto.UserDto;
import ua.jdroidcoder.persistent.dto.UserProfileDto;
import ua.jdroidcoder.service.UserService;

import javax.validation.Valid;

/**
 * Created by jdroidcoder on 07.04.17.
 */
@RestController
public class UserController {
    private UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("register")
    private ResponseEntity register(@Valid UserDto userDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(bindingResult.getFieldErrors().get(0).getDefaultMessage());
        }
        return ResponseEntity.ok(userService.register(userDto));
    }

    @PostMapping("setDataToProfile")
    private ResponseEntity setDataToProfile(@Valid UserProfileDto userDto, BindingResult bindingResult) {
//        if (bindingResult.hasErrors()) {
//            return ResponseEntity.badRequest().body(bindingResult.getFieldErrors().get(0).getDefaultMessage());
//        }
        return ResponseEntity.ok(userService.setDataForUser(userDto));
    }

    @PostMapping("login")
    private ResponseEntity login(@Valid UserDto userDto, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(bindingResult.getFieldErrors().get(0).getDefaultMessage());
        }
        return ResponseEntity.ok(userService.login(userDto));
    }
}
