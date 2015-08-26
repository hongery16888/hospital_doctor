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
import com.nostra13.universalimageloader.core.display.SimpleBitmapDisplayer;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;
import iori.hdoctor.R;
import iori.hdoctor.net.HttpRequest;
import iori.hdoctor.net.entity.PatNearDoc;
import iori.hdoctor.net.entity.PatNearHosp;

public class PatientFJYYAdapter extends BaseAdapter {

	private ArrayList<PatNearHosp> data = new ArrayList<>();
	private Context context;
	private ImageLoader imageLoader = ImageLoader.getInstance();
	private DisplayImageOptions options;

	public PatientFJYYAdapter(Context context, ArrayList<PatNearHosp> patNearDocs) {
		this.context = context;
		data.addAll(patNearDocs);
		options = new DisplayImageOptions.Builder()
				.showImageOnLoading(R.drawable.img_fj_hospital)
				.showImageForEmptyUri(R.drawable.img_fj_hospital)
				.showImageOnFail(R.drawable.img_fj_hospital)
				.cacheOnDisk(true)
				.cacheInMemory(true)
				.displayer(new SimpleBitmapDisplayer())
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
			view = LayoutInflater.from(context).inflate(R.layout.patient_nearby_item_yy, parent, false);
			holder = new ViewHolder(view);
			view.setTag(holder);
		}

		imageLoader.displayImage(HttpRequest.PHOTO_PATH + data.get(position).getImg(), holder.img, options);
		holder.name.setText(data.get(position).getName());
		holder.type.setText(data.get(position).getLevel());
		holder.adds.setText(data.get(position).getAddress());
		holder.distance.setText(data.get(position).getDistance() + "km");

		return view;
	}

	static class ViewHolder {

		@InjectView(R.id.img)
		ImageView img;
		@InjectView(R.id.name)
		TextView name;
		@InjectView(R.id.type)
		TextView type;
		@InjectView(R.id.adds)
		TextView adds;
		@InjectView(R.id.distance)
		TextView distance;

		public ViewHolder(View view) {
			ButterKnife.inject(this, view);
		}
	}
}
