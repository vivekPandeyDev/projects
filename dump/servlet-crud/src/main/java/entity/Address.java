package entity;

public class Address {
	private int houseNo;
	private String mainAddress;
	private int pinCode;
	private String city;
	private String state;
	private String country;

	public Address() {
		super();
	}
	

	public Address(int houseNo, String mainAddress, int pinCode, String city, String state, String country) {
		super();
		this.houseNo = houseNo;
		this.mainAddress = mainAddress;
		this.pinCode = pinCode;
		this.city = city;
		this.state = state;
		this.country = country;
	}


	public String getMainAddress() {
		return mainAddress;
	}

	public void setMainAddress(String mainAddress) {
		this.mainAddress = mainAddress;
	}

	public int getPinCode() {
		return pinCode;
	}

	public void setPinCode(int pinCode) {
		this.pinCode = pinCode;
	}

	public int getHouseNo() {
		return houseNo;
	}

	public void setHouseNo(int houseNo) {
		this.houseNo = houseNo;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getState() {
		return state;
	}

	public void setState(String state) {
		this.state = state;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	@Override
	public String toString() {
		return "Address [houseNo=" + houseNo + ", city=" + city + ", state=" + state + ", country=" + country + "]";
	}

}
