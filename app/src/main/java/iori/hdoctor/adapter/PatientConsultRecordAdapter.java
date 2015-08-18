package iori.hdoctor.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import butterknife.ButterKnife;
import butterknife.InjectView;
import iori.hdoctor.R;
import iori.hdoctor.net.entity.ConsultRecord;

public class PatientConsultRecordAdapter extends BaseAdapter {

	private ArrayList<ConsultRecord> data = new ArrayList<>();
	private Context context;

	public PatientConsultRecordAdapter(Context context, ArrayList<ConsultRecord> consultRecords) {
		this.context = context;
		data.addAll(consultRecords);
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
			view = LayoutInflater.from(context).inflate(R.layout.patient_record_zxda_item, parent, false);
			holder = new ViewHolder(view);
			view.setTag(holder);
		}

		holder.name.setText(data.get(position).getDname());
		holder.content.setText(data.get(position).getDcontent());
		holder.docName.setText(data.get(position).getRealname());
		holder.time.setText(data.get(position).getAddtime());

		return view;
	}

	static class ViewHolder {

		@InjectView(R.id.name)
		TextView name;
		@InjectView(R.id.content)
		TextView content;
		@InjectView(R.id.doc_name)
		TextView docName;
		@InjectView(R.id.time)
		TextView time;

		public ViewHolder(View view) {
			ButterKnife.inject(this, view);
		}
	}
}
