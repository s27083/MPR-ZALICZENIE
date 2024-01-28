package controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.s27083_bank.model.User;
import com.example.s27083_bank.service.UserService;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;



@RestController
@RequestMapping("/user")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    
    @PostMapping("/register")
    public ResponseEntity<User> registerUser(@RequestBody User entity) {

        try {
            User user = userService.registerUser(entity);
            return ResponseEntity.ok(user);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(null);
        }
      
    }

    @GetMapping("/hello")
    public String getMethodName(@RequestParam String param) {
        return new String("elo");
    }
    
    

}
