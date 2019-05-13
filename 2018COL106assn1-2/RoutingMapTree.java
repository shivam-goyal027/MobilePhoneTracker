public class RoutingMapTree
{
	Exchange root;
	//RoutingMapTree subtree_from_root;.
	//LinkedList<Integer> num=new LinkedList<>();
RoutingMapTree()
{
// constructor method. This should create a RoutingMapTree with //one Exchange node, the //root node which has an identifier of //0. Create other constructors that you deem //necessary.
	root=new Exchange(0);
	root.parent=null;	
	//t.list.addFirst(root);
}
public Exchange find(int key,Exchange node)
{

	if(node.number()==key)
		return node;
	int i=0;
	while(i<node.numChildren())
	{	
	  	try{
			Exchange temp=(Exchange)node.givechild(i);
		if(find(key,temp)!=null)
			return find(key,temp);		
			i++;
		   }catch(Exception e2){System.out.println("Exchange with identifier "+key+" not present");	}
	
	}
	return null;
}
public MobilePhone find(int key)
{
	int i=0;
	while(i<root.residentSet().l1.size())
	{
		if(((MobilePhone)root.residentSet().l1.get(i)).number()==key)
			return (MobilePhone)root.residentSet().l1.get(i);
		i++;
	}
	return null;
	
}
public void removeall(MobilePhone a)
{
	Exchange base=a.baseParent;
	Exchange temp=base;
	while(temp!=root)
	{
		temp.residentSet().l1.remove(a);
		temp=temp.parent;
	}
	temp.residentSet().l1.remove(a);
}
// All general tree methods like public Boolean //containsNode(Exchange
//a) but with Exchange as the node class.
 public void switchOn(MobilePhone a, Exchange b)
{
// This method only works on mobile phones that are currently //switched off. It
//switches the phone a on and registers it with base station b. //The
//entire routing map tree will be updated accordingly.
	if(!root.residentSet().l1.contains(a))
	{
		a.switchOn();
		a.baseParent=b;
		Exchange temp=b;
		while(temp!=root)
		{
			temp.residentSet().l1.addLast(a);
			temp=temp.parent;
		}
		temp.residentSet().l1.addLast(a);
	}
}
public void switchOff(MobilePhone a)
{
// This method only works
//on mobile phones that are currently switched on. It switches //the
//phone a off. The entire routing map tree has to be updated //accordingly.
	if(root.residentSet().l1.contains(a))
	{
		a.switchOff();
		removeall(a);
	}
	
}


public Exchange findPhone(MobilePhone m) throws Exception
{
//Given a mobile phone
//m it returns the level 0 area exchange with which it is //registered or
//throws an exception if the phone is not found or switched off.
	try{
		if(root.residentSet().IsMember(m)==true)
			return m.location();
		else{System.out.println("a");
			throw new Exception("Could not access this mobile phone");	}
	   }
	catch(Exception en){}
return null;
}

public Exchange lowestRouter(Exchange a, Exchange b)throws Exception
{
		if(find(a.number(),root)==null || find(b.number(),root)==null )
	throw new Exception("No such Exchanges");

		else if(a==b)
			return a;
		else
		{
			Exchange temp1=a;
			Exchange temp2=b;
			while(temp1!=temp2 && temp1!=null && temp2!=null)
			{
				temp1=temp1.parent;
				temp2=temp2.parent;
			}
			return temp1;
		}
	  }

public ExchangeList routeCall(MobilePhone a, MobilePhone b)
{
	try
		{
			if(root.residentSet().IsMember(a) && root.residentSet().IsMember(b) ){
			Exchange temp1=a.location();
			Exchange temp2=b.location();
			Exchange temp= lowestRouter(temp1,temp2);

			ExchangeList route=new ExchangeList();
			ExchangeList route2=new ExchangeList();
			while(temp1!=temp)
			{
				route.addLast(temp1);
//System.out.println("intemp1");

				temp1=temp1.parent;
			} 
			route.addLast(temp1);
			while(temp2!=temp)
			{
				route2.addLast(temp2);
				//System.out.println("intemp2");
				temp2=temp2.parent;
			}
			int i=0;
			while(i<route2.size())
			{
				Exchange pin=(Exchange)route2.get(route2.size()-i-1);
//System.out.println("inpin");

				route.addLast(pin);
				i++;
			}
 			return route;
}	
		}
	catch(Exception en){}
return null;
}


public void movePhone(MobilePhone a, Exchange b)throws Exception
{
	try{if(find(b.number(),root)==null || root.residentSet().IsMember(a)==false )
		throw new Exception("not allowed");
	else
		switchOff(a);
		switchOn(a,b);
	}
	catch(Exception en){}
}












public String performAction(String actionMessage)
{
// This the
//main stub method that you have to implement. It takes an action
//as a string. The list of actions, and their format will be //described
//next.
	String newline="";
	String newline2="";
	String newline3="";
	String output="";
	int flag=0;
	for(int i=0;i<actionMessage.length();i++)
	{
		if(actionMessage.charAt(i)==' ')
			flag++;
		if(actionMessage.charAt(i)!=' ' && flag==0)
		  newline=newline+actionMessage.substring(i,i+1);
		if(actionMessage.charAt(i)!=' ' && flag==1)
		  newline2=newline2+actionMessage.substring(i,i+			  1);
		if(actionMessage.charAt(i)!=' ' && flag==2)
		  newline3=newline3+actionMessage.substring(i,i+			  1);
	}
	if(newline.equals("addExchange"))
	{
		int a=Integer.parseInt(newline2);
		int b=Integer.parseInt(newline3);
		Exchange var=find(a,root);
		if(var!=null)
			var.setChild(b);
		else
			output=output+("Exchange with identifier "+a+" not present");
output=output+"";
	}
	if(newline.equals("switchOnMobile"))
	{
		int a=Integer.parseInt(newline2);
		int b=Integer.parseInt(newline3);
		if(find(a)==null){
			MobilePhone m= new MobilePhone(a);
		if(find(b,root)!=null)
			switchOn(m,find(b,root));
		else
			output=output+("Exchange with identifier "+b+" not present");
output=output+"";
}
			
	}
	if(newline.equals("switchOffMobile"))
	{
		int a=Integer.parseInt(newline2);
		if(find(a)!=null)
			switchOff(find(a));
		else
			output=output+("MobilePhone with identifier "+a+" not present");
output=output+"";

	}
	if(newline.equals("queryNthChild"))
	{
		int a=Integer.parseInt(newline2);
		int b=Integer.parseInt(newline3);
	   try{
		
		Exchange var=((Exchange)find(a,root).givechild(b));
		output=output+(actionMessage+": "+var.number());
		 }
	   catch(Exception en){output=output+("Exchange "+a+" does not have "+b+"th child");	}
	}
	if(newline.equals("queryMobilePhoneSet"))
	{
		int a=Integer.parseInt(newline2);
		int i=0;
		output=output+(actionMessage+": ");
		if(find(a,root)!=null)
{
		while(i<find(a,root).residentSet().l1.size())
		{
			output=output+(((MobilePhone)find(a,root).residentSet().l1.get(i)).number());
if(i!=find(a,root).residentSet().l1.size()-1)
	output=output+", ";
i++;
		}
}
else
	output=output+("Exchange with identifier "+a+" not present");
		

	}
if(newline.equals("findPhone"))
{
	output=output+"query"+actionMessage+": ";
	try{
	int a=Integer.parseInt(newline2);
			if(find(a)!=null){
		output=output+findPhone(find(a)).number();
}
else
	output=output+"Error - No mobile phone with identifier "+a+" found in the network";
	}
catch(Exception en){}
	
	
}
if(newline.equals("lowestRouter"))
	{
		int a=Integer.parseInt(newline2);
		int b=Integer.parseInt(newline3);
	   try{
if(find(a,root)!=null && find(b,root)!=null){
		output=output+"query"+(actionMessage+": "+lowestRouter(find(a,root),find(b,root)).number());
}
		 }
	   catch(Exception en){}
	}
if(newline.equals("findCallPath"))
	{output=output+"query"+(actionMessage+": ");
		int a=Integer.parseInt(newline2);
		int b=Integer.parseInt(newline3);

	   try{
		int i=0;
		if(find(a)!=null && find(b)!=null){
		
		while(i<routeCall(find(a),find(b)).size())
		{
			output=output+(((Exchange)routeCall(find(a),find(b)).get(i)).number());
//System.out.println(routeCall(find(a),find(b)).size());

if(i!=routeCall(find(a),find(b)).size()-1){
	output=output+", ";
//System.out.print("int");
}
i++;
		}
		}
	else if(find(a)==null)
        output=output+("Error - Mobile phone with identifier "+a+" is currently switched off");
    
    else output=output+("Error - Mobile phone with identifier "+b+" is currently switched off");
		 }
	   catch(Exception en){}
	}
if(newline.equals("movePhone"))
	{
		int a=Integer.parseInt(newline2);
		int b=Integer.parseInt(newline3);
	   try{
		int i=0;
		if(find(a)!=null && find(b,root)!=null){
		output=output+"";
		movePhone(find(a),find(b,root));
		}
		 }
	   catch(Exception en){}
	}




return output;
}
}