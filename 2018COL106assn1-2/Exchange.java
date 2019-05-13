public class Exchange
{
	private int id;
	private MobilePhoneSet obj=new MobilePhoneSet();
	private ExchangeList listofchildren=new ExchangeList();
	private Exchange child;
	public Exchange parent;
	Exchange(int number)
	{
	//constructor to create an exchange. Unique identifier for 	//an exchange is an integer.
		id=number;
	}
	public int number()
	{
	// returns the id of the Exchange node.
		return id;
	}
	public int numChildren()
	{
		return listofchildren.size();
	}
	public Exchange givechild(int i) throws Exception
	{
		if(i>=numChildren())
			throw new Exception();
		else
			return ((Exchange)listofchildren.get(i));
	}
	public boolean isExternal()
	{
	//returns 1 if node is external
		if(child==null)
			return true;
		else
			return false; 
	}
	public boolean isRoot()
	{
	//returns 1 if node is root.
		if(parent==null)
			return true;
		else
			return false; 
	}

	public void setParent(int a)
	{
		parent=new Exchange(a);
		
	}
	public void setChild(int b)
	{
		child=new Exchange(b);
		listofchildren.addLast(child);
		child.parent=this;
		
	}

	public MobilePhoneSet residentSet()
	{
	//returns the resident set of mobile phones of the 	//exchange.
		/*int i=0;
		while(i<listofchildren.size())
		{
			obj=obj.Union(listofchildren.get(i).residentSet());
			i++;
		}*/
		return obj;
	}
}