package com.zhanjixun.interfaces;

import com.zhanjixun.domain.BaseResult;

public interface OnDataReturnListener {
	public abstract void onDataReturn(String taskTag, BaseResult result,
			String json);
}
