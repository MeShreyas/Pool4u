package com.carpool.entities;

import java.util.List;
import java.util.Set;

/**
 * Interface for StateDAO.
 * 
 * @author MyEclipse Persistence Tools
 */

public interface IStateDAO {
	/**
	 * Perform an initial save of a previously unsaved State entity. All
	 * subsequent persist actions of this entity should use the #update()
	 * method. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#persist(Object)
	 * EntityManager#persist} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * IStateDAO.save(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            State entity to persist
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void save(State entity);

	/**
	 * Delete a persistent State entity. This operation must be performed within
	 * the a database transaction context for the entity's data to be
	 * permanently deleted from the persistence store, i.e., database. This
	 * method uses the {@link javax.persistence.EntityManager#remove(Object)
	 * EntityManager#delete} operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * IStateDAO.delete(entity);
	 * EntityManagerHelper.commit();
	 * entity = null;
	 * </pre>
	 * 
	 * @param entity
	 *            State entity to delete
	 * @throws RuntimeException
	 *             when the operation fails
	 */
	public void delete(State entity);

	/**
	 * Persist a previously saved State entity and return it or a copy of it to
	 * the sender. A copy of the State entity parameter is returned when the JPA
	 * persistence mechanism has not previously been tracking the updated
	 * entity. This operation must be performed within the a database
	 * transaction context for the entity's data to be permanently saved to the
	 * persistence store, i.e., database. This method uses the
	 * {@link javax.persistence.EntityManager#merge(Object) EntityManager#merge}
	 * operation.
	 * 
	 * <pre>
	 * EntityManagerHelper.beginTransaction();
	 * entity = IStateDAO.update(entity);
	 * EntityManagerHelper.commit();
	 * </pre>
	 * 
	 * @param entity
	 *            State entity to update
	 * @return State the persisted State entity instance, may not be the same
	 * @throws RuntimeException
	 *             if the operation fails
	 */
	public State update(State entity);

	public State findById(Integer id);

	/**
	 * Find all State entities with a specific property value.
	 * 
	 * @param propertyName
	 *            the name of the State property to query
	 * @param value
	 *            the property value to match
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            count of results to return.
	 * @return List<State> found by query
	 */
	public List<State> findByProperty(String propertyName, Object value,
			int... rowStartIdxAndCount);

	public List<State> findByStateName(Object stateName,
			int... rowStartIdxAndCount);

	/**
	 * Find all State entities.
	 * 
	 * @param rowStartIdxAndCount
	 *            Optional int varargs. rowStartIdxAndCount[0] specifies the the
	 *            row index in the query result-set to begin collecting the
	 *            results. rowStartIdxAndCount[1] specifies the the maximum
	 *            count of results to return.
	 * @return List<State> all State entities
	 */
	public List<State> findAll(int... rowStartIdxAndCount);
}