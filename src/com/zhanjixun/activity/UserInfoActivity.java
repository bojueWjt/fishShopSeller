package com.zhanjixun.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

import com.zhanjixun.R;
import com.zhanjixun.base.BackActivity;
import com.zhanjixun.data.Constants;
import com.zhanjixun.utils.SPUtil;
import com.zhanjixun.views.ConfirmDialog;

public class UserInfoActivity extends BackActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_me_myinfo);
	}

	public void onExit(View v) {
		ConfirmDialog dialog = new ConfirmDialog(this, "确定退出当前帐号?");
		dialog.setNegativeButton("返回", null);
		dialog.setPositiveButton("退出", new OnClickListener() {

			@Override
			public void onClick(View v) {
				Constants.user = null;
				SPUtil.deleteSP(UserInfoActivity.this, Constants.SP_USER);

				Intent intent = new Intent(UserInfoActivity.this,
						LoginActivity.class);
				intent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TASK);
				startActivity(intent);
				UserInfoActivity.this.finish();
			}
		});
		dialog.show();

	}
}
