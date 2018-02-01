package mongoDB;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.data.mongodb.core.query.Query;

public class MemberDaoTest {
	
	MemberDao memberDao;
	BookDao bookDao;
	 @Before
	public void test() {
		ApplicationContext app = new ClassPathXmlApplicationContext("applicationContext.xml");
		memberDao=(MemberDao)app.getBean("memberDao");
		bookDao=(BookDao)app.getBean("bookDao");
	}
	/**
	 * 1.测试保存对象
	 */
	@Test
	public void test1() {
       Member member = new Member();
		
		member.setId("2");
		member.setUsername("username1");
		member.setPassword("password1");
		
		memberDao.save(member);
		
	}
	
    //2.测试保存对象
	@Test
	public void save2() throws Exception
	{
		Member member = new Member();
		
		member.setId("2");
		member.setUsername("username2");
		member.setPassword("password2");
		
		Book book = new Book();
		book.setId("2");
		book.setTheName("book2");
		
		member.setBook(book);
		
		memberDao.save(member);
	}
	//两个对象互相保存
	@Test
	public void save3() throws Exception
	{
		
		
		Book book = new Book();
		book.setId("4");
		book.setTheName("book4");
		
		
		//在Member和Book互相包含的情况下，若没有设置@DBRef，则此处会规模溢出
		bookDao.save(book);
		
		System.out.println("保存完成");
	}
	
	
	
	
	/**
	 * 更新数据
	 * @throws Exception
	 */
	@Test
	public void update() throws Exception
	{
		Member member = memberDao.queryById("1");
		member.setUsername("username_1");
		memberDao.updateFirst(member);
	}
	
	
	/**
	 * 查询
	 * @throws Exception
	 */
	@Test
	public void getMember() throws Exception
	{
		Member member = memberDao.queryById("3");
		if(member == null)
		{
			System.out.println("没有这个会员");
		}
		else
		{
			
			System.out.println(member.getUsername());
			if(member.getBook() == null)
			{
				System.out.println("该会员没有book");
			}
			else
			{
				System.out.println("该会员有book:"+member.getBook().getTheName());
			}
		}
	}
	
	/**
	 * 查询
	 * @throws Exception
	 */
	@Test
	public void getBook() throws Exception
	{
		Book book = bookDao.queryById("3");
		if(book != null)
		{
			System.out.println(book.getTheName());
			if(book.getMember() == null)
			{
				System.out.println("该book没有会员");
			}
			else
			{
				System.out.println("该book有会员:"+book.getMember().getUsername());
			}
		}
		else
		{
			System.out.println("没有找到这本书");
		}
	}
	/**
	 * 删除所有book对象
	 * @throws Exception
	 */
	
	@Test
	public void deleteBook() throws Exception
	{
		List<Book> bookList = bookDao.queryList(new Query());
		for(Book book:bookList)
		{
			bookDao.delete(book);
		}
	}
	
	
	/*
	 * 删除所有会员对象
	 */
	@Test
	public void deleteMember() throws Exception
	{
		List<Member> memberList = memberDao.queryList(new Query());
		for(Member member:memberList)
		{
			memberDao.delete(member);
		}
	}
	
	/*
	 * 查询book对象
	 */
	@Test
	public void queryBook() throws Exception
	{
		List<Book> bookList = bookDao.queryList(new Query());
		
		if(bookList.size() == 0)
		{
			System.out.println("没有book");
		}
		else
		{
			for(Book book:bookList)
			{
				System.out.println(book.getTheName());
			}
		}
	}
	/**
	 * 查询会员对象
	 * @throws Exception
	 */
	@Test
	public void queryMember() throws Exception
	{
		List<Member> memberList = memberDao.queryList(new Query());
		if(memberList.size() == 0)
		{
			System.out.println("没有会员");
		}
		else
		{
			for(Member member:memberList)
			{
				System.out.println(member.getUsername());
			}
		}
	}

}
