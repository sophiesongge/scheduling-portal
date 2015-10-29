/*
 * ################################################################
 *
 * ProActive Parallel Suite(TM): The Java(TM) library for
 *    Parallel, Distributed, Multi-Core Computing for
 *    Enterprise Grids & Clouds
 *
 * Copyright (C) 1997-2011 INRIA/University of
 *                 Nice-Sophia Antipolis/ActiveEon
 * Contact: proactive@ow2.org or contact@activeeon.com
 *
 * This library is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Affero General Public License
 * as published by the Free Software Foundation; version 3 of
 * the License.
 *
 * This library is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Affero General Public License for more details.
 *
 * You should have received a copy of the GNU Affero General Public License
 * along with this library; if not, write to the Free Software
 * Foundation, Inc., 59 Temple Place, Suite 330, Boston, MA  02111-1307
 * USA
 *
 * If needed, contact us to obtain a release under GPL Version 2 or 3
 * or a different license than the AGPL.
 *
 *  Initial developer(s):               The ProActive Team
 *                        http://proactive.inria.fr/team_members.htm
 *  Contributor(s):
 *
 * ################################################################
 * $$PROACTIVE_INITIAL_DEV$$
 */
package org.ow2.proactive_grid_cloud_portal.scheduler.client.view;

import java.util.List;

import org.ow2.proactive_grid_cloud_portal.scheduler.client.SchedulerListeners.TasksUpdatedListener;
import org.ow2.proactive_grid_cloud_portal.scheduler.client.Task;
import org.ow2.proactive_grid_cloud_portal.scheduler.client.controller.TasksController;
import org.ow2.proactive_grid_cloud_portal.scheduler.client.view.grid.ExpandableTasksListGrid;

import com.smartgwt.client.widgets.layout.Layout;


/**
 * Contains the ListGrid that displays tasks
 *
 * @author mschnoor
 */
public class TasksView extends AbstractGridItemsView implements TasksUpdatedListener {
   
   
    protected TasksController controller;


    public TasksView(TasksController controller) {
        this.controller = controller;
        this.controller.getModel().addTasksUpdatedListener(this);
        this.itemName = "tasks";
    }


    public void tasksUpdating(boolean jobChanged) {
        if (jobChanged) {
            this.itemUpdating();
        }
    }

    public void tasksUpdatedFailure(String message) {
        this.itemUpdatedFailure(message);
    }

    public void tasksUpdated(List<Task> tasks, long totalTasks) {
        this.itemUpdated();
    }

    
    protected Layout buildToolbar(){
        return this.controller.getTaskNavigationController().buildView();
    }
    
    protected Layout buildPagination(){
        return this.controller.getTaskNavigationController().getPaginationController().buildView();
    }
    
    protected void buildGrid(){
        this.itemsGrid = new ExpandableTasksListGrid(this.controller);
        this.itemsGrid.build();
    }
}