package iori.hdoctor.adapter;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import org.apache.http.impl.io.ContentLengthInputStream;

import java.lang.reflect.Type;
import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;
import io.rong.imkit.RongIM;
import io.rong.imlib.model.UserInfo;
import iori.hdoctor.R;
import iori.hdoctor.net.HttpRequest;
import iori.hdoctor.net.entity.Friend;
import iori.hdoctor.view.CircleBitmapDisplayer;

public class PatientFriendAdapter extends BaseAdapter {

	private ImageLoader imageLoader = ImageLoader.getInstance();
	private DisplayImageOptions options;
	private ArrayList<Friend> data = new ArrayList<>();
	private Context context;

	public PatientFriendAdapter(Context context, ArrayList<Friend> friends) {
		this.context = context;
		data.addAll(friends);
		options = new DisplayImageOptions.Builder()
				.showImageOnLoading(R.drawable.img_fj_doctor)
				.showImageOnFail(R.drawable.img_fj_doctor)
				.showImageForEmptyUri(R.drawable.img_fj_doctor)
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
			view = LayoutInflater.from(context).inflate(R.layout.patient_circle_friend_item, parent, false);
			holder = new ViewHolder(view);
			view.setTag(holder);
		}

		imageLoader.displayImage(HttpRequest.PHOTO_PATH + data.get(position).getImg(), holder.head, options);
		holder.name.setText(data.get(position).getNicheng());
		holder.signature.setText(data.get(position).getSignature());
		if (data.get(position).getIsdoc().equals("1")){
			holder.type.setVisibility(View.VISIBLE);
		}else{
			holder.type.setVisibility(View.GONE);
		}

		return view;
	}

	static class ViewHolder {

		@InjectView(R.id.head)
		ImageView head;
		@InjectView(R.id.name)
		TextView name;
		@InjectView(R.id.signature)
		TextView signature;
		@InjectView(R.id.type)
		TextView type;

		public ViewHolder(View view) {
			ButterKnife.inject(this, view);
		}
	}

	public void addData(ArrayList<Friend> friends){
		data.clear();
		data.addAll(friends);
		notifyDataSetChanged();
	}
}
