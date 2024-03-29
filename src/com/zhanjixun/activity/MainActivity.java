package com.zhanjixun.activity;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.zhanjixun.R;
import com.zhanjixun.data.Constants;
import com.zhanjixun.domain.Shop;
import com.zhanjixun.domain.User;
import com.zhanjixun.factory.MeFragmentFactory;
import com.zhanjixun.fragment.MainFragment;
import com.zhanjixun.fragment.OrderFragment;
import com.zhanjixun.fragment.SailOrderFragment;
import com.zhanjixun.utils.LogCat;
import com.zhanjixun.utils.SPUtil;

public class MainActivity extends FragmentActivity {

	private TextView mainTv;
	private TextView orderTv;
	private TextView meTv;

	private ImageView meImg;
	private ImageView orderImg;
	private ImageView mainImg;

	private final static MainFragment mainFragment = new MainFragment();
	private final static OrderFragment orderFragment = new OrderFragment();
	private final static SailOrderFragment sailOrderFragment = new SailOrderFragment();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		// 初始化软件设置
		initSoftwareData();

		initViews();
		initData();
	}

	private void initSoftwareData() {
		// 读取用户信息
		Constants.user = User.getInstance(this);
		Constants.shop = Shop.getInstance(this);
		// 航行状态设置
		Constants.sailModel = Boolean.parseBoolean(SPUtil.getString(this,
				Constants.SP_SAIL_MODEL, Constants.SP_SAIL_MODEL, "false"));
	}

	private void initViews() {
		mainTv = (TextView) findViewById(R.id.main_text_main);
		orderTv = (TextView) findViewById(R.id.main_text_order);
		meTv = (TextView) findViewById(R.id.main_text_me);

		mainImg = (ImageView) findViewById(R.id.main_btn_main);
		orderImg = (ImageView) findViewById(R.id.main_btn_order);
		meImg = (ImageView) findViewById(R.id.main_btn_me);
	}

	private void initData() {
		setPressed(0);
		setContent(0);
	}

	public void onClick(View v) {
		String tag = (String) v.getTag();

		if (tag.equals("MAIN")) {
			initData();
		} else if (tag.equals("ORDER")) {
			setPressed(1);
			setContent(1);
		} else if (tag.equals("ME")) {
			setPressed(2);
			setContent(2);
		}
	}

	private void setPressed(int index) {
		mainTv.setTextColor(getResources().getColor(R.color.theme_normal));
		orderTv.setTextColor(getResources().getColor(R.color.theme_normal));
		meTv.setTextColor(getResources().getColor(R.color.theme_normal));

		mainImg.setImageResource(R.drawable.activity_main_main_normal);
		orderImg.setImageResource(R.drawable.activity_main_order_normal);
		meImg.setImageResource(R.drawable.activity_main_me_normal);

		switch (index) {
		case 0:
			mainTv.setTextColor(getResources().getColor(R.color.theme));
			mainImg.setImageResource(R.drawable.activity_main_main_pressed);
			break;
		case 1:
			orderTv.setTextColor(getResources().getColor(R.color.theme));
			orderImg.setImageResource(R.drawable.activity_main_order_pressed);
			break;
		case 2:
			meTv.setTextColor(getResources().getColor(R.color.theme));
			meImg.setImageResource(R.drawable.activity_main_me_pressed);
			break;
		default:
			break;
		}
	}

	private void setContent(int index) {
		FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
		switch (index) {
		case 0:
			LogCat.verbose("发布商品");
			ft.replace(R.id.main_content, mainFragment);
			ft.commit();
			break;
		case 1:
			if (Constants.sailModel) {
				setSailOrderFragment();
			} else {
				setOrderFragment();
			}
			break;
		case 2:
			LogCat.verbose("商家信息");
			ft.replace(R.id.main_content, MeFragmentFactory.getMeFragment());
			ft.commit();
			break;
		default:
			break;
		}
	}

	public void setSailOrderFragment() {
		FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
		LogCat.verbose("商品订单--航行模式");
		ft.replace(R.id.main_content, sailOrderFragment);
		ft.commit();
	}

	public void setOrderFragment() {
		FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
		LogCat.verbose("商品订单--普通模式");
		ft.replace(R.id.main_content, orderFragment);
		ft.commit();
	}
}
