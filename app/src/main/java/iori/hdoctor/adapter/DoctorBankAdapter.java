package iori.hdoctor.adapter;

import android.content.Context;
import android.provider.Telephony;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;
import iori.hdoctor.R;
import iori.hdoctor.activity.DoctorSRXQActivity;
import iori.hdoctor.net.HttpRequest;
import iori.hdoctor.net.NetworkAPI;
import iori.hdoctor.net.NetworkConnectListener;
import iori.hdoctor.net.entity.DocBankInfo;

public class DoctorBankAdapter extends BaseAdapter implements NetworkConnectListener{

	private ArrayList<DocBankInfo> data = new ArrayList<>();
	private Context context;
	private CheckBox checkBox;
	private CheckBox checkBoxed;
	private CheckBox checkBoxBefore;
	private CheckBox checkBoxedBefore;

	private boolean del;
	private int positionId;

	public DoctorBankAdapter(Context context, ArrayList<DocBankInfo> docBankInfos) {
		this.context = context;
		data.addAll(docBankInfos);
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

	@Override public View getView(final int position, View view, ViewGroup parent) {
		ViewHolder holder;
		if (view != null) {
			holder = (ViewHolder) view.getTag();
		} else {
			view = LayoutInflater.from(context).inflate(R.layout.doctor_income_item, parent, false);
			holder = new ViewHolder(view);
			view.setTag(holder);
		}

		holder.name.setText(data.get(position).getBankname());
		if(data.get(position).getBankno().length() > 4)
			holder.bankNo.setText("**** " + data.get(position).getBankno().substring(data.get(position).getBankno().length() - 4, data.get(position).getBankno().length()));
		else
			holder.bankNo.setText(data.get(position).getBankno());
		holder.choice.setChecked(data.get(position).getBankisselect().equals("1"));
		if (data.get(position).getBankisselect().equals("1")) checkBox = holder.choice;
		holder.choice.setTag(position);
		holder.del.setTag(position);

		holder.choice.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				if (!((CheckBox) v).isChecked()) {
					((CheckBox) v).setChecked(true);
				} else if (checkBox != null) {
					checkBoxed = checkBox;
					checkBox.setChecked(false);
					del = false;
					NetworkAPI.getNetworkAPI().docModifyBank(data.get(Integer.parseInt(v.getTag().toString())).getBankno(),
							"001", "1", ((DoctorSRXQActivity) context).showProgressDialog(), DoctorBankAdapter.this);
				}

				if(checkBox == null){
					NetworkAPI.getNetworkAPI().docModifyBank(data.get(Integer.parseInt(v.getTag().toString())).getBankno(),
							"001", "1", ((DoctorSRXQActivity) context).showProgressDialog(), DoctorBankAdapter.this);
				}
				checkBoxBefore = checkBox;
				checkBox = (CheckBox) v;
				checkBoxedBefore = checkBox;
			}
		});

		holder.del.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				del = true;
				positionId = Integer.parseInt(v.getTag().toString());
				NetworkAPI.getNetworkAPI().docDelBank(data.get(Integer.parseInt(v.getTag().toString())).getBankno(),
						((DoctorSRXQActivity) context).showProgressDialog(), DoctorBankAdapter.this);
			}
		});

		return view;
	}

	public void setData(ArrayList<DocBankInfo> docBankInfos){
		data.clear();
		data.addAll(docBankInfos);
		notifyDataSetChanged();
	}

	static class ViewHolder {

		@InjectView(R.id.bank_ico)
		ImageView icon;
		@InjectView(R.id.name)
		TextView name;
		@InjectView(R.id.bank_no)
		TextView bankNo;
		@InjectView(R.id.choice)
		CheckBox choice;
		@InjectView(R.id.del)
		TextView del;


		public ViewHolder(View view) {
			ButterKnife.inject(this, view);
		}
	}

	@Override
	public void onRequestSucceed(Object data, String requestAction) {
		if (HttpRequest.DOC_INCOME.equals(requestAction)){
			if(del){
				this.data.remove(positionId);
				notifyDataSetChanged();
			}
		}
		((DoctorSRXQActivity)context).dismissProgressDialog();
	}

	@Override
	public void onRequestFailure(int error, String errorMsg, String requestAction) {
		Toast.makeText(context, errorMsg, Toast.LENGTH_LONG).show();
		((DoctorSRXQActivity) context).dismissProgressDialog();
		checkBox = checkBoxBefore;
		checkBoxed = checkBoxedBefore;
		if (checkBoxed != null)
			checkBoxed.setChecked(!checkBoxed.isChecked());
		if (checkBox != null)
			checkBox.setChecked(!checkBox.isChecked());
	}
}
