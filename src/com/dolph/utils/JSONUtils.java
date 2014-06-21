package com.dolph.utils;

import java.util.List;

import com.dolph.model.Contact;
import com.dolph.model.User;
import com.dolph.vo.ContactVo;
import com.sdicons.json.mapper.JSONMapper;
import com.sdicons.json.mapper.MapperException;

public class JSONUtils {
	public static String toJSON(Object o) {
		try {
			String s = JSONMapper.toJSON(o).render(false);
			return s;
		} catch (MapperException e) {
			e.printStackTrace();
		}
		return null;
	}

	public static String listToJson(List list) {
		StringBuffer sb = new StringBuffer();
		sb.append("[");
		for (Object o : list) {
			ContactVo vo = new ContactVo((Contact) o);
			sb.append(toJSON(vo) + ",");
		}
		String str = sb.substring(0, sb.length() - 1);
		str = str + "]";
		return str;
	}

	public static String listToJson2(List list) {
		StringBuffer sb = new StringBuffer();
		sb.append("[");
		for (Object o : list) {
			sb.append(o.toString());
			sb.append(",");
		}
		String str = sb.substring(0, sb.length() - 1);
		str = str + "]";
		return str;
	}
}
