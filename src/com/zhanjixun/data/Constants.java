package com.zhanjixun.data;

import android.os.Environment;

import com.zhanjixun.domain.BaseResult;

public class Constants {

	public static final String HOST = "http://www.earltech.cn:8080";
	public static final String PROJECT = "fishshop";

	public static final String JSON_STATE = "serviceResult";
	public static final String JSON_MESSAGE = "resultInfo";

	public static String HOME_DIR = Environment.getExternalStorageDirectory()
			+ "/" + Constants.PROJECT;
	public static String CACHE_DIR = HOME_DIR + "/Cache";

	public static final BaseResult SERVER_ERROR = new BaseResult() {
		{
			setServiceResult(false);
			setResultInfo("服务器连接错误！");
		}
	};
	public static final BaseResult JSON_ERROR = new BaseResult() {
		{
			setServiceResult(false);
			setResultInfo("JSON数据解析错误！");
		}
	};
}
