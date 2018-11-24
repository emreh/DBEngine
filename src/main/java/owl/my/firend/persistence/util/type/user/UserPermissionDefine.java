package owl.my.firend.persistence.util.type.user;

public enum UserPermissionDefine {
	
	ADMIN("ALL"), ADMIN_INSERT("ADMIN_INSERT:Access"), ADMIN_DELETE("ADMIN_DELETE:Access"), ADMIN_UPDATE(
		    "ADMIN_UPDATE:Access");

	private final String returnPermissionDefine;

	UserPermissionDefine(String returnPermissionDefine) {
		this.returnPermissionDefine = returnPermissionDefine;
	}

	public String getUserPermissionDefineValue() {
		return this.returnPermissionDefine;
	}
}