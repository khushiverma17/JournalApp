package com.kverma.journalApp.service;

import com.kverma.journalApp.entity.User;
import com.kverma.journalApp.repository.UserRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Component
public class UserService {
    @Autowired
    private UserRepository userRepository;

    private static  final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();


    public void saveNewUser(User user){
        System.out.println("hdkjfl");
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        System.out.println("hi");
        user.setRoles(Arrays.asList("USER"));
        System.out.println("j");
        userRepository.save(user);
        System.out.println("hello*******");
    }

    public void saveUser(User user){
        userRepository.save(user);
    }



    public List<User> getAll(){
        return userRepository.findAll();
    }

    public Optional<User> findById(ObjectId id){
        return userRepository.findById(id);
    }

    public void deleteById(ObjectId id){
        userRepository.deleteById(id);
    }

    public User findByUserName(String userName){
        User ans =  userRepository.findByUserName(userName);
        return ans;
    }
}
