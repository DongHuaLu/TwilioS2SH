package com.dolph.Dao;

import java.util.List;

import com.dolph.model.Record;
import com.dolph.model.User;

public interface RecordDao extends BaseDao {

	List<Record> findAllRecordByUser(User currentUser);

	boolean deleteRecord(int recordId, User currentUser);

}
