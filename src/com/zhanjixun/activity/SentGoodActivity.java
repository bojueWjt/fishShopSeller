package com.zhanjixun.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.zhanjixun.R;
import com.zhanjixun.base.BackActivity;
import com.zhanjixun.data.DC;
import com.zhanjixun.domain.BaseResult;
import com.zhanjixun.interfaces.OnDataReturnListener;
import com.zhanjixun.utils.LogCat;
import com.zhanjixun.utils.StringUtil;
import com.zhanjixun.views.LoadingDialog;
import com.zhanjixun.views.MessageDialog;

/**
 * 传入orderId
 * 
 * @author 詹命天子
 *
 */
public class SentGoodActivity extends BackActivity implements
		OnDataReturnListener {
	private EditText text;
	private MessageDialog msg;
	private String orderId;
	private LoadingDialog dialog;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sent_good);

		orderId = getIntent().getExtras().getString("orderId");
		dialog = new LoadingDialog(this);
		msg = new MessageDialog(this);
		text = (EditText) findViewById(R.id.activity_edittext_code);
	}

	private void setText(String text) {
		this.text.setText(text);
	}

	private String getText() {
		return text.getText().toString();
	}

	public void onScan(View v) {
		LogCat.info("onScan");
		// TODO
		// startActivityForResult(intent, requestCode);
	}

	public void onCommit(View v) {
		LogCat.info("onCommit");
		if (StringUtil.isEmptyString(getText())) {
			msg.setMessage("请输入快递单号").show();
		} else {
			dialog.show();
			DC.getInstance().setOrderExpressNum(this, orderId, getText());
		}
	}

	@Override
	public void onDataReturn(String taskTag, BaseResult result, String json) {
		dialog.dismiss();
		if (result.getServiceResult()) {
			// TODO
		} else {

		}
	}
}
