package com.carpool.entities;

import com.carpool.dao.helper.EntityManagerHelper;
import java.sql.Timestamp;
import java.util.List;
import java.util.logging.Level;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 * A data access object (DAO) providing persistence and search support for
 * UserCredentials entities. Transaction control of the save(), update() and
 * delete() operations must be handled externally by senders of these methods or
 * must be manually added to each of these methods for data to be persisted to
 * the JPA datastore.
 * 
 * @see com.carpool.entities.UserCredentials
 * @author MyEclipse Persistence Tools
 */

public class UserCredentialsDAO implements IUserCredentialsDAO {
	// property constants
	public static final String USER_NAME = "userName";
	public static final String PASSWORD = "password";
	public static final String IS_AUTHORIZED = "isAuthorized";
	public static final String LOGIN_ATTEMPT_COUNT = "loginAttemptCount";
	public static final String IS_ACTIVE = "isActive";
	public static final String USER_CREDITS = "userCredits";

	private EntityManager getEntityManager() {
		return EntityManagerHelper.getEntityManager();
	}

	/**
	 * Perform an initial save of a previously unsaved UserCredentials entity.
	 * All subsequent persist actions of this entity should use the #update()
	 * method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object)
	 * EntityManager#persist} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * UserCredentialsDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            UserCredentials entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(UserCredentials entity) {
		EntityManagerHelper.log("saving UserCredentials instance", Level.INFO,
				null);
		try {
			getEntityManager().persist(entity);
			EntityManagerHelper.log("save successful", Level.INFO, null);
		} catch (RuntimeException re) {
			EntityManagerHelper.log("save failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Delete a persistent UserCredentials entity. This operation must be
	 * performed within the a database transaction context for the entity's data
	 * to be permanently deleted from the persistence store, i.e., database.
	 * This method uses the
	 * {@link javax.persistence.EntityManager#remove(Object)
	 * EntityManager#delete} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * UserCredentialsDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            UserCredentials entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(UserCredentials entity) {
		EntityManagerHelper.log("deleting UserCredentials instance",
				Level.INFO, null);
		try {
			entity = getEntityManager().getReference(UserCredentials.class,
					entity.getUserId());
			getEntityManager().remove(entity);
			EntityManagerHelper.log("delete successful", Level.INFO, null);
		} catch (RuntimeException re) {
			EntityManagerHelper.log("delete failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Persist a previously saved UserCredentials entity and return it or a copy
	 * of it to the sender. A copy of the UserCredentials entity parameter is
	 * returned when the JPA persistence mechanism has not previously been
	 * tracking the updated entity. This operation must be performed within the
	 * a database transaction context for the entity's data to be permanently
	 * saved to the persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = UserCredentialsDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            UserCredentials entity to update
	 * @return UserCredentials the persisted UserCredentials entity instance,
	 *         may not be the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public UserCredentials update(UserCredentials entity) {
		EntityManagerHelper.log("updating UserCredentials instance",
				Level.INFO, null);
		try {
			UserCredentials result = getEntityManager().merge(entity);
			EntityManagerHelper.log("update successful", Level.INFO, null);
			return result;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("update failed", Level.SEVERE, re);
			throw re;
		}
	}

	public UserCredentials findById(Integer id) {
		EntityManagerHelper.log("finding UserCredentials instance with id: "
				+ id, Level.INFO, null);
		try {
			UserCredentials instance = getEntityManager().find(
					UserCredentials.class, id);
			return instance;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Find all UserCredentials entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the UserCredentials property to query
	 * @param value
	 *            the property value to match
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            number of results to return.
	 * @return List<UserCredentials> found by query
	 */
	@SuppressWarnings("unchecked")
	public List<UserCredentials> findByProperty(String propertyName,
			final Object value, final int... rowStartIdxAndCount) {
		EntityManagerHelper.log(
				"finding UserCredentials instance with property: "
						+ propertyName + ", value: " + value, Level.INFO, null);
		try {
			final String queryString = "select model from UserCredentials model where model."
					+ propertyName + "= :propertyValue";
			Query query = getEntityManager().createQuery(queryString);
			query.setParameter("propertyValue", value);
			if (rowStartIdxAndCount != null && rowStartIdxAndCount.length > 0) {
				int rowStartIdx = Math.max(0, rowStartIdxAndCount[0]);
				if (rowStartIdx > 0) {
					query.setFirstResult(rowStartIdx);
				}

				if (rowStartIdxAndCount.length > 1) {
					int rowCount = Math.max(0, rowStartIdxAndCount[1]);
					if (rowCount > 0) {
						query.setMaxResults(rowCount);
					}
				}
			}
			return query.getResultList();
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find by property name failed",
					Level.SEVERE, re);
			throw re;
		}
	}

	public List<UserCredentials> findByUserName(Object userName,
			int... rowStartIdxAndCount) {
		return findByProperty(USER_NAME, userName, rowStartIdxAndCount);
	}

	public List<UserCredentials> findByPassword(Object password,
			int... rowStartIdxAndCount) {
		return findByProperty(PASSWORD, password, rowStartIdxAndCount);
	}

	public List<UserCredentials> findByIsAuthorized(Object isAuthorized,
			int... rowStartIdxAndCount) {
		return findByProperty(IS_AUTHORIZED, isAuthorized, rowStartIdxAndCount);
	}

	public List<UserCredentials> findByLoginAttemptCount(
			Object loginAttemptCount, int... rowStartIdxAndCount) {
		return findByProperty(LOGIN_ATTEMPT_COUNT, loginAttemptCount,
				rowStartIdxAndCount);
	}

	public List<UserCredentials> findByIsActive(Object isActive,
			int... rowStartIdxAndCount) {
		return findByProperty(IS_ACTIVE, isActive, rowStartIdxAndCount);
	}

	public List<UserCredentials> findByUserCredits(Object userCredits,
			int... rowStartIdxAndCount) {
		return findByProperty(USER_CREDITS, userCredits, rowStartIdxAndCount);
	}

	/**
	 * Find all UserCredentials entities.
	 * 
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            count of results to return.
	 * @return List<UserCredentials> all UserCredentials entities
	 */
	@SuppressWarnings("unchecked")
	public List<UserCredentials> findAll(final int... rowStartIdxAndCount) {
		EntityManagerHelper.log("finding all UserCredentials instances",
				Level.INFO, null);
		try {
			final String queryString = "select model from UserCredentials model";
			Query query = getEntityManager().createQuery(queryString);
			if (rowStartIdxAndCount != null && rowStartIdxAndCount.length > 0) {
				int rowStartIdx = Math.max(0, rowStartIdxAndCount[0]);
				if (rowStartIdx > 0) {
					query.setFirstResult(rowStartIdx);
				}

				if (rowStartIdxAndCount.length > 1) {
					int rowCount = Math.max(0, rowStartIdxAndCount[1]);
					if (rowCount > 0) {
						query.setMaxResults(rowCount);
					}
				}
			}
			return query.getResultList();
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find all failed", Level.SEVERE, re);
			throw re;
		}
	}

}