package iori.hdoctor.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;
import iori.hdoctor.R;
import iori.hdoctor.net.HttpRequest;
import iori.hdoctor.net.entity.DocContent;
import iori.hdoctor.view.CircleBitmapDisplayer;

public class DoctorCircleInfoAdapter extends BaseAdapter {

	private ArrayList<DocContent> data = new ArrayList<>();
	private Context context;
	private ImageLoader imageLoader = ImageLoader.getInstance();
	private DisplayImageOptions options;

	public DoctorCircleInfoAdapter(Context context, ArrayList<DocContent> patContents) {
		this.context = context;
		if (patContents != null)
			data.addAll(patContents);
		options = new DisplayImageOptions.Builder()
				.showImageOnLoading(R.drawable.img_avater_blgl_head)
				.showImageForEmptyUri(R.drawable.img_avater_blgl_head)
				.showImageOnFail(R.drawable.img_avater_blgl_head)
				.cacheInMemory(true)
				.cacheOnDisk(true)
				.displayer(new CircleBitmapDisplayer())
				.build();
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return data.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return data.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override public View getView(int position, View view, ViewGroup parent) {
		ViewHolder holder;
		if (view != null) {
			holder = (ViewHolder) view.getTag();
		} else {
			view = LayoutInflater.from(context).inflate(R.layout.patient_circle_reply_item, parent, false);
			holder = new ViewHolder(view);
			view.setTag(holder);
		}

		imageLoader.displayImage(HttpRequest.PHOTO_PATH + data.get(position).getPimg(), holder.head, options);
		holder.name.setText(data.get(position).getNicheng());
		holder.content.setText(data.get(position).getContent());
		holder.time.setText(data.get(position).getNowtime());

		return view;
	}

	static class ViewHolder {

		@InjectView(R.id.head)
		ImageView head;
		@InjectView(R.id.name)
		TextView name;
		@InjectView(R.id.content)
		TextView content;
		@InjectView(R.id.time)
		TextView time;

		public ViewHolder(View view) {
			ButterKnife.inject(this, view);
		}
	}
}
