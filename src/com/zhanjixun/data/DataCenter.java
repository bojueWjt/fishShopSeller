package com.zhanjixun.data;

import java.util.Map;

import com.zhanjixun.interfaces.OnDataReturnListener;
import com.zhanjixun.net.AsyncHttpTask;

public class DataCenter {

	public void getDatasFromServer(String taskTag, String url,
			Map<String, String> params, OnDataReturnListener dataReturnListener) {

		new AsyncHttpTask(taskTag, dataReturnListener).execute(Constants.HOST
				+ "/" + Constants.PROJECT + "/" + url, params);
	}

	public void getLogistics(String taskTag, Map<String, String> params,
			OnDataReturnListener dataReturnListener) {
		new AsyncHttpTask(taskTag, dataReturnListener, AsyncHttpTask.GET)
				.execute("http://www.kuaidi100.com/query", params);
	}
}
