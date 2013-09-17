package com.dolph.service.impl;

import java.sql.SQLException;
import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.dolph.Dao.CodeDao;
import com.dolph.Dao.UserDao;
import com.dolph.model.User;
import com.dolph.model.Validation;
import com.dolph.service.CodeService;

@Service("codeService")
public class CodeServiceImpl implements CodeService {
	private CodeDao codeDao;
	private UserDao userDao;

	@Resource
	public void setCodeDao(CodeDao codeDao) {
		this.codeDao = codeDao;
	}

	@Resource
	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	public String queryReceiptientCode(String receiptient) {
		return codeDao.findVByReceiptient(receiptient).getCode();

	}

	public void insertOrUpdateCode(String newCode, String mobile_phone) {
		Validation validation = codeDao.findVByReceiptient(mobile_phone);
		if (validation != null) {
			validation.setCode(newCode);
			validation.setSendTime(new Date());
			codeDao.update(validation);
		} else {
			validation = new Validation(mobile_phone, newCode, new Date());
			codeDao.save(validation);
		}

	}

	public boolean isRegister(String mobile_phone) {
		User user = userDao.findUserByMobliePhone(mobile_phone);
		if (user != null) {
			return true;
		} else {
			return false;
		}
	}
}
