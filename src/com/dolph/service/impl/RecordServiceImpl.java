package com.dolph.service.impl;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.dolph.Dao.ContactDao;
import com.dolph.Dao.RecordDao;
import com.dolph.model.Contact;
import com.dolph.model.Record;
import com.dolph.model.User;
import com.dolph.service.RecordService;

@Service("recordService")
public class RecordServiceImpl implements RecordService {
	private RecordDao recordDao;
	private ContactDao contactDao;

	@Resource
	public void setContactDao(ContactDao contactDao) {
		this.contactDao = contactDao;
	}

	@Resource
	public void setRecordDao(RecordDao recordDao) {
		this.recordDao = recordDao;
	}

	@Override
	public boolean saveRecord(Date startDate, String duration,
			String phoneNumber, User user) {
		List<Contact> contacts = contactDao
				.findContactsByPhoneNumber(phoneNumber);
		StringBuffer sb = new StringBuffer();
		String names = "";
		if (contacts != null && contacts.size()>0) {
			for (Contact c : contacts) {
				if (!"".equals(c.getName())) {
					sb.append(c.getName() + ",");
				}
			}
			names = sb.substring(0, sb.length() - 1);
		}
		Record record = new Record(phoneNumber, names, startDate,
				Integer.parseInt(duration), user);
		recordDao.save(record);
		return true;
	}

	@Override
	public List<Record> findAllRecordByCurrentUser(User currentUser) {
		return recordDao.findAllRecordByUser(currentUser);
	}

	@Override
	public boolean deleteRecord(int recordId, User currentUser) {
		return recordDao.deleteRecord(recordId,currentUser);
	}
}
