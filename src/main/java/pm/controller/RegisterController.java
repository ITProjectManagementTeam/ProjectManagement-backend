package pm.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pm.entities.R;
import pm.entities.User;
import pm.service.UserService;

import javax.annotation.Resource;

@RestController
@Slf4j
@RequestMapping("/api")
public class RegisterController {

    @Resource
    UserService userService;

    @PostMapping("/register")
    public R Register(@RequestBody User user){
        try {
            int i = userService.create(user);
            if (i == 1) {
                log.info("Register username:{}, email:{} success", user.getUsername(), user.getEmail());
                return R.ok().put("user", user);
            } else{
                log.info("Register failed");
                return R.error(-1, "注册失败");
            }
        }catch (DuplicateKeyException e){
            return R.error(-1,"注册失败，用户已存在");
        }catch (Exception e){
            log.info(e.getMessage());
            return R.error(-1, "注册失败");
        }
    }
}
