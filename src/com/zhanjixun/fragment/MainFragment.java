package com.zhanjixun.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.zhanjixun.R;
import com.zhanjixun.activity.AddFishGoodActivity1;

public class MainFragment extends Fragment implements OnClickListener {

	private ImageView addGoodIv;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_main, container, false);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		initViews();
	}

	private void initViews() {
		addGoodIv = (ImageView) getView()
				.findViewById(R.id.image_main_add_good);
		addGoodIv.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		String tag = (String) v.getTag();
		if (tag.equals("AddGood")) {
			Intent intent = new Intent(getActivity(),
					AddFishGoodActivity1.class);
			getActivity().startActivity(intent);
		}
	}
}
