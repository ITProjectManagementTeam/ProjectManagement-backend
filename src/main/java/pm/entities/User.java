package pm.entities;


import lombok.Data;
import java.io.Serializable;
import java.util.Date;

/**
 * 系统用户
 *
 * @author Mark sunlightcs@gmail.com
 */
@Data
public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long userId;
    private String username;
    private String password;
    private String email;
    private String mobile;
}
