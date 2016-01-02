package com.zhanjixun.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.zhanjixun.R;
import com.zhanjixun.activity.UserInfoActivity;
import com.zhanjixun.data.Constants;
import com.zhanjixun.data.IC;
import com.zhanjixun.views.RoundImageView;

public class FishermanFragment extends Fragment implements OnClickListener {
	private ImageView rela_face_bg;
	private RoundImageView image_face;
	private TextView userName;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_fisherman, container, false);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		initViews();
		initData();
	}

	private void initData() {
		IC.getInstance()
				.setForegound(Constants.user.getHeadImage(), image_face);
		IC.getInstance().setBlurForegound(getActivity(),
				Constants.user.getHeadImage(), rela_face_bg);
		userName.setText(Constants.user.getUserName());
	}

	private void initViews() {
		rela_face_bg = (ImageView) getActivity().findViewById(R.id.image_me_bg);
		image_face = (RoundImageView) getActivity().findViewById(
				R.id.img_me_face);
		image_face.setOnClickListener(this);
		userName = (TextView) getActivity().findViewById(R.id.text_me_username);
	}

	@Override
	public void onClick(View v) {
		String tag = (String) v.getTag();
		if (tag.equals("face")) {
			Intent intent = new Intent(getActivity(), UserInfoActivity.class);
			getActivity().startActivity(intent);
		}
	}
}
