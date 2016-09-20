package com.gamasoft.hps.sab.domain.base;

/**
 * @author jose.muguerza
 */
public class AuditContext {
	@SuppressWarnings({ "unchecked", "rawtypes" })
	private static final ThreadLocal<String> currentUser = new ThreadLocal();

	public static String getUser() {
		return (String) currentUser.get();
	}

	public static void setUser(String user) {
		currentUser.set(user);
	}

	public static void clear() {
		currentUser.set(null);
	}
}
