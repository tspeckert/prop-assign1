import java.util.*;

class DelegationObject {
	public DelegationObject proto;
	public HashMap <String, DelegationFunction> functions;
	
	public DelegationObject() {
		proto = null;
		functions = new HashMap();
	}
}
