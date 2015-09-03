package iori.hdoctor.activity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.drawable.ColorDrawable;
import android.net.Uri;
import android.os.Handler;
import android.os.Message;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.alipay.sdk.app.PayTask;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;

import java.io.UnsupportedEncodingException;
import java.lang.ref.PhantomReference;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.Random;

import butterknife.InjectView;
import butterknife.OnClick;
import io.rong.imkit.RongIM;
import iori.hdoctor.R;
import iori.hdoctor.activity.base.BaseActivity;
import iori.hdoctor.activity.base.HDoctorCode;
import iori.hdoctor.activity.pay.PayResult;
import iori.hdoctor.activity.pay.SignUtils;
import iori.hdoctor.app.MyApp;
import iori.hdoctor.net.HttpRequest;
import iori.hdoctor.net.NetworkAPI;
import iori.hdoctor.net.NetworkConnectListener;
import iori.hdoctor.net.response.PatientYYResponse;
import iori.hdoctor.net.response.PatientZXResponse;
import iori.hdoctor.net.response.PatientZXZXResponse;
import iori.hdoctor.view.crop.CropHelper;
import iori.hdoctor.view.crop.CropParams;

/**
 * Created by Administrator on 2015/7/11.
 */
public class PatientZXZXActivity extends BasePhotoCropActivity implements NetworkConnectListener {

    private CropParams mCropParams = new CropParams(HDoctorCode.HEAD_PATH);
    private ImageLoader imageLoader = ImageLoader.getInstance();
    private DisplayImageOptions options;
    private PopupWindow mPhotoPopWindow;
    private ColorDrawable dw = new ColorDrawable(0xb0000000);

    private boolean isConfirm;
    private boolean hasImg;
    private boolean hadConfirm = false;
    private String orderId;
    private String pricePay;

    @InjectView(R.id.content)
    TextView content;
    @InjectView(R.id.img)
    ImageView img;
    @InjectView(R.id.hospital)
    TextView hospital;
    @InjectView(R.id.doctor)
    TextView doctor;
    @InjectView(R.id.price_lay)
    View priceLay;
    @InjectView(R.id.price)
    TextView price;
    @InjectView(R.id.confirm)
    TextView confirm;

    @OnClick(R.id.img)
    public void setimg() {
        if (!hadConfirm) {
            getPhotoPopWindowInstance();
            mPhotoPopWindow.setFocusable(true);
            mPhotoPopWindow.showAtLocation(this
                    .findViewById(R.id.persion_main), Gravity.BOTTOM | Gravity
                    .CENTER_HORIZONTAL, 0, 0);
        }
    }

    @OnClick(R.id.confirm)
    public void confirm() {
        if (!hadConfirm) {
            if (!TextUtils.isEmpty(content.getText().toString())) {
                isConfirm = true;
                if (hasImg)
                    NetworkAPI.getNetworkAPI().patzxzximg(getIntent().getStringExtra("did"),
                            content.getText().toString(),
                            showProgressDialog(), this);
                else
                    NetworkAPI.getNetworkAPI().patzxzxnoimg(getIntent().getStringExtra("did"),
                            content.getText().toString(),
                            showProgressDialog(), this);
            } else {
                showToast("病情简述不能为空");
            }
        } else {
            pay();
        }
    }

    @Override
    protected int setContentViewResId() {
        return R.layout.patient_zxzx_main;
    }

    @Override
    protected void initView() {
        setBackAction();
        setTitleAction("在线咨询");
    }

    @Override
    protected void initData() {
        options = new DisplayImageOptions.Builder()
                .showImageOnLoading(R.drawable.icon_camera)
                .showImageForEmptyUri(R.drawable.icon_camera)
                .showImageOnFail(R.drawable.icon_camera)
                .cacheInMemory(true)
                .cacheOnDisk(true)
                .build();
        NetworkAPI.getNetworkAPI().patzxzx(getIntent().getStringExtra("did"), showProgressDialog(), this);
    }

    @Override
    public void onRequestSucceed(Object data, String requestAction) {
        if (HttpRequest.PAT_ONLINE_CONS.equals(requestAction)) {
            if (isConfirm) {
                hadConfirm = true;
                confirm.setText("立即支付");
                confirm.setBackgroundResource(R.drawable.fbpl_red_button_selector);
                priceLay.setVisibility(View.VISIBLE);
                orderId = ((PatientZXResponse)data).getOrderid();
                content.setEnabled(false);
            } else {
                hospital.setText(((PatientZXZXResponse) data).getHospital());
                doctor.setText(((PatientZXZXResponse) data).getRealname());
                price.setText("支付金额：" + ((PatientZXZXResponse) data).getPrice() + "元");
                pricePay = ((PatientZXZXResponse) data).getPrice();
            }
        }
        dismissProgressDialog();
    }

    @Override
    public void onRequestFailure(int error, String errorMsg, String requestAction) {
        showToast(errorMsg);
        dismissProgressDialog();
    }

    private void getPhotoPopWindowInstance() {
        if (null != mPhotoPopWindow) {
            mPhotoPopWindow.dismiss();
            return;
        } else {
            initPhotoPopWindow();
        }
    }

    private void initPhotoPopWindow() {
        LayoutInflater layoutInflater = LayoutInflater.from(this);
        View popupWindow = layoutInflater.inflate(R.layout.photo_pop_main, null);
        mPhotoPopWindow = new PopupWindow(popupWindow, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        mPhotoPopWindow.getContentView().measure(0, 0);
        mPhotoPopWindow.setAnimationStyle(R.style.mypopwindow_anim_style);

        popupWindow.findViewById(R.id.photo_cancel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPhotoPopWindow.dismiss();
            }
        });

