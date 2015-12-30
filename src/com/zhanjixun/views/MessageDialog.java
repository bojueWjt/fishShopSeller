package com.zhanjixun.views;

import android.content.Context;
import android.util.TypedValue;
import android.view.Gravity;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.LinearLayout.LayoutParams;

public class MessageDialog {
	protected ThemeDialog dialog;
	protected TextView messageTv;

	public MessageDialog(Context context, String msg) {
		dialog = new ThemeDialog(context);
		messageTv = initView(context, msg);
		dialog.setView(messageTv);
		dialog.setPositiveButton("ȷ��", null);
		dialog.setDialogSize(0.8, 0.4);
	}

	private TextView initView(Context context, String msg) {
		TextView v = new TextView(context);
		LinearLayout.LayoutParams lp = new LayoutParams(
				LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
		v.setText(msg);
		v.setTextSize(TypedValue.COMPLEX_UNIT_SP, 20);
		v.setLayoutParams(lp);
		v.setGravity(Gravity.CENTER);
		return v;
	}

	public MessageDialog(Context context) {
		this(context, "��ʾ��Ϣ");
	}

	public void setCancelable(boolean flag) {
		dialog.setCancelable(flag);
	}

	public void setTitle(CharSequence title) {
		dialog.setTitle(title);
	}

	public void setMessage(CharSequence msg) {
		messageTv.setText(msg);
	}

	public void show() {
		if (dialog != null && !dialog.isShowing()) {
			dialog.show();
		}
	}

	public void dissmiss() {
		if (dialog != null && dialog.isShowing()) {
			dialog.dismiss();
		}
	}
}
