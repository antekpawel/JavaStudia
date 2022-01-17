package pl.edu.pw.ii.pte.patterns.account_without_state;

import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;



public class AccountTest {

	@Test
	public void creditAccountOpen() {
		Account a = new Account("Ann", 10);
		assertTrue(a.getBalance() == 10);
		
		a.credit(10);
		assertTrue(a.getBalance() == 20);
	}

	@Test
	public void creditAccountClose() {
		Account a = new Account("Ann", 10);
		a.close();
		
//		assertThrows(a.credit(10));
		assertThrows(
		IllegalStateException.class,
		()->a.credit(10)
		);
	}
}
