package com.example.lab14;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GestureDetectorCompat;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
public class MainActivity extends AppCompatActivity {
    private GestureDetectorCompat myDetector;
    TextView upX; TextView upY;
    ImageView imageView;
    View myView;
    boolean flag=false;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        myView = findViewById(R.id.myView);
        upX = (TextView)findViewById(R.id.up_x);
        upY = (TextView)findViewById(R.id.up_y);
        imageView=findViewById(R.id.imageView);
        imageView.setVisibility(View.GONE);
        GestureListener myGestures = new GestureListener();
        myDetector = new GestureDetectorCompat(this, myGestures);
        myView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return myDetector.onTouchEvent(event);
            }
        });
        myDetector.setOnDoubleTapListener(myGestures);
    }
    public class GestureListener implements
            GestureDetector.OnGestureListener,
            GestureDetector.OnDoubleTapListener {
        //OnDown return true for fling and doubleTap
        @Override
        public boolean onDown(MotionEvent event) {
            return true;
        }
        @Override
        public boolean onFling(MotionEvent event1, MotionEvent event2,
                               float velocityX, float velocityY) {
            if (flag){
                imageView.setVisibility(View.GONE);
                flag=false;
            }
            else{
                imageView.setVisibility(View.VISIBLE);
                flag=true;
            }
            return true;
        }
        @Override
        public void onLongPress(MotionEvent event) {
        }
        @Override
        public boolean onScroll(MotionEvent event1, MotionEvent event2, float
                distanceX, float distanceY) {
            return false;
        }
        @Override
        public void onShowPress(MotionEvent event) {
        }
        @Override
        public boolean onSingleTapUp(MotionEvent event) {
            String x; String y;
            int action = event.getAction();
            x = Integer.toString((int)event.getX());
            y = Integer.toString((int)event.getY());
            if (action==MotionEvent.ACTION_UP){
                upX.setText(x);
                upY.setText(y);
            }
            return true;
        }
        @Override
        public boolean onDoubleTap(MotionEvent event) {
            Toast toast = Toast.makeText(getApplicationContext(),
                    "Double tap", Toast.LENGTH_SHORT);
            toast.show();
            return true;
        }
        @Override
        public boolean onDoubleTapEvent(MotionEvent event) {
            return false;
        }
        @Override
        public boolean onSingleTapConfirmed(MotionEvent event) {
            return false;
        }
    }
}