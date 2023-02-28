package project.vnsbg.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import project.vnsbg.entity.dto.DataLoginDto;
import project.vnsbg.entity.dto.UserDetailDto;
import project.vnsbg.entity.dto.UserDto;
import project.vnsbg.service.UserService;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("login")
    public DataLoginDto loginToTheSystem(@RequestBody UserDto userDto) {
        return userService.loginToTheSystem(userDto);
    }

    @GetMapping("userDetail")
    public UserDetailDto getUserDetail(@RequestParam Long userId) {
        return userService.getUserDetail(userId);
    }
}
