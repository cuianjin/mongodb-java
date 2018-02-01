package mongoDB;
import java.util.List;

import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

@Repository
public class BookDao extends MongoGenDao<Book>
{
	/**
	 * 分页查询 对应mongodb操作中的 db.book.find().skip(10).limit(10);
	 * 
	 * @author http://www.chisalsoft.com
	 */
	public List<Book> queryPage(Book book, Integer start, Integer size)
	{
		Query query = new Query();
		// 此处可以增加分页查询条件Criteria.然后query.addCriteria(criteria);
		return this.getPage(query, (start - 1) * size, size);
	}

	/**
	 * 查询满足分页的记录总数
	 * 
	 * @author http://www.chisalsoft.com
	 */
	public Long queryPageCount(Book book)
	{
		Query query = new Query();
		// 此处可以增加分页查询条件Criteria.然后query.addCriteria(criteria);
		return this.getPageCount(query);
	}

	/**
	 * 更新操作
	 * 
	 * @author http://www.chisalsoft.com
	 */
	public void updateFirst(Book book) throws Exception
	{
		Update update = new Update();
		if (null == book.getId() || "".equals(book.getId().trim()))
		{
			// 如果主键为空,则不进行修改
			throw new Exception("Update data Id is Null");
		}
		if (book.getTheName() != null)
		{
			update.set("theName", book.getTheName());
		}
		this.updateFirst(Query.query(Criteria.where("_id").is(book.getId())), update);
	}

	/**
	 * 更新库中所有数据
	 * 
	 * @author http://www.chisalsoft.com
	 */
	public void updateMulti(Book book) throws Exception
	{
		Update update = new Update();
		if (null == book.getId() || "".equals(book.getId().trim()))
		{
			// 如果主键为空,则不进行修改
			throw new Exception("Update data Id is Null");
		}
		if (book.getTheName() != null)
		{
			update.set("theName", book.getTheName());
		}
		this.updateMulti(Query.query(Criteria.where("_id").is(book.getId())), update);
	}

	/**
	 * 实现钩子方法,返回反射的类型
	 * 
	 * @author http://www.chisalsoft.com
	 */
	@Override
	protected Class<Book> getEntityClass()
	{
		return Book.class;
	}
}