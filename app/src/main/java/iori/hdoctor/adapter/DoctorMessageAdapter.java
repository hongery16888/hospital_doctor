package iori.hdoctor.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;

import java.util.ArrayList;
import java.util.jar.Attributes;

import butterknife.ButterKnife;
import butterknife.InjectView;
import iori.hdoctor.R;
import iori.hdoctor.net.HttpRequest;
import iori.hdoctor.net.entity.DocMessage;
import iori.hdoctor.view.CircleBitmapDisplayer;

public class DoctorMessageAdapter extends BaseAdapter {

	private ArrayList<DocMessage> data = new ArrayList<>();
	private Context context;
	private DisplayImageOptions options;
	private ImageLoader imageLoader = ImageLoader.getInstance();

	public DoctorMessageAdapter(Context context, ArrayList<DocMessage> docMessages) {
		this.context = context;
		data.addAll(docMessages);
		options = new DisplayImageOptions.Builder()
				.resetViewBeforeLoading(false)  // default
				.showImageOnLoading(R.drawable.img_fj_doctor)
				.cacheInMemory(true) // default
				.cacheOnDisk(true) // default
				.considerExifParams(true) // default
				.imageScaleType(ImageScaleType.IN_SAMPLE_POWER_OF_2) // default
				.bitmapConfig(Bitmap.Config.ARGB_8888) // default
				.displayer(new CircleBitmapDisplayer()) // default
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
			view = LayoutInflater.from(context).inflate(R.layout.doctor_circle_message_item, parent, false);
			holder = new ViewHolder(view);
			view.setTag(holder);
		}

		imageLoader.displayImage(HttpRequest.PHOTO_PATH + data.get(position).getImg(), holder.avatar, options);
		holder.name.setText(data.get(position).getNicheng());
		holder.time.setText(data.get(position).getCha());

		return view;
	}

	static class ViewHolder {

		@InjectView(R.id.avatar)
		ImageView avatar;
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
