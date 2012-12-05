import java.util.*;

public class DelegationObject {
	public DelegationObject proto;
	public HashMap <String, DelegationFunction> functions;
	public String msg;
	
	public DelegationObject() {
		proto = null;
		functions = new HashMap();
	}
	
	public void invokeMethod(String method, Object arg) {
		if (functions.containsKey(method)) {
			functions.get(method).execute(this, arg);
		} else if (proto != null){
			proto.invokeMethod(method, arg);
		} else {
			System.out.println("Method " + method + " does not exist.");
		}
		return;
	}
}
