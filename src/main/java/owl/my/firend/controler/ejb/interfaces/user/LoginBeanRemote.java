package owl.my.firend.controler.ejb.interfaces.user;

import javax.ejb.Remote;

import owl.my.firend.persistence.model.user.UserDetails;

@Remote
public interface LoginBeanRemote {
	public UserDetails login(String userName, String password);

	public void logOut(String userName);
}