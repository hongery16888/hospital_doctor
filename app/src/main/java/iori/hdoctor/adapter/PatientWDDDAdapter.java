package iori.hdoctor.adapter;

import android.content.Context;
import android.content.Intent;
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
import iori.hdoctor.activity.PatientPublishCommentActivity;
import iori.hdoctor.activity.PatientWDDDActivity;
import iori.hdoctor.net.HttpRequest;
import iori.hdoctor.net.entity.PatMyOrder;
import iori.hdoctor.view.CircleBitmapDisplayer;

public class PatientWDDDAdapter extends BaseAdapter {

	private ArrayList<PatMyOrder> data = new ArrayList<>();
	private ImageLoader imageLoader = ImageLoader.getInstance();
	private DisplayImageOptions options;
	private Context context;

	public PatientWDDDAdapter(Context context, ArrayList<PatMyOrder> patMyOrders) {
		this.context = context;
		data.addAll(patMyOrders);
		options = new DisplayImageOptions.Builder()
				.showImageForEmptyUri(R.drawable.img_fj_doctor)
				.showImageOnLoading(R.drawable.img_fj_doctor)
				.showImageOnFail(R.drawable.img_fj_doctor)
				.cacheInMemory(true) // default
				.cacheOnDisk(true) // default
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
			view = LayoutInflater.from(context).inflate(R.layout.patient_wddd_item, parent, false);
			holder = new ViewHolder(view);
			view.setTag(holder);
		}

		imageLoader.displayImage(HttpRequest.PHOTO_PATH + data.get(position).getImg(), holder.img, options);
		holder.hospital.setText(data.get(position).getHospital());
		holder.name.setText(data.get(position).getRealname());
		holder.price.setText("￥" + data.get(position).getPrice() + "元");
//		holder.type.setText(data.get(position).getType());
		if (data.get(position).getType().equals("2"))
			holder.type.setText("电话咨询");
		else if (data.get(position).getType().equals("1"))
			holder.type.setText("在线咨询");
		else if (data.get(position).getType().equals("3"))
			holder.type.setText("预约咨询");

		holder.reply.setTag(position);
		if (data.get(position).getIscomment().equals("1")) {
			holder.reply.setText("已评价");
			holder.reply.setTextColor(context.getResources().getColor(R.color.grey_deep));
		}else{
			holder.reply.setText("立即评价");
			holder.reply.setTextColor(context.getResources().getColor(R.color.patient_top_bar_bg));
		}

		if (!data.get(position).getIscomment().equals("1"))
			holder.reply.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					Intent intent = new Intent(context, PatientPublishCommentActivity.class);
					intent.putExtra("orderId", data.get(Integer.parseInt(v.getTag().toString())).getOrderid());
					context.startActivity(intent);
				}
			});

		return view;
	}

	static class ViewHolder {

		@InjectView(R.id.img)
		ImageView img;
		@InjectView(R.id.hospital)
		TextView hospital;
		@InjectView(R.id.name)
		TextView name;
		@InjectView(R.id.price)
		TextView price;
		@InjectView(R.id.type)
		TextView type;
		@InjectView(R.id.reply)
		TextView reply;

		public ViewHolder(View view) {
			ButterKnife.inject(this, view);
		}
	}

	public void setData(ArrayList<PatMyOrder> patMyOrders){
		data.clear();
		data.addAll(patMyOrders);
		notifyDataSetChanged();
	}
}
