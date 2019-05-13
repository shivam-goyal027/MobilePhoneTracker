public class MobilePhone extends Object
{	
	private boolean tell_status;
	private int id;
	public Exchange baseParent;
	MobilePhone(int number)
	{ 
	//constructor to create a mobile phone. Unique identifier 	//for a mobile phone is an integer.
		id=number;
	}
             public int number()
	{
	// returns the id of the mobile phone.
		return id;
	}
	public Boolean status()
	{
	// returns the status of the phone, i.e. switched on or 	//switched off
		return tell_status;
	}
	public void switchOn()
	{
 	//Changes the status to switched on.
		tell_status=true;
	}
	public void switchOff()
	{
	// Changes the status to switched off.
		tell_status=false;
	}
	public Exchange location()throws Exception
	{
	// returns the base station with which the phone is 	//registered if the phone is switched on and an exception if 	//the phone is off. The class Exchange will be described 	//next.
		
			if(tell_status==true)
				return baseParent;
			else
				throw new Exception("MobilePhone switched off");
		   	
	}
}