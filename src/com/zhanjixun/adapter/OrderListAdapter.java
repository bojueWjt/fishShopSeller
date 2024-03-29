package com.zhanjixun.adapter;

import java.util.List;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.zhanjixun.R;
import com.zhanjixun.activity.OrderInfoActivity;
import com.zhanjixun.data.IC;
import com.zhanjixun.domain.OrderGood;
import com.zhanjixun.domain.Order;
import com.zhanjixun.utils.UnitUtil;

/**
 * 加载订单列表的适配器，通用
 * 
 * @author 詹命天子
 *
 */
public class OrderListAdapter extends BaseAdapter {

	private List<Order> orders;
	private Context context;

	public OrderListAdapter(Context context, List<Order> orders) {
		this.orders = orders;
		this.context = context;
	}

	@Override
	public int getCount() {
		return orders.size();
	}

	@Override
	public Object getItem(int position) {
		return orders.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@SuppressLint("InflateParams")
	@Override
	public View getView(int position, View v, ViewGroup parent) {
		ViewHolder vh = null;
		if (v == null) {
			vh = new ViewHolder();
			v = LayoutInflater.from(context).inflate(R.layout.item_order_home,
					null);
			vh.buyerName = (TextView) v
					.findViewById(R.id.order_home_item_shopTitle_name);
			vh.orderState = (TextView) v
					.findViewById(R.id.order_home_item_order_state);
			vh.goodImage = (ImageView) v
					.findViewById(R.id.order_home_item_shop_image);
			vh.allPiece = (TextView) v
					.findViewById(R.id.order_home_item_allmoney);
			vh.postagePrice = (TextView) v
					.findViewById(R.id.order_home_logistics_money);
			vh.goodName = (TextView) v
					.findViewById(R.id.order_home_item_shop_goodname);
			vh.goodPirce = (TextView) v
					.findViewById(R.id.order_home_item_shop_goodprice);
			vh.goodNumber = (TextView) v
					.findViewById(R.id.order_home_item_shop_goodnumber);
			vh.goodAllNumber = (TextView) v
					.findViewById(R.id.order_home_item_shop_number2);
			vh.goodSize = (TextView) v
					.findViewById(R.id.order_home_item_shop_size);
			v.setTag(vh);
		} else {
			vh = (ViewHolder) v.getTag();
			v.setTag(vh);
		}
		Order order = orders.get(position);
		initView(order, vh);
		v.setOnClickListener(new MyOnClickListener(order.getOrdersId()));
		return v;
	}

	private void initView(Order order, ViewHolder vh) {
		vh.buyerName.setText(order.getBuyerName());
		OrderGood good = order.getOrdersDetail().get(0);
		IC.getInstance().setForegound(good.getGoodsPhoto(), vh.goodImage);
		switch (order.getState()) {
		case Order.state_un_pay:
			vh.orderState.setText("等待买家付款");
			break;
		case Order.state_un_sent:
			vh.orderState.setText("等待卖家发货");
			break;
		case Order.state_un_get:
			vh.orderState.setText("等待买家签收");
			break;
		case Order.state_un_commet:
			vh.orderState.setText("等待买家评论");
			break;
		case Order.state_finish:
			vh.orderState.setText("已完成");
			break;
		default:
			break;
		}
		vh.goodName.setText(good.getGoodsName() + "");
		vh.goodSize.setText(good.getSku() + "");
		vh.goodPirce.setText(UnitUtil.toRMB(good.getPrice()) + "/"
				+ good.getUnit());
		vh.goodNumber.setText("×" + good.getNumber());
		vh.goodAllNumber.setText("共" + order.getOrdersDetail().size()
				+ "件商品  合计：");
		vh.allPiece.setText(UnitUtil.toRMB(order.getTotalprice()));
		vh.postagePrice.setText("(含运费"
				+ UnitUtil.toRMB(order.getPostagePrice()) + ")");
	}

	private class MyOnClickListener implements View.OnClickListener {
		private String orderId;

		public MyOnClickListener(String orderId) {
			this.orderId = orderId;
		}

		@Override
		public void onClick(View v) {
			Intent intent = new Intent(context, OrderInfoActivity.class);
			intent.putExtra("orderId", orderId);
			context.startActivity(intent);
		}
	}

	private final class ViewHolder {
		/**
		 * 商品店名标题
		 */
		TextView buyerName;
		/**
		 * 订单状态
		 */
		TextView orderState;
		/**
		 * 商品名
		 */
		TextView goodName;
		/**
		 * 商品数量
		 */
		TextView goodNumber;
		/**
		 * 商品价格
		 */
		TextView goodPirce;
		/**
		 * 商品所有价格
		 */
		TextView allPiece;
		/**
		 * 商品规格 中等大小
		 */
		TextView goodSize;
		/**
		 * 订单商品数量
		 */
		TextView goodAllNumber;
		/**
		 * 商品运费数量
		 */
		TextView postagePrice;
		/**
		 * 商品图片
		 */
		ImageView goodImage;
	}
}
