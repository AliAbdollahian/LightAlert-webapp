package com.capstone.lightalert;

import com.capstone.lightalert.controller.HealthController;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
@ActiveProfiles("test")
class LightAlertApplicationTests {

    @Autowired
    private HealthController healthController;
    @Test
    void contextLoads() {
        assertThat(healthController).isNotNull();
    }
}
