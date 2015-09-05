package iori.hdoctor.activity;

import android.app.ActivityManager;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.KeyEvent;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;

import java.util.ArrayList;
import java.util.List;

import butterknife.InjectView;
import io.rong.imkit.MainActivity;
import io.rong.imkit.RongContext;
import io.rong.imkit.RongIM;
import io.rong.imkit.widget.provider.CameraInputProvider;
import io.rong.imkit.widget.provider.ImageInputProvider;
import io.rong.imkit.widget.provider.InputProvider;
import io.rong.imlib.RongIMClient;
import io.rong.imlib.model.Conversation;
import io.rong.imlib.model.UserInfo;
import iori.hdoctor.R;
import iori.hdoctor.activity.base.BaseActivity;
import iori.hdoctor.activity.base.BasePager;
import iori.hdoctor.activity.pager.PatientCirclePager;
import iori.hdoctor.activity.pager.PatientDoctorPager;
import iori.hdoctor.activity.pager.PatientIndexPager;
import iori.hdoctor.activity.pager.PatientMinePager;
import iori.hdoctor.adapter.ViewPagerAdapter;
import iori.hdoctor.net.DataTransfer;
import iori.hdoctor.net.NetworkConnectListener;
import iori.hdoctor.net.response.CheckVersionResponse;
import iori.hdoctor.view.LazyViewPager.OnPageChangeListener;
import iori.hdoctor.view.MyViewPager;

public class PatientMainActivity extends BaseActivity implements NetworkConnectListener {

	private LocationClient mLocationClient;

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
		return R.layout.patient_main;
	}

	@Override
	protected void initView() {
		setHideBackAction();
		setTitleAction(getResources().getString(R.string.patient_menu_jk));
	}

	@Override
	protected void initData() {
		mLocationClient = getApp().mLocationClient;
		mLocationClient.setLocOption(new LocationClientOption());
		getApp().setMainActivity(this);
		pages.clear();
		pages.add(new PatientIndexPager(this));
		pages.add(new PatientDoctorPager(this));
		pages.add(new PatientCirclePager(this));
		pages.add(new PatientMinePager(this));
		viewPagerAdapter = new ViewPagerAdapter(pages);
		viewPager.setAdapter(viewPagerAdapter);
		viewPager.setOnPageChangeListener(pageChangeListener);
		main_radio.setOnCheckedChangeListener(checkedChangeListener);
		main_radio.check(currentItem);
		pages.get(oldPosition).onResume();
//		NetworkAPI.getNetworkAPI().checkVersion("1", null, this);
		mLocationClient.start();

		getApp().connectRong();


		//扩展功能自定义
		InputProvider.ExtendProvider[] provider = {
				new ImageInputProvider(RongContext.getInstance()),//图片
				new CameraInputProvider(RongContext.getInstance()),//相机
//                new VoIPInputProvider(RongContext.getInstance()),// 语音通话
		};

		RongIM.getInstance().resetInputExtensionProvider(Conversation.ConversationType.PRIVATE, provider);

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
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
	}

	@Override
	protected void onDestroy() {
		if (pages != null) {
			for (BasePager pager : pages) {
				pager.onDestroy();
			}
		}
//		mLocationClient.stop();
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
					setTitleAction(getString(R.string.patient_menu_jk));
					setHideSetting();
					setHidePublish();
					break;
				case R.id.shopping_main:
					if (oldPosition != 1) {
						pages.get(oldPosition).onPause();
						oldPosition = 1;
					}
					viewPager.setCurrentItem(1, false);
					setTitleAction(getString(R.string.patient_menu_ys));
					setHideSetting();
					setHidePublish();
					break;
				case R.id.activity_main:
					if (oldPosition != 2) {
						pages.get(oldPosition).onPause();
						oldPosition = 2;
					}
					viewPager.setCurrentItem(2, false);
					setTitleAction(getString(R.string.patient_menu_qz));
					setHideSetting();
					showPublish(publishListener);
					break;
				case R.id.personal_main:
					if (oldPosition != 3) {
						pages.get(oldPosition).onPause();
						oldPosition = 3;
					}
					viewPager.setCurrentItem(3, false);
					setTitleAction(getString(R.string.text_null));
					setHidePublish();
					showSetting(settingListener);
					break;
			}
			currentItem = checkedId;
		}
	};

	private View.OnClickListener settingListener = new View.OnClickListener() {
		@Override
		public void onClick(View v) {
			startActivity(new Intent(PatientMainActivity.this, PatientSettingActivity.class));
		}
	};

	private View.OnClickListener publishListener = new View.OnClickListener() {
		@Override
		public void onClick(View v) {
			startActivity(new Intent(PatientMainActivity.this, PatientPublishActivity.class));
		}
	};

	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if(keyCode == KeyEvent.KEYCODE_BACK || keyCode == KeyEvent.KEYCODE_HOME){
			Intent intent=new Intent(Intent.ACTION_MAIN);
			intent.addCategory(Intent.CATEGORY_HOME);
			startActivity(intent);
			return true;
		}

		return super.onKeyDown(keyCode, event);
	}

}