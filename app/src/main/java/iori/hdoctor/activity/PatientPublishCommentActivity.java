package iori.hdoctor.activity;

import com.ms.square.android.expandabletextview.ExpandableTextView;

import butterknife.InjectView;
import iori.hdoctor.R;
import iori.hdoctor.activity.base.BaseActivity;

/**
 * Created by Administrator on 2015/7/11.
 */
public class PatientPublishCommentActivity extends BaseActivity{


    @Override
    protected int setContentViewResId() {
        return R.layout.patient_publish_comment_main;
    }

    @Override
    protected void initView() {
        setBackAction();
        setTitleAction(getString(R.string.patient_publish_comment_title_main));
    }

    @Override
    protected void initData() {
    }

}
