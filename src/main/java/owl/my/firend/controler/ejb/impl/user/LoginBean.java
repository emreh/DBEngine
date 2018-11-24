package owl.my.firend.controler.ejb.impl.user;

import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import owl.my.firend.bussiness.annotation.security.HasPerm;
import owl.my.firend.controler.ejb.interfaces.user.LoginBeanRemote;
import owl.my.firend.controler.ejb.interfaces.user.UserActionRemote;
import owl.my.firend.persistence.model.user.UserDetails;

/**
 * Session Bean implementation class LoginBean
 */
@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class LoginBean implements LoginBeanRemote {

	@PersistenceContext(unitName = "DBEngine")
	private EntityManager entityManager;

	@EJB
	private UserActionRemote userActionRemote;

	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	@HasPerm(isEnable = true)
	public UserDetails login(String userName, String password) {
		return new UserDetails();
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public void logOut(String userName) {

	}
}