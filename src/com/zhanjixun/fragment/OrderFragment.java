package com.zhanjixun.fragment;

import android.graphics.Matrix;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

import com.zhanjixun.R;
import com.zhanjixun.activity.MainActivity;
import com.zhanjixun.utils.ScreenUtil;
import com.zhanjixun.utils.UnitUtil;

public class OrderFragment extends Fragment {

	private MainActivity activity;

	private final OrderContentFragment ocf0 = new OrderContentFragment(0);
	private final OrderContentFragment ocf1 = new OrderContentFragment(1);
	private final OrderContentFragment ocf2 = new OrderContentFragment(2);
	private final OrderContentFragment ocf3 = new OrderContentFragment(3);
	private final OrderContentFragment ocf4 = new OrderContentFragment(4);

	private TextView t1;
	private TextView t2;
	private TextView t3;
	private TextView t4;
	private TextView t5;
	private ImageView cursor;

	private int bmpW;
	private int offset;
	private int currIndex = 0;
	private TextView sailTv;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_order, container, false);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		activity = (MainActivity) getActivity();
		initViews();
		initData();
	}

	private void initData() {
		setFragmentContent(0);
		cursorAnimation(0);
		setTextViewColor(0);
	}

	private void initViews() {
		// TextView
		t1 = (TextView) getActivity().findViewById(R.id.fragment_order_all);
		t2 = (TextView) getActivity().findViewById(R.id.fragment_order_waitPay);
		t3 = (TextView) getActivity()
				.findViewById(R.id.fragment_order_waitSend);
		t4 = (TextView) getActivity().findViewById(
				R.id.fragment_order_waitCargo);
		t5 = (TextView) getActivity().findViewById(
				R.id.fragment_order_waitAppraise);

		t1.setOnClickListener(new OnMenuClickListener(0));
		t2.setOnClickListener(new OnMenuClickListener(1));
		t3.setOnClickListener(new OnMenuClickListener(2));
		t4.setOnClickListener(new OnMenuClickListener(3));
		t5.setOnClickListener(new OnMenuClickListener(4));

		// imageView
		cursor = (ImageView) getActivity()
				.findViewById(R.id.image_order_cursor);
		int screenW = ScreenUtil.getWidth(getActivity());
		// ÉèÖÃÓÎ±ê×ÔÊÊÓ¦³¤¶È
		int dipToPixels = UnitUtil.DipToPixels(getActivity(), 2);
		LinearLayout.LayoutParams lp = new LayoutParams((int) (screenW / 5.0),
				dipToPixels);
		cursor.setLayoutParams(lp);
		bmpW = cursor.getHeight();
		offset = (screenW / 5 - bmpW) / 2;
		Matrix matrix = new Matrix();
		matrix.postTranslate(offset, 0);
		cursor.setImageMatrix(matrix);

		// textView
		sailTv = (TextView) getView().findViewById(R.id.text_order_sail);
		sailTv.setOnClickListener(new OnSailTextViewClickListener());
	}

	private class OnSailTextViewClickListener implements View.OnClickListener {

		@Override
		public void onClick(View v) {
			activity.setSailOrderFragment();
		}
	}

	private class OnMenuClickListener implements View.OnClickListener {

		private int index = 0;

		public OnMenuClickListener(int i) {
			index = i;
		}

		@Override
		public void onClick(View v) {
			setFragmentContent(index);
			cursorAnimation(index);
			setTextViewColor(index);
		}

	}

	private void setFragmentContent(int index) {

		FragmentTransaction ft = getChildFragmentManager().beginTransaction();
		switch (index) {
		case 0:
			ft.replace(R.id.order_content, ocf0);
			ft.commit();
			break;
		case 1:
			ft.replace(R.id.order_content, ocf1);
			ft.commit();
			break;
		case 2:
			ft.replace(R.id.order_content, ocf2);
			ft.commit();
			break;
		case 3:
			ft.replace(R.id.order_content, ocf3);
			ft.commit();
			break;
		case 4:
			ft.replace(R.id.order_content, ocf4);
			ft.commit();
			break;
		default:
			break;
		}
	}

	private void cursorAnimation(int index) {
		// Ò³¿¨1 -> Ò³¿¨2 Æ«ÒÆÁ¿
		int one = offset * 2 + bmpW;
		// Ò³¿¨1 -> Ò³¿¨3 Æ«ÒÆÁ¿
		int two = one * 2;
		// Ò³¿¨1 -> Ò³¿¨4 Æ«ÒÆÁ¿
		int third = one * 3;
		// Ò³¿¨1 -> Ò³¿¨5Æ«ÒÆÁ¿
		int four = one * 4;

		Animation animation = null;
		switch (index) {
		case 0:
			animation = new TranslateAnimation(one * currIndex, 0, 0, 0);
			break;
		case 1:
			if (currIndex == 0) {
				animation = new TranslateAnimation(offset, one, 0, 0);
			} else {
				animation = new TranslateAnimation(one * currIndex, one, 0, 0);
			}
			break;
		case 2:
			if (currIndex == 0) {
				animation = new TranslateAnimation(offset, two, 0, 0);
			} else {
				animation = new TranslateAnimation(one * currIndex, two, 0, 0);
			}
			break;
		case 3:
			if (currIndex == 0) {
				animation = new TranslateAnimation(offset, third, 0, 0);
			} else {
				animation = new TranslateAnimation(one * currIndex, third, 0, 0);
			}
			break;
		case 4:
			if (currIndex == 0) {
				animation = new TranslateAnimation(offset, four, 0, 0);
			} else {
				animation = new TranslateAnimation(one * currIndex, four, 0, 0);
			}
			break;
		}
		currIndex = index;
		animation.setFillAfter(true);// True:Í¼Æ¬Í£ÔÚ¶¯»­½áÊøÎ»ÖÃ
		animation.setDuration(150);
		cursor.startAnimation(animation);
	}

	private void setTextViewColor(int index) {
		t1.setTextColor(0xaa000000);
		t2.setTextColor(0xaa000000);
		t3.setTextColor(0xaa000000);
		t4.setTextColor(0xaa000000);
		t5.setTextColor(0xaa000000);
		switch (index) {
		case 0:
			t1.setTextColor(getActivity().getResources()
					.getColor(R.color.theme));
			break;
		case 1:
			t2.setTextColor(getActivity().getResources()
					.getColor(R.color.theme));
			break;
		case 2:
			t3.setTextColor(getActivity().getResources()
					.getColor(R.color.theme));
			break;
		case 3:
			t4.setTextColor(getActivity().getResources()
					.getColor(R.color.theme));
			break;
		case 4:
			t5.setTextColor(getActivity().getResources()
					.getColor(R.color.theme));
			break;
		default:
			break;
		}
	}

}
