package com.github.migbee.mongoguice.service;

import com.github.migbee.annotation.ChangeSet;
import com.github.migbee.exceptions.DBMigrationServiceException;
import com.github.migbee.service.AbstractMigrationService;
import com.github.migbee.service.ChangeEntry;
import com.google.inject.Inject;
import com.google.inject.Injector;
import com.google.inject.Singleton;

import java.util.Date;

@Singleton
public class MigrationServiceImpl extends AbstractMigrationService {

	private final Injector injector;
	private final MigrationCRUDService migrationCRUDService;
	private final String changeLogsBasePackage;

	@Inject
	public MigrationServiceImpl(Injector injector, MigrationCRUDService migrationCRUDService, String changeLogsBasePackage) {
		this.injector = injector;
		this.migrationCRUDService = migrationCRUDService;
		this.changeLogsBasePackage = changeLogsBasePackage;
	}

	@Override
	protected <T> T getInstance(Class<T> changelogClass) {
		return this.injector.getInstance(changelogClass);
	}

	@Override
	protected boolean isMigrationAlreadyDone(ChangeEntry changeEntry) throws DBMigrationServiceException {
		return this.migrationCRUDService.exist(this.createChangeEntry(changeEntry));
	}

	@Override
	protected void putChangeEntry(ChangeEntry changeEntry) throws DBMigrationServiceException {
		this.migrationCRUDService.create(this.createChangeEntry(changeEntry));
	}

	private ChangeEntryImpl createChangeEntry (ChangeEntry changeEntry) {
		ChangeSet changeSet = changeEntry.getChangeSet();
		return new ChangeEntryImpl(changeEntry.getChangeLog().version(),
				changeSet.name(),
				changeSet.author(),
				new Date(),
				changeEntry.getChangelogClass().getName(),
				changeEntry.getChangeSetMethod().getName(),
				changeSet.isCritical());
	}

	@Override
	protected String getChangeLogBasePackageName() {
		return changeLogsBasePackage;
	}
}
