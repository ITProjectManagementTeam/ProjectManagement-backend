package pm.controller;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pm.common.JwtUtils;
import pm.entities.R;
import pm.entities.User;
import pm.form.LoginForm;
import pm.service.UserService;

import javax.annotation.Resource;

@RestController
@RequestMapping("/api")
@Slf4j
public class LoginController {

    @Resource
    UserService userService;

    @Resource
    JwtUtils jwtUtils;

    @PostMapping("/login")
    public R login(@RequestBody LoginForm form) {
        User user = userService.queryByUsername(form.getUsername());
        if (user == null) {
            return R.error(-1, "用户不存在");
        }
        if (user.getPassword() == null || !user.getPassword().equals(form.getPassword())) {
            return R.error(-1, "密码错误");
        }
        String token = jwtUtils.generateToken(user.getUserId());
        return R.ok().put("token", token).put("user", user);
    }
}

