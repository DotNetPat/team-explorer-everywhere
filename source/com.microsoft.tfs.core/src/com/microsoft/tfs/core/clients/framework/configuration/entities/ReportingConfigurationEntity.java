// Copyright (c) Microsoft. All rights reserved.
// Licensed under the MIT license. See License.txt in the repository root.

package com.microsoft.tfs.core.clients.framework.configuration.entities;

import com.microsoft.tfs.core.clients.framework.configuration.TFSEntity;

/**
 * @since TEE-SDK-10.1
 */
public interface ReportingConfigurationEntity extends TFSEntity {
    public ReportingServerEntity getReportingServer();
}
