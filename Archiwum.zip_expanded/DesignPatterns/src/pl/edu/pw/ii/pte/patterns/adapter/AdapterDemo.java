package pl.edu.pw.ii.pte.patterns.adapter;

public class AdapterDemo {
	
	public static void main(String[] args)  {
		
		Adapter adapter = new Adapter(new Adaptee());
		adapter.clientRequest();
		
	}

}
