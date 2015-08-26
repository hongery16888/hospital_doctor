package iori.hdoctor.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;
import iori.hdoctor.R;
import iori.hdoctor.activity.DoctorYYZXActivity;
import iori.hdoctor.activity.base.HDoctorCode;
import iori.hdoctor.net.HttpRequest;
import iori.hdoctor.net.NetworkAPI;
import iori.hdoctor.net.NetworkConnectListener;
import iori.hdoctor.net.entity.DocReserve;
import iori.hdoctor.util.DateUtil;
import iori.hdoctor.view.CircleBitmapDisplayer;

public class DoctorYYGLAdapter extends BaseAdapter {

	private ArrayList<DocReserve> data = new ArrayList<>();
	private Context context;
	private DisplayImageOptions options;
	private DisplayImageOptions optionsC;
	private ImageLoader imageLoader = ImageLoader.getInstance();

	public DoctorYYGLAdapter(Context context, ArrayList<DocReserve> reserves, DisplayImageOptions options) {
		this.context = context;
		data.addAll(reserves);
		this.options = options;
		optionsC = new DisplayImageOptions.Builder()
				.resetViewBeforeLoading(false)  // default
				.showImageOnLoading(R.drawable.img_avater_blgl_head)
				.showImageForEmptyUri(R.drawable.img_avater_blgl_head)
				.showImageOnFail(R.drawable.img_avater_blgl_head)
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
			view = LayoutInflater.from(context).inflate(R.layout.doctor_yygl_item, parent, false);
			holder = new ViewHolder(view);
			view.setTag(holder);
		}

		imageLoader.displayImage(HttpRequest.PHOTO_PATH + data.get(position).getImg(), holder.head, optionsC);
		imageLoader.displayImage(HttpRequest.PHOTO_PATH + data.get(position).getPhoto(), holder.photo, options);
		holder.age.setText(data.get(position).getAge() + context.getString(R.string.blgl_item_age));
		holder.sex.setText(data.get(position).getSex());
		holder.name.setText(data.get(position).getNicheng());
		holder.timeInfo.setText(data.get(position).getCha());
		holder.bingli.setText(data.get(position).getBingli());
		holder.describe.setText(data.get(position).getDescribe());

		holder.date.setText(DateUtil.getDates(data.get(position).getYuyuetime(), "yyyy-MM-dd HH:mm:ss",  "yyyy-MM-dd"));
		holder.time.setText(DateUtil.getDates(data.get(position).getYuyuetime(), "yyyy-MM-dd HH:mm:ss", "HH:mm"));
		holder.confirm.setTag(data.get(position).getOrderid());
		if (data.get(position).getIsok() == HDoctorCode.DONE)
			holder.confirm.setVisibility(View.INVISIBLE);

		holder.confirm.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				NetworkAPI.getNetworkAPI().docverify(v.getTag().toString(), ((DoctorYYZXActivity)context).showProgressDialog(), ((DoctorYYZXActivity)context));
			}
		});

		return view;
	}

	public void setCaseArray(ArrayList<DocReserve> reserves){
		data.clear();
		data.addAll(reserves);
		notifyDataSetChanged();
	}

	static class ViewHolder {
		@InjectView(R.id.head) ImageView head;
		@InjectView(R.id.photo) ImageView photo;
		@InjectView(R.id.name) TextView name;
		@InjectView(R.id.age) TextView age;
		@InjectView(R.id.sex) TextView sex;
		@InjectView(R.id.time) TextView time;
		@InjectView(R.id.bingli) TextView bingli;
		@InjectView(R.id.describe) TextView describe;
		@InjectView(R.id.date) TextView date;
		@InjectView(R.id.time_info) TextView timeInfo;
		@InjectView(R.id.confirm) TextView confirm;

		public ViewHolder(View view) {
			ButterKnife.inject(this, view);
		}
	}
}
