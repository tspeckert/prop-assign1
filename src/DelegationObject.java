import java.util.*;

public class DelegationObject {
	public DelegationObject proto;
	public HashMap <String, DelegationFunction> functions;
	public String msg;
	
	public DelegationObject() {
		proto = null;
		functions = new HashMap <String,DelegationFunction> ();
	}

	//this method determines what gets executed
	public void invokeMethod(String method, Object arg) {
		if (functions.containsKey(method)) { 			//check to see if this object has a matching method
			functions.get(method).execute(this, arg);	//method exists; execute it
		} else if (proto != null){
			proto.invokeMethod(method, arg);			//delegate execution to the prototype (if one exists)
		} else {
			System.out.println("Method " + method + 
					" does not exist.");				//the method that was called does not exist
		}
		return;
	}
}
