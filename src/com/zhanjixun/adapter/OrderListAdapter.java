package com.zhanjixun.adapter;

import java.util.List;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.zhanjixun.R;
import com.zhanjixun.data.IC;
import com.zhanjixun.domain.Good;
import com.zhanjixun.domain.Order;
import com.zhanjixun.utils.UnitUtil;

/**
 * ���ض����б����������ͨ��
 * 
 * @author ղ������
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

	@Override
	public View getView(int position, View v, ViewGroup parent) {
		Order order = orders.get(position);
		ViewHolder vh = null;
		if (v == null) {
			vh = new ViewHolder();
			v = LayoutInflater.from(context).inflate(R.layout.item_order_home,
					null);
			vh.buyerName = (TextView) v
					.findViewById(R.id.order_home_item_shopTitle_name);
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
		vh.buyerName.setText(order.getBuyerName());
		// ������ƷͼƬ
		Good good = order.getOrdersDetail().get(0);
		IC.getInstance().setForegound(good.getGoodsPhoto(), vh.goodImage);

		vh.goodName.setText(good.getGoodsName() + "");
		vh.goodSize.setText(good.getSku() + "");
		vh.goodPirce.setText(UnitUtil.toRMB(good.getPrice()) + "/"
				+ good.getUnit());
		vh.goodNumber.setText("��" + good.getNumber());
		vh.goodAllNumber.setText("��" + order.getOrdersDetail().size()
				+ "����Ʒ  �ϼƣ�");
		vh.allPiece.setText(UnitUtil.toRMB(order.getTotalprice()));
		vh.postagePrice.setText("(���˷�"
				+ UnitUtil.toRMB(order.getPostagePrice()) + ")");
		// v.setOnClickListener(myOnClickListener);
		return v;
	}

	final class ViewHolder {
		/**
		 * ��Ʒ��������
		 */
		TextView buyerName;
		/**
		 * ��Ʒ��
		 */
		TextView goodName;
		/**
		 * ��Ʒ����
		 */
		TextView goodNumber;
		/**
		 * ��Ʒ�۸�
		 */
		TextView goodPirce;
		/**
		 * ��Ʒ���м۸�
		 */
		TextView allPiece;
		/**
		 * ��Ʒ��� �еȴ�С
		 */
		TextView goodSize;
		/**
		 * ������Ʒ����
		 */
		TextView goodAllNumber;
		/**
		 * ��Ʒ�˷�����
		 */
		TextView postagePrice;
		/**
		 * ��ƷͼƬ
		 */
		ImageView goodImage;
	}
}
