/**
 * Copyright (c) 2000-2012 Liferay, Inc. All rights reserved.
 *
 * This library is free software; you can redistribute it and/or modify it under
 * the terms of the GNU Lesser General Public License as published by the Free
 * Software Foundation; either version 2.1 of the License, or (at your option)
 * any later version.
 *
 * This library is distributed in the hope that it will be useful, but WITHOUT
 * ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS
 * FOR A PARTICULAR PURPOSE. See the GNU Lesser General Public License for more
 * details.
 */

package com.liferay.opensocial.service.persistence;

import com.liferay.opensocial.model.OAuthConsumer;

import com.liferay.portal.service.persistence.BasePersistence;

/**
 * The persistence interface for the o auth consumer service.
 *
 * <p>
 * Caching information and settings can be found in <code>portal.properties</code>
 * </p>
 *
 * @author Brian Wing Shun Chan
 * @see OAuthConsumerPersistenceImpl
 * @see OAuthConsumerUtil
 * @generated
 */
public interface OAuthConsumerPersistence extends BasePersistence<OAuthConsumer> {
	/*
	 * NOTE FOR DEVELOPERS:
	 *
	 * Never modify or reference this interface directly. Always use {@link OAuthConsumerUtil} to access the o auth consumer persistence. Modify <code>service.xml</code> and rerun ServiceBuilder to regenerate this interface.
	 */

	/**
	* Caches the o auth consumer in the entity cache if it is enabled.
	*
	* @param oAuthConsumer the o auth consumer
	*/
	public void cacheResult(
		com.liferay.opensocial.model.OAuthConsumer oAuthConsumer);

	/**
	* Caches the o auth consumers in the entity cache if it is enabled.
	*
	* @param oAuthConsumers the o auth consumers
	*/
	public void cacheResult(
		java.util.List<com.liferay.opensocial.model.OAuthConsumer> oAuthConsumers);

	/**
	* Creates a new o auth consumer with the primary key. Does not add the o auth consumer to the database.
	*
	* @param oAuthConsumerId the primary key for the new o auth consumer
	* @return the new o auth consumer
	*/
	public com.liferay.opensocial.model.OAuthConsumer create(
		long oAuthConsumerId);

