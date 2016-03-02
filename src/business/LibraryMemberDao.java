package business;

import java.lang.reflect.Member;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import dataaccess.DataReader;
import dataaccess.storage.CheckoutEntryDto;
import dataaccess.storage.LibraryMemberDto;

public class LibraryMemberDao {
	private dataaccess.DataReader reader;

	public LibraryMemberDao(String path){
		reader = new DataReader(path);
	}

	public void addMember(List<LibraryMemberDto> member){
		reader.write(member);
	}
	
	public boolean doesMemberExist(String memberId){
		LibraryMemberDto member = getLibraryMember(memberId);
		if(member == null){
			return false;
		}
		else{
			return true;
		}
	}

	//check if the member can checkout book..if he already has some overdue book then he can not checkout another one
	public boolean canUserCheckoutBook(String memberId){
		return true;
	}
	
	public void updateCheckoutRecord(CheckoutEntryDto checkoutEntryDto, LibraryMemberDto libraryMember){
		
		try {
			List<LibraryMemberDto> memberList = getMemberList();
			Iterator<LibraryMemberDto> itrMembers = memberList.iterator();
			if(itrMembers.hasNext()){
				while(itrMembers.hasNext()){
					LibraryMemberDto member = itrMembers.next();
					if(member.equals(libraryMember)){
						member.getCheckoutRecordDto().getCheckoutEntryDtos().add(checkoutEntryDto);
					}
				}
			}
			
			addMember(memberList);
		} catch (Exception e) {
		}
		
		
	}
	
	public boolean doesUserExist(String memberId){
		List<LibraryMemberDto> totalMembers;
		boolean doesUserExist = false;
		try {
			totalMembers= getMemberList();
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
		Iterator<LibraryMemberDto> itr = totalMembers.iterator();
		while(itr.hasNext()){
			LibraryMemberDto libraryMemberDto = itr.next();
			if(libraryMemberDto.getMemberId().equals(memberId)) {
				doesUserExist = true;
				break;
			}
		}
		return doesUserExist;
	}

	public List<LibraryMemberDto> getMemberList() throws Exception{
		Object data = reader.read();
		if(data == null){
			return new ArrayList<LibraryMemberDto>();
		}
		else{
			return (List<LibraryMemberDto>)reader.read();
		}
	}
	
	public LibraryMemberDto getLibraryMember(String memberId){
		List<LibraryMemberDto> totalMembers;
		LibraryMemberDto memberAsked = null;;
		try {
			totalMembers= getMemberList();
		} catch (Exception e) {
			e.printStackTrace();
			return memberAsked;
		}
		Iterator<LibraryMemberDto> itr = totalMembers.iterator();
		while(itr.hasNext()){
			LibraryMemberDto libraryMemberDto = itr.next();
			if(libraryMemberDto.getMemberId().equals(memberId)) {
				memberAsked = libraryMemberDto;
				break;
			}
		}
		return memberAsked;
	} 
	
}