        popupWindow.findViewById(R.id.open_photo).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CropHelper.clearCachedCropFile(mCropParams.uri);
                startActivityForResult(CropHelper.buildCropFromGalleryIntent(mCropParams), CropHelper.REQUEST_CROP);
                mPhotoPopWindow.dismiss();
            }
        });

        popupWindow.findViewById(R.id.open_camera).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = CropHelper.buildCaptureIntent(mCropParams.uri);
                startActivityForResult(intent, CropHelper.REQUEST_CAMERA);
                mPhotoPopWindow.dismiss();
            }
        });

        popupWindow.findViewById(R.id.outside).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPhotoPopWindow.dismiss();
            }
        });
    }

    @Override
    public CropParams getCropParams() {
        return mCropParams;
    }

    @Override
    public void onPhotoCropped(Uri uri) {
        hasImg = true;
        imageLoader.displayImage(MyApp.PHOTO_BASIC_PATH + uri.getPath(), img, options);
    }

    @Override
    public void onCropCancel() {
        Toast.makeText(this, "Crop canceled!", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onCropFailed(String message) {
        Toast.makeText(this, "Crop failed:" + message, Toast.LENGTH_LONG).show();
    }

    // 商户PID
    public static final String PARTNER = "2088021102299606";
    // 商户收款账号
    public static final String SELLER = "cdxt999@126.com";
    // 商户私钥，pkcs8格式
    public static final String RSA_PRIVATE = "MIICdgIBADANBgkqhkiG9w0BAQEFAASCAmAwggJcAgEAAoGBALuY0R1drK1NvUg+1SvzS+iawrjiONnnbJTMZJAsHPr2xp3xRVQNPfCBdrDzZ5nVOwx5pY/4c60KzlpZ9nRTQdYvLIXdmhCX62hGq59b8MGKfoYY1wQAUcMwcZMT+V+iTkw4J73EwmqS2y4kOHEcoMp8TB63qgQIvLb+0bHV35mDAgMBAAECgYAQtsHS2vi6jBWULTzy6H9X3woATO+BwpOzAi7nhXajiX8SrV9e6LsfBXnctCMD2rKjHud4V0t/HFpcRG2n2Y8g7gY+K3DQSeaQSyGuSTX5QE6tv14Ov/Kl7uB8qkIw/jcbPk5uQhm9oXIH9drTc4/QU5MdIV9h6rFlH1UM+LxPuQJBAOUqBRAAOVpAn66C6R1WM+Kp5aMxlIOGZ7GWgwDzPKXYt0iqTo7I2P3GPJ0sGcYVZGmbXWQhbfR8kHI+Z25/bN0CQQDRkK2JeC5epMS/4cyb+msB1/TbBSPQYsDACDKL0W0baH8VYn1vwpnnhY8EXHkUjIslsvXUPAYB3dzF6iFrfAnfAkA3829y/asDFx4lnH7QE9jtMXAIzTUme61blZT8qWaYU3ZEfphZkj4wj7MC6N6OF3EBu9YseWEAPV2DFytntiLdAkBWTt6ZwkxeoD+Dw0wQZUcS4E0wsuI4HaPCst2WZe8onZXRSdndYGkgSApJwof/ZY6dPSIvgXT76dLWFAIlkVVDAkEAsnFzGE409zb4UifADQmo767s2NDBFtTnaUpetZjYjGUmHClYXEQFZQMOK71rJKu9KVRBeNzRYdvFs+3jLnXezw==";
    // 支付宝公钥
    public static final String RSA_PUBLIC = "";
    private static final int SDK_PAY_FLAG = 1;

    private static final int SDK_CHECK_FLAG = 2;

    private Handler mHandler = new Handler() {
        public void handleMessage(Message msg) {
            switch (msg.what) {
                case SDK_PAY_FLAG: {
                    PayResult payResult = new PayResult((String) msg.obj);

                    // 支付宝返回此次支付结果及加签，建议对支付宝签名信息拿签约时支付宝提供的公钥做验签
                    String resultInfo = payResult.getResult();

                    String resultStatus = payResult.getResultStatus();

                    // 判断resultStatus 为“9000”则代表支付成功，具体状态码代表含义可参考接口文档
                    if (TextUtils.equals(resultStatus, "9000")) {
                        getApp().getPatDocInfoActivity().finish();
                        finish();
                        Toast.makeText(PatientZXZXActivity.this, "支付成功",
                                Toast.LENGTH_SHORT).show();
                    } else {
                        // 判断resultStatus 为非“9000”则代表可能支付失败
                        // “8000”代表支付结果因为支付渠道原因或者系统原因还在等待支付结果确认，最终交易是否成功以服务端异步通知为准（小概率状态）
                        if (TextUtils.equals(resultStatus, "8000")) {
                            Toast.makeText(PatientZXZXActivity.this, "支付结果确认中",
                                    Toast.LENGTH_SHORT).show();

                        } else {
                            // 其他值就可以判断为支付失败，包括用户主动取消支付，或者系统返回的错误
                            Toast.makeText(PatientZXZXActivity.this, "支付失败",
                                    Toast.LENGTH_SHORT).show();
                            if (RongIM.getInstance() != null) {
                                RongIM.getInstance().startPrivateChat(PatientZXZXActivity.this, "11396", "盗版哥");
                            }
                        }
                    }
                    break;
                }
                case SDK_CHECK_FLAG: {
                    Toast.makeText(PatientZXZXActivity.this, "检查结果为：" + msg.obj,
                            Toast.LENGTH_SHORT).show();
                    break;
                }
                default:
                    break;
            }
        };
    };

    /**
     * call alipay sdk pay. 调用SDK支付
     *
     */
    public void pay() {
        if (TextUtils.isEmpty(PARTNER) || TextUtils.isEmpty(RSA_PRIVATE)
                || TextUtils.isEmpty(SELLER)) {
            new AlertDialog.Builder(this)
                    .setTitle("警告")
                    .setMessage("需要配置PARTNER | RSA_PRIVATE| SELLER")
                    .setPositiveButton("确定",
                            new DialogInterface.OnClickListener() {
                                public void onClick(
                                        DialogInterface dialoginterface, int i) {
                                    //
                                    finish();
                                }
                            }).show();
            return;
        }
        // 订单
        String orderInfo = getOrderInfo("咨询付款", "预约支付", pricePay);

        // 对订单做RSA 签名
        String sign = sign(orderInfo);
        try {
            // 仅需对sign 做URL编码
            sign = URLEncoder.encode(sign, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        // 完整的符合支付宝参数规范的订单信息
        final String payInfo = orderInfo + "&sign=\"" + sign + "\"&"
                + getSignType();

        Runnable payRunnable = new Runnable() {

            @Override
            public void run() {
                // 构造PayTask 对象
                PayTask alipay = new PayTask(PatientZXZXActivity.this);
                // 调用支付接口，获取支付结果
                String result = alipay.pay(payInfo);

                Message msg = new Message();
                msg.what = SDK_PAY_FLAG;
                msg.obj = result;
                mHandler.sendMessage(msg);
            }
        };

        // 必须异步调用
        Thread payThread = new Thread(payRunnable);
        payThread.start();
    }

    /**
     * check whether the device has authentication alipay account.
     * 查询终端设备是否存在支付宝认证账户
     *
     */
    public void check(View v) {
        Runnable checkRunnable = new Runnable() {

            @Override
            public void run() {
                // 构造PayTask 对象
                PayTask payTask = new PayTask(PatientZXZXActivity.this);
                // 调用查询接口，获取查询结果
                boolean isExist = payTask.checkAccountIfExist();

                Message msg = new Message();
                msg.what = SDK_CHECK_FLAG;
                msg.obj = isExist;
                mHandler.sendMessage(msg);
            }
        };

        Thread checkThread = new Thread(checkRunnable);
        checkThread.start();

    }

    /**
     * get the sdk version. 获取SDK版本号
     *
     */
    public void getSDKVersion() {
        PayTask payTask = new PayTask(this);
        String version = payTask.getVersion();
        Toast.makeText(this, version, Toast.LENGTH_SHORT).show();
    }

    /**
     * create the order info. 创建订单信息
     *
     */
    public String getOrderInfo(String subject, String body, String price) {

        // 签约合作者身份ID
        String orderInfo = "partner=" + "\"" + PARTNER + "\"";

        // 签约卖家支付宝账号
        orderInfo += "&seller_id=" + "\"" + SELLER + "\"";

        // 商户网站唯一订单号
        orderInfo += "&out_trade_no=" + "\"" + orderId + "\"";

        // 商品名称
        orderInfo += "&subject=" + "\"" + subject + "\"";

        // 商品详情
        orderInfo += "&body=" + "\"" + body + "\"";

        // 商品金额
        orderInfo += "&total_fee=" + "\"" + price + "\"";

        // 服务器异步通知页面路径
        orderInfo += "&notify_url=" + "\"" + "http://notify.msp.hk/notify.htm"
                + "\"";

        // 服务接口名称， 固定值
        orderInfo += "&service=\"mobile.securitypay.pay\"";

        // 支付类型， 固定值
        orderInfo += "&payment_type=\"1\"";

        // 参数编码， 固定值
        orderInfo += "&_input_charset=\"utf-8\"";

        // 设置未付款交易的超时时间
        // 默认30分钟，一旦超时，该笔交易就会自动被关闭。
        // 取值范围：1m～15d。
        // m-分钟，h-小时，d-天，1c-当天（无论交易何时创建，都在0点关闭）。
        // 该参数数值不接受小数点，如1.5h，可转换为90m。
        orderInfo += "&it_b_pay=\"30m\"";

        // extern_token为经过快登授权获取到的alipay_open_id,带上此参数用户将使用授权的账户进行支付
        // orderInfo += "&extern_token=" + "\"" + extern_token + "\"";

        // 支付宝处理完请求后，当前页面跳转到商户指定页面的路径，可空
        orderInfo += "&return_url=\"m.alipay.com\"";

        // 调用银行卡支付，需配置此参数，参与签名， 固定值 （需要签约《无线银行卡快捷支付》才能使用）
        // orderInfo += "&paymethod=\"expressGateway\"";

        return orderInfo;
    }

    /**
     * get the out_trade_no for an order. 生成商户订单号，该值在商户端应保持唯一（可自定义格式规范）
     *
     */
    public String getOutTradeNo() {
        SimpleDateFormat format = new SimpleDateFormat("MMddHHmmss",
                Locale.getDefault());
        Date date = new Date();
        String key = format.format(date);

        Random r = new Random();
        key = key + r.nextInt();
        key = key.substring(0, 15);
        return key;
    }

    /**
     * sign the order info. 对订单信息进行签名
     *
     * @param content
     *            待签名订单信息
     */
    public String sign(String content) {
        return SignUtils.sign(content, RSA_PRIVATE);
    }

    /**
     * get the sign type we use. 获取签名方式
     *
     */
    public String getSignType() {
        return "sign_type=\"RSA\"";
    }
}
