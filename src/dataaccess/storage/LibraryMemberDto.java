package dataaccess.storage;

import java.util.UUID;

public class LibraryMemberDto extends PersonDto {
	UUID memberId;

	public UUID getMemberId() {
		return memberId;
	}

	public void setMemberId(UUID memberId) {
		this.memberId = memberId;
	}

	public LibraryMemberDto(String firstName, String lastName, AddressDto address, String phoneNo) {
		super(firstName, lastName, address, phoneNo);
		this.memberId = UUID.randomUUID();
	}

	public LibraryMemberDto(UUID memberId, String firstName, String lastName, AddressDto address, String phoneNo) {
		super(firstName, lastName, address, phoneNo);
		this.memberId = memberId;
	}

	@Override
	public boolean equals(Object obj) {
		LibraryMemberDto member = (LibraryMemberDto)obj;
		String ida = this.memberId.toString();
		String idb = member.memberId.toString();
		if(ida.equals(idb))
			return true;
		else
			return false;
	}
}
