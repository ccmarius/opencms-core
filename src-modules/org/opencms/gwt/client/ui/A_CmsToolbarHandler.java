/*
 * File   : $Source: /alkacon/cvs/opencms/src-modules/org/opencms/gwt/client/ui/Attic/A_CmsToolbarHandler.java,v $
 * Date   : $Date: 2011/05/27 14:51:46 $
 * Version: $Revision: 1.5 $
 *
 * This library is part of OpenCms -
 * the Open Source Content Management System
 *
 * Copyright (C) 2002 - 2009 Alkacon Software (http://www.alkacon.com)
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 2.1 of the License, or (at your option) any later version.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE. See the GNU
 * Lesser General Public License for more details.
 *
 * For further information about Alkacon Software, please see the
 * company website: http://www.alkacon.com
 *
 * For further information about OpenCms, please see the
 * project website: http://www.opencms.org
 * 
 * You should have received a copy of the GNU Lesser General Public
 * License along with this library; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307  USA
 */

package org.opencms.gwt.client.ui;

import org.opencms.gwt.client.CmsCoreProvider;
import org.opencms.gwt.shared.CmsContextMenuEntryBean;
import org.opencms.util.CmsStringUtil;
import org.opencms.util.CmsUUID;

import java.util.ArrayList;
import java.util.List;

import com.google.gwt.user.client.Command;

/**
 * Abstract class which implements the common part of all toolbar handler functionality.<p>
 * 
 * @author Georg Westenberger
 * 
 * @version $Revision: 1.5 $
 * 
 * @since 8.0.0
 */
public abstract class A_CmsToolbarHandler implements I_CmsToolbarHandler {

    /**
     * Transforms a list of context menu entry beans to a list of context menu entries.<p>
     * 
     * @param menuBeans the list of context menu entry beans
     * @param structureId the id of the resource for which to transform the context menu entries 
     * 
     * @return a list of context menu entries 
     */
    public List<I_CmsContextMenuEntry> transformEntries(
        List<CmsContextMenuEntryBean> menuBeans,
        final CmsUUID structureId) {

        List<I_CmsContextMenuEntry> menuEntries = new ArrayList<I_CmsContextMenuEntry>();
        for (CmsContextMenuEntryBean bean : menuBeans) {
            final CmsContextMenuEntry entry = new CmsContextMenuEntry();

            entry.setBean(bean);

            if (bean.hasSubMenu()) {
                entry.setSubMenu(transformEntries(bean.getSubMenu(), structureId));
            }

            Command cmd = null;

            String name = entry.getName();
            if (CmsStringUtil.isNotEmptyOrWhitespaceOnly(name)) {

                if (name.equals(CmsAvailabilityDialog.class.getName())) {
                    entry.setImageClass(org.opencms.gwt.client.ui.css.I_CmsImageBundle.INSTANCE.contextMenuIcons().availability());

                    cmd = new Command() {

                        /**
                         * @see com.google.gwt.user.client.Command#execute()
                         */
                        public void execute() {

                            if (canOpenAvailabilityDialog()) {
                                new CmsAvailabilityDialog(CmsCoreProvider.get().getStructureId()).loadAndShow();
                            }
                        }
                    };
                } else if (name.equals(CmsShowWorkplace.class.getName())) {
                    entry.setImageClass(org.opencms.gwt.client.ui.css.I_CmsImageBundle.INSTANCE.contextMenuIcons().workplace());

                    cmd = new Command() {

                        /**
                         * @see com.google.gwt.user.client.Command#execute()
                         */
                        public void execute() {

                            new CmsShowWorkplace(structureId).openWorkplace();
                        }
                    };
                } else if (name.equals(CmsEditProperties.class.getName())) {
                    entry.setImageClass(org.opencms.gwt.client.ui.css.I_CmsImageBundle.INSTANCE.contextMenuIcons().properties());
                    cmd = new Command() {

                        public void execute() {

                            if (canEditProperties()) {

                                CmsEditProperties editProperties = new CmsEditProperties();
                                if (structureId != null) {
                                    editProperties.editProperties(
                                        CmsCoreProvider.get().getStructureId(),
                                        useAdeTemplates());
                                }
                            }
                        }
                    };
                }
            }
            entry.setCommand(cmd);
            menuEntries.add(entry);
        }
        return menuEntries;
    }

}