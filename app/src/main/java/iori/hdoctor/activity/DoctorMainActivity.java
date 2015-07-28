package iori.hdoctor.activity;

import android.widget.RadioGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import butterknife.InjectView;
import iori.hdoctor.R;
import iori.hdoctor.activity.base.BaseActivity;
import iori.hdoctor.activity.base.BasePager;
import iori.hdoctor.activity.pager.DoctorCirclePager;
import iori.hdoctor.activity.pager.DoctorIndexPager;
import iori.hdoctor.activity.pager.DoctorMinePager;
import iori.hdoctor.adapter.ViewPagerAdapter;
import iori.hdoctor.net.NetworkAPI;
import iori.hdoctor.net.NetworkConnectListener;
import iori.hdoctor.net.response.CheckVersionResponse;
import iori.hdoctor.view.MyViewPager;
import iori.hdoctor.view.LazyViewPager.OnPageChangeListener;

public class DoctorMainActivity extends BaseActivity implements NetworkConnectListener {

	@InjectView(R.id.viewpager)
	MyViewPager viewPager;
	@InjectView(R.id.main_radio)
	RadioGroup main_radio;
	@InjectView(R.id.result)
	TextView result;

	private List<BasePager> pages = new ArrayList<BasePager>();
	private int currentItem = R.id.index_main;
	private int oldPosition = 2;
	private ViewPagerAdapter viewPagerAdapter;

	@Override
	protected int setContentViewResId() {
		return R.layout.doctor_main;
	}

	@Override
	protected void initView() {
		setHideBackAction();
		setTitleAction(getResources().getString(R.string.doctor_title_main));
	}

	@Override
	protected void initData() {
		pages.clear();
		pages.add(new DoctorIndexPager(this));
		pages.add(new DoctorCirclePager(this));
		pages.add(new DoctorMinePager(this));
		viewPagerAdapter = new ViewPagerAdapter(pages);
		viewPager.setAdapter(viewPagerAdapter);
		viewPager.setOnPageChangeListener(pageChangeListener);
		main_radio.setOnCheckedChangeListener(checkedChangeListener);
		main_radio.check(currentItem);
		pages.get(oldPosition).onResume();
		NetworkAPI.getNetworkAPI().checkVersion("1", null, this);
	}

	@Override
	protected void onResume() {
		super.onResume();
		pages.get(oldPosition).onResume();
	}

	@Override
	protected void onPause() {
		super.onPause();
		pages.get(oldPosition).onPause();
	}

	@Override
	protected void onDestroy() {
		if (pages != null) {
			for (BasePager pager : pages) {
				pager.onDestroy();
			}
		}
		super.onDestroy();
	}

	@Override
	public void onRequestSucceed(Object data, String requestAction) {
		result.setText(((CheckVersionResponse)data).toString());
	}

	@Override
	public void onRequestFailure(int error, String errorMsg, String requestAction) {
		result.setText("error message : " + errorMsg);
	}

	private OnPageChangeListener pageChangeListener = new OnPageChangeListener() {
		@Override
		public void onPageSelected(int position) {
			BasePager pager = pages.get(position);
			pager.onResume();
			//pager.initData();
		}

		@Override
		public void onPageScrolled(int position, float positionOffset,
								   int positionOffsetPixels) {

		}

		@Override
		public void onPageScrollStateChanged(int state) {

		}
	};

	private RadioGroup.OnCheckedChangeListener checkedChangeListener = new RadioGroup.OnCheckedChangeListener() {
		@Override
		public void onCheckedChanged(RadioGroup group, int checkedId) {
			switch (checkedId) {
				case R.id.index_main:
					if (oldPosition != 0) {
						pages.get(oldPosition).onPause();
						oldPosition = 0;
					}
					viewPager.setCurrentItem(0, false);
					setTitleAction(getResources().getString(R.string.doctor_title_main));
					break;
				case R.id.shopping_main:
					if (oldPosition != 1) {
						pages.get(oldPosition).onPause();
						oldPosition = 1;
					}
					viewPager.setCurrentItem(1, false);
					setTitleAction(getResources().getString(R.string.doctor_menu_qz));
					break;
				case R.id.personal_main:
					if (oldPosition != 2) {
						pages.get(oldPosition).onPause();
						oldPosition = 2;
					}
					viewPager.setCurrentItem(2, false);
					setTitleAction(getResources().getString(R.string.doctor_menu_wo));
					break;
			}
			currentItem = checkedId;
		}
	};
}