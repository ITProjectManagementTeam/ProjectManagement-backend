package pm.controller;

import io.jsonwebtoken.Claims;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.apache.shiro.crypto.hash.Sha256Hash;

import pm.common.JwtUtils;
import pm.entities.R;
import pm.entities.User;
import pm.form.EmailForm;
import pm.form.PasswordForm;
import pm.service.UserService;

import javax.annotation.Resource;

@RestController
@RequestMapping("/api/users")
@Slf4j
public class UserController {

    @Resource
    UserService userService;

    @Resource
    JwtUtils jwtUtils;

    @PostMapping("/{userId}/password")
    public R password(@RequestBody PasswordForm form, @PathVariable String userId){
        User user = new User();
        String password = form.getPassword();
        String newPassword = form.getNewPassword();
        try {
            user.setUserId(Long.parseLong(userId));
            user.setPassword(form.getPassword());
            User queryUser = userService.queryById(user.getUserId());
            if (queryUser == null){
                return R.error(-2, "用户不存在");
            }
            if (!queryUser.getPassword().equals(password)){
                return R.error(-3, "原密码不正确");
            }

            int flag = userService.updatePassword(user, newPassword);
            if (flag == 0){
                return R.error(-1, "原密码不正确");
            }
        }catch (Exception e){
            log.info(e.getMessage());
            return R.error(-999, "原密码不正确");
        }
        return R.ok().put("password", form.getNewPassword());
    }

    @PostMapping("/{userId}/email")
    public R email(@RequestBody EmailForm form, @PathVariable String userId, @RequestHeader String authorization){
        Claims claim = jwtUtils.getClaimByToken(authorization);
        try {
//            if (!claim.getSubject().equals(userId)){
//               return R.error(-1, "认证失败");
//            }
            User user = new User();
            user.setUserId(Long.parseLong(userId));
            user.setEmail(form.getEmail());
            int flag = userService.updateEmail(user);
            if (flag == 0) {
                return R.error(-1, "更改email失败");
            }
        }catch (Exception e){
            log.info(e.getMessage());
            return R.error(-1, "更改email失败");
        }
        return R.ok().put("email", form.getEmail());
    }

}
