package com.zhanjixun.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.zhanjixun.R;
import com.zhanjixun.base.BackActivity;

public class SetPriceActivity extends BackActivity {
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_set_price);
	}

	public void onNext(View v) {
		Intent intent = new Intent(this, AddGoodPreviewActivity.class);
		intent.putExtra("", "");
		startActivity(intent);
	}
}
