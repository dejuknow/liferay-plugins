/**
 * Copyright (c) 2000-present Liferay, Inc. All rights reserved.
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

package com.liferay.sync.hook.security.auth;

import com.liferay.portal.kernel.log.Log;
import com.liferay.portal.kernel.log.LogFactoryUtil;
import com.liferay.portal.kernel.util.PortalClassLoaderUtil;
import com.liferay.portal.kernel.util.ReflectionUtil;
import com.liferay.portal.model.User;
import com.liferay.portal.security.auth.AuthException;
import com.liferay.portal.security.auth.Authenticator;
import com.liferay.portal.service.UserLocalServiceUtil;

import java.lang.reflect.Method;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Shinn Lok
 */
public class SyncAuth implements Authenticator {

	@Override
	public int authenticateByEmailAddress(
			long companyId, String emailAddress, String password,
			Map<String, String[]> headerMap, Map<String, String[]> parameterMap)
		throws AuthException {

		try {
			User user = UserLocalServiceUtil.fetchUserByEmailAddress(
				companyId, emailAddress);

			if (user == null) {
				return Authenticator.DNE;
			}

			return authenticate(password, user.getPassword());
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new AuthException(e);
		}
	}

	@Override
	public int authenticateByScreenName(
			long companyId, String screenName, String password,
			Map<String, String[]> headerMap, Map<String, String[]> parameterMap)
		throws AuthException {

		try {
			User user = UserLocalServiceUtil.fetchUserByScreenName(
				companyId, screenName);

			if (user == null) {
				return Authenticator.DNE;
			}

			return authenticate(password, user.getPassword());
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new AuthException(e);
		}
	}

	@Override
	public int authenticateByUserId(
			long companyId, long userId, String password,
			Map<String, String[]> headerMap, Map<String, String[]> parameterMap)
		throws AuthException {

		try {
			User user = UserLocalServiceUtil.fetchUserById(userId);

			if (user == null) {
				return Authenticator.DNE;
			}

			return authenticate(password, user.getPassword());
		}
		catch (Exception e) {
			_log.error(e, e);

			throw new AuthException(e);
		}
	}

	protected int authenticate(String plainTextPassword, String userPassword)
		throws Exception {

		String encryptedPassword = _passwords.get(plainTextPassword);

		if (encryptedPassword == null) {
			Thread currentThread = Thread.currentThread();

			ClassLoader contextClassLoader =
				currentThread.getContextClassLoader();

			ClassLoader portalClassLoader =
				PortalClassLoaderUtil.getClassLoader();

			try {
				currentThread.setContextClassLoader(portalClassLoader);

				Class<?> clazz = portalClassLoader.loadClass(
					"com.liferay.portal.security.pwd.PasswordEncryptorUtil");

				Object object = clazz.newInstance();

				Method method = ReflectionUtil.getDeclaredMethod(
					clazz, "encrypt", String.class, String.class);

				encryptedPassword = (String)method.invoke(
					object, plainTextPassword, userPassword);
			}
			finally {
				currentThread.setContextClassLoader(contextClassLoader);
			}

			_passwords.put(userPassword, encryptedPassword);
		}

		if (encryptedPassword.equals(userPassword)) {
			return Authenticator.SKIP_LIFERAY_CHECK;
		}

		return Authenticator.SUCCESS;
	}

	private static Log _log = LogFactoryUtil.getLog(SyncAuth.class);

	private static Map<String, String> _passwords =
		new ConcurrentHashMap<String, String>();

}