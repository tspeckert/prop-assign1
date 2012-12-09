import java.util.HashMap;

public class InheritanceClass {

	public HashMap <String, InheritanceFunction> functions;
	private InheritanceClass parent;
	
	//create a new class that doesn't have a parent 	
	public InheritanceClass() {
		functions = new HashMap <String,InheritanceFunction> ();
		parent = null;
	}
	
	//create a new class that is a subclass of another
	public InheritanceClass(InheritanceClass parent) {
		functions = new HashMap <String,InheritanceFunction> ();
		this.parent = parent;
	}
	
	InheritanceFunction getMethod (String method) {
		if (functions.containsKey(method)) {
			return functions.get(method);
		} else if (parent != null) {
			return parent.getMethod(method);
		} else {
			return null;
		}
	}

}
