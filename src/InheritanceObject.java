import java.util.HashMap;


public class InheritanceObject {
	
	//variable points to this objects class
	//set as private as we don't want it to be changed after it is set during its instantiation
	private InheritanceClass myClass;
	
	public String msg;
	
	public HashMap <String, InheritanceFunction> functions;
	private InheritanceFunction func;
	
	public InheritanceObject(InheritanceClass c) {
		myClass = c;
		functions = new HashMap<String, InheritanceFunction> (c.functions);
		func = null;
	}
	
	//attempt to get and then invoke a method from the class hierarchy -
	//  simplified for 1 or 0 arguments
	public void invokeMethod(String method, Object arg) {
		func = myClass.getMethod(method); 				//retrieve the method from the class
		
		if (func == null) {
			System.out.println("Method " + method + 
					" does not exist.");				//method does not exist
			return;
		}
		
		func.execute(this, arg);						//execute the retrieved method
		return;
	}
	
}
