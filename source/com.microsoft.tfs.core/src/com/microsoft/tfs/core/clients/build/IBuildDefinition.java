// Copyright (c) Microsoft. All rights reserved.
// Licensed under the MIT license. See License.txt in the repository root.

package com.microsoft.tfs.core.clients.build;

import java.util.Calendar;
import java.util.Map;

import com.microsoft.tfs.core.clients.build.flags.BuildReason;
import com.microsoft.tfs.core.clients.build.flags.BuildStatus;
import com.microsoft.tfs.core.clients.build.flags.DefinitionQueueStatus;
import com.microsoft.tfs.core.clients.build.flags.DeleteOptions;
import com.microsoft.tfs.core.clients.build.internal.soapextensions.BuildDefinitionSourceProvider;
import com.microsoft.tfs.core.clients.build.soapextensions.ContinuousIntegrationType;
import com.microsoft.tfs.core.clients.build.soapextensions.DefinitionTriggerType;

public interface IBuildDefinition extends IBuildGroupItem {
    /**
     * The maximum batch size for queued builds when using the Gated continuous
     * integration type.
     *
     *
     * @return
     */
    public int getBatchSize();

    public void setBatchSize(int batchSize);

    /**
     * The build controller that builds this build definition.
     *
     *
     * @return
     */
    public IBuildController getBuildController();

    public void setBuildController(IBuildController controller);

    /**
     * The Uri of the build controller that builds this build definition.
     *
     *
     * @return
     */
    public String getBuildControllerURI();

    /**
     * The description of the definition.
     *
     *
     * @return
     */
    public String getDescription();

    public void setDescription(String description);

    /**
     * The default location to drop the output of builds generated by the
     * definition.
     *
     *
     * @return
     */
    public String getDefaultDropLocation();

    public void setDefaultDropLocation(String value);

    /**
     * The enabled status of the build definition - when false, no new builds
     * can be generated for the definition.
     *
     *
     * @return
     */
    public boolean isEnabled();

    public void setEnabled(boolean value);

    /**
     * The queue status of the build definition.
     *
     *
     * @return
     */
    public DefinitionQueueStatus getQueueStatus();

    public void setQueueStatus(DefinitionQueueStatus status);

    /**
     * The Id portion of the build definition's Uri.
     *
     *
     * @return
     */
    public String getID();

    /**
     * A list of the retention policies for the definition.
     *
     *
     * @return
     */
    public IRetentionPolicy[] getRetentionPolicies();

    /**
     * A list of schedules for the definition
     *
     *
     * @return
     */
    public ISchedule[] getSchedules();

    /**
     * Get a list of source providers.
     *
     * @return
     */
    public BuildDefinitionSourceProvider[] getSourceProviders();

    /**
     * Get default source provider.
     *
     *
     * @return
     */
    public BuildDefinitionSourceProvider getDefaultSourceProvider();

    /**
     * Set default source provider.
     *
     * @param provider
     */
    public void setDefaultSourceProvider(final BuildDefinitionSourceProvider provider);

    /**
     * The workspace template for the definition.
     *
     *
     * @return
     */
    public IWorkspaceTemplate getWorkspace();

    /**
     * Gets the URI of the last build completed for this build definition
     * regardless of status.
     *
     *
     * @return
     */
    public String getLastBuildURI();

    /**
     * Gets the URI of the last build completed for this build definition with a
     * successful compilation and test status.
     *
     *
     * @return
     */
    public String getLastGoodBuildURI();

    /**
     * The name of the label of the last build generated for the build
     * definition for which compilation and tests were successful.
     *
     *
     * @return
     */
    public String getLastGoodBuildLabel();

    /**
     * The build process template for the definition.
     *
     *
     * @return
     */
    public IProcessTemplate getProcess();

    public void setProcess(IProcessTemplate value);

    /**
     * The default process parameters, used to initialize new build processes.
     *
     *
     * @return
     */
    public String getProcessParameters();

    public void setProcessParameters(String value);

    /**
     * Attached properties
     *
     *
     * @return
     */
    public Map<String, Object> getAttachedProperties();

    /**
     * Gets or sets the definition system trigger.
     *
     *
     * @return
     */
    public DefinitionTriggerType getTriggerType();

    public void setTriggerType(DefinitionTriggerType value);

    /**
     * The date and time at which this build definition was created.
     *
     *
     * @return
     */
    public Calendar getDateCreated();

