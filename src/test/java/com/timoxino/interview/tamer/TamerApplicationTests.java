package com.timoxino.interview.tamer;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@TestPropertySource(properties = { "openai.token = testToken" })
class TamerApplicationTests {

	@Test
	void contextLoads() {
	}

}
