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

import java.security.PrivateKey;
import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;
import iori.hdoctor.R;
import iori.hdoctor.net.HttpRequest;
import iori.hdoctor.net.entity.PatWDYY;
import iori.hdoctor.view.CircleBitmapDisplayer;

public class PatientWDYYAdapter extends BaseAdapter {

	private ArrayList<PatWDYY> data = new ArrayList<>();
	private Context context;
	private ImageLoader imageLoader = ImageLoader.getInstance();
	private DisplayImageOptions options;

	public PatientWDYYAdapter(Context context, ArrayList<PatWDYY> patWDYYs) {
		this.context = context;
		data.addAll(patWDYYs);
		options = new DisplayImageOptions.Builder()
				.showImageOnLoading(R.drawable.img_fj_doctor)
				.showImageForEmptyUri(R.drawable.img_fj_doctor)
				.showImageOnFail(R.drawable.img_fj_doctor)
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
			view = LayoutInflater.from(context).inflate(R.layout.patient_wdyy_item, parent, false);
			holder = new ViewHolder(view);
			view.setTag(holder);
		}

		imageLoader.displayImage(HttpRequest.PHOTO_PATH + data.get(position).getImg(), holder.img, options);
		holder.hospital.setText(data.get(position).getHospital());
		holder.name.setText(data.get(position).getRealname());
		holder.date.setText(data.get(position).getYuyuetime().split(" ")[0]);
		holder.hour.setText(data.get(position).getYuyuetime().split(" ")[1]);
		holder.adds.setText(data.get(position).getAddress());

		return view;
	}

	static class ViewHolder {

		@InjectView(R.id.img)
		ImageView img;
		@InjectView(R.id.hospital)
		TextView hospital;
		@InjectView(R.id.name)
		TextView name;
		@InjectView(R.id.date)
		TextView date;
		@InjectView(R.id.hour)
		TextView hour;
		@InjectView(R.id.adds)
		TextView adds;

		public ViewHolder(View view) {
			ButterKnife.inject(this, view);
		}
	}
}
