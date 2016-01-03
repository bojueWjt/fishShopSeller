package com.zhanjixun.activity;

import java.util.List;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.zhanjixun.R;
import com.zhanjixun.base.BackActivity;
import com.zhanjixun.data.DC;
import com.zhanjixun.data.IC;
import com.zhanjixun.domain.BaseResult;
import com.zhanjixun.domain.Order;
import com.zhanjixun.domain.OrderGood;
import com.zhanjixun.interfaces.OnDataReturnListener;
import com.zhanjixun.utils.MyGson;
import com.zhanjixun.utils.UnitUtil;
import com.zhanjixun.views.LoadingDialog;
import com.zhanjixun.views.MessageDialog;

/**
 * 上级Activity传入orderId
 * 
 * @author 詹命天子
 *
 */
public class OrderInfoActivity extends BackActivity implements
		OnDataReturnListener, OnClickListener {

	private String orderId;
	private TextView orderStateTv;
	private TextView orderStateMsgTv;
	private ImageView sellerBg;
	private TextView geterNameTv;
	private TextView geterPhoneTv;
	private TextView addressTv;
	private TextView sellerName;
	private LinearLayout goodContent;
	private TextView goodSum;
	private TextView allPrice;
	private TextView postPrice;
	private Button btn;
	private LoadingDialog dialog;
	private MessageDialog msg;
	private Order order;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_order_info);

		dialog = new LoadingDialog(this);
		msg = new MessageDialog(this);

		orderId = getIntent().getExtras().getString("orderId");
		initView();
	}

	private void initView() {
		orderStateTv = (TextView) findViewById(R.id.orderinfo_state_text);
		orderStateMsgTv = (TextView) findViewById(R.id.orderinfo_state_msg);
		sellerBg = (ImageView) findViewById(R.id.orderinfo_sellerface);
		geterNameTv = (TextView) findViewById(R.id.orderinfo__user_name);
		geterPhoneTv = (TextView) findViewById(R.id.orderinfo__user_phone);
		addressTv = (TextView) findViewById(R.id.orderinfo__user_address);
		sellerName = (TextView) findViewById(R.id.orderinfo_sellername_text);
		goodContent = (LinearLayout) findViewById(R.id.orderinfo_content);
		goodSum = (TextView) findViewById(R.id.order_home_item_shop_number2);
		allPrice = (TextView) findViewById(R.id.order_home_item_allmoney);
		postPrice = (TextView) findViewById(R.id.order_home_logistics_money);

		btn = (Button) findViewById(R.id.orderinfo_Btn);
		// 获取订单数据
		dialog.show();
		DC.getInstance().getOrderInfo(this, orderId);

	}

	@Override
	public void onDataReturn(String taskTag, BaseResult result, String json) {
		dialog.dismiss();
		if (result.getServiceResult()) {
			order = MyGson.getInstance().fromJson(
					result.getResultParam().get("orders"), Order.class);
			showData();
		} else {
			msg = new MessageDialog(this, result.getResultInfo());
			msg.show();
		}
	}

	private void showData() {
		if (order.getOrdersId() != null) {
			setAdress();
			setGoods();
			setOrderState();
			IC.getInstance().setBlurForegound(this, order.getShopPhoto(),
					sellerBg);
		}
	}

	private void setGoods() {
		sellerName.setText(order.getShopKeeperName());
		List<OrderGood> goods = order.getOrdersDetail();
		for (OrderGood g : goods) {
			// 加载布局
			View v = View.inflate(this, R.layout.item_order_gooditem, null);
			ImageView face = (ImageView) v
					.findViewById(R.id.face_item_gooditem);
			TextView name = (TextView) v
					.findViewById(R.id.goodname_item_gooditem);
			TextView size = (TextView) v.findViewById(R.id.size_item_gooditem);
			TextView price = (TextView) v
					.findViewById(R.id.price_item_gooditem);
			TextView number = (TextView) v
					.findViewById(R.id.number_item_gooditem);

			IC.getInstance().setForegound(g.getGoodsPhoto(), face);
			name.setText(g.getGoodsName());
			size.setText(g.getSkuId());
			price.setText(UnitUtil.toRMB(g.getPrice()) + "/" + g.getUnit());
			number.setText("×" + g.getNumber());
			goodContent.addView(v);
		}
		goodSum.setText("共" + goods.size() + "件商品  合计：");
		allPrice.setText(UnitUtil.toRMB(order.getTotalprice()));
		postPrice.setText("(含运费" + UnitUtil.toRMB(order.getPostagePrice())
				+ ")");
	}

	private void setAdress() {
		geterNameTv.setText("收货人：" + order.getUserName());
		geterPhoneTv.setText(order.getPhone());
		addressTv.setText("收货地址：" + order.getSendAddress());
	}

	private void setOrderState() {
		int state = order.getState();

		switch (state) {
		case Order.state_un_pay:
			orderStateTv.setText("等待买家付款");
			orderStateMsgTv.setText("");
			btn.setVisibility(View.INVISIBLE);
			break;
		case Order.state_un_sent:
			orderStateTv.setText("等待卖家发货");
			orderStateMsgTv.setText("");
			btn.setText("填写发货信息");
			btn.setTag(Order.state_un_sent);
			btn.setOnClickListener(this);// 发货事件
			break;
		case Order.state_un_get:
			orderStateTv.setText("卖家已发货");
			orderStateMsgTv.setText("");
			btn.setText("查看物流");
			btn.setTag(Order.state_un_get);
			btn.setOnClickListener(this);// 查看物流事件
			break;
		case Order.state_un_commet:
			orderStateTv.setText("已签收");
			orderStateMsgTv.setText("");
			btn.setVisibility(View.INVISIBLE);
			break;
		case Order.state_finish:
			orderStateTv.setText("已完成");
			orderStateMsgTv.setText("");
			btn.setVisibility(View.INVISIBLE);
			break;
		default:
			break;
		}
	}

	@Override
	public void onClick(View v) {
		int tag = (Integer) v.getTag();
		if (tag == Order.state_un_sent) {
			Intent intent = new Intent(this, SentGoodActivity.class);
			intent.putExtra("orderId", orderId);
			startActivity(intent);
		} else if (tag == Order.state_un_get) {
			Intent intent = new Intent(this, LogisiticeActivity.class);
			try {
				intent.putExtra("orderId", orderId);
				intent.putExtra("GoodImage", order.getOrdersDetail().get(0)
						.getGoodsPhoto());
			} catch (Exception e) {
				e.printStackTrace();
			}
			startActivity(intent);
		}
	}
}
