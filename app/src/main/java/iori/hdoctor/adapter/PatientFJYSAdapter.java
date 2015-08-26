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
import iori.hdoctor.net.entity.PatNearDoc;
import iori.hdoctor.view.CircleBitmapDisplayer;

public class PatientFJYSAdapter extends BaseAdapter {

	private ArrayList<PatNearDoc> data = new ArrayList<>();
	private Context context;
	private ImageLoader imageLoader = ImageLoader.getInstance();
	private DisplayImageOptions options;

	public PatientFJYSAdapter(Context context, ArrayList<PatNearDoc> patNearDocs) {
		this.context = context;
		data.addAll(patNearDocs);
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
			view = LayoutInflater.from(context).inflate(R.layout.patient_nearby_item_ys, parent, false);
			holder = new ViewHolder(view);
			view.setTag(holder);
		}

		imageLoader.displayImage(HttpRequest.PHOTO_PATH + data.get(position).getImg(), holder.head, options);
		holder.name.setText(data.get(position).getRealname());
		holder.zili.setText(data.get(position).getZhicheng());
		holder.adds.setText(data.get(position).getHospital());
		holder.skill.setText(data.get(position).getShanchang());
		holder.distance.setText(data.get(position).getDistance() + "km");
		if (data.get(position).getOnline() == 1){
			holder.statusImg.setImageResource(R.drawable.patient_fj_item_zx);
			holder.status.setText("在线");
		}else{
			holder.statusImg.setImageResource(R.drawable.patient_fj_item_zx_un);
			holder.status.setText("离线");
		}

		return view;
	}

	static class ViewHolder {

		@InjectView(R.id.head)
		ImageView head;
		@InjectView(R.id.name)
		TextView name;
		@InjectView(R.id.distance)
		TextView distance;
		@InjectView(R.id.zili)
		TextView zili;
		@InjectView(R.id.adds)
		TextView adds;
		@InjectView(R.id.skill)
		TextView skill;
		@InjectView(R.id.status)
		TextView status;
		@InjectView(R.id.status_img)
		ImageView statusImg;

		public ViewHolder(View view) {
			ButterKnife.inject(this, view);
		}
	}
}
