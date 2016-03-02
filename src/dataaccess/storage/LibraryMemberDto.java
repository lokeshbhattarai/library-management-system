package dataaccess.storage;

import java.io.Serializable;
import java.util.UUID;

public class LibraryMemberDto /*extends PersonDto*/ implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String memberId;
	private CheckoutRecordDto checkoutRecordDto;
	private String firstName;
	private String lastName;
	private AddressDto addressDto;
	private String phoneNumber;

	public String getMemberId() {
		return memberId;
	}

	public void setMemberId(String memberId) {
		this.memberId = memberId;
	}

	
	public LibraryMemberDto(String memberId, String firstName, String lastName, AddressDto address, String phoneNo) {
		//super(firstName, lastName, address, phoneNo);
		this.memberId = memberId;
		this.firstName= firstName;
		this.lastName = lastName;
		this.addressDto = address;
		this.phoneNumber = phoneNo;
	}

	/*@Override
	public boolean equals(Object obj) {
		LibraryMemberDto member = (LibraryMemberDto)obj;
		String ida = this.memberId.toString();
		String idb = member.memberId.toString();
		if(ida.equals(idb))
			return true;
		else
			return false;
	}*/

	public CheckoutRecordDto getCheckoutRecordDto() {
		return checkoutRecordDto;
	}

	public void setCheckoutRecordDto(CheckoutRecordDto checkoutRecordDto) {
		this.checkoutRecordDto = checkoutRecordDto;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public AddressDto getAddressDto() {
		return addressDto;
	}

	public void setAddressDto(AddressDto addressDto) {
		this.addressDto = addressDto;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}
	
	
}
