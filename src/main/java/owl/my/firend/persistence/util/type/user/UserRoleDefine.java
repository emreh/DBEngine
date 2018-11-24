package owl.my.firend.persistence.util.type.user;

public enum UserRoleDefine {

	ROLE_SUPER_ADMIN("ROLE_SUPER_ADMIN"), ROLE_ADMIN("ROLE_ADMIN"), ROLE_SUPER_USER("ROLE_SUPER_USER"), ROLE_USER(
			"ROLE_USER");

	private final String returnRoleDefine;

	UserRoleDefine(String returnRoleDefine) {
		this.returnRoleDefine = returnRoleDefine;
	}

	public String getUserRoleDefineValue() {
		return this.returnRoleDefine;
	}

}