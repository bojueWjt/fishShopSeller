package com.zhanjixun.data;

import java.util.HashMap;
import java.util.Map;

import com.zhanjixun.interfaces.OnDataReturnListener;

public class DC extends DataCenter {

	private static DC dc = null;

	private DC() {
	}

	public static DC getInstance() {
		if (dc == null) {
			synchronized (DC.class) {
				if (dc == null)
					dc = new DC();
			}
		}
		return dc;
	}

	/**
	 * ÓÃ»§µÇÂ¼
	 * 
	 * @param dataReturnListener
	 * @param id
	 * @param pw
	 */
	public void login(OnDataReturnListener dataReturnListener, String id,
			String pw) {

		Map<String, String> params = new HashMap<String, String>();
		params.put("phoneNumber", id);
		params.put("password", pw);
		getDatasFromServer(TaskTag.LOGIN, "user_userLogin.action", params,
				dataReturnListener);
	}
}
