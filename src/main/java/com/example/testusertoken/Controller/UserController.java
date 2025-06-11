package com.example.testusertoken.Controller;

import com.example.testusertoken.DTOs.RequestDTO;
import com.example.testusertoken.Services.UserService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/secure")
    public ResponseEntity<String> getProtectedStuff(@RequestBody RequestDTO requestDTO) {
        boolean result = userService.validateToken(requestDTO.getToken());
        if(result){
            return new ResponseEntity<>("SUCCESS", HttpStatus.OK);
        }else{
            return new ResponseEntity<>("FAILURE", HttpStatus.BAD_REQUEST);
        }
    }
}
