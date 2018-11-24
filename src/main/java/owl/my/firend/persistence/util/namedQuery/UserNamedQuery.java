package owl.my.firend.persistence.util.namedQuery;

public enum UserNamedQuery {
	SAVE;
	public enum UserDetails_findUserByAccount {
		MAIN_NAME("UserDetails.findUserByAccount"), ACCOUNT(":account");

		private String name;

		UserDetails_findUserByAccount(String name) {
			this.name = name;
		}

		public String getName() {
			return this.name;
		}
	};

	public enum UserDetails_changeStatusUser {
		MAIN_NAME("UserDetails.changeStatusUser"), ENABLE(":enable"), ACCOUNT(":account");

		private String name;

		UserDetails_changeStatusUser(String name) {
			this.name = name;
		}

		public String getName() {
			return this.name;
		}
	}

}