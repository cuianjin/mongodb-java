package mongoDB;
import java.util.List;

import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Repository;

@Repository
public class MemberDao extends MongoGenDao<Member>
{
	/**
	 * 分页查询 对应mongodb操作中的 db.member.find().skip(10).limit(10);
	 * 
	 * @author http://www.chisalsoft.com
	 */
	public List<Member> queryPage(Member member, Integer start, Integer size)
	{
		Query query = new Query();
		return this.getPage(query, (start - 1) * size, size);
	}

	/**
	 * 查询满足分页的记录总数
	 * 
	 * @author http://www.chisalsoft.com
	 */
	public Long queryPageCount(Member member)
	{
		Query query = new Query();
		return this.getPageCount(query);
	}

	/**
	 * 更新操作
	 * 
	 * @author http://www.chisalsoft.com
	 */
	public void updateFirst(Member member) throws Exception
	{
		Update update = new Update();
		if (null == member.getId() || "".equals(member.getId().trim()))
		{
			// 如果主键为空,则不进行修改
			throw new Exception("Update data Id is Null");
		}
		if (member.getUsername() != null)
		{
			update.set("username", member.getUsername());
		}
		if (member.getPassword() != null)
		{
			update.set("password", member.getPassword());
		}
		this.updateFirst(Query.query(Criteria.where("_id").is(member.getId())), update);
	}

	/**
	 * 更新库中所有数据
	 * 
	 * @author http://www.chisalsoft.com
	 */
	public void updateMulti(Member member) throws Exception
	{
		Update update = new Update();
		if (null == member.getId() || "".equals(member.getId().trim()))
		{
			// 如果主键为空,则不进行修改
			throw new Exception("Update data Id is Null");
		}
		if (member.getUsername() != null)
		{
			update.set("username", member.getUsername());
		}
		if (member.getPassword() != null)
		{
			update.set("password", member.getPassword());
		}
		this.updateMulti(Query.query(Criteria.where("_id").is(member.getId())), update);
	}

	/**
	 * 实现钩子方法,返回反射的类型
	 * 
	 * @author http://www.chisalsoft.com
	 */
	@Override
	protected Class<Member> getEntityClass()
	{
		return Member.class;
	}
}