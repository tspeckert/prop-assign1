import java.util.HashMap;


public class InheritanceObject {
	
	//variable points to this objects class
	//set as private as we don't want it to be changed after it is set during its instantiation
	private InheritanceClass myClass;
	
	private String msg;
	
	public HashMap <String, InheritanceFunction> functions;

	
	public InheritanceObject(InheritanceClass c) {
		myClass = c;
		functions = new HashMap<String, InheritanceFunction> (c.functions);
	}
	
	
	
}
