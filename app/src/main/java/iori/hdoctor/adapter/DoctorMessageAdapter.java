package iori.hdoctor.adapter;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.Handler;
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
import java.util.jar.Attributes;

import butterknife.ButterKnife;
import butterknife.InjectView;
import io.rong.imkit.MainActivity;
import iori.hdoctor.R;
import iori.hdoctor.activity.base.BaseActivity;
import iori.hdoctor.net.HttpRequest;
import iori.hdoctor.net.NetworkAPI;
import iori.hdoctor.net.NetworkConnectListener;
import iori.hdoctor.net.entity.DocMessage;
import iori.hdoctor.net.entity.FriendMsg;
import iori.hdoctor.view.CircleBitmapDisplayer;

public class DoctorMessageAdapter extends BaseAdapter implements NetworkConnectListener{

	private ArrayList<FriendMsg> data = new ArrayList<>();
	private Context context;
	private DisplayImageOptions options;
	private ImageLoader imageLoader = ImageLoader.getInstance();
	private Handler handler;

	public DoctorMessageAdapter(Context context, ArrayList<FriendMsg> friendMsgs, Handler handler) {
		this.context = context;
		data.addAll(friendMsgs);
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
		this.handler = handler;
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
			view = LayoutInflater.from(context).inflate(R.layout.friend_msg_item, parent, false);
			holder = new ViewHolder(view);
			view.setTag(holder);
		}

		holder.name.setText(data.get(position).getRequestnicheng());
		holder.agree.setTag(data.get(position).getRequestuid());

		holder.agree.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				NetworkAPI.getNetworkAPI().confirmfriend(v.getTag().toString(), "1", ((BaseActivity)context).showProgressDialog(), DoctorMessageAdapter.this);
			}
		});

		holder.refuse.setTag(data.get(position).getRequestuid());
		holder.refuse.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				NetworkAPI.getNetworkAPI().confirmfriend(v.getTag().toString(), "2", ((BaseActivity)context).showProgressDialog(), DoctorMessageAdapter.this);
			}
		});

		return view;
	}

	static class ViewHolder {

		@InjectView(R.id.name)
		TextView name;
		@InjectView(R.id.agree)
		TextView agree;
		@InjectView(R.id.refuse)
		TextView refuse;

		public ViewHolder(View view) {
			ButterKnife.inject(this, view);
		}
	}

	@Override
	public void onRequestSucceed(Object data, String requestAction) {
		if (HttpRequest.CONFIRM_FRIEND.equals(requestAction)){
			handler.sendEmptyMessage(0);
		}
		((BaseActivity)context).dismissProgressDialog();
	}

	@Override
	public void onRequestFailure(int error, String errorMsg, String requestAction) {
		Toast.makeText(context, errorMsg, Toast.LENGTH_LONG).show();
		((BaseActivity)context).dismissProgressDialog();
	}
}
