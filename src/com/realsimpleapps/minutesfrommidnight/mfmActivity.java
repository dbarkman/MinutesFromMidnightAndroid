package com.realsimpleapps.minutesfrommidnight;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.Calendar;

public class mfmActivity extends Activity {

	TextView tv;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		tv = (TextView)findViewById(R.id.mfmtv);

		Thread t = new Thread() {

			@Override
			public void run() {
				try {
					while (!isInterrupted()) {
						Thread.sleep(1000);
						runOnUiThread(new Runnable() {
							@Override
							public void run() {
								String mfm = getMFM();
								tv.setText(mfm);
							}
						});
					}
				} catch (InterruptedException e) {
				}
			}
		};
		t.start();
	}

	private String getMFM() {
		Calendar cal = Calendar.getInstance();
		int hour = cal.get(Calendar.HOUR_OF_DAY);
		int minute = cal.get(Calendar.MINUTE);
		int second = cal.get(Calendar.SECOND);
		int minutes = ((hour * 60) + minute);
		String minutesString = String.valueOf(minutes);
		String secondString = String.format("%02d", second);
		return minutesString + ":" + secondString;
	}
}
