package com.sourcebits.gautam.volumeBar;

import android.app.Activity;
import android.os.Bundle;
import android.view.KeyEvent;

/**
 * Main activity to display the Custom Volume Buttom
 * 
 * @author cynogn
 * 
 */

public class MainActivity extends Activity {
	public VolumeButton mVolumeButton;// Volume slider of Custom Button

	/*
	 * OnCreate Method
	 * 
	 * @see android.app.Activity#onCreate(android.os.Bundle)
	 */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);

		mVolumeButton = (VolumeButton) findViewById(R.id.sliderButton);
		mVolumeButton.setMax_Volume(50);

	}

	/*
	 * Overridding onKeyDown keydown to handle volume control
	 * 
	 * @see android.app.Activity#onKeyDown(int, android.view.KeyEvent)
	 */
	@Override
	public boolean onKeyDown(int keyCode, android.view.KeyEvent event) {
		/*
		 * On Key down of Volume button , decrease the volume
		 */
		if (keyCode == KeyEvent.KEYCODE_VOLUME_DOWN) {
			mVolumeButton.decreaseValues();
			return true;
		} else if (keyCode == KeyEvent.KEYCODE_VOLUME_UP) {
			mVolumeButton.increaseValues();
			return true;
		} else
			return super.onKeyDown(keyCode, event);

	}
}
