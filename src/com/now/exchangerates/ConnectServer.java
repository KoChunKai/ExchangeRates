package com.now.exchangerates;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import org.htmlcleaner.HtmlCleaner;
import org.htmlcleaner.TagNode;

import android.appwidget.AppWidgetManager;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.RemoteViews;
import android.widget.TextView;

public class ConnectServer extends AsyncTask<String, Integer, String> {
	
	private TextView[] tvprice;
	private int buyorself;
	private String country = null;
	public RemoteViews widgetview;
	public AppWidgetManager appWidgetManager;
	public int[] appWidgetId;
	public Context context;
	
	ConnectServer(TextView[] textview,int buyorself){
		tvprice = new TextView[13];
		this.tvprice = textview;
		this.buyorself = buyorself;
	}
	ConnectServer(String country,Context context,AppWidgetManager appWidgetManager,RemoteViews widgetview, int[] appWidgetId){
		this.country = country;
		this.context = context;
		this.widgetview = widgetview;
		this.appWidgetManager = appWidgetManager;
		this.appWidgetId = appWidgetId;
	}

	@Override
	protected String doInBackground(String... RateUrl) {
		// TODO Auto-generated method stub
		InputStream iStream = null;
	    HttpURLConnection urlConnection = null;
	    try{
	        URL url = new URL(RateUrl[0]);
	        urlConnection = (HttpURLConnection) url.openConnection();
	        urlConnection.connect();
	        iStream = urlConnection.getInputStream();
	        BufferedReader br = new BufferedReader(new InputStreamReader(iStream));
	        StringBuffer sb  = new StringBuffer();
	        String line = "";
	        while( ( line = br.readLine())  != null){
	            sb.append(line);
	        }
	        br.close();
	        iStream.close();
	        urlConnection.disconnect();
	        return sb.toString();	        
	    } catch(Exception e){
	    	return "error!!!!";
	    }
	}
	
	@Override
	protected void onPostExecute(String result) {
		TagNode tagNode;
		tagNode = new HtmlCleaner().clean(result);
		TagNode[] RateData = tagNode.getElementsByAttValue("class", "decimal", true, true);
		StringBuffer RateValue  = new StringBuffer();
		for(TagNode a : RateData){
			RateValue.append(a.getText().toString()+",");
		}
		String[] RateDataDetail =  RateValue.toString().split(",");
		//setApptvprice(RateValue.toString().split(","));
		if(country==null){
			setApptvprice(RateValue.toString().split(","));
		}
		else{
			widgetview.setTextViewText(R.id.widgetlayoutbuyorself, context.getString(R.string.self));
			widgetview.setTextViewText(R.id.widgetlayoutmoney, RateDataDetail[1]+ "\n"+RateDataDetail[3]);
			appWidgetManager.updateAppWidget(appWidgetId, widgetview);
		}
	}
	
	public void setApptvprice(String[] RateDataDetail){
		switch(buyorself){
		case 0:
			tvprice[0].setText	(RateDataDetail[1]+ "\n"+RateDataDetail[3]);
			tvprice[1].setText	(RateDataDetail[5]+ "\n"+RateDataDetail[7]);
			tvprice[2].setText	(RateDataDetail[9]+ "\n"+RateDataDetail[11]);
			tvprice[3].setText	(RateDataDetail[13]+"\n"+RateDataDetail[15]);
			tvprice[4].setText	(RateDataDetail[17]+"\n"+RateDataDetail[19]);
			tvprice[5].setText	(RateDataDetail[21]+"\n"+RateDataDetail[23]);
			tvprice[6].setText	(RateDataDetail[25]+"\n"+RateDataDetail[27]);
			tvprice[7].setText	(RateDataDetail[29]+"\n"+RateDataDetail[31]);
			tvprice[8].setText	(RateDataDetail[45]+"\n"+RateDataDetail[47]);
			tvprice[9].setText	(RateDataDetail[61]+"\n"+RateDataDetail[63]);
			tvprice[10].setText	(RateDataDetail[65]+"\n"+RateDataDetail[67]);
			tvprice[11].setText	(RateDataDetail[69]+"\n"+RateDataDetail[71]);
			tvprice[12].setText	(RateDataDetail[73]+"\n"+RateDataDetail[75]);
			break;
		case 1:
			tvprice[0].setText	(RateDataDetail[0]+ "\n"+RateDataDetail[2]);
			tvprice[1].setText	(RateDataDetail[4]+ "\n"+RateDataDetail[6]);
			tvprice[2].setText	(RateDataDetail[8]+ "\n"+RateDataDetail[10]);
			tvprice[3].setText	(RateDataDetail[12]+"\n"+RateDataDetail[14]);
			tvprice[4].setText	(RateDataDetail[16]+"\n"+RateDataDetail[18]);
			tvprice[5].setText	(RateDataDetail[20]+"\n"+RateDataDetail[22]);
			tvprice[6].setText	(RateDataDetail[24]+"\n"+RateDataDetail[26]);
			tvprice[7].setText	(RateDataDetail[28]+"\n"+RateDataDetail[30]);
			tvprice[8].setText	(RateDataDetail[44]+"\n"+RateDataDetail[46]);
			tvprice[9].setText	(RateDataDetail[60]+"\n"+RateDataDetail[62]);
			tvprice[10].setText	(RateDataDetail[64]+"\n"+RateDataDetail[66]);
			tvprice[11].setText	(RateDataDetail[68]+"\n"+RateDataDetail[70]);
			tvprice[12].setText	(RateDataDetail[72]+"\n"+RateDataDetail[74]);
			break;
		}
	}
	
	public void setWidgeview(String country){
		switch(country){
		case "usd":

			break;
		case "hkd":

			break;
		}
	}
	
	
	
}
