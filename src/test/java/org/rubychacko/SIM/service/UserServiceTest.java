package org.rubychacko.SIM.service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

@SpringBootTest
public class UserServiceTest {

    @Autowired
    private UserServiceImpl service;

//    @Test
//    public void testLoadUserByUsernameForSuccess() {
//
//        UserDetails userDetails = service.loadUserByUsername("ruby.chacko@gmail.com");
//
//        Assertions.assertNotNull(userDetails);
//        Assertions.assertEquals(userDetails.getUsername(), "ruby.chacko@gmail.com");
//    }

    @Test
    public void testLoadUserByUsernameForFailure() {
        assertThatThrownBy(() -> service.loadUserByUsername("ruby@gmail.com"))
                .isExactlyInstanceOf(UsernameNotFoundException.class)
                .hasMessage("Invalid username or password.");
    }
}
