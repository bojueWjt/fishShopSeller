package com.zhanjixun.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.zhanjixun.R;
import com.zhanjixun.base.BackActivity;

public class AddFishGoodActivity2 extends BackActivity {
	private TextView leadText;
	private TextView back;
	private TextView title;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_addgood);
		initView();
	}

	private void initView() {
		back = (TextView) findViewById(R.id.activity_addgood_back);
		back.setText("���");

		title = (TextView) findViewById(R.id.activity_addgood_titletext);
		title.setText("����");

		leadText = (TextView) findViewById(R.id.activity_addgood_leadtext);
		leadText.setText("��ѡ����Ʒ���");
	}

	public void onNext(View v) {
	}
}