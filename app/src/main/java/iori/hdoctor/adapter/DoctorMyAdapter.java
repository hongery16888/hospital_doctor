package iori.hdoctor.adapter;

import android.content.Context;
import android.os.Handler;
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
import iori.hdoctor.net.entity.DocCircle;
import iori.hdoctor.view.CircleBitmapDisplayer;

public class DoctorMyAdapter extends BaseAdapter {

	private ArrayList<DocCircle> data = new ArrayList<>();
	private Context context;
	private ImageLoader imageLoader = ImageLoader.getInstance();
	private DisplayImageOptions options;
	private Handler handler;

	public DoctorMyAdapter(Context context, ArrayList<DocCircle> docCircles, Handler handler) {
		this.context = context;
		if (docCircles != null)
			data.addAll(docCircles);
		this.handler = handler;
		options = new DisplayImageOptions.Builder()
				.showImageOnLoading(R.drawable.img_avater_blgl_head)
				.showImageOnFail(R.drawable.img_avater_blgl_head)
				.showImageForEmptyUri(R.drawable.img_avater_blgl_head)
				.cacheOnDisk(true)
				.cacheInMemory(true)
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
			view = LayoutInflater.from(context).inflate(R.layout.doctor_my_item, parent, false);
			holder = new ViewHolder(view);
			view.setTag(holder);
		}

		imageLoader.displayImage(HttpRequest.PHOTO_PATH + data.get(position).getImg(), holder.head, options);
		holder.name.setText(data.get(position).getNicheng());
		holder.time.setText(data.get(position).getAddtime());
		holder.content.setText(data.get(position).getContent());
		holder.best.setText(data.get(position).getBest());
		holder.commentnum.setText(data.get(position).getCommentnum());
		holder.best.setTag(position);

		holder.del.setTag(position);
		holder.del.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				handler.sendEmptyMessage(Integer.parseInt(v.getTag().toString()));
			}
		});

		return view;
	}

	static class ViewHolder {

		@InjectView(R.id.head)
		ImageView head;
		@InjectView(R.id.name)
		TextView name;
		@InjectView(R.id.time)
		TextView time;
		@InjectView(R.id.content)
		TextView content;
		@InjectView(R.id.best)
		TextView best;
		@InjectView(R.id.commentnum)
		TextView commentnum;
		@InjectView(R.id.del)
		ImageView del;

		public ViewHolder(View view) {
			ButterKnife.inject(this, view);
		}

	}

	public void setData(ArrayList<DocCircle> patCircles){
		data.clear();
		data.addAll(patCircles);
		notifyDataSetChanged();
	}
}
