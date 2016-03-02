package business;

import java.util.ArrayList;
import java.util.List;

import dataaccess.DataReader;
import dataaccess.storage.LibraryMemberDto;

public class LibraryMemberDao {
	private dataaccess.DataReader reader;

	public LibraryMemberDao(String path){
		reader = new DataReader(path);
	}

	public void addMember(List<LibraryMemberDto> member){
		reader.write(member);
	}

	//check if the member can checkout book..if he already has some overdue book then he can not checkout another one
	public boolean canUserCheckoutBook(){
		return true;
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
}
