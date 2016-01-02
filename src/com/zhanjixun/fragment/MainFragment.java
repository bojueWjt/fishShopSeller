package com.zhanjixun.fragment;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.GridView;
import android.widget.ImageView;

import com.google.gson.reflect.TypeToken;
import com.zhanjixun.R;
import com.zhanjixun.activity.AddFishGoodActivity1;
import com.zhanjixun.data.Constants;
import com.zhanjixun.data.DC;
import com.zhanjixun.domain.BaseResult;
import com.zhanjixun.domain.OnSellGood;
import com.zhanjixun.interfaces.OnDataReturnListener;
import com.zhanjixun.utils.MyGson;
import com.zhanjixun.views.MessageDialog;

public class MainFragment extends Fragment implements OnClickListener,
		OnDataReturnListener {
	private int index = 1;
	private static final int page = 7;

	private ImageView addGoodIv;
	private GridView mGridView;
	private MessageDialog msg;

	private List<OnSellGood> onSellGoods = new ArrayList<OnSellGood>();

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		return inflater.inflate(R.layout.fragment_main, container, false);
	}

	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		msg = new MessageDialog(getActivity());
		initViews();
		initDatas();
	}

	private void initDatas() {
		DC.getInstance().onSell(this, Constants.shop.getShopId(), page, index);
	}

	private void initViews() {
		addGoodIv = (ImageView) getView()
				.findViewById(R.id.image_main_add_good);
		addGoodIv.setOnClickListener(this);
		mGridView = (GridView) getView().findViewById(R.id.fragment_main_data);
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

	@Override
	public void onDataReturn(String taskTag, BaseResult result, String json) {
		if (result.getServiceResult()) {
			String categoryList = result.getResultParam().get("categoryList");
			Type type = new TypeToken<List<OnSellGood>>() {
			}.getType();

			List<OnSellGood> onSellGoods = MyGson.getInstance().fromJson(
					categoryList, type);
		} else {
			msg.setMessage(result.getResultInfo()).show();
		}
	}
}