	/**
	* Removes the o auth consumer with the primary key from the database. Also notifies the appropriate model listeners.
	*
	* @param oAuthConsumerId the primary key of the o auth consumer
	* @return the o auth consumer that was removed
	* @throws com.liferay.opensocial.NoSuchOAuthConsumerException if a o auth consumer with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.opensocial.model.OAuthConsumer remove(
		long oAuthConsumerId)
		throws com.liferay.opensocial.NoSuchOAuthConsumerException,
			com.liferay.portal.kernel.exception.SystemException;

	public com.liferay.opensocial.model.OAuthConsumer updateImpl(
		com.liferay.opensocial.model.OAuthConsumer oAuthConsumer, boolean merge)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the o auth consumer with the primary key or throws a {@link com.liferay.opensocial.NoSuchOAuthConsumerException} if it could not be found.
	*
	* @param oAuthConsumerId the primary key of the o auth consumer
	* @return the o auth consumer
	* @throws com.liferay.opensocial.NoSuchOAuthConsumerException if a o auth consumer with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.opensocial.model.OAuthConsumer findByPrimaryKey(
		long oAuthConsumerId)
		throws com.liferay.opensocial.NoSuchOAuthConsumerException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the o auth consumer with the primary key or returns <code>null</code> if it could not be found.
	*
	* @param oAuthConsumerId the primary key of the o auth consumer
	* @return the o auth consumer, or <code>null</code> if a o auth consumer with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.opensocial.model.OAuthConsumer fetchByPrimaryKey(
		long oAuthConsumerId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the o auth consumers where moduleId = &#63;.
	*
	* @param moduleId the module ID
	* @return the matching o auth consumers
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.opensocial.model.OAuthConsumer> findByModuleId(
		long moduleId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the o auth consumers where moduleId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param moduleId the module ID
	* @param start the lower bound of the range of o auth consumers
	* @param end the upper bound of the range of o auth consumers (not inclusive)
	* @return the range of matching o auth consumers
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.opensocial.model.OAuthConsumer> findByModuleId(
		long moduleId, int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the o auth consumers where moduleId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param moduleId the module ID
	* @param start the lower bound of the range of o auth consumers
	* @param end the upper bound of the range of o auth consumers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of matching o auth consumers
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.opensocial.model.OAuthConsumer> findByModuleId(
		long moduleId, int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the first o auth consumer in the ordered set where moduleId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param moduleId the module ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the first matching o auth consumer
	* @throws com.liferay.opensocial.NoSuchOAuthConsumerException if a matching o auth consumer could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.opensocial.model.OAuthConsumer findByModuleId_First(
		long moduleId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.opensocial.NoSuchOAuthConsumerException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the last o auth consumer in the ordered set where moduleId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param moduleId the module ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the last matching o auth consumer
	* @throws com.liferay.opensocial.NoSuchOAuthConsumerException if a matching o auth consumer could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.opensocial.model.OAuthConsumer findByModuleId_Last(
		long moduleId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.opensocial.NoSuchOAuthConsumerException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the o auth consumers before and after the current o auth consumer in the ordered set where moduleId = &#63;.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param oAuthConsumerId the primary key of the current o auth consumer
	* @param moduleId the module ID
	* @param orderByComparator the comparator to order the set by (optionally <code>null</code>)
	* @return the previous, current, and next o auth consumer
	* @throws com.liferay.opensocial.NoSuchOAuthConsumerException if a o auth consumer with the primary key could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.opensocial.model.OAuthConsumer[] findByModuleId_PrevAndNext(
		long oAuthConsumerId, long moduleId,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.opensocial.NoSuchOAuthConsumerException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the o auth consumer where moduleId = &#63; and serviceName = &#63; or throws a {@link com.liferay.opensocial.NoSuchOAuthConsumerException} if it could not be found.
	*
	* @param moduleId the module ID
	* @param serviceName the service name
	* @return the matching o auth consumer
	* @throws com.liferay.opensocial.NoSuchOAuthConsumerException if a matching o auth consumer could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.opensocial.model.OAuthConsumer findByM_S(long moduleId,
		java.lang.String serviceName)
		throws com.liferay.opensocial.NoSuchOAuthConsumerException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the o auth consumer where moduleId = &#63; and serviceName = &#63; or returns <code>null</code> if it could not be found. Uses the finder cache.
	*
	* @param moduleId the module ID
	* @param serviceName the service name
	* @return the matching o auth consumer, or <code>null</code> if a matching o auth consumer could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.opensocial.model.OAuthConsumer fetchByM_S(
		long moduleId, java.lang.String serviceName)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the o auth consumer where moduleId = &#63; and serviceName = &#63; or returns <code>null</code> if it could not be found, optionally using the finder cache.
	*
	* @param moduleId the module ID
	* @param serviceName the service name
	* @param retrieveFromCache whether to use the finder cache
	* @return the matching o auth consumer, or <code>null</code> if a matching o auth consumer could not be found
	* @throws SystemException if a system exception occurred
	*/
	public com.liferay.opensocial.model.OAuthConsumer fetchByM_S(
		long moduleId, java.lang.String serviceName, boolean retrieveFromCache)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns all the o auth consumers.
	*
	* @return the o auth consumers
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.opensocial.model.OAuthConsumer> findAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns a range of all the o auth consumers.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of o auth consumers
	* @param end the upper bound of the range of o auth consumers (not inclusive)
	* @return the range of o auth consumers
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.opensocial.model.OAuthConsumer> findAll(
		int start, int end)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns an ordered range of all the o auth consumers.
	*
	* <p>
	* Useful when paginating results. Returns a maximum of <code>end - start</code> instances. <code>start</code> and <code>end</code> are not primary keys, they are indexes in the result set. Thus, <code>0</code> refers to the first result in the set. Setting both <code>start</code> and <code>end</code> to {@link com.liferay.portal.kernel.dao.orm.QueryUtil#ALL_POS} will return the full result set.
	* </p>
	*
	* @param start the lower bound of the range of o auth consumers
	* @param end the upper bound of the range of o auth consumers (not inclusive)
	* @param orderByComparator the comparator to order the results by (optionally <code>null</code>)
	* @return the ordered range of o auth consumers
	* @throws SystemException if a system exception occurred
	*/
	public java.util.List<com.liferay.opensocial.model.OAuthConsumer> findAll(
		int start, int end,
		com.liferay.portal.kernel.util.OrderByComparator orderByComparator)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the o auth consumers where moduleId = &#63; from the database.
	*
	* @param moduleId the module ID
	* @throws SystemException if a system exception occurred
	*/
	public void removeByModuleId(long moduleId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes the o auth consumer where moduleId = &#63; and serviceName = &#63; from the database.
	*
	* @param moduleId the module ID
	* @param serviceName the service name
	* @throws SystemException if a system exception occurred
	*/
	public void removeByM_S(long moduleId, java.lang.String serviceName)
		throws com.liferay.opensocial.NoSuchOAuthConsumerException,
			com.liferay.portal.kernel.exception.SystemException;

	/**
	* Removes all the o auth consumers from the database.
	*
	* @throws SystemException if a system exception occurred
	*/
	public void removeAll()
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of o auth consumers where moduleId = &#63;.
	*
	* @param moduleId the module ID
	* @return the number of matching o auth consumers
	* @throws SystemException if a system exception occurred
	*/
	public int countByModuleId(long moduleId)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of o auth consumers where moduleId = &#63; and serviceName = &#63;.
	*
	* @param moduleId the module ID
	* @param serviceName the service name
	* @return the number of matching o auth consumers
	* @throws SystemException if a system exception occurred
	*/
	public int countByM_S(long moduleId, java.lang.String serviceName)
		throws com.liferay.portal.kernel.exception.SystemException;

	/**
	* Returns the number of o auth consumers.
	*
	* @return the number of o auth consumers
	* @throws SystemException if a system exception occurred
	*/
	public int countAll()
		throws com.liferay.portal.kernel.exception.SystemException;
}