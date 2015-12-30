package com.zhanjixun.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.TextView;

import com.zhanjixun.R;
import com.zhanjixun.activity.MainActivity;

public class SailOrderFragment extends Fragment implements OnClickListener {
	private TextView sailTv;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_sail_order, container, false);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		initViews();
	}

	private void initViews() {
		sailTv = (TextView) getView().findViewById(R.id.text_sailorder_outsail);
		sailTv.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		MainActivity activity = (MainActivity) getActivity();
		activity.setOrderFragment();
	}
}
