package pm.dao;

import org.apache.ibatis.annotations.Mapper;
import pm.entities.User;


@Mapper
public interface UserDao {


    User queryByUsername(String username);

    User queryById(Long userId);

    int create(User user);

    int updateEmail(User user);

    int updatePassword(User user);

    int delete(String username);

}
