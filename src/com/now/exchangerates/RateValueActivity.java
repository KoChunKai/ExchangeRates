package com.now.exchangerates;

import android.support.v7.app.ActionBarActivity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.TextView;
import com.google.android.gms.ads.*;

public class RateValueActivity extends ActionBarActivity {

	private TextView[] tvprice,tvbuyorself;
	private int[] tvpriceID,tvbuyorselfID;
	private AdView adView;
	private static final String AD_UNIT_ID = "ca-app-pub-5486177198741618/3919826081";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.applayout_ratevalue);
		setTV();
	}
	
	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		SelfRate();
	    // 建立 adView。
	    adView = new AdView(this);
	    adView.setAdUnitId(AD_UNIT_ID);
	    adView.setAdSize(AdSize.BANNER);

	    // 假設 LinearLayout 已獲得 android:id="@+id/mainLayout" 屬性，
	    // 查詢 LinearLayout。
	    LinearLayout layout = (LinearLayout)findViewById(R.id.ad);

	    // 在其中加入 adView。
	    layout.addView(adView);

	    // 啟動一般請求。
	    AdRequest adRequest = new AdRequest.Builder().build();

	    // 以廣告請求載入 adView。
	    adView.loadAd(adRequest);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.setting, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		switch(id){
		case R.id.action_about:
			showAbout();
			return true;
		case R.id.action_buyrate:
			buyRate();
			return true;
		case R.id.action_selfrate:
			SelfRate();
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	private void showAbout() {
		AlertDialog.Builder AboutDialog = new AlertDialog.Builder(this);
		AboutDialog.setTitle(getString(R.string.app_name));
		AboutDialog.setMessage(getString(R.string.about_detail));
		AboutDialog.setNegativeButton(getString(R.string.suggestion),new DialogInterface.OnClickListener() {
				 
			@Override
			public void onClick(DialogInterface dialog, int which) {
				Intent intent = new Intent(Intent.ACTION_VIEW,Uri.parse("market://details?id=com.now.exchangerates"));
				startActivity(intent);
			}
		});
		AboutDialog.show();
	}

	private void SelfRate(){
		new ConnectServer(tvprice,0)
		.execute("http://rate.bot.com.tw/Pages/Static/UIP003.zh-TW.htm");
		for(int step=0;step<13;step++){
			tvbuyorself[step].setText(getString(R.string.self));
		}
	}
	
	private void buyRate(){
		new ConnectServer(tvprice,1)
		.execute("http://rate.bot.com.tw/Pages/Static/UIP003.zh-TW.htm");
		for(int step=0;step<13;step++){
			tvbuyorself[step].setText(getString(R.string.buy));
		}
	}
	
	private void setTV(){
		tvprice = new TextView[13];
		tvbuyorself = new TextView[13];
		tvpriceID = new int[]{R.id.tvprice01,	R.id.tvprice02,	R.id.tvprice03,	R.id.tvprice04,	R.id.tvprice05,	R.id.tvprice06,
				R.id.tvprice07,	R.id.tvprice08, R.id.tvprice09,	R.id.tvprice10,	R.id.tvprice11,	R.id.tvprice12,	R.id.tvprice13};
		tvbuyorselfID = new int[]{R.id.tvbuyorself01,	R.id.tvbuyorself02,	R.id.tvbuyorself03,	R.id.tvbuyorself04,	R.id.tvbuyorself05, R.id.tvbuyorself06,	R.id.tvbuyorself07,
				R.id.tvbuyorself08,	R.id.tvbuyorself09,	R.id.tvbuyorself10,	R.id.tvbuyorself11,	R.id.tvbuyorself12,	R.id.tvbuyorself13 };
		for(int index=0;index<13;index++){
			tvprice[index] = (TextView)this.findViewById(tvpriceID[index]);
			tvbuyorself[index] = (TextView)this.findViewById(tvbuyorselfID[index]);
			//usd//hkd//gbp//aud//cad//sgd//chf//jpy//thb//krw//vnd//myr//cny
		}
		
	}
}
