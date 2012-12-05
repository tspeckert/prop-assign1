
public class Test {

	//simplified invoke method (0 or 1 parameters only)
	public static void invoke (DelegationObject obj, String method, Object arg) {
		if (obj.functions.containsKey(method)) {
			obj.functions.get(method).execute(obj, arg);
		}
		
		return;
	}
	
	public static void main(String [] arguments) {
		
		DelegationObject delObjOne = new DelegationObject();
		DelegationObject delObjTwo = new DelegationObject();
		DelegationObject delObjThree = new DelegationObject();

		delObjOne.proto = null;
		delObjTwo.proto = delObjOne;
		delObjThree.proto = delObjTwo;
		
		delObjTwo.functions.put("test", new DelegationFunction() {
			public void execute(DelegationObject self, Object arg) {
				System.out.println("test method invoked");
			}
		});
		
		invoke(delObjTwo, "test", (Object) null);
	}

}
