package iori.hdoctor.adapter;

import android.content.Context;
import android.text.TextUtils;
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
import iori.hdoctor.activity.PatientJKTXActivity;
import iori.hdoctor.net.HttpRequest;
import iori.hdoctor.net.NetworkAPI;
import iori.hdoctor.net.NetworkConnectListener;
import iori.hdoctor.net.entity.HealthyRemind;
import iori.hdoctor.view.WiperSwitch;

public class PatientJKTXAdapter extends BaseAdapter implements NetworkConnectListener{

	private ArrayList<HealthyRemind> data = new ArrayList<>();
	private Context context;
	private DisplayImageOptions options;
	private ImageLoader imageLoader = ImageLoader.getInstance();
	private int pid;
	private WiperSwitch switcher;

	public PatientJKTXAdapter(Context context, ArrayList<HealthyRemind> healthyReminds, DisplayImageOptions options) {
		this.context = context;
		data.addAll(healthyReminds);
		this.options = options;
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
			view = LayoutInflater.from(context).inflate(R.layout.patient_jktx_item, parent, false);
			holder = new ViewHolder(view);
			view.setTag(holder);
		}


		if (!TextUtils.isEmpty(data.get(position).getIsremind())) {
			imageLoader.displayImage(data.get(position).getImg(), holder.img, options);
			holder.name.setText(data.get(position).getRealname());
			holder.content.setText(data.get(position).getHostital());
			holder.remark.setText("预约提醒");
			holder.wiperSwitch.setChecked(data.get(position).getIsremind().equals("1"));
			holder.wiperSwitch.setTag(position);
		}else if(!TextUtils.isEmpty(data.get(position).getIstixing())){
			imageLoader.displayImage(data.get(position).getThumb(), holder.img, options);
			holder.name.setText(data.get(position).getName());
			holder.content.setText(data.get(position).getTakeingtime());
			holder.remark.setText("服药提醒");
			holder.wiperSwitch.setChecked(data.get(position).getIstixing().equals("1"));
			holder.wiperSwitch.setTag(position);
		}

		holder.wiperSwitch.setOnChangedListener(new WiperSwitch.OnChangedListener() {
			@Override
			public void OnChanged(WiperSwitch wiperSwitch, boolean checkState) {
				if (!TextUtils.isEmpty(data.get(Integer.parseInt(wiperSwitch.getTag().toString())).getIsremind()))
					NetworkAPI.getNetworkAPI().txa(data.get(Integer.parseInt(wiperSwitch.getTag().toString())).getTxaid(), ((PatientJKTXActivity)context).showProgressDialog(), PatientJKTXAdapter.this);
				if (!TextUtils.isEmpty(data.get(Integer.parseInt(wiperSwitch.getTag().toString())).getIstixing()))
					NetworkAPI.getNetworkAPI().txb(data.get(Integer.parseInt(wiperSwitch.getTag().toString())).getTxbid(),((PatientJKTXActivity) context).showProgressDialog(), PatientJKTXAdapter.this);
				pid = Integer.parseInt(wiperSwitch.getTag().toString());
				switcher = wiperSwitch;
			}
		});

		return view;
	}

	static class ViewHolder {

		@InjectView(R.id.img)
		ImageView img;
		@InjectView(R.id.name)
		TextView name;
		@InjectView(R.id.content)
		TextView content;
		@InjectView(R.id.remark)
		TextView remark;
		@InjectView(R.id.switcher)
		WiperSwitch wiperSwitch;

		public ViewHolder(View view) {
			ButterKnife.inject(this, view);
		}
	}

	@Override
	public void onRequestSucceed(Object data, String requestAction) {
		if (HttpRequest.PAT_HEALTHY_REMIND.equals(requestAction)){
		}
		((PatientJKTXActivity)context).dismissProgressDialog();
	}

	@Override
	public void onRequestFailure(int error, String errorMsg, String requestAction) {
		switcher.setChecked(!switcher.isChecked());
		Toast.makeText(context, errorMsg, Toast.LENGTH_LONG).show();
		((PatientJKTXActivity)context).dismissProgressDialog();
	}
}
