package com.zhanjixun.fragment;

import com.zhanjixun.R;
import com.zhanjixun.activity.LoginActivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;

public class NoUserFragment extends Fragment implements OnClickListener {

	private Button login;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_no_user, container, false);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		initViews();
	}

	private void initViews() {
		login = (Button) getView().findViewById(R.id.fragment_no_user_login);
		login.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		Intent intent = new Intent();
		intent.setClass(getActivity(), LoginActivity.class);
		getActivity().startActivity(intent);
	}
}
