package com.zhanjixun.fragment;

import java.util.ArrayList;
import java.util.List;

import android.graphics.Matrix;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
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

	private ViewPager mViewPager;

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
		// …Ë÷√”Œ±Í◊‘  ”¶≥§∂»
		int dipToPixels = UnitUtil.DipToPixels(getActivity(), 2);
		LinearLayout.LayoutParams lp = new LayoutParams((int) (screenW / 5.0),
				dipToPixels);
		cursor.setLayoutParams(lp);
		bmpW = cursor.getHeight();
		offset = (screenW / 5 - bmpW) / 2;
		Matrix matrix = new Matrix();
		matrix.postTranslate(offset, 0);
		cursor.setImageMatrix(matrix);

		// ViewPager
		mViewPager = (ViewPager) getView().findViewById(
				R.id.viewPager_order_content);
		List<Fragment> fragments = new ArrayList<Fragment>();
		fragments.add(ocf0);
		fragments.add(ocf1);
		fragments.add(ocf2);
		fragments.add(ocf3);
		fragments.add(ocf4);
		mViewPager.setAdapter(new ViewPagerAdpter(getChildFragmentManager(),
				fragments));
		mViewPager.setOnPageChangeListener(new ViewPagerOnPageChangeListener());
		// textView
		sailTv = (TextView) getView().findViewById(R.id.text_order_sail);
		sailTv.setVisibility(View.GONE);
		try {

		} catch (Exception e) {

		}
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
			cursorAnimation(index);
			setTextViewColor(index);
			mViewPager.setCurrentItem(index);
		}

	}

	private void cursorAnimation(int index) {
		// “≥ø®1 -> “≥ø®2 ∆´“∆¡ø
		int one = offset * 2 + bmpW;
		// “≥ø®1 -> “≥ø®3 ∆´“∆¡ø
		int two = one * 2;
		// “≥ø®1 -> “≥ø®4 ∆´“∆¡ø
		int third = one * 3;
		// “≥ø®1 -> “≥ø®5∆´“∆¡ø
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
		animation.setFillAfter(true);// True:Õº∆¨Õ£‘⁄∂Øª≠Ω· ¯Œª÷√
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

	private class ViewPagerAdpter extends FragmentPagerAdapter {

		List<Fragment> fragments = null;

		public ViewPagerAdpter(FragmentManager fm, List<Fragment> fragments) {
			super(fm);
			this.fragments = fragments;
		}

		@Override
		public Fragment getItem(int arg0) {
			return fragments.get(arg0);
		}

		@Override
		public int getCount() {
			return fragments.size();
		}
	}

	private class ViewPagerOnPageChangeListener implements OnPageChangeListener {

		@Override
		public void onPageSelected(int i) {

			cursorAnimation(i);
			currIndex = i;
			setTextViewColor(i);
		}

		@Override
		public void onPageScrolled(int arg0, float arg1, int arg2) {

		}

		@Override
		public void onPageScrollStateChanged(int arg0) {

		}
	}
}
