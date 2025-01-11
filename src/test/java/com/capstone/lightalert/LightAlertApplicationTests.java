package com.capstone.lightalert;

import com.capstone.lightalert.controller.HealthController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
class LightAlertApplicationTests {

    @Autowired
    private HealthController healthController;
    @Test
    void contextLoads() {
        assertThat(healthController).isNotNull();
    }
}
