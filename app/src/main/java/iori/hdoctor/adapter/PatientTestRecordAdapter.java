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
import iori.hdoctor.net.entity.TestRecord;

public class PatientTestRecordAdapter extends BaseAdapter {

	private ArrayList<TestRecord> data = new ArrayList<>();
	private Context context;

	public PatientTestRecordAdapter(Context context, ArrayList<TestRecord> testRecords) {
		this.context = context;
		data.addAll(testRecords);
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
			view = LayoutInflater.from(context).inflate(R.layout.patient_record_zjda_item, parent, false);
			holder = new ViewHolder(view);
			view.setTag(holder);
		}

		holder.score.setText(data.get(position).getScore());
		holder.content.setText(data.get(position).getHealth());
		holder.time.setText(data.get(position).getAddtime());

		return view;
	}

	static class ViewHolder {

		@InjectView(R.id.score)
		TextView score;
		@InjectView(R.id.content)
		TextView content;
		@InjectView(R.id.time)
		TextView time;

		public ViewHolder(View view) {
			ButterKnife.inject(this, view);
		}
	}
}
