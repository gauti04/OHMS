/* ********************************************************************************
 * MemoryInformationTask.java
 * 
 * Copyright (C) 2014-2016 VMware, Inc. - All rights reserved.
 *
 * *******************************************************************************/
package com.vmware.vrack.hms.task.oob.rmm;

import java.util.List;

import org.apache.log4j.Logger;

import com.vmware.vrack.hms.boardservice.BoardServiceProvider;
import com.vmware.vrack.hms.boardservice.HmsPluginServiceCallWrapper;
import com.vmware.vrack.hms.common.boardvendorservice.api.IBoardService;
import com.vmware.vrack.hms.common.boardvendorservice.resource.ServiceServerNode;
import com.vmware.vrack.hms.common.exception.HmsException;
import com.vmware.vrack.hms.common.exception.HmsResourceBusyException;
import com.vmware.vrack.hms.common.notification.TaskResponse;
import com.vmware.vrack.hms.common.servernodes.api.ServerNode;
import com.vmware.vrack.hms.common.servernodes.api.memory.PhysicalMemory;

/**
 * Task class to get System Memory related data using RMM
 * 
 * @author Yagnesh Chawda
 */
public class MemoryInformationTask
    extends RmmTask
{
    private static Logger logger = Logger.getLogger( MemoryInformationTask.class );

    public MemoryInformationTask( TaskResponse response )
    {
        this.response = response;
        this.node = (ServerNode) response.getNode();
    }

    @Override
    public void executeTask()
        throws Exception
    {
        try
        {
            ServiceServerNode serviceServerNode = (ServiceServerNode) node.getServiceObject();
            IBoardService boardService = BoardServiceProvider.getBoardService( serviceServerNode );
            if ( boardService != null )
            {
                Object[] paramsArray = new Object[] { serviceServerNode };
                List<PhysicalMemory> physicalMemoryList =
                    HmsPluginServiceCallWrapper.invokeHmsPluginService( boardService, serviceServerNode,
                                                                        "getPhysicalMemoryInfo", paramsArray );
                this.node.setPhysicalMemoryInfo( physicalMemoryList );
            }
            else
            {
                throw new Exception( "Board Service is NULL for node:" + node.getNodeID() );
            }
        }
        catch ( HmsResourceBusyException e )
        {
            String error =
                String.format( "HMS Resource is Busy for the node [%s]. Please try after some time", node.getNodeID() );
            logger.debug( error, e );
            throw e;
        }
        catch ( HmsException e )
        {
            logger.error( "Error while getting System Memory Info for Node:" + node.getNodeID(), e );
            throw new HmsException( "Error while getting System Memory Info for Node:" + node.getNodeID(), e );
        }
    }
}