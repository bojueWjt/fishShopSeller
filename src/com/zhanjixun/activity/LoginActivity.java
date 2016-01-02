package com.zhanjixun.activity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.zhanjixun.R;
import com.zhanjixun.data.Constants;
import com.zhanjixun.data.DC;
import com.zhanjixun.data.TaskTag;
import com.zhanjixun.domain.BaseResult;
import com.zhanjixun.domain.Shop;
import com.zhanjixun.domain.User;
import com.zhanjixun.interfaces.OnDataReturnListener;
import com.zhanjixun.utils.MyGson;
import com.zhanjixun.utils.StringUtil;
import com.zhanjixun.views.LoadingDialog;
import com.zhanjixun.views.MessageDialog;

public class LoginActivity extends Activity implements OnClickListener,
		OnDataReturnListener {

	private EditText usernameEt;
	private EditText passwordEt;
	private TextView forgetPasswordTv;
	private Button enterBtn;
	private TextView newuserTv;
	private MessageDialog msgDialog;
	private LoadingDialog dialog;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		if (isLoginIn()) {
			toMainActivity();
		}
		setContentView(R.layout.activity_login);
		msgDialog = new MessageDialog(this);
		dialog = new LoadingDialog(this);
		initViews();
	}

	private boolean isLoginIn() {
		try {
			// 测试用户信息是否存在
			User.getInstance(this).getUserId();
			return true;
		} catch (Exception e) {
			
		}
		return false;
	}

	private void initViews() {
		usernameEt = (EditText) findViewById(R.id.login_username);
		passwordEt = (EditText) findViewById(R.id.login_password);

		forgetPasswordTv = (TextView) findViewById(R.id.login_forget_password);
		enterBtn = (Button) findViewById(R.id.login_confirm);
		newuserTv = (TextView) findViewById(R.id.login_new_user);

		enterBtn.setOnClickListener(this);
		newuserTv.setOnClickListener(this);
		forgetPasswordTv.setOnClickListener(this);
	}

	private String getUserName() {
		return usernameEt.getText().toString();
	}

	private String getPassword() {
		return passwordEt.getText().toString();
	}

	private boolean isEmptyText() {
		return StringUtil.isEmptyString(getUserName())
				|| StringUtil.isEmptyString(getPassword());
	}

	@Override
	public void onClick(View v) {
		int id = v.getId();
		switch (id) {
		case R.id.login_forget_password:
			// TODO
			break;
		case R.id.login_confirm:
			if (isEmptyText()) {
				msgDialog.setMessage("请输入完整信息！").show();
			} else {
				dialog.show();
				DC.getInstance().login(this, getUserName(), getPassword());
			}
			break;
		case R.id.login_new_user:
			// TODO
			break;
		default:
			break;
		}
	}

	@Override
	public void onDataReturn(String taskTag, BaseResult result, String json) {
		dialog.dismiss();
		if (result.getServiceResult()) {
			if (taskTag.equals(TaskTag.LOGIN)) {
				try {
					// 读取用户信息
					String string = result.getResultParam().get("user");
					User user = MyGson.getInstance().fromJson(string,
							User.class);
					Constants.user = user;
					// 读取商店信息
					String shopKey = result.getResultParam().get("shop");
					Shop shop = MyGson.getInstance().fromJson(shopKey,
							Shop.class);
					Constants.shop = shop;

					Toast.makeText(this, result.getResultInfo(),
							Toast.LENGTH_LONG).show();
					Constants.user.saveInstance(this);// 保存用户信息
					Constants.shop.saveInstance(this);// 保存商店信息

					toMainActivity();
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		} else {
			msgDialog.setMessage(result.getResultInfo()).show();
		}
	}

	private void toMainActivity() {
		Intent intent = new Intent(this, MainActivity.class);
		intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
		startActivity(intent);
		this.finish();
	}
}
