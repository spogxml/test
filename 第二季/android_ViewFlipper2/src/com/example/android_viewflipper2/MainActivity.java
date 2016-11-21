package com.example.android_viewflipper2;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.ImageView;
import android.widget.ViewFlipper;

public class MainActivity extends Activity {

	private ViewFlipper flipper;
    private float startX;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		flipper = (ViewFlipper) findViewById(R.id.flipper);
		flipper.addView(getImageView(R.drawable.pic1));
		flipper.addView(getImageView(R.drawable.pic2));
		flipper.addView(getImageView(R.drawable.pic3));
		flipper.addView(getImageView(R.drawable.pic4));
		flipper.setBackgroundColor(Color.BLACK);
		// flipper.setFlipInterval(5000);
		// flipper.setInAnimation(this, R.anim.anim_in);
		// flipper.setOutAnimation(this, R.anim.anim_out);
		// flipper.startFlipping();
		// detector=new GestureDetector(this, this);
	}

	private ImageView getImageView(int resId) {
		ImageView image = new ImageView(this);
		image.setBackgroundResource(resId);
		return image;
	}

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN: {
			startX=event.getX();
			break;
		}
		
		case MotionEvent.ACTION_UP:
		{
			//向右滑动
			if(event.getX()-startX>50)
			{
				 flipper.setInAnimation(this, R.anim.left_in);
				 flipper.setOutAnimation(this, R.anim.left_out);
				 flipper.showNext();
				
			}
			//向左滑动
			if(startX-event.getX()>50)
			{
				 flipper.setInAnimation(this, R.anim.right_in);
				 flipper.setOutAnimation(this, R.anim.right_out);
				 flipper.showPrevious();
			}
			break;
		}
		}

		return super.onTouchEvent(event);

	}

}
