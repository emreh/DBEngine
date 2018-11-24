package owl.my.firend.persistence.util.type.user;

public enum AddressDefine {

	HOME("HOME"), WORK("WORK"), OTHER("OTHER");

	private final String returnAddressDefine;

	AddressDefine(String returnAddressDefine) {
		this.returnAddressDefine = returnAddressDefine;
	}

	public String getUserAddressDefineValue() {
		return this.returnAddressDefine;
	}

}