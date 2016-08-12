/* ********************************************************************************
 * TaskType.java
 *
 * Copyright © 2013 - 2016 VMware, Inc. All Rights Reserved.
 * Copyright (c) 2016 Intel Corporation
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed
 * under the License is distributed on an "AS IS" BASIS, without warranties or
 * conditions of any kind, EITHER EXPRESS OR IMPLIED. See the License for the
 * specific language governing permissions and limitations under the License.
 *
 * *******************************************************************************/
package com.vmware.vrack.hms.task;

public enum TaskType
{
    /* Core HMS tasks */
    HMSBootUp,
    DiscoverServer,
    PowerStatusServer,
    PowerDownServer,
    PowerResetServer,
    PowerUpServer,
    ValidateServerOS,
    DiscoverSwitch,
    PowerStatusSwitch,
    MacAddressDiscovery,
    ServerBoardInfo,
    InitMonitorService,
    ListBmcUsers,
    PowerCycleServer,
    ColdResetBmc,
    SelfTest,
    AcpiPowerState,
    HMSResourceMonitor,
    FruInfo,
    NicInfo,
    RmmCPUInfo,
    RmmDimmInfo,
    GetSystemBootOptions,
    SetSystemBootOptions,
    ChassisIdentify,
    HDDInfo,
    StorageControllerInfo,
    SelInfo,
    SwitchMonitorService,
    GetSupportedAPI,

    /* Redfish-related tasks */
    RedfishDiscoverComputerSystems
}
