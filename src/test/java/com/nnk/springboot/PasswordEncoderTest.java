package com.nnk.springboot;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * Created by Khang Nguyen. Email: khang.nguyen@banvien.com Date: 09/03/2019 Time: 11:26 AM
 */

public class PasswordEncoderTest {

    @Test
    public void testPassword() {
        final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        final String pw = encoder.encode("123456");
        assertThat(pw).isNotEmpty();
    }
}
