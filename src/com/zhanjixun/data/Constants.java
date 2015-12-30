package com.zhanjixun.data;

import android.os.Environment;

import com.zhanjixun.domain.BaseResult;
import com.zhanjixun.domain.User;

public class Constants {

	public static final String HOST = "http://www.earltech.cn:8080";
	public static final String PROJECT = "fishshop";

	public static final String SP_USER = "SP_USER";
	public static final String SP_SAIL_MODEL = "SP_SAIL_MODEL";
	
	/**
	 * ��ǰ��¼�û�����Ҫ�ļ����������ڿ������ʱ���ʼ��
	 */
	public static User user;

	/**
	 * ����ģʽ����Ҫ�ļ����������ڿ������ʱ���ʼ��
	 */
	public static boolean sailModel = false;

	public static final String JSON_STATE = "serviceResult";
	public static final String JSON_MESSAGE = "resultInfo";

	public static String HOME_DIR = Environment.getExternalStorageDirectory()
			+ "/" + Constants.PROJECT;
	public static String CACHE_DIR = HOME_DIR + "/Cache";

	public static final BaseResult SERVER_ERROR = new BaseResult() {
		{
			setServiceResult(false);
			setResultInfo("���������Ӵ���");
		}
	};

	public static final BaseResult JSON_ERROR = new BaseResult() {
		{
			setServiceResult(false);
			setResultInfo("JSON���ݽ�������");
		}
	};

}
