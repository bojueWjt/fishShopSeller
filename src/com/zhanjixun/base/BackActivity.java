package com.zhanjixun.base;

import android.app.Activity;
import android.view.View;

/**
 * 需要返回上一页的Activity的父类，直接在布局文件返回的控件加上 onClick="onBack"就可以实现返回了
 * 
 * @author 詹命天子
 *
 */
public class BackActivity extends Activity {

	public void onBack(View v) {
		this.finish();
	}
}
