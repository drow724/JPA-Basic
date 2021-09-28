package hellojpa;

public class ValueMain {

	public static void main(String[] args) {
		
		Integer a = 10;
		Integer b = 10;
	
		
		System.out.println("a == b " + (a == b));
		
		Address adress1 = new Address("city", "street","10000");
		Address adress2 = new Address("city", "street","10000");
		
		System.out.println("adress1 == adress2 " + (adress1 == adress2));
		System.out.println("adress1 == adress2 " + (adress1.equals(adress2)));
	}

}