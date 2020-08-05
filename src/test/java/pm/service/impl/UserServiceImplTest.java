package pm.service.impl;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;
import pm.entities.User;
import pm.service.UserService;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class UserServiceImplTest {

    @Resource
    UserService userService;

    @Test
    void queryByUsername() {
        User admin = userService.queryByUsername("admin");
    }

    @Test
    void queryById() {
        User user = userService.queryById(1L);
    }

    @Test
    void create() {
        User user = new User();
        user.setUsername("username990");
        user.setPassword("password990");
        user.setEmail("abc@mail.com");
        try {
            userService.create(user);
        } catch (Exception e){
        }
    }

    @Test
    void updateEmail() {
        User user = userService.queryById(1L);
        user.setEmail("123@mail.com");
        userService.updateEmail(user);
    }

    @Test
    void updatePassword() {
        User user = userService.queryById(1L);
        user.setEmail("123@mail.com");
        userService.updatePassword(user, user.getPassword());
    }

    @Test
    void delete() {
        userService.delete("username");
    }
}