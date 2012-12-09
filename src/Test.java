
public class Test {

	//simplified invoke method (0 or 1 parameters only)
	public static void invoke (DelegationObject obj, String method, Object arg) {
		obj.invokeMethod(method, arg);
		return;
	}
	
	public static void invoke (InheritanceObject obj, String method, Object arg) {
		obj.invokeMethod(method, arg);
		return;
	}
	
	public static void main(String [] arguments) {
		
		System.out.println ("**************************");
		System.out.println ("*** Testing delegation ***");
		System.out.println ("**************************\n");
		
		/* Create four objects in the following hierarchy:
		 *      
		 *       delObjOne
		 *        /    \
		 * delObjFour   delObjTwo
		 *                     \
		 *                    delObjThree 
		 *                    
		 */
		
		DelegationObject delObjOne = new DelegationObject();
		DelegationObject delObjTwo = new DelegationObject();
		DelegationObject delObjThree = new DelegationObject();
		DelegationObject delObjFour = new DelegationObject();

		delObjOne.proto = null;
		delObjTwo.proto = delObjOne;
		delObjThree.proto = delObjTwo;
		delObjFour.proto = delObjOne;
		
		delObjOne.functions.put("test", new DelegationFunction() {
			public void execute(DelegationObject self, Object arg) {
				System.out.println("test method invoked");
			}
		});
		
		delObjTwo.functions.put("setMsg", new DelegationFunction() {
			public void execute(DelegationObject self, Object arg) {
				self.msg = (String) arg;
			}
		});
		
		delObjTwo.functions.put("printMsg", new DelegationFunction() {
			public void execute(DelegationObject self, Object arg) {
				System.out.println(self.msg);
			}
		});
		
		invoke(delObjOne, "test", (Object) null);
		invoke(delObjTwo, "test", (Object) null);
		invoke(delObjThree, "test", (Object) null);
	
		//set a message on object 2
		invoke(delObjTwo, "setMsg", "This is a set message on object 2. Hello.");
		
		//these should fail as object 1 does not have a printMsg set and it does
		//not have a prototype and object 4's prototype is object 1
		invoke(delObjOne, "printMsg", null);
		invoke(delObjFour, "printMsg", null);
		
		//this should succeed in printing the message
		invoke(delObjTwo, "printMsg", null);
		
		//this should succeed in printing the message as object three will
		//delegate it to object 2
		invoke(delObjThree, "printMsg", null);
		
		//change object four's prototype
		delObjFour.proto = delObjThree;
		
		//this should now succeed in printing the message as object four will
		//now delegate it to object 3, who will delegate it to object 2
		invoke(delObjFour, "printMsg", null);
		
		System.out.println ("\n***************************");
		System.out.println ("*** Testing inheritance ***");
		System.out.println ("***************************\n");
		
		/* Create four classes in the following hierarchy:
		 *      
		 *       classOne
		 *        /    \
		 * classTwo   classThree
		 *                     \
		 *                    classFour 
		 *                    
		 */
		
		InheritanceClass classOne = new InheritanceClass();
		InheritanceClass classTwo = new InheritanceClass(classOne);
		InheritanceClass classThree = new InheritanceClass(classOne);
		InheritanceClass classFour =  new InheritanceClass(classThree);
		
		//add a test function to the root of the hierarchy
		classOne.functions.put("test", new InheritanceFunction() {
			public void execute(InheritanceObject self, Object arg) {
				System.out.println("Class one's inheritance test method invoked.");
			}
		});
		
		//create one object for each class
		InheritanceObject objOne = new InheritanceObject(classOne);
		InheritanceObject objTwo = new InheritanceObject(classTwo);
		InheritanceObject objThree = new InheritanceObject(classThree);
		InheritanceObject objFour = new InheritanceObject(classFour);
		
		//invoke the test method in each of the objects
		invoke(objOne, "test", (Object) null);
		invoke(objTwo, "test", (Object) null);
		invoke(objThree, "test", (Object) null);
		invoke(objFour, "test", (Object) null);
		
		//override objFour's test methodand invoke it
		classFour.functions.put("test", new InheritanceFunction() {
			public void execute(InheritanceObject self, Object arg) {
				System.out.println("Class four's inheritance test method invoked.");
			}
		});
		
		invoke(objFour, "test", (Object) null);
		
		
		//define two new functions in the root class that sets the msg member and prints its contents
		classOne.functions.put("setMsg", new InheritanceFunction() {
			public void execute(InheritanceObject self, Object arg) {
				self.msg = (String) arg;
			}
		});
		
		classOne.functions.put("printMsg", new InheritanceFunction() {
			public void execute(InheritanceObject self, Object arg) {
				System.out.println(self.msg);
			}
		});
		
		//set msg to a different message in each object
		invoke(objOne, "setMsg", "Hello I am object 1");
		invoke(objTwo, "setMsg", "Hello I am object 2");
		invoke(objThree, "setMsg", "Hello I am object 3");
		invoke(objFour, "setMsg", "Hello I am object 4");
		
		//print each of the messages
		//a key difference between this and delegation is that the object gets the method from its class, 
		//  and then invokes it as its own
		invoke(objOne, "printMsg", null);
		invoke(objTwo, "printMsg", null);
		invoke(objThree, "printMsg", null);
		invoke(objFour, "printMsg", null);
	}

}
