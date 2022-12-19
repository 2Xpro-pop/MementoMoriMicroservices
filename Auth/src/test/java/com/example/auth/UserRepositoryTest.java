package com.example.auth;

import com.example.auth.models.Role;
import com.example.auth.models.User;
import com.example.auth.repositories.UserRepository;
import org.aspectj.lang.annotation.*;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.context.*;
import org.springframework.test.context.event.annotation.BeforeTestMethod;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
public class UserRepositoryTest {
    @Autowired
    private UserRepository userRepository;

    @Test
    public void create() throws Exception {
        User user1= new User(-1,"Alice", "sadasd", "dsad", "sdasdasd", Role.Receptionist);
        User user2= new User(-1,"Privet", "sadasd", "dsad", "sdasdasd", Role.Admin);
        //save user, verify has ID value after save
        assertEquals(user1.getId(), -1);
        assertEquals(user2.getId(), -1);
        this.userRepository.save(user1);
        this.userRepository.save(user2);
        assertNotNull(user1.getId());
        assertNotNull(user2.getId());
    }
    @Test
    public void testFetchData(){
        /*Test data retrieval*/
        User userA = userRepository.findByLogin("Alice");
        assertNotNull(userA);
        assertEquals(Role.Receptionist, userA.getRole());
        /*Get all products, list should only have two*/
        Iterable users = userRepository.findAll();
        int count = 0;
        for(Object p : users){
            count++;
        }
        assertEquals(count, 2);
    }
}