    /**
     * The quiet period associated with a 'Batch' continuous integration type.
     * New builds will not be queued for changesets which are encountered during
     * the quiet period. Instead a single build will be queued for all
     * changesets which occurred during the quiet period once it has lapsed.
     * This value is used as an offset (in minutes) from the starting time of
     * the last queued build for Continuous Integration.
     *
     *
     * @return
     */
    public int getContinuousIntegrationQuietPeriod();

    public void setContinuousIntegrationQuietPeriod(int value);

    /**
     * Adds a retention policy to the list of retention policies for the build
     * definition.
     *
     *
     * @param reason
     *        The reason of the retention policy.
     * @param status
     * @param numberToKeep
     *        The number to keep of the retention policy.
     * @param deleteOptions
     *        The parts of the build to delete.
     * @return The added retention policy.
     */
    public IRetentionPolicy addRetentionPolicy(
        BuildReason reason,
        BuildStatus status,
        int numberToKeep,
        DeleteOptions deleteOptions);

    /**
     * Adds a schedule to the list of schedules for the build definition. The
     * schedule has no affect if the ContinuousIntegrationType is not Schedule.
     * Only one schedule is allowed in the list.
     *
     *
     * @return The schedule that was added.
     */
    public ISchedule addSchedule();

    /**
     * Creates an IBuildRequest from the build definition with values for
     * BuildAgent and DropLocation set to DefaultBuildAgent and
     * DefaultDropLocation.
     *
     *
     * @return The new build request, which can be used to Queue a build.
     */
    public IBuildRequest createBuildRequest();

    /**
     * Creates a BuildDetail record in the TFS Build database. Build Information
     * and other changes can be made to the IBuildDetail object
     *
     *
     * @param buildNumber
     *        Build number to assign to the new build.
     * @return The new IBuildDetail object that now exists in the database.
     */
    public IBuildDetail createManualBuild(String buildNumber);

    /**
     * Creates a BuildDetail record in the TFS Build database. Build Information
     * and other changes can be made to the IBuildDetail object returned.
     *
     *
     * @param buildNumber
     *        Build number to assign to the new build.
     * @param dropLocation
     *        Location of the binary files created by the manual build.
     * @return The new IBuildDetail object that now exists in the database.<
     */
    public IBuildDetail createManualBuild(String buildNumber, String dropLocation);

    /**
     * Creates a BuildDetail record in the TFS Build database. Build Information
     * and other changes can be made to the IBuildDetail object returned.
     *
     *
     * @param buildNumber
     *        Build number to assign to the new build.
     * @param dropLocation
     *        Location of the binary files created by the manual build.
     * @param buildStatus
     *        The status that you want the build to have. PartiallySucceeded is
     *        not supported.
     * @param controller
     *        Agent used to create the build.
     * @param requestedFor
     *        The user that requested the build.
     * @return The new IBuildDetail object that now exists in the database.
     */
    public IBuildDetail createManualBuild(
        String buildNumber,
        String dropLocation,
        BuildStatus buildStatus,
        IBuildController controller,
        String requestedFor);

    /**
     * Deletes this build definition from the server. Note: this will throw if
     * there are builds pending or existing for this definition.
     *
     *
     */
    public void delete();

    /**
     * Saves any changes made to the object to the server.
     *
     *
     */
    public void save();

    /**
     * Creates a new build definition specification for this definition.
     *
     *
     * @return The new build definition specification.
     */
    public IBuildDefinitionSpec createSpec();

    /**
     * Gets all of the builds for this build definition.
     *
     *
     * @return The builds for this build definition.
     */
    public IBuildDetail[] queryBuilds();

    /**
     * Gets the server that owns the build definition.
     *
     *
     * @return
     */
    public IBuildServer getBuildServer();

    /**
     * The ContinuousIntegrationType of the definition. Individual ensures that
     * a new build is queued for every check in made to version control which
     * contains files that affect the workspace associated with the definition.
     * For more information regarding the Batch type, see
     * <see cref="ContinuousIntegrationQuietPeriod"/>.
     *
     *
     * @return
     */
    public ContinuousIntegrationType getContinuousIntegrationType();

    public void setContinuousIntegrationType(ContinuousIntegrationType value);

    public String getConfigurationFolderPath();

    public void setConfigurationFolderPath(String path);
}
