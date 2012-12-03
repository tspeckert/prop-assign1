
public class Test {

	public static void main() {
		
		DelegationObject delObjOne = new DelegationObject();
		DelegationObject delObjTwo = new DelegationObject();
		DelegationObject delObjThree = new DelegationObject();

		delObjOne.proto = null;
		delObjTwo.proto = delObjOne;
		delObjThree.proto = delObjTwo;
		
	}

}
