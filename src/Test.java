
public class Test {

	public static void invoke (DelegationObject obj, String method, Object... args) {
		if (obj.functions.containsKey(method)) {
			obj.functions.get(method).execute(obj, args);
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
			public void execute(DelegationObject self, Object... args) {
				System.out.println("test method invoked");
			}
		});
		
		invoke(delObjTwo, "test", (Object) null);
	}

}
