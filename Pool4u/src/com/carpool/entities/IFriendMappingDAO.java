package com.carpool.entities;

import java.util.List;

/**
 * Interface for FriendMappingDAO.
 * 
 * @author MyEclipse Persistence Tools
 */

public interface IFriendMappingDAO {
	/**
	 * Perform an initial save of a previously unsaved FriendMapping entity. All
	 * subsequent persist actions of this entity should use the #update()
	 * method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object)
	 * EntityManager#persist} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * IFriendMappingDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            FriendMapping entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(FriendMapping entity);

	/**
	 * Delete a persistent FriendMapping entity. This operation must be
	 * performed within the a database transaction context for the entity's data
	 * to be permanently deleted from the persistence store, i.e., database.
	 * This method uses the
	 * {@link javax.persistence.EntityManager#remove(Object)
	 * EntityManager#delete} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * IFriendMappingDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            FriendMapping entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(FriendMapping entity);

	/**
	 * Persist a previously saved FriendMapping entity and return it or a copy
	 * of it to the sender. A copy of the FriendMapping entity parameter is
	 * returned when the JPA persistence mechanism has not previously been
	 * tracking the updated entity. This operation must be performed within the
	 * a database transaction context for the entity's data to be permanently
	 * saved to the persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = IFriendMappingDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            FriendMapping entity to update
	 * @return FriendMapping the persisted FriendMapping entity instance, may
	 *         not be the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public FriendMapping update(FriendMapping entity);

	public FriendMapping findById(FriendMappingId id);

	/**
	 * Find all FriendMapping entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the FriendMapping property to query
	 * @param value
	 *            the property value to match
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            count of results to return.
	 * @return List<FriendMapping> found by query
	 */
	public List<FriendMapping> findByProperty(String propertyName,
			Object value, int... rowStartIdxAndCount);

	/**
	 * Find all FriendMapping entities.
	 * 
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            count of results to return.
	 * @return List<FriendMapping> all FriendMapping entities
	 */
	public List<FriendMapping> findAll(int... rowStartIdxAndCount);
}