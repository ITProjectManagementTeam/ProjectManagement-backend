package pm.service.impl;

import org.springframework.stereotype.Service;
import pm.dao.UserDao;
import pm.entities.User;
import pm.service.UserService;
import org.apache.shiro.crypto.hash.Sha256Hash;

import javax.annotation.Resource;

@Service
public class UserServiceImpl implements UserService {
    @Resource
    UserDao userDao;


    @Override
    public User queryByUsername(String username) {
        return userDao.queryByUsername(username);
    }

    @Override
    public User queryById(Long userId) {
        return userDao.queryById(userId);
    }


    @Override
    public int create(User user) {
        return userDao.create(user);
    }

    @Override
    public int updateEmail(User user) {
        return userDao.updateEmail(user);
    }


    @Override
    public int updatePassword(User user, String newPassword) {
        if (newPassword == null || newPassword.length() <= 1){
            return 0;
        }
        user.setPassword(newPassword);
        return userDao.updatePassword(user);
    }

    @Override
    public int delete(String username) {
        return userDao.delete(username);
    }
}
