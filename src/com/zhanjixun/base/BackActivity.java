package com.zhanjixun.base;

import android.app.Activity;
import android.view.View;

/**
 * ��Ҫ������һҳ��Activity�ĸ��ֱ࣬���ڲ����ļ����صĿؼ����� onClick="onBack"�Ϳ���ʵ�ַ�����
 * 
 * @author ղ������
 *
 */
public class BackActivity extends Activity {

	public void onBack(View v) {
		this.finish();
	}
}
