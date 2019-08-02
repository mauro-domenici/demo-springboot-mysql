package it.ats.springboot.provaMySQL.testequals;

import org.junit.Assert;
import org.junit.Test;
import it.ats.springboot.provaMySQL.user.User;

public class TestEquals {

	@Test
	public void testEquals() {
		
		User user1 = new User(1, "Mauro", "mauro@esempio.it");
		User user2 = new User(2,"Elena", "elena@esempio.it");
		User user3 = new User(1, "John", "john@esempio.it");
		
		System.out.println(user1);
		System.out.println(user2);
		
		Assert.assertFalse(user1.equals(user2));
		Assert.assertTrue(user1.equals(user3));
	}

}
