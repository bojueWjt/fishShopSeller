package com.zhanjixun.views;

import android.content.Context;
import android.view.View.OnClickListener;

/**
 * 确认对话框，两个button
 * 
 * @author 詹命天子
 *
 */
public class ConfirmDialog extends MessageDialog {

	public ConfirmDialog(Context context, String msg) {
		super(context, msg);
		dialog.setCancelable(true);
	}

	public void setPositiveButton(String text, OnClickListener l) {
		dialog.setPositiveButton(text, l);
	}

	@Override
	public void setCancelable(boolean flag) {
		super.setCancelable(flag);
	}

	public void setNegativeButton(String text, OnClickListener l) {
		dialog.setNegativeButton(text, l);
	}
}
