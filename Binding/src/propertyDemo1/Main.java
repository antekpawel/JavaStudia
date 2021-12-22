package propertyDemo1;

import javafx.beans.binding.Bindings;
import javafx.beans.binding.NumberBinding;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;

public class Main {
	public static void main(String[] args) {
		Bill electricBill = new Bill();
		electricBill.amountDueProperty().addListener(
				(ov, oldVal, newVal) -> {
					System.out.println("Zmieniono rachunek z: " + oldVal.toString() 
					+ " na: " + newVal.toString());
				}
				);
		
		electricBill.setAmountDue(100.0);
		electricBill.setAmountDue(50.0);
		electricBill.setAmountDue(10.000);
		//-------------------------------------
		IntegerProperty num1 = new SimpleIntegerProperty(1);
		IntegerProperty num2 = new SimpleIntegerProperty(10);
		IntegerProperty num3 = new SimpleIntegerProperty(15);
		
		NumberBinding sum = num1.add(num2);
		NumberBinding total = Bindings.add(num1, num2.multiply(num3));
		System.out.println("Sum = " + sum.getValue());
		System.out.println("Total = " + total.getValue());
		
		num2.set(1000);
		System.out.println("Zmienione sum = " + sum.getValue());
		System.out.println("Zmienione total = " + total.getValue());
		//-------------------------------------
		Bill bill1 = new Bill();
		Bill bill2 = new Bill();
		Bill bill3 = new Bill();
		
		NumberBinding totalBill = Bindings.add(
				bill1.amountDueProperty(),
				bill2.amountDueProperty().add(bill3.amountDueProperty()));
		
		totalBill.addListener(
				observale -> {
					System.out.println("Wi¹zanie niewa¿ne");
				});
		bill1.setAmountDue(299.0);
		bill2.setAmountDue(10.0);
		bill3.setAmountDue(20.0);
		System.out.println("totalBill = " + totalBill.getValue());
		bill3.setAmountDue(10.0);
		//-------------------------------------
		Bill bill4 = new Bill();
		Bill bill5 = new Bill();
		Bill bill6 = new Bill();
		
		NumberBinding totalBill2 = Bindings.add(
				bill4.amountDueProperty(),
				bill5.amountDueProperty().add(bill6.amountDueProperty()));
		
		totalBill2.addListener(
				(observale_value, old_value, new_value) -> {
					System.out.println("Wartoœæ zosta³a zmianiona!");
				});
		bill4.setAmountDue(299.0);
		bill5.setAmountDue(10.0);
		bill6.setAmountDue(20.0);
		
	}
}
