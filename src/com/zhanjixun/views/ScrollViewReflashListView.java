package com.zhanjixun.views;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.AbsListView;
import android.widget.AbsListView.OnScrollListener;
import android.widget.LinearLayout;
import android.widget.ListView;

import com.zhanjixun.R;

public class ScrollViewReflashListView extends ListView implements OnScrollListener {
	private int firstVisibleItemPosition; // 屏幕显示在第一个的item的索引
	private int downY; // 按下时y轴的偏移量
	private int headerViewHeight; // 头布局的高度
	private LinearLayout headerView; // 头布局的对象

	private final int DOWN_PULL_REFRESH = 0; // 下拉刷新状态
	private final int RELEASE_REFRESH = 1; // 松开刷新
	private final int REFRESHING = 2; // 正在刷新中
	private int currentState = DOWN_PULL_REFRESH; // 头布局的状态: 默认为下拉刷新状态

	private Animation upAnimation; // 向上旋转的动画
	private Animation downAnimation; // 向下旋转的动画

	private OnRefreshListener mOnRefershListener;
	private boolean isScrollToBottom; // 是否滑动到底部
	private View footerView; // 脚布局的对象
	private int footerViewHeight; // 脚布局的高度
	private boolean isLoadingMore = false; // 是否正在加载更多中

	public ScrollViewReflashListView(Context context) {
		super(context);
	}

	public ScrollViewReflashListView(Context context, AttributeSet attrs) {
		super(context, attrs);
		initHeaderView();
		initFooterView();

		setOnScrollListener(this);
	}

	/**
	 * 适应ScrollView
	 */
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		int expandSpec = MeasureSpec.makeMeasureSpec(Integer.MAX_VALUE >> 2,
				MeasureSpec.AT_MOST);
		super.onMeasure(widthMeasureSpec, expandSpec);
	}

	/**
	 * 初始化脚布局
	 */
	private void initFooterView() {
		footerView = View.inflate(getContext(), R.layout.my_listview_bottom,
				null);
		footerView.measure(0, 0);
		footerViewHeight = footerView.getMeasuredHeight();
		footerView.setPadding(0, -footerViewHeight, 0, 0);
		this.addFooterView(footerView);
	}

	/**
	 * 初始化头布局
	 */
	private void initHeaderView() {
		headerView = new LinearLayout(getContext());
		android.view.ViewGroup.LayoutParams lp = new LayoutParams(
				android.view.ViewGroup.LayoutParams.MATCH_PARENT,
				android.view.ViewGroup.LayoutParams.MATCH_PARENT);
		headerView.setLayoutParams(lp);
		headerView.setBackgroundColor(getResources().getColor(R.color.black));

		headerView.measure(0, 0); // 系统会帮我们测量出headerView的高度
		headerViewHeight = headerView.getMeasuredHeight();
		headerView.setPadding(0, -headerViewHeight, 0, 0);

		this.addHeaderView(headerView); // 向ListView的顶部添加一个view对象
		initAnimation();
	}

	/**
	 * 初始化动画
	 */
	private void initAnimation() {
		upAnimation = new RotateAnimation(0f, -180f,
				Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
				0.5f);
		upAnimation.setDuration(500);
		upAnimation.setFillAfter(true); // 动画结束后, 停留在结束的位置上

		downAnimation = new RotateAnimation(-180f, -360f,
				Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
				0.5f);
		downAnimation.setDuration(500);
		downAnimation.setFillAfter(true); // 动画结束后, 停留在结束的位置上
	}

	@Override
	public boolean onTouchEvent(MotionEvent ev) {
		switch (ev.getAction()) {
		case MotionEvent.ACTION_DOWN:
			downY = (int) ev.getY();
			break;
		case MotionEvent.ACTION_MOVE:
			int moveY = (int) ev.getY();
			// 移动中的y - 按下的y = 间距.
			int diff = (moveY - downY) / 2;
			// -头布局的高度 + 间距 = paddingTop
			int paddingTop = -headerViewHeight + diff;
			// 如果: -头布局的高度 > paddingTop的值 执行super.onTouchEvent(ev);
			if (firstVisibleItemPosition == 0 && -headerViewHeight < paddingTop) {
				if (paddingTop > 0 && currentState == DOWN_PULL_REFRESH) { // 完全显示了.
					currentState = RELEASE_REFRESH;
					// refreshHeaderView();
				} else if (paddingTop < 0 && currentState == RELEASE_REFRESH) { // 没有显示完全
					currentState = DOWN_PULL_REFRESH;
					// refreshHeaderView();
				}
				// 下拉头布局
				headerView.setPadding(0, paddingTop, 0, 0);
				return true;
			}
			break;
		case MotionEvent.ACTION_UP:
			// 判断当前的状态是松开刷新还是下拉刷新
			if (currentState == RELEASE_REFRESH) {
				// 把头布局设置为完全显示状态
				headerView.setPadding(0, 0, 0, 0);
				// 进入到正在刷新中状态
				currentState = REFRESHING;
				this.hideHeaderView();
			} else if (currentState == DOWN_PULL_REFRESH) {
				// 隐藏头布局
				headerView.setPadding(0, -headerViewHeight, 0, 0);
			}
			break;
		default:
			break;
		}
		return super.onTouchEvent(ev);
	}

	/**
	 * 当滚动状态改变时回调
	 */
	@Override
	public void onScrollStateChanged(AbsListView view, int scrollState) {

		if (scrollState == SCROLL_STATE_IDLE
				|| scrollState == SCROLL_STATE_FLING) {
			// 判断当前是否已经到了底部
			if (isScrollToBottom && !isLoadingMore) {
				isLoadingMore = true;
				// 当前到底部
				footerView.setPadding(0, 0, 0, 0);
				this.setSelection(this.getCount());

				if (mOnRefershListener != null) {
					mOnRefershListener.onLoadingMore(this);
				}
			}
		}
	}

	/**
	 * 当滚动时调用
	 * 
	 * @param firstVisibleItem
	 *            当前屏幕显示在顶部的item的position
	 * @param visibleItemCount
	 *            当前屏幕显示了多少个条目的总数
	 * @param totalItemCount
	 *            ListView的总条目的总数
	 */
	@Override
	public void onScroll(AbsListView view, int firstVisibleItem,
			int visibleItemCount, int totalItemCount) {
		firstVisibleItemPosition = firstVisibleItem;
		if (getLastVisiblePosition() == (totalItemCount - 1)) {
			isScrollToBottom = true;
		} else {
			isScrollToBottom = false;
		}
	}

	public void setOnRefreshListener(OnRefreshListener listener) {
		mOnRefershListener = listener;
	}

	private void hideHeaderView() {
		headerView.setPadding(0, -headerViewHeight, 0, 0);
		currentState = DOWN_PULL_REFRESH;
	}

	public void hideFooterView() {
		footerView.setPadding(0, -footerViewHeight, 0, 0);
		isLoadingMore = false;
	}

	public interface OnRefreshListener {

		void onLoadingMore(View v);
	}

}
