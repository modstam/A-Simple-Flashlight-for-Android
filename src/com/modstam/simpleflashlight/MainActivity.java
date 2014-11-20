package com.modstam.simpleflashlight;

import java.util.List;

import android.hardware.Camera;
import android.hardware.Camera.AutoFocusCallback;
import android.hardware.Camera.Parameters;
import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;

public class MainActivity extends Activity {
	private Camera cam = Camera.open();
	private Parameters p = cam.getParameters();
	List<String> flashModes = p.getSupportedFlashModes();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
//	@Override
//	public void onConfigurationChanged(Configuration newConfig) {
//	    super.onConfigurationChanged(newConfig);
//	    setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
//	}

	/**
	 * turns the camera light on or off
	 * @param view
	 */
	public void light(View view){

		//		if(manuName.contains("motorola")){
		//			DroidLED led;
		//			try {
		//	            led = new DroidLED();
		//	            led.enable(true);
		//	        } catch (Exception e) {
		//	            // TODO Auto-generated catch block
		//	            e.printStackTrace();
		//	        }
		//		}else 
		String flashMode = p.getFlashMode();
		

		if(!flashModes.contains(Parameters.FLASH_MODE_TORCH)){ //SAMSUNG ETC

			if(p.getFlashMode().equals(p.FLASH_MODE_ON)){ 
				cam.stopPreview(); //OFF
				p.setFlashMode(p.FLASH_MODE_OFF);
				cam.setParameters(p);
				//cam.release();
			}else{

				p.setFlashMode(Parameters.FLASH_MODE_ON);

				cam.setParameters(p);
				try {
					cam.autoFocus(new AutoFocusCallback() {
						public void onAutoFocus(boolean success, Camera camera) {
							//nothing
						}
					});
				} catch (Exception e) {
					e.printStackTrace();
				}
			}



		}else{
			if(p.getFlashMode().equals(p.FLASH_MODE_TORCH)){ 
				cam.stopPreview(); //OFF
				p.setFlashMode(p.FLASH_MODE_OFF);
				cam.setParameters(p);
				//cam.release();
			}
			else{
				p.setFlashMode(p.FLASH_MODE_TORCH); //ON
				cam.setParameters(p);
				cam.startPreview();
				//			cam.autoFocus(new AutoFocusCallback() {
				//				public void onAutoFocus(boolean success, Camera camera) {
				//				}
				//			});
			}
		}
	}
}

