package com.zhanjixun.net;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.view.View;
import android.widget.ImageView;

import com.zhanjixun.data.LoadImage;
import com.zhanjixun.utils.BitmapUtils;
import com.zhanjixun.utils.LogCat;

public class AsyncBlurImageTask extends AsyncTask<String, Void, Bitmap> {
	private View view;
	private Context context;

	public AsyncBlurImageTask(View view, Context context, String url) {
		this.view = view;
		this.context = context;
		Bitmap bitmap = LoadImage.getInstance().getBitmapFromLruCache(
				url + LoadImage.BLUR_KEY);

		if (null != bitmap) {
			LogCat.v(bitmap.toString());
			bitmap = BitmapUtils.getBitmap(bitmap, view.getMeasuredWidth(),
					view.getMeasuredHeight());
			((ImageView) view).setImageBitmap(bitmap);
		} else {
			this.execute(url);
		}
	}

	@Override
	protected void onPreExecute() {
		super.onPreExecute();
	}

	@Override
	protected Bitmap doInBackground(String... params) {
		String path = params[0];
		Bitmap bitmap = LoadImage.getInstance().getBlurBitmap(path, context);
		return bitmap;
	}

	@Override
	protected void onPostExecute(Bitmap result) {
		super.onPostExecute(result);
		if (result != null) {
			((ImageView) view).setAlpha(0.8f);
			((ImageView) view).setImageBitmap(result);
		}
	}

}
