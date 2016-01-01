package com.zhanjixun.net;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import org.json.JSONObject;

import android.content.Intent;
import android.os.AsyncTask;

import com.zhanjixun.data.Constants;
import com.zhanjixun.domain.BaseResult;
import com.zhanjixun.interfaces.OnDataReturnListener;
import com.zhanjixun.utils.MyGson;
import com.zhanjixun.utils.LogCat;
import com.zhanjixun.utils.StringUtil;

public class AsyncHttpTask extends AsyncTask<Object, Intent, String> {

	public static final String POST = "POST";
	public static final String GET = "GET";

	private String taskTag;
	private OnDataReturnListener dataReturnListener;
	private String method = "POST";

	public AsyncHttpTask(String taskTag,
			OnDataReturnListener dataReturnListener, String method) {
		this.taskTag = taskTag;
		this.method = method;
		this.dataReturnListener = dataReturnListener;
	}

	public AsyncHttpTask(String taskTag, OnDataReturnListener dataReturnListener) {
		this.taskTag = taskTag;
		this.dataReturnListener = dataReturnListener;
	}

	@Override
	protected void onPreExecute() {
		super.onPreExecute();
	}

	@Override
	protected String doInBackground(Object... executeParams) {
		String url = (String) executeParams[0];
		@SuppressWarnings("unchecked")
		Map<String, String> parames = (Map<String, String>) executeParams[1];
		try {
			if (method.equals(POST)) {
				return HttpConnection.doPOSTMethod(url, parames);
			} else {
				return HttpConnection.doGETMethod(url, parames);
			}
		} catch (Exception e) {
			LogCat.error(e.toString());
		}
		return "";
	}

	@Override
	protected void onPostExecute(String json) {
		super.onPostExecute(json);

		BaseResult jsonResult = null;
		if (!StringUtil.isEmptyString(json)) {
			try {
				jsonResult = MyGson.getInstance().fromJson(json,
						BaseResult.class);
			} catch (Exception e) {
				jsonResult = Constants.JSON_ERROR;
				e.printStackTrace();
			}
		} else {
			jsonResult = Constants.SERVER_ERROR;
		}
		try {
			JSONObject jo = new JSONObject(json).getJSONObject("resultParm");
			Map<String, String> map = new HashMap<String, String>();
			Iterator<String> keys = jo.keys();

			while (keys.hasNext()) {
				String k = keys.next();
				map.put(k, jo.getString(k));
			}
			jsonResult.setResultParam(map);
		} catch (Exception e) {
			e.printStackTrace();
		}
		if (dataReturnListener != null) {
			dataReturnListener.onDataReturn(taskTag, jsonResult, json);
		}
	}
}
