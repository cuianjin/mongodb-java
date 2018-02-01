package mongoDB;

import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.mongodb.core.query.Query;

import repositories.UserRepository;

public class UserTest {
	UserRepository userRepository;
	
	 @Before
	public void test() {
		ApplicationContext app = new ClassPathXmlApplicationContext("applicationContext.xml");
		userRepository=(UserRepository)app.getBean("userRepository");
	}
	/**
	 * 批量添加记录
	 */
	@Test
	public void test1() {
		for (int i = 1; i <=100; i++) {
			User user = new User();
			user.setName("mongodb");
			user.setAge(i);
			user.setDate(new Date());
			userRepository.save(user);
		}
	}
	/**
	 * 删除一条记录
	 */
	@Test
	public void test2() {
		//查询所有
	     Iterable<User> all = userRepository.findAll();
	     all.forEach(user ->{
	    	userRepository.delete(user);//批量删除
	     });
	     
	}
	
	@Test
	public void test3() {
		//查询所有
     List<User> all = userRepository.findByNameOrderByAgeDesc("mongodb");
	     all.forEach(user ->{
               System.out.println(user.getAge());
		  });
	     
	}
	
	@Test
	public void test4() {
		//查询所有
     List<User> all = userRepository.findByNameOrderByAgeDesc("mongodb");
	     all.forEach(user ->{
               System.out.println(user.getAge());
		  });
	     
	}
	
	
	/**
	  * 分页查询所有，并且排序
	  */
	@Test
	 public void findByPage() {
	      int page = 0;
	      int size = 10;
	      Pageable pageable = new PageRequest(page, size,new Sort(Direction.ASC, "Age"));
	      Page<User> pageInfo = userRepository.findAll(pageable);
	      //总数量
	      System.out.println(pageInfo.getTotalElements());
	      //总页数
	      System.out.println(pageInfo.getTotalPages());
	      for (User user : pageInfo.getContent()) {
	         System.out.println(user.getAge());
	      }

	 }
	
	
	

}

