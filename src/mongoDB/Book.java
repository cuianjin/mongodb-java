package mongoDB;
import org.springframework.data.mongodb.core.mapping.DBRef;

public class Book
{
	private String id;
	private String theName;
	@DBRef
	private Member member;
	
	public String getId()
	{
		return id;
	}
	public void setId(String id)
	{
		this.id = id;
	}
	public String getTheName()
	{
		return theName;
	}
	public void setTheName(String theName)
	{
		this.theName = theName;
	}
	public Member getMember()
	{
		return member;
	}
	public void setMember(Member member)
	{
		this.member = member;
	}
}