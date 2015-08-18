package iori.hdoctor.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;
import iori.hdoctor.R;
import iori.hdoctor.activity.PatientDAGLActivity;
import iori.hdoctor.activity.PatientWDYXActivity;
import iori.hdoctor.net.HttpRequest;
import iori.hdoctor.net.NetworkAPI;
import iori.hdoctor.net.NetworkConnectListener;
import iori.hdoctor.net.entity.Medicine;

public class PatientWDYXAdapter extends BaseAdapter implements NetworkConnectListener{

	private ArrayList<Medicine> data = new ArrayList<>();
	private Context context;
	private ImageLoader imageLoader = ImageLoader.getInstance();
	private DisplayImageOptions options;
	private int pid;

	public PatientWDYXAdapter(Context context, ArrayList<Medicine> medicines, DisplayImageOptions options) {
		this.context = context;
		this.data.addAll(medicines);
		this.options = new DisplayImageOptions.Builder()
				.showImageOnLoading(R.drawable.img_fj_hospital)
				.showImageOnFail(R.drawable.img_fj_hospital)
				.showImageForEmptyUri(R.drawable.img_fj_hospital)
				.cacheInMemory(true) // default
				.cacheOnDisk(true) // default
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
			view = LayoutInflater.from(context).inflate(R.layout.patient_wdyx_item, parent, false);
			holder = new ViewHolder(view);
			view.setTag(holder);
		}

		holder.name.setText(data.get(position).getName());
		holder.img.setTag(HttpRequest.PHOTO_PATH + data.get(position).getImg());
		imageLoader.displayImage(holder.img.getTag().toString(), holder.img, options);
		holder.beizhu.setText(data.get(position).getBeizhu());
		holder.del.setTag(position);

		holder.del.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				pid = Integer.parseInt(v.getTag().toString());
				NetworkAPI.getNetworkAPI().delmedicine(data.get(pid).getMedicineidid(), ((PatientWDYXActivity)context).showProgressDialog(), PatientWDYXAdapter.this);
			}
		});

		return view;
	}

	public void setData(ArrayList<Medicine> medicines){
		this.data.clear();
		this.data.addAll(medicines);
		notifyDataSetChanged();
	}

	static class ViewHolder {

		@InjectView(R.id.img)
		ImageView img;
		@InjectView(R.id.name)
		TextView name;
		@InjectView(R.id.beizhu)
		TextView beizhu;
		@InjectView(R.id.del)
		ImageView del;

		public ViewHolder(View view) {
			ButterKnife.inject(this, view);
		}
	}

	@Override
	public void onRequestSucceed(Object data, String requestAction) {
		if (HttpRequest.PAT_MEDICINE.equals(requestAction)){
			this.data.remove(pid);
			notifyDataSetChanged();
		}
		((PatientWDYXActivity)context).dismissProgressDialog();
	}

	@Override
	public void onRequestFailure(int error, String errorMsg, String requestAction) {
		Toast.makeText(context, errorMsg, Toast.LENGTH_LONG).show();
		((PatientWDYXActivity)context).dismissProgressDialog();
	}
}
