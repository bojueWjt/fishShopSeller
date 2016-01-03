package com.zhanjixun.views;

import android.app.Dialog;
import android.content.Context;
import android.view.Display;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.LinearLayout;

import com.zhanjixun.R;

/**
 * 主题对话框
 * 
 * @author 詹命天子
 *
 */
public class ThemeDialog extends Dialog {

	private Button positiveBtn;
	private Button negativeBtn;
	private LinearLayout btns;
	private View linear;
	private boolean hasPositiveButton = false;
	private boolean hasNegativeButton = false;
	private LinearLayout content;

	public ThemeDialog(Context context) {
		super(context, R.style.ThemeDialog);
		initDialog();

	}

	private void initDialog() {
		View v = View.inflate(getContext(), R.layout.theme_dialog, null);
		content = (LinearLayout) v.findViewById(R.id.dialog_content);
		btns = (LinearLayout) v.findViewById(R.id.dialog_btn);
		positiveBtn = (Button) v.findViewById(R.id.btn_positive_message_dialog);
		linear = v.findViewById(R.id.view_message_dialog);
		negativeBtn = (Button) v.findViewById(R.id.btn_negative_message_dialog);
		btns.setVisibility(View.GONE);
		setContentView(v);
		setDialogSize(0.55, 0.55);
	}

	/**
	 * 设置以屏幕大小为基准的对话框大小
	 * 
	 * @param width
	 *            宽度占屏幕宽度的百分比
	 * @param height
	 *            高度占屏幕高度的百分比
	 */
	public void setDialogSize(double width, double height) {
		Window dialogWindow = this.getWindow();
		WindowManager.LayoutParams lp = dialogWindow.getAttributes();
		WindowManager wm = (WindowManager) getContext().getSystemService(
				Context.WINDOW_SERVICE);
		Display d = wm.getDefaultDisplay();
		lp.width = (int) (d.getWidth() * width);
		lp.height = (int) (d.getHeight() * height);
		dialogWindow.setAttributes(lp);
	}

	public void setView(View v) {
		content.removeAllViews();
		content.addView(v);
	}

	public void setPositiveButton(String text, View.OnClickListener l) {
		btns.setVisibility(View.VISIBLE);
		if (hasNegativeButton) {
			linear.setVisibility(View.VISIBLE);
		} else {
			linear.setVisibility(View.GONE);
			negativeBtn.setVisibility(View.GONE);
		}
		positiveBtn.setVisibility(View.VISIBLE);
		positiveBtn.setText(text);
		positiveBtn.setOnClickListener(new ButtonOnClickListener(l));
		hasPositiveButton = true;
	}

	public void setNegativeButton(String text, View.OnClickListener l) {
		btns.setVisibility(View.VISIBLE);
		if (hasPositiveButton) {
			linear.setVisibility(View.VISIBLE);
		} else {
			linear.setVisibility(View.GONE);
			positiveBtn.setVisibility(View.GONE);
		}
		negativeBtn.setVisibility(View.VISIBLE);
		negativeBtn.setText(text);
		negativeBtn.setOnClickListener(new ButtonOnClickListener(l));
		hasNegativeButton = true;
	}

	private class ButtonOnClickListener implements View.OnClickListener {
		private View.OnClickListener l;

		public ButtonOnClickListener(android.view.View.OnClickListener l) {
			this.l = l;
		}

		@Override
		public void onClick(View v) {
			ThemeDialog.this.dismiss();
			if (l != null) {
				l.onClick(v);
			}
		}
	}

}
