package iori.hdoctor.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;

import java.util.ArrayList;

import butterknife.ButterKnife;
import iori.hdoctor.R;

public class PatientMessageAdapter extends BaseAdapter {

	private ArrayList<String> data = new ArrayList<String>();
	private Context context;

	public PatientMessageAdapter(Context context) {
		this.context = context;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return 3;
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
			view = LayoutInflater.from(context).inflate(R.layout.patient_circle_message_item, parent, false);
			holder = new ViewHolder(view);
			view.setTag(holder);
		}

		// etc...

		return view;
	}

	static class ViewHolder {

		public ViewHolder(View view) {
			ButterKnife.inject(this, view);
		}
	}
}
