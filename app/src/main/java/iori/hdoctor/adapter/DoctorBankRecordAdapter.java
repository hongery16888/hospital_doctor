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
import iori.hdoctor.net.entity.DocBankRecord;
import iori.hdoctor.net.entity.HealthyRemind;
import iori.hdoctor.view.WiperSwitch;

public class DoctorBankRecordAdapter extends BaseAdapter{

	private ArrayList<DocBankRecord> data = new ArrayList<>();
	private Context context;

	public DoctorBankRecordAdapter(Context context, ArrayList<DocBankRecord> docBankRecords) {
		this.context = context;
		data.addAll(docBankRecords);
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
			view = LayoutInflater.from(context).inflate(R.layout.doctor_income_recome_item, parent, false);
			holder = new ViewHolder(view);
			view.setTag(holder);
		}

		holder.price.setText(data.get(position).getMoney() + context.getString(R.string.rmb_unit));
		holder.name.setText(data.get(position).getBankname());
		holder.cardNo.setText("..." + data.get(position).getBankno().substring(data.get(position).getBankno().length()-4, data.get(position).getBankno().length()));
		holder.time.setText(data.get(position).getNowtime().split(" ")[0]);

		return view;
	}

	static class ViewHolder {

		@InjectView(R.id.name)
		TextView name;
		@InjectView(R.id.price)
		TextView price;
		@InjectView(R.id.card_no)
		TextView cardNo;
		@InjectView(R.id.time)
		TextView time;

		public ViewHolder(View view) {
			ButterKnife.inject(this, view);
		}
	}

}
