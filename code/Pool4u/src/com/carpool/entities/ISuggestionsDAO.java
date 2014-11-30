package com.carpool.entities;

import java.util.List;

/**
 * Interface for SuggestionsDAO.
 * 
 * @author MyEclipse Persistence Tools
 */

public interface ISuggestionsDAO {
	/**
	 * Perform an initial save of a previously unsaved Suggestions entity. All
	 * subsequent persist actions of this entity should use the #update()
	 * method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object)
	 * EntityManager#persist} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * ISuggestionsDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            Suggestions entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(Suggestions entity);

	/**
	 * Delete a persistent Suggestions entity. This operation must be performed
	 * within the a database transaction context for the entity's data to be
	 * permanently deleted from the persistence store, i.e., database. This
	 * method uses the {@link javax.persistence.EntityManager#remove(Object)
	 * EntityManager#delete} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * ISuggestionsDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            Suggestions entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(Suggestions entity);

	/**
	 * Persist a previously saved Suggestions entity and return it or a copy of
	 * it to the sender. A copy of the Suggestions entity parameter is returned
	 * when the JPA persistence mechanism has not previously been tracking the
	 * updated entity. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = ISuggestionsDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            Suggestions entity to update
	 * @return Suggestions the persisted Suggestions entity instance, may not be
	 *         the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public Suggestions update(Suggestions entity);

	public Suggestions findById(Integer id);

	/**
	 * Find all Suggestions entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the Suggestions property to query
	 * @param value
	 *            the property value to match
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            count of results to return.
	 * @return List<Suggestions> found by query
	 */
	public List<Suggestions> findByProperty(String propertyName, Object value,
			int... rowStartIdxAndCount);

	public List<Suggestions> findBySuggestion(Object suggestion,
			int... rowStartIdxAndCount);

	/**
	 * Find all Suggestions entities.
	 * 
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            count of results to return.
	 * @return List<Suggestions> all Suggestions entities
	 */
	public List<Suggestions> findAll(int... rowStartIdxAndCount);
}