package com.zhanjixun.factory;

import android.support.v4.app.Fragment;

import com.zhanjixun.data.Constants;
import com.zhanjixun.domain.User;
import com.zhanjixun.fragment.FarmersFragment;
import com.zhanjixun.fragment.FishermanFragment;
import com.zhanjixun.fragment.NoUserFragment;

/**
 * 商家详情工厂
 * 
 * @author 詹命天子
 *
 */
public class MeFragmentFactory {

	private static NoUserFragment noUserFragment = new NoUserFragment();
	private static FishermanFragment fishermanFragment = new FishermanFragment();
	private static FarmersFragment farmersFragment = new FarmersFragment();

	/**
	 * 根据登录状态或者用户类型加载不同的MeFragment
	 * 
	 * @return
	 */
	public static Fragment getMeFragment() {
		try {
			if (Constants.user.getUserType() == User.userType_farmer) {
				return farmersFragment;
			} else if (Constants.user.getUserType() == User.userType_fisherman) {
				return fishermanFragment;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return noUserFragment;
	}
}
