package com.petter;

import com.petter.service.impl.DemoServiceImpl;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BootLearningApplicationTests {

	@Resource
	private DemoServiceImpl demoService;

	@Test
	public void testGetName(){
		Assert.assertEquals("Angel", demoService.getById(1L).getName());
	}
	@Test
	public void contextLoads() {
	}

}
