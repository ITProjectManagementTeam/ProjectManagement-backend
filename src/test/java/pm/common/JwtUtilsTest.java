package pm.common;

import io.jsonwebtoken.Claims;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class JwtUtilsTest {

    @Resource
    JwtUtils jwtUtils;

    String token;

    @Before
    void f(){
        this.token = jwtUtils.generateToken(1833);

    }

    @Test
    void generateToken() {
    }

    @Test
    void getClaimByToken() {
        Claims claim = jwtUtils.getClaimByToken(token);
        String id = claim.getSubject();
        assertEquals("" + 1833, id);
    }

    @Test
    void isTokenExpired() {
        Claims claim = jwtUtils.getClaimByToken(token);
        boolean isExpired = jwtUtils.isTokenExpired(claim.getExpiration());
        assertFalse(isExpired);
    }

    @Test
    void getSecret() {
        String secret = jwtUtils.getSecret();
        assertEquals("saj332h9hfk20pz",secret);
    }

    @Test
    void setSecret() {
    }

    @Test
    void getExpire() {
    }

    @Test
    void setExpire() {
    }

    @Test
    void getHeader() {
    }

    @Test
    void setHeader() {
    }
}