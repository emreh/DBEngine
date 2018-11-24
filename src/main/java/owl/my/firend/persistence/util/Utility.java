package owl.my.firend.persistence.util;

import owl.my.firend.persistence.model.user.UserDetails;

public class Utility {

	public void updateUser(UserDetails obj1, UserDetails obj2) {
		obj1.setName((obj2.getName() != null && !obj2.getName().isEmpty()) ? obj2.getName() : obj1.getName());
	}
}