package com.microservices.demo.Services;

import com.microservices.demo.POJO.User;
import com.microservices.demo.Repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {


    public UserService(UserRepository userRepository){
        this.userRepository=userRepository;
    }


    private UserRepository userRepository;


    public User getUser(Integer id){
        return userRepository.findById(id).get();
    }
    public void addAndUpdateUser(User user){
        userRepository.saveAndFlush(user);
    }
    public List<User> getAllUsers(){return userRepository.findAll();}
    //public void  updateUser(User user){ userRepository.saveAndFlush(user);}
    public void deleteUser(Integer id) { userRepository.deleteById(id);
    }
}
