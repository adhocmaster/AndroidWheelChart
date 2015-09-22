package com.muktadir.canvas1;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    public static final String TAG = "Main::";
    WheelChartView wheelChartView;
    private int []chosenLevels;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        chosenLevels = new int[16];

        chosenLevels[0] = 3;
        chosenLevels[1] = 1;
        chosenLevels[2] = 4;

        wheelChartView = (WheelChartView) findViewById(R.id.myView);

        wheelChartView.setChosenLevels(chosenLevels);
//        wheelChartView.populateTestTitles();

        wheelChartView.setMaxWheelSize(300f);

        wheelChartView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {


                wheelChartView.UpdateWithMouseEvent(event);

                wheelChartView.performClick();

                return true;
            }
        });

        wheelChartView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Log.e(TAG, "Click coordinates : " +
                        String.valueOf(wheelChartView.getCurX()) + "x" + String.valueOf(wheelChartView.getCurY()));
                Log.e(TAG, "View coordinates : lf " +
                        String.valueOf(wheelChartView.getLeft()) + "x" + String.valueOf(wheelChartView.getTop()));

                wheelChartView.invalidate();
            }
        });


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
