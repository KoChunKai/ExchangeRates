package com.now.exchangerates;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

/*新增一個class，並繼承AppWidgetProvider，
 * 然後覆寫onUpdate()方法。通常會在其中啟動
 * Service來做想要做的事。這裡要知道兩個class
 * ：AppWidgetManager及RemoteViews。*/
public class WidgetAppWidgetProvider extends AppWidgetProvider {
	
	@Override
	public void onReceive(Context context, Intent intent) {
		int[] appWidgetIds = intent.getIntArrayExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS);
		AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
		//設定Widget的外觀
		RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.widgetlayout);
		remoteViews.setTextViewText(R.id.widgetlayoutbuyorself, "");
		appWidgetManager.updateAppWidget(appWidgetIds, remoteViews);
		ConnectServer setWidget = new ConnectServer("usd",context,appWidgetManager,remoteViews,appWidgetIds);
		setWidget.execute("http://rate.bot.com.tw/Pages/Static/UIP003.zh-TW.htm");
		super.onReceive(context, intent);
	}
	
	@Override
	public void onUpdate(Context context,AppWidgetManager appWidgetManager, int[] appWidgetIds) {
		/*RemoteViews 是用來更新widget的顯示畫面，
		 * 例如：RemoteViews有一個setTextViewText()的方法，
		 * 可以設定Widget中的 TextView。它會傳入widget中的
		 * TextView的Id，及要顯示的字串，等同於呼叫那個Vidw
		 * 的setText()方法，來設定文字。
		 * AppWidgetManager則是用來把你的AppWidgetProvider和RemoteViews做連結。*/
		RemoteViews updateViews = new RemoteViews(context.getPackageName(), R.layout.widgetlayout);
		updateViews.setTextViewText(R.id.widgetlayoutbuyorself, "");
	    appWidgetManager.updateAppWidget(appWidgetIds, updateViews);
	    super.onUpdate(context, appWidgetManager, appWidgetIds);
	}
	
	
	@Override
	public void onDeleted(Context context, int[] appWidgetIds) {
	}

	@Override
	public void onDisabled(Context context) {
	}
	
}
