package pm.service;

import org.apache.ibatis.annotations.Result;
import pm.dao.UserDao;
import pm.entities.User;

import javax.annotation.Resource;

public interface UserService {

    User queryByUsername(String username);
    User queryById(Long userId);
    int create(User user);

    int updateEmail(User user);

    int updatePassword(User user, String newPassword);

    int delete(String username);

}
