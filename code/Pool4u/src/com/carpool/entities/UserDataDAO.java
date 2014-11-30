package com.carpool.entities;

import com.carpool.dao.helper.EntityManagerHelper;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 * A data access object (DAO) providing persistence and search support for
 * UserData entities. Transaction control of the save(), update() and delete()
 * operations must be handled externally by senders of these methods or must be
 * manually added to each of these methods for data to be persisted to the JPA
 * datastore.
 * 
 * @see com.carpool.entities.UserData
 * @author MyEclipse Persistence Tools
 */

public class UserDataDAO implements IUserDataDAO {
	// property constants
	public static final String FIRST_NAME = "firstName";
	public static final String LAST_NAME = "lastName";
	public static final String DISPLAY_NAME = "displayName";
	public static final String GENDER = "gender";
	public static final String PHONE = "phone";
	public static final String EMAIL = "email";

	private EntityManager getEntityManager() {
		return EntityManagerHelper.getEntityManager();
	}

	/**
	 * Perform an initial save of a previously unsaved UserData entity. All
	 * subsequent persist actions of this entity should use the #update()
	 * method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object)
	 * EntityManager#persist} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * UserDataDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            UserData entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(UserData entity) {
		EntityManagerHelper.log("saving UserData instance", Level.INFO, null);
		try {
			getEntityManager().persist(entity);
			EntityManagerHelper.log("save successful", Level.INFO, null);
		} catch (RuntimeException re) {
			EntityManagerHelper.log("save failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Delete a persistent UserData entity. This operation must be performed
	 * within the a database transaction context for the entity's data to be
	 * permanently deleted from the persistence store, i.e., database. This
	 * method uses the {@link javax.persistence.EntityManager#remove(Object)
	 * EntityManager#delete} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * UserDataDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            UserData entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(UserData entity) {
		EntityManagerHelper.log("deleting UserData instance", Level.INFO, null);
		try {
			entity = getEntityManager().getReference(UserData.class,
					entity.getUserId());
			getEntityManager().remove(entity);
			EntityManagerHelper.log("delete successful", Level.INFO, null);
		} catch (RuntimeException re) {
			EntityManagerHelper.log("delete failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Persist a previously saved UserData entity and return it or a copy of it
	 * to the sender. A copy of the UserData entity parameter is returned when
	 * the JPA persistence mechanism has not previously been tracking the
	 * updated entity. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = UserDataDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            UserData entity to update
	 * @return UserData the persisted UserData entity instance, may not be the
	 *         same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public UserData update(UserData entity) {
		EntityManagerHelper.log("updating UserData instance", Level.INFO, null);
		try {
			UserData result = getEntityManager().merge(entity);
			EntityManagerHelper.log("update successful", Level.INFO, null);
			return result;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("update failed", Level.SEVERE, re);
			throw re;
		}
	}

	public UserData findById(Integer id) {
		EntityManagerHelper.log("finding UserData instance with id: " + id,
				Level.INFO, null);
		try {
			UserData instance = getEntityManager().find(UserData.class, id);
			return instance;
		} catch (RuntimeException re) {
			EntityManagerHelper.log("find failed", Level.SEVERE, re);
			throw re;
		}
	}

	/**
	 * Find all UserData entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the UserData property to query
	 * @param value
	 *            the property value to match
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            number of results to return.
	 * @return List<UserData> found by query
	 */
	@SuppressWarnings("unchecked")
	public List<UserData> findByProperty(String propertyName,
			final Object value, final int... rowStartIdxAndCount) {
		EntityManagerHelper.log("finding UserData instance with property: "
				+ propertyName + ", value: " + value, Level.INFO, null);
		try {
			final String queryString = "select model from UserData model where model."
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

	public List<UserData> findByFirstName(Object firstName,
			int... rowStartIdxAndCount) {
		return findByProperty(FIRST_NAME, firstName, rowStartIdxAndCount);
	}

	public List<UserData> findByLastName(Object lastName,
			int... rowStartIdxAndCount) {
		return findByProperty(LAST_NAME, lastName, rowStartIdxAndCount);
	}

	public List<UserData> findByDisplayName(Object displayName,
			int... rowStartIdxAndCount) {
		return findByProperty(DISPLAY_NAME, displayName, rowStartIdxAndCount);
	}

	public List<UserData> findByGender(Object gender,
			int... rowStartIdxAndCount) {
		return findByProperty(GENDER, gender, rowStartIdxAndCount);
	}

	public List<UserData> findByPhone(Object phone, int... rowStartIdxAndCount) {
		return findByProperty(PHONE, phone, rowStartIdxAndCount);
	}

	public List<UserData> findByEmail(Object email, int... rowStartIdxAndCount) {
		return findByProperty(EMAIL, email, rowStartIdxAndCount);
	}

	/**
	 * Find all UserData entities.
	 * 
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            count of results to return.
	 * @return List<UserData> all UserData entities
	 */
	@SuppressWarnings("unchecked")
	public List<UserData> findAll(final int... rowStartIdxAndCount) {
		EntityManagerHelper.log("finding all UserData instances", Level.INFO,
				null);
		try {
			final String queryString = "select model from UserData model";
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