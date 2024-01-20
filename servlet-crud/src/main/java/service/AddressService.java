package service;

import javax.servlet.http.HttpServletRequest;

import entity.Address;
import utility.CustomerException;

public class AddressService {
	public static Address getAddress(HttpServletRequest request) {
		Address address = null;
		try {
			int houseNumber = Integer.parseInt(request.getParameter("h_no"));
			int pincode = Integer.parseInt(request.getParameter("pincode"));
			String city = request.getParameter("city");
			String state = request.getParameter("state");
			String country = request.getParameter("country");
			String mainAddress = request.getParameter("main_address");
			address = new Address(houseNumber, mainAddress, pincode, city, state, country);
		} catch (NumberFormatException e) {
			throw new CustomerException("cannot get Address " + e.getMessage());
		}
		return address;

	}

}
