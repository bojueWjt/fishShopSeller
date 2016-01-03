package com.zhanjixun.activity;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.zhanjixun.R;
import com.zhanjixun.adapter.TimelineAdapter;
import com.zhanjixun.base.BackActivity;
import com.zhanjixun.data.DC;
import com.zhanjixun.data.IC;
import com.zhanjixun.data.TaskTag;
import com.zhanjixun.domain.BaseResult;
import com.zhanjixun.domain.LogisticsInfo;
import com.zhanjixun.interfaces.OnDataReturnListener;
import com.zhanjixun.utils.MyGson;
import com.zhanjixun.utils.StringUtil;
import com.zhanjixun.views.LoadingDialog;
import com.zhanjixun.views.MessageDialog;

/**
 * Intent String 1,orderId ����id 2,GoodImage ��һ����Ʒ��ͼƬ
 * 
 * @author ղ������
 *
 */
public class LogisiticeActivity extends BackActivity implements
		OnDataReturnListener {

	// ����������Ϣ
	private ListView listView;
	// ��ƷͼƬ
	private ImageView order_image;
	// ����״̬
	private TextView logistice_status;
	// �������
	private TextView logistice_code;

	private Button logistics_Btn;
	// ҳ��hear������
	private String orderId;
	private LoadingDialog dialog;
	private String ordersNumber;
	// Btn״̬
	private Integer integer = 0;
	private MessageDialog msgDialog;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_order_logistics_state_information);
		orderId = getIntent().getStringExtra("orderId");
		initViews();
		initData();
	}

	private void initData() {
		dialog = new LoadingDialog(this);
		dialog.show();
		DC.getInstance().orderPostCode(this, orderId);
		IC.getInstance().setForegound(getIntent().getStringExtra("GoodImage"),
				order_image);
	}

	private void initState() {
		switch (integer) {
		case 0:
			logistice_status.setText("����������");
			break;
		case 1:
			logistice_status.setText("������");
			break;
		case 2:
			logistice_status.setText("���͹��̳�������");
			break;
		case 3:
			logistice_status.setText("��ǩ��");
			break;
		case 4:
			logistice_status.setText("������ǩ");
			break;
		case 5:
			logistice_status.setText("�����ɼ�");
			break;
		case 6:
			logistice_status.setText("�����˻���");
			break;
		default:
			logistice_status.setText("");
			break;
		}
		// 0����;�������ﴦ����������У�
		// 1���������������ɿ�ݹ�˾���ղ��Ҳ����˵�һ��������Ϣ��
		// 2�����ѣ�������͹��̳������⣻
		// 3��ǩ�գ��ռ�����ǩ�գ�
		// 4����ǩ�������������û���ǩ��������ԭ���˻أ����ҷ������Ѿ�ǩ�գ�
		// 5���ɼ�����������ڽ���ͬ���ɼ���
		// 6���˻أ������������˻ط����˵�;��
	}

	private void initViews() {
		msgDialog = new MessageDialog(this);
		listView = (ListView) findViewById(R.id.logistice_informantion_listview);
		order_image = (ImageView) findViewById(R.id.logistics_state_information_Orderimage);
		logistice_status = (TextView) findViewById(R.id.logistics_state_information);
		logistice_code = (TextView) findViewById(R.id.logistics_status_information_code);
		logistics_Btn = (Button) findViewById(R.id.logistice_information_status_ensureBtn);
	}

	@Override
	public void onDataReturn(String taskTag, BaseResult result, String json) {
		dialog.dismiss();
		if (TaskTag.ORDER_POST_CODE.equals(taskTag)) {
			ordersNumber = result.getResultParam().get("ordersNumber");
			if (!StringUtil.isEmptyString(ordersNumber)) {
				DC.getInstance().getLogistic(this, ordersNumber);
				logistice_code.setText(ordersNumber);
			} else {
				msgDialog.setMessage("û�л�ȡ����ݵ��ţ�");
				msgDialog.show();
				logistics_Btn.setVisibility(View.INVISIBLE);
			}
		} else if (TaskTag.LOGISTIC.equals(taskTag)) {
			if (!StringUtil.isEmptyString(json)) {
				LogisticsInfo i = MyGson.getInstance().fromJson(json,
						LogisticsInfo.class);
				if (i.getStatus() == 200) {
					TimelineAdapter timeAdapter = new TimelineAdapter(this, i);
					listView.setAdapter(timeAdapter);
					integer = i.getState();
					initState();
					// logistics_Btn.setOnClickListener(this);
					setListViewHeightBasedOnChildren(listView);
				} else {
					msgDialog.setMessage(i.getMessage());
					msgDialog.show();
					logistics_Btn.setVisibility(View.INVISIBLE);
				}
			} else {
				msgDialog.setMessage("����������ʧ�ܣ�");
				msgDialog.show();
				logistics_Btn.setVisibility(View.INVISIBLE);
			}
		}
		// else if (TaskTag.ENSUREGET.equals(taskTag)) {
		// msgDialog.setMessage(result.getResultInfo());
		// msgDialog.show();
		// }
	}

	// @Override
	// public void onClick(View v) {
	// dialog.show();
	// DC.getInstance().ensureGet(this, orderId);
	// }

	protected void setListViewHeightBasedOnChildren(ListView listView) {
		if (listView == null)
			return;
		ListAdapter listAdapter = listView.getAdapter();
		if (listAdapter == null) {
			return;
		}
		int totalHeight = 0;
		for (int i = 0; i < listAdapter.getCount(); i++) {
			View listItem = listAdapter.getView(i, null, listView);
			listItem.measure(0, 0);
			totalHeight += listItem.getMeasuredHeight();
		}
		ViewGroup.LayoutParams params = listView.getLayoutParams();
		params.height = totalHeight
				+ (listView.getDividerHeight() * (listAdapter.getCount() - 1));
		listView.setLayoutParams(params);
	}
}
