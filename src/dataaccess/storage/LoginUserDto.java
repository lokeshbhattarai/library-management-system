package dataaccess.storage;

public class LoginUserDto {
	String username;
	String password;
	RoleDto role;

	public LoginUserDto(String username, String password, RoleDto role){
		this.username = username;
		this.password = password;
		this.role = role;
	}
}
