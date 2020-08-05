package pm.controller;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;
import pm.entities.R;
import pm.entities.User;
import pm.form.LoginForm;
import pm.form.PasswordForm;
import pm.service.UserService;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@RunWith(SpringRunner.class)
class LoginControllerTest {

    @Resource
    private LoginController loginController;

    @Resource
    private RegisterController registerController;

    @Resource
    private UserController userController;

    @Resource
    private UserService userService;

    @Test
    void loginTest(){
        LoginForm form = new LoginForm();
        form.setPassword("admin");
        form.setUsername("admin");
        loginController.login(form);
    }

    @Test
    void passwordTest(){
        User user = new User();
        user.setUsername("test2");
        user.setPassword("test2");
        try {
            registerController.Register(user);
        } catch (DuplicateKeyException e){
        }
        PasswordForm form = new PasswordForm();
        form.setNewPassword("test333");
        form.setPassword("test2");
        System.out.println(user.getUserId());
        userController.password(form, "" + user.getUserId());
        userService.delete("test2");
    }
}