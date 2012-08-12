package com.sourcebits.gautam.volumeBar;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

/**
 * VolumeButton draws a Volume bar and the volume on it.
 * 
 * @author Gautam
 * 
 * 
 */
public class VolumeButton extends View {

	private boolean stateOn;// state of the Volume Button increase volume or
							// Decrease volume
	private static final String TAG = VolumeButton.class.getCanonicalName();
	private static Paint paint;
	private final int COMPONENT_WIDTH = 0;// Default Component Width
	private final int COMPONENT_HEIGHT = 0;// Default Component Width
	private int mWidth;// Volume Bar width center
	private int mHeight;// Volume Bar height center
	float center_x;// Volume Bar x center
	float center_y;// Volume Bar y center
	private static int K = -1;// Default volume value
	private float radius = 0;// //Default radius value
	private int MAX_VOLUME = 20; // default value is 20 but this can be changed
									// using setMax_Volume method
	private int VOLUMEBAR_WIDTH = 0; // Set maximum maximum volume
	private int VOLUME_SIZE = 0; // Set maximum maximum volume

	/*
	 * Set the volume
	 */
	void setMax_Volume(int maxVolume) {
		MAX_VOLUME = maxVolume;
	}

	/*
	 * Get the volume
	 */
	int getMax_Volume() {
		return MAX_VOLUME;
	}

	/*
	 * Get the current volume
	 */
	int getVOLUME() {
		return K;
	}

	/*
	 * Get the volume
	 */
	int getVOLUMEBAR_WIDTH() {
		return VOLUMEBAR_WIDTH;
	}

	/*
	 * Constructor of the class Volume Button
	 */
	public VolumeButton(Context context) {
		super(context);
		paint = new Paint();// initialized paint

	}

	/*
	 * Parameterised Constructor of the class Volume Button
	 */

	public VolumeButton(Context context, AttributeSet attrs) {
		super(context, attrs);
		paint = new Paint();
		stateOn = true;// default value is increase

	}

	/*
	 * Decreases the volume
	 */

	public void decreaseValues() {

		if (K > 0) {
			stateOn = false;
			invalidate();
			Log.v("CLICKED", "false");
		}
	}

	/*
	 * Increses the volume
	 */
	public void increaseValues() {

		if (K < MAX_VOLUME) {
			stateOn = true;
			invalidate();
			Log.v("CLICKED", "true");
		}
	}

	/*
	 * On draw
	 * 
	 * @see android.view.View#onDraw(android.graphics.Canvas)
	 */
	@Override
	protected void onDraw(Canvas canvas) {

		final RectF oval = new RectF();
		oval.set(center_x - radius, center_y - radius, center_x + radius,
				center_y + radius);
		paint.setColor(Color.GRAY);
		canvas.drawCircle(center_x, center_y, radius, paint);
		/*
		 * Increasing the Volume
		 */
		if (stateOn) {

			K++;
			paint.setColor(Color.CYAN);
			canvas.drawArc(oval, 0, 360 / MAX_VOLUME * K, true, paint);
			if (K == MAX_VOLUME) {
				paint.setColor(Color.RED);
				canvas.drawArc(oval, 360 / MAX_VOLUME * K, 10, true, paint);
			}
			paint.setColor(Color.WHITE);
			canvas.drawCircle(center_x, center_y, radius - VOLUMEBAR_WIDTH,
					paint);

		}
		/*
		 * Decreasing the Volume
		 */
		else {
			K--;

			paint.setColor(Color.CYAN);
			canvas.drawArc(oval, 0, 360 / MAX_VOLUME * K, true, paint);

			paint.setColor(Color.WHITE);
			canvas.drawCircle(center_x, center_y, radius - VOLUMEBAR_WIDTH,
					paint);
		}

		paint.setColor(Color.CYAN);
		paint.setTextSize(VOLUME_SIZE);// setining the Volume on the canvas
		canvas.drawText(K + "", center_x, center_y, paint);

	}

	/*
	 * On Measure
	 * 
	 * @see android.view.View#onMeasure(int, int)
	 */
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		mWidth = MeasureSpec.getSize(widthMeasureSpec);
		mHeight = MeasureSpec.getSize(heightMeasureSpec);

		int widthMode = MeasureSpec.getMode(widthMeasureSpec);
		int heightMode = MeasureSpec.getMode(heightMeasureSpec);

		mWidth = chooseDimensionWidth(widthMode, mWidth);
		mHeight = chooseDimensionHeight(heightMode, mHeight);

		Log.d(TAG, "width =" + mWidth + ", height =" + mHeight);
		setMeasuredDimension(mWidth, mHeight);
		center_x = mWidth / 2;// setting the width
		center_y = mHeight / 2;// setting the height
		radius = mHeight / 2;
		VOLUME_SIZE = (int) radius / 4;
		VOLUMEBAR_WIDTH = VOLUME_SIZE;
	}

	/*
	 * Returns the Height
	 */
	private int chooseDimensionWidth(int mode, int size) {
		if (mode == MeasureSpec.AT_MOST || mode == MeasureSpec.EXACTLY) {
			return size;
		} else { // (mode == MeasureSpec.UNSPECIFIED)
			return COMPONENT_WIDTH;
		}
	}

	/*
	 * Returns the width
	 */
	private int chooseDimensionHeight(int mode, int size) {
		if (mode == MeasureSpec.AT_MOST || mode == MeasureSpec.EXACTLY) {
			return size;
		} else { // (mode == MeasureSpec.UNSPECIFIED)
			return COMPONENT_HEIGHT;
		}
	}

}
