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

package com.liferay.opensocial.admin.portlet;

import java.io.InputStream;
import java.util.List;

import javax.portlet.ActionRequest;
import javax.portlet.ActionResponse;

import com.liferay.opensocial.GadgetURLException;
import com.liferay.opensocial.model.Gadget;
import com.liferay.opensocial.service.GadgetLocalServiceUtil;
import com.liferay.opensocial.service.GadgetServiceUtil;
import com.liferay.opensocial.service.permission.GadgetPermission;
import com.liferay.opensocial.shindig.util.ShindigUtil;
import com.liferay.opensocial.util.ActionKeys;
import com.liferay.portal.kernel.dao.orm.QueryUtil;
import com.liferay.portal.kernel.repository.model.FileEntry;
import com.liferay.portal.kernel.repository.model.Folder;
import com.liferay.portal.kernel.upload.UploadPortletRequest;
import com.liferay.portal.kernel.util.Constants;
import com.liferay.portal.kernel.util.ParamUtil;
import com.liferay.portal.kernel.util.StreamUtil;
import com.liferay.portal.kernel.util.StringPool;
import com.liferay.portal.kernel.util.StringUtil;
import com.liferay.portal.kernel.util.WebKeys;
import com.liferay.portal.security.permission.PermissionChecker;
import com.liferay.portal.service.ServiceContext;
import com.liferay.portal.service.ServiceContextFactory;
import com.liferay.portal.service.UserLocalServiceUtil;
import com.liferay.portal.theme.ThemeDisplay;
import com.liferay.portal.util.PortalUtil;
import com.liferay.portlet.documentlibrary.model.DLFileEntry;
import com.liferay.portlet.documentlibrary.service.DLAppLocalServiceUtil;
import com.liferay.util.bridges.mvc.MVCPortlet;

/**
 * @author Michael Young
 * @author Dennis Ju
 */
public class AdminPortlet extends MVCPortlet {

	public void deleteGadget(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		long gadgetId = ParamUtil.getLong(actionRequest, "gadgetId");

		ServiceContext serviceContext = ServiceContextFactory.getInstance(
			Gadget.class.getName(), actionRequest);

		GadgetServiceUtil.deleteGadget(gadgetId, serviceContext);
	}

	public void refreshGadgets(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		long[] gadgetIds = ParamUtil.getLongValues(actionRequest, "gadgetId");

		if (gadgetIds.length == 0) {
			List<Gadget> gadgets = GadgetLocalServiceUtil.getGadgets(
				QueryUtil.ALL_POS, QueryUtil.ALL_POS);

			for (Gadget gadget : gadgets) {
				ShindigUtil.clearGadgetSpecCache(gadget.getUrl());
			}
		}
		else {
			for (long gadgetId : gadgetIds) {
				Gadget gadget = GadgetLocalServiceUtil.getGadget(gadgetId);

				ShindigUtil.clearGadgetSpecCache(gadget.getUrl());
			}
		}
	}

	public void updateGadget(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		UploadPortletRequest uploadPortletRequest =
			PortalUtil.getUploadPortletRequest(actionRequest);
			
		String cmd = ParamUtil.getString(uploadPortletRequest, Constants.CMD);

		if (cmd.equals(Constants.ADD)) {
			doAddGadget(uploadPortletRequest, actionResponse);
		}
		else if (cmd.equals(Constants.UPDATE)) {
			doUpdateGadget(uploadPortletRequest, actionResponse);
		}
	}

	public void updateOAuthConsumers(
			ActionRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)actionRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		PermissionChecker permissionChecker =
			themeDisplay.getPermissionChecker();

		long gadgetId = ParamUtil.getLong(actionRequest, "gadgetId");

		GadgetPermission.check(
			permissionChecker, themeDisplay.getScopeGroupId(), gadgetId,
			ActionKeys.UPDATE);

		ShindigUtil.updateOAuthConsumers(actionRequest, actionResponse);
	}

	protected Gadget doAddGadget(
			UploadPortletRequest uploadPortletRequest, ActionResponse actionResponse)
		throws Exception {

		ThemeDisplay themeDisplay = (ThemeDisplay)uploadPortletRequest.getAttribute(
			WebKeys.THEME_DISPLAY);

		String sourceFileName = uploadPortletRequest.getFileName("file");
		
		InputStream inputStream = null;

		FileEntry fileEntry = null;

		try {
			String contentType = uploadPortletRequest.getContentType("file");

			inputStream = uploadPortletRequest.getFileAsStream("file");
			
			String content = StringUtil.read(inputStream);
			
			if (!ShindigUtil.isContentValid(content)) {
				throw new GadgetURLException();
			}

			ServiceContext serviceContext = ServiceContextFactory.getInstance(
				DLFileEntry.class.getName(), uploadPortletRequest);

			long companyId = themeDisplay.getCompanyId();
			long repositoryId = themeDisplay.getScopeGroupId();
			
			Folder folder = ShindigUtil.getGadgetEditorRootFolder(
				companyId, repositoryId);

			long defaultUserId = UserLocalServiceUtil.getDefaultUserId(companyId);
			
			fileEntry = DLAppLocalServiceUtil.addFileEntry(
				defaultUserId,
				repositoryId, folder.getFolderId(), sourceFileName, contentType, sourceFileName,
				StringPool.BLANK, StringPool.BLANK, content.getBytes(), serviceContext);
		}
		catch (Exception e) {
			System.out.println(e);
		}
		finally {
			StreamUtil.cleanUp(inputStream);
		}

		String url = StringPool.BLANK;
		
		if (fileEntry != null) {
			String portalURL = PortalUtil.getPortalURL(themeDisplay);

			url = ShindigUtil.getFileEntryURL(portalURL, fileEntry.getFileEntryId());
		}
		else {
			url = ParamUtil.getString(uploadPortletRequest, "url");			
		}
		
		String portletCategoryNames = ParamUtil.getString(
				uploadPortletRequest, "portletCategoryNames");

		ServiceContext serviceContext = ServiceContextFactory.getInstance(
			Gadget.class.getName(), uploadPortletRequest);

		Gadget gadget = GadgetServiceUtil.addGadget(
			themeDisplay.getCompanyId(), url, portletCategoryNames,
			serviceContext);

		return gadget;
	}

	protected void doUpdateGadget(
			UploadPortletRequest actionRequest, ActionResponse actionResponse)
		throws Exception {

		long gadgetId = ParamUtil.getLong(actionRequest, "gadgetId");

		String portletCategoryNames = ParamUtil.getString(
			actionRequest, "portletCategoryNames");

		ServiceContext serviceContext = ServiceContextFactory.getInstance(
			Gadget.class.getName(), actionRequest);

		GadgetServiceUtil.updateGadget(
			gadgetId, portletCategoryNames, serviceContext);
	}

}