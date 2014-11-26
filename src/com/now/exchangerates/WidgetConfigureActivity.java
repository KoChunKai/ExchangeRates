package com.now.exchangerates;

import android.app.Activity;
import android.appwidget.AppWidgetManager;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;

public class WidgetConfigureActivity extends Activity {
	
	private Button setTest;
	private ImageButton[] setWidgetRate;
	private int[] ImageButtonID;
	private int mAppWidgetId;
	
	/*@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();
		

		/*Intent resultIntent = new Intent();
		resultIntent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, mAppWidgetId);
		setResult(RESULT_CANCELED, resultIntent);

		if (mAppWidgetId == AppWidgetManager.INVALID_APPWIDGET_ID)
		{
			finish();
		}
	}*/

	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		Log.v("WidgetConfingureActivity", "onCreate");
		super.onCreate(savedInstanceState);
		setContentView(R.layout.widgetconfigure);
		findCompent();
		setTest.setOnClickListener(Lisenter);
		for(int index=0;index<3;index++){
			setWidgetRate[index].setOnClickListener(Lisenter);
		}
		mAppWidgetId = AppWidgetManager.INVALID_APPWIDGET_ID;
		Intent intent = getIntent();
		Bundle extras = intent.getExtras();
		if (extras != null) {
			mAppWidgetId = extras.getInt(AppWidgetManager.EXTRA_APPWIDGET_ID, AppWidgetManager.INVALID_APPWIDGET_ID);
		}
		
	}

	private void findCompent(){
		setTest = (Button)findViewById(R.id.button1);
		setWidgetRate = new ImageButton[13];
		ImageButtonID = new int[]{R.id.widgetbutton01, R.id.widgetbutton02, R.id.widgetbutton03};
		for(int index=0;index<3;index++){
			setWidgetRate[index] = (ImageButton)findViewById(ImageButtonID[index]);
		}
	}
	
	private Button.OnClickListener Lisenter = new Button.OnClickListener() {
		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			
			Intent resultIntent = new Intent();
			resultIntent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, mAppWidgetId);
			
			switch(v.getId()){
			case R.id.button1:
				break;
			case R.id.widgetbutton01:
				Log.v("setCountry", "usd");
				break;
			case R.id.widgetbutton02:
				Log.v("setCountry", "hkd");
				break;
			case R.id.widgetbutton03:
				break;
			}
			Log.v("setResult", "ok");
			setResult(RESULT_OK, resultIntent);
			finish();
		}
	};
	
	@Override
	public void finish()
	{
		Log.v("TAG", "calling finish"); 
		super.finish();
	}

}
