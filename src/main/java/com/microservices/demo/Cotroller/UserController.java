package com.microservices.demo.Cotroller;

import com.microservices.demo.POJO.User;
import com.microservices.demo.Services.UserService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.RequestEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {



    public UserController(UserService userService,KafkaTemplate<String,User> kafkaTemplate){
        this.userService=userService;
        this.kafkaTemplate=kafkaTemplate;
    }

    final private UserService userService;
    final private KafkaTemplate<String,User> kafkaTemplate;

    @PostMapping("/kafka/{id}")
            public void KafkaGetUser(@PathVariable Integer id){

        kafkaTemplate.send("my_topic",userService.getUser(id));
    }

    @GetMapping("{id}")
    public ResponseEntity<User> getUser(@PathVariable Integer id)
    {

        return new ResponseEntity<User>(userService.getUser(id), HttpStatus.OK);
    }

    @GetMapping()
    public ResponseEntity<List<User>> getAllUsers()
    {
        return new ResponseEntity<List<User>>(userService.getAllUsers(),HttpStatus.OK);

    }

    @DeleteMapping("/{id}")
    public void deleteUser(@PathVariable Integer id)
    {
        userService.deleteUser(id);
    }



    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    public void addUser(RequestEntity<User> requestEntity)
    {
        userService.addAndUpdateUser(requestEntity.getBody());
    }

    @PutMapping()
    public void updateUser(RequestEntity<User> requestEntity)
    {
        User newUser=requestEntity.getBody();
        System.out.println(newUser.getUserId());
        User oldUser=userService.getUser(newUser.getUserId());
        BeanUtils.copyProperties(newUser,oldUser,"userID");
        userService.addAndUpdateUser(oldUser);

    }

}
