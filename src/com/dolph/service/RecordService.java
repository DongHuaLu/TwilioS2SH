package com.dolph.service;

import java.util.Date;
import java.util.List;

import com.dolph.model.Record;
import com.dolph.model.User;

public interface RecordService {

	boolean saveRecord(Date startDate, String duration, String phoneNumber,User user);

	List<Record> findAllRecordByCurrentUser(User currentUser);

	boolean deleteRecord(int recordId, User currentUser);

}
