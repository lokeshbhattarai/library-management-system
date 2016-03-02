package business;

import java.util.ArrayList;
import java.util.List;

import dataaccess.storage.LoginUserDto;import dataaccess.storage.RoleDto;

public class LoginDao {
	List<LoginUserDto> users;

	public LoginDao(){
		users = new ArrayList<LoginUserDto>();
		users.add(new LoginUserDto("suraj", "suraj", RoleDto.Both));
		users.add(new LoginUserDto("lokesh", "lokesh", RoleDto.Administrator));
		users.add(new LoginUserDto("oriyon", "oriyon", RoleDto.Librarian));
	}

	public LoginUserDto validateUser(String username, String password){
		for (LoginUserDto user : users) {
			if(user.getUsername().equals(username) && user.getPassword().equals(password)){
				return user;
			}
		}
		return null;
	}
}
