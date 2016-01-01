package com.zhanjixun.factory;

import android.support.v4.app.Fragment;

import com.zhanjixun.data.Constants;
import com.zhanjixun.domain.User;
import com.zhanjixun.fragment.FarmersFragment;
import com.zhanjixun.fragment.FishermanFragment;
import com.zhanjixun.fragment.NoUserFragment;

/**
 * �̼����鹤��
 * 
 * @author ղ������
 *
 */
public class MeFragmentFactory {

	private static NoUserFragment noUserFragment = new NoUserFragment();
	private static FishermanFragment fishermanFragment = new FishermanFragment();
	private static FarmersFragment farmersFragment = new FarmersFragment();

	/**
	 * ���ݵ�¼״̬�����û����ͼ��ز�ͬ��MeFragment
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
