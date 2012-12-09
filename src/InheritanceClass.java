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
	
	//fetch the appropriate method from the class hierarchy
	InheritanceFunction getMethod (String method) {
		if (functions.containsKey(method)) {	//check to see if the method exists in this class
			return functions.get(method);		//method exists, so pass it to the object
		} else if (parent != null) {			
			return parent.getMethod(method);	//go to the objects parent (if one exists) to fetch the method
		} else {
			return null;						//method does not exist in this hierarchy
		}
	}

}
