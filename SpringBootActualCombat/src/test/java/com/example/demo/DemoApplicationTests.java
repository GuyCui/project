package com.example.demo;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class DemoApplicationTests {

	@Test
    public void contextLoads() {
	    TestAnnotationAspect testAnnotationAspect = new TestAnnotationAspect();
	    testAnnotationAspect.testAnnotation();
	}

}
