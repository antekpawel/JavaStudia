package pl.edu.pw.ii.pte.patterns.adapter;

public class Adaptee {
	public void specialCall() {
		for (int i = 3; i > 0; i--) {
			System.out.println(i);
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		System.out.println("Hello!");
		
	}
}
