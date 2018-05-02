package practice;

public class A {

	public A() {
		this("A");
		System.out.println("Default Constructor");
	}
	
	
	public A(String a) {
		
		System.out.println("Constructor with String");
	}
	public void methodA(){
		System.out.println("MethodA");
	}
	
	public String methodA(String a){
		System.out.println("MethodA");
		return "A";
	}
	
}
