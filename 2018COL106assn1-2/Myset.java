import java.util.*;
public class Myset
{
	linkedList l1 = new linkedList();

	public Boolean IsEmpty()
	{
	//returns true if the set is empty.
		return l1.isEmpty();	
	}

	public Boolean IsMember(Object o)
	{
	// Returns true if o is in the set, false otherwise.
		return l1.contains(o);
	}
	public void Insert(Object o)
	{
		l1.addLast(o);
	// Inserts o into the set.
	}	
	public void Delete(Object o)throws Exception
	{ 
	//Deletes o from the set, throws exception if o is not in 
	//the set.
		
			if(IsMember(o))
			    l1.remove(o);
			else
				throw new Exception("Element not present");
		   
	}
//	public Object Remove()
//	{
	//Helper function to remove head element and return it.
//		return l1.remove();
//	}
	public Myset Union(Myset a)
	{
	//Returns a set which is the union of the current set with 
	//the set a.
		Myset b=new Myset();
		int i=0;
		while(i<a.l1.size())
		{
			Object v=a.l1.get(i);
			if(!IsMember(v))
				Insert(v);
		}
		i=0;
		while(i<l1.size())
		{
			Object v=l1.get(i);
			b.Insert(v);
		}
		return b;
	}
	public Myset Intersection(Myset a)
	{
	//Returns a set which is
	//the intersection of the current set with the set a.
		Myset b=new Myset();
		int i=0;
		while(i<a.l1.size())
		{
			Object v=a.l1.get(i);
			if(IsMember(v))
				b.Insert(v);
		}
		return b;
	}
}