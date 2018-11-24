package owl.my.firend.controler.ejb.impl.user;

import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import owl.my.firend.controler.ejb.interfaces.user.UserActionRemote;
import owl.my.firend.persistence.model.user.UserDetails;
import owl.my.firend.persistence.util.Utility;
import owl.my.firend.persistence.util.namedQuery.UserNamedQuery;

/**
 * Session Bean implementation class UserAction
 */
@Stateless
@TransactionManagement(TransactionManagementType.CONTAINER)
public class UserAction implements UserActionRemote {

	@PersistenceContext(unitName = "DBEngine")
	private EntityManager entityManager;

	@Resource
	private SessionContext sessionContext;

	@Inject
	private Utility utility;

	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public boolean saveUser(UserDetails userDetails) {
		try {
			entityManager.persist(userDetails);
			return true;
		} catch (Exception e) {
			sessionContext.setRollbackOnly();
			return false;
		}
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public boolean changeUserStatus(boolean changeStatus, String userName) {
		try {
			UserDetails userDetails = findUser(userName);
			userDetails.setEnable(changeStatus);
			return true;
		} catch (Exception e) {
			sessionContext.setRollbackOnly();
			return false;
		}
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public boolean updateUser(UserDetails userDetails) {
		try {
			UserDetails details = findUser(userDetails.getAccount());
			utility.updateUser(details, userDetails);
			return true;
		} catch (Exception e) {
			sessionContext.setRollbackOnly();
			return false;
		}
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public UserDetails findUser(String userName) {
		UserDetails userDetails = new UserDetails();
		try {
			Query query = entityManager
					.createNamedQuery(UserNamedQuery.UserDetails_findUserByAccount.MAIN_NAME.getName());
			query.setParameter(UserNamedQuery.UserDetails_findUserByAccount.ACCOUNT.getName(), userName);
			userDetails = (UserDetails) query.getSingleResult();

			return userDetails;
		} catch (Exception e) {
			sessionContext.setRollbackOnly();
			return userDetails;
		}
	}

	@Override
	@TransactionAttribute(TransactionAttributeType.REQUIRED)
	public boolean delete(String userName) {
		try {
			entityManager.remove(findUser(userName));
			return true;
		} catch (Exception e) {
			sessionContext.setRollbackOnly();
			return false;
		}
	}
}