package owl.my.firend.controler.ejb.interfaces.user;

import javax.ejb.Remote;

import owl.my.firend.persistence.model.user.UserDetails;

@Remote
public interface UserActionRemote {

	public boolean saveUser(UserDetails userDetails);

	public boolean changeUserStatus(boolean changeStatus, String userName);

	public boolean updateUser(UserDetails userDetails);
	
	public UserDetails findUser(String userName);
	
	public boolean delete(String userName);

}