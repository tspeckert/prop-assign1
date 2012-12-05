
public class Test {

	//simplified invoke method (0 or 1 parameters only)
	public static void invoke (DelegationObject obj, String method, Object arg) {
		obj.invokeMethod(method, arg);
		return;
	}
	
	public static void main(String [] arguments) {
		
		DelegationObject delObjOne = new DelegationObject();
		DelegationObject delObjTwo = new DelegationObject();
		DelegationObject delObjThree = new DelegationObject();

		delObjOne.proto = null;
		delObjTwo.proto = delObjOne;
		delObjThree.proto = delObjTwo;
		
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
		invoke(delObjTwo, "setMsg", "This is a set message. Hello.");
		invoke(delObjOne, "printMsg", null);
		invoke(delObjTwo, "printMsg", null);
		invoke(delObjThree, "printMsg", null);
	}

}
