package com.zhanjixun.activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.zhanjixun.R;
import com.zhanjixun.base.BackActivity;

public class AddFishGoodActivity1 extends BackActivity {
	private TextView leadText;
	private TextView title;
	private TextView back;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_addgood);
		initView();
	}

	private void initView() {
		back = (TextView) findViewById(R.id.activity_addgood_back);
		back.setText("首页");
		
		title = (TextView) findViewById(R.id.activity_addgood_titletext);
		title.setText("类别");

		leadText = (TextView) findViewById(R.id.activity_addgood_leadtext);
		leadText.setText("请选择商品类别：");
	}

	public void onNext(View v) {
		Intent intent = new Intent(this, AddFishGoodActivity2.class);
		intent.putExtra("", "");
		startActivity(intent);
	}
}
