package com.github.migbee.mongoguice.service;

import com.github.migbee.exceptions.DBMigrationServiceException;
import org.mongojack.DBCursor;
import org.mongojack.DBQuery;
import org.mongojack.JacksonDBCollection;
import org.mongojack.WriteResult;

import java.util.ArrayList;
import java.util.List;

public class MigrationCRUDService {

	private JacksonDBCollection<ChangeEntryImpl, String> collection;

	public MigrationCRUDService(JacksonDBCollection<ChangeEntryImpl, String> collection) {
		this.collection = collection;
	}

	public ChangeEntryImpl create(ChangeEntryImpl doc) {
		WriteResult<ChangeEntryImpl, String> result = this.collection.insert(doc);
		return result.getSavedObject();
	}

	private List<ChangeEntryImpl> selectByVersionAndName (ChangeEntryImpl changeEntry) throws DBMigrationServiceException {
		DBQuery.Query query = DBQuery.is("version", changeEntry.getVersion())
				.is("name", changeEntry.getName());
		return dbCursorToList(this.collection.find(query));
	}

	public boolean exist (ChangeEntryImpl changeEntry) throws DBMigrationServiceException {
		List<ChangeEntryImpl> entries = this.selectByVersionAndName (changeEntry);
		return !entries.isEmpty();
	}

	private List<ChangeEntryImpl> dbCursorToList(DBCursor<ChangeEntryImpl> dbCursor) {
		List<ChangeEntryImpl> documents = new ArrayList<>();
		while (dbCursor.hasNext()) {
			ChangeEntryImpl document = dbCursor.next();
			documents.add(document);
		}
		return documents;
	}

}
