package com.now.exchangerates;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.Intent;
import android.widget.RemoteViews;

/*�s�W�@��class�A���~��AppWidgetProvider�A
 * �M���мgonUpdate()��k�C�q�`�|�b�䤤�Ұ�
 * Service�Ӱ��Q�n�����ơC�o�̭n���D���class
 * �GAppWidgetManager��RemoteViews�C*/
public class WidgetAppWidgetProvider extends AppWidgetProvider {
	
	@Override
	public void onReceive(Context context, Intent intent) {
		int[] appWidgetIds = intent.getIntArrayExtra(AppWidgetManager.EXTRA_APPWIDGET_IDS);
		AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(context);
		//�]�wWidget���~�[
		RemoteViews remoteViews = new RemoteViews(context.getPackageName(), R.layout.widgetlayout);
		remoteViews.setTextViewText(R.id.widgetlayoutbuyorself, "");
		appWidgetManager.updateAppWidget(appWidgetIds, remoteViews);
		ConnectServer setWidget = new ConnectServer("usd",context,appWidgetManager,remoteViews,appWidgetIds);
		setWidget.execute("http://rate.bot.com.tw/Pages/Static/UIP003.zh-TW.htm");
		super.onReceive(context, intent);
	}
	
	@Override
	public void onUpdate(Context context,AppWidgetManager appWidgetManager, int[] appWidgetIds) {
		/*RemoteViews �O�Ψӧ�swidget����ܵe���A
		 * �Ҧp�GRemoteViews���@��setTextViewText()����k�A
		 * �i�H�]�wWidget���� TextView�C���|�ǤJwidget����
		 * TextView��Id�A�έn��ܪ��r��A���P��I�s����Vidw
		 * ��setText()��k�A�ӳ]�w��r�C
		 * AppWidgetManager�h�O�Ψӧ�A��AppWidgetProvider�MRemoteViews���s���C*/
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
