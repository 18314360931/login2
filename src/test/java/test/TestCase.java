package test;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import dao.AdminDao;
import entity.Admin;

public class TestCase {
	AdminDao adminDao;

	@Before
	public void InitBinder() {
		ApplicationContext ac = new ClassPathXmlApplicationContext("spring-mvc.xml");
		adminDao = ac.getBean("adminDao", AdminDao.class);

	}

	@Test
	public void test1() {
		Admin admin = new Admin();
		admin.setName("kimy");
		admin.setAge(new Double(11));
		adminDao.save(admin);
	}

	@Test
	public void test2() {
		System.out.println(adminDao.findAll());
		System.out.println();
	}

}
