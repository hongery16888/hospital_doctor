package iori.hdoctor.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
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

import butterknife.ButterKnife;
import butterknife.InjectView;
import iori.hdoctor.R;
import iori.hdoctor.app.MyApp;
import iori.hdoctor.net.HttpRequest;
import iori.hdoctor.net.entity.DocBlgl;
import iori.hdoctor.view.CircleBitmapDisplayer;

public class DoctorBLGLAdapter extends BaseAdapter {

	private ArrayList<DocBlgl> data = new ArrayList<>();
	private Context context;
	private DisplayImageOptions options;
	private DisplayImageOptions optionsC;
	private ImageLoader imageLoader = ImageLoader.getInstance();

	public DoctorBLGLAdapter(Context context, ArrayList<DocBlgl> docBlgls, DisplayImageOptions options) {
		this.context = context;
		this.data.addAll(docBlgls);
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
			view = LayoutInflater.from(context).inflate(R.layout.doctor_blgl_item, parent, false);
			holder = new ViewHolder(view);
			view.setTag(holder);
		}

		imageLoader.displayImage(HttpRequest.PHOTO_PATH + data.get(position).getImg(), holder.head, optionsC);
		imageLoader.displayImage(HttpRequest.PHOTO_PATH + data.get(position).getPhoto(), holder.photo, options);
		holder.age.setText(data.get(position).getAge() + context.getString(R.string.blgl_item_age));
		holder.sex.setText(data.get(position).getSex());
		holder.name.setText(data.get(position).getNicheng());
		holder.time.setText(data.get(position).getCha());
		holder.bingli.setText(data.get(position).getBingli());
		holder.describe.setText(data.get(position).getDescribe());
		holder.price.setText(data.get(position).getPrice() + context.getString(R.string.blgl_item_reserve_price_unit));
		holder.zixunname.setText(data.get(position).getZixunname());

		return view;
	}

	public void setCaseArray(ArrayList<DocBlgl> docBlgls){
		data.clear();
		data.addAll(docBlgls);
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
		@InjectView(R.id.price) TextView price;
		@InjectView(R.id.zixunname) TextView zixunname;

		public ViewHolder(View view) {
			ButterKnife.inject(this, view);
		}
	}
}
