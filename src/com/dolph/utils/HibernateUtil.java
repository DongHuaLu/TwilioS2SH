package com.dolph.utils;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class HibernateUtil {

	private static SessionFactory sessionFactroy;

	static {
		sessionFactroy = new Configuration().configure().buildSessionFactory();
	}

	public static final ThreadLocal threadLocal = new ThreadLocal();

	public static Session currentSession() {
		Session s = (Session) threadLocal.get();
		if (s == null) {
			s = sessionFactroy.openSession();
			threadLocal.set(s);
		}
		return s;
	}

	public static void closeSession() {
		Session s = (Session) threadLocal.get();
		threadLocal.set(null);
		if (s != null) {
			s.close();
		}
	}

}
