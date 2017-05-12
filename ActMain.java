//==============================================================
//
// Title:       Wayne County Road Commission
// Course:      CSC 5991 Android Mobile Application Development
// Application: 3
// Author:      Shailja Sharma
// Date:        July-06-2016
// Description: This Android application monitor and control the traffic light at a busy intersection.
// The application simulates traffic moving,through the lights and the lights changing colors.
// It enables the user to monitor how much traffic is waiting in each direction and to control how long the green light is in each direction.
//==============================================================


package wsu.csc5991.waynecountyroadcommission;


/*------------------------------------
 Import Android packages
 */

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.Random;
import java.util.Timer;

/*--------------------------------------------------------------
  Class ActMain
--------------------------------------------------------------
*/

public class ActMain extends AppCompatActivity {

    /*--------------------------------------------------------------
  Declare Variable for the controls
--------------------------------------------------------------
*/

    SeekBar seekBar1;
    SeekBar seekBar2;
    EditText clock;
    EditText timeLeft;
    ImageView imageView1;
    ImageView imageView2;
    ImageView imageView3;
    ImageView imageView4;
    ImageView imageView5;
    ImageView imageView6;
    EditText headingNorth;
    EditText headingSouth;
    EditText headingEast;
    EditText headingWest;
    TextView seekbar1progress;
    TextView seekbar2progress;

    /*--------------------------------------------------------------
     Declare few Variable
   --------------------------------------------------------------
   */
    Timer timer1;
    Timer timer2;
    private int progressTimeseekbar1;
    private int progressTimeseekbar2;
    private int clockTime = 1;
    private int clockTime2 = 1;
    ActMain actMain;
    Handler handler;


    /*--------------------------------------------------------------
      Constants
    --------------------------------------------------------------
    */
    private static final int UPDATE_INTERVAL = 1;  // Seconds
    private static final int PROGRESS_INTIAL_VALUE = 3;



    /*--------------------------------------------------------------
           onCreate
     --------------------------------------------------------------
    */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.laymain);

        //Calling method trafficControl
        trafficControl(null);


    }

     /*--------------------------------------------------------------
           trafficControl
     --------------------------------------------------------------
    */


    public void trafficControl(String success) {
         /*--------------------------------------------------------------
           initializing the variables
     --------------------------------------------------------------
    */
        imageView1 = (ImageView) findViewById(R.id.ib1);
        imageView2 = (ImageView) findViewById(R.id.ib2);
        imageView3 = (ImageView) findViewById(R.id.ib3);
        imageView4 = (ImageView) findViewById(R.id.ib4);
        imageView5 = (ImageView) findViewById(R.id.ib5);
        imageView6 = (ImageView) findViewById(R.id.ib6);

        seekBar1 = (SeekBar) findViewById(R.id.seekbar1);
        seekBar2 = (SeekBar) findViewById(R.id.seekbar2);

        clock = (EditText) findViewById(R.id.clock);
        timeLeft = (EditText) findViewById(R.id.timeLeft);

        headingEast = (EditText) findViewById(R.id.headingeast);
        headingWest = (EditText) findViewById(R.id.headingwest);
        headingNorth = (EditText) findViewById(R.id.headingnorth);
        headingSouth = (EditText) findViewById(R.id.headingsouth);

        seekbar1progress = (TextView) findViewById(R.id.seekbar1progress);
        seekbar2progress = (TextView) findViewById(R.id.seekbar2progress);

        //setting the maximum value for seekbar

        seekBar1.setMax(30);
        seekBar2.setMax(30);


        progressTimeseekbar1 = seekBar1.getProgress();
        progressTimeseekbar2 = seekBar2.getProgress();


        System.out.println("progressTimeseekbar1::" + progressTimeseekbar1);
        System.out.println("progressTimeseekbar2::" + progressTimeseekbar2);

        //set west yellow to black
        imageView5.setImageResource(R.mipmap.trafficlight);

        //setting north south red to black
        imageView1.setImageResource(R.mipmap.trafficlight);

        /*--- state1 ---*/

        //east-west to red
        imageView4.setImageResource(R.mipmap.traffic_light_red);

        //north -south to green
        imageView3.setImageResource(R.mipmap.traffic_light_green);

        //timer for state1
        timer1 = new Timer();
        timer1.schedule(new AutoTask(this), 0, (UPDATE_INTERVAL * 1000));


        handler = new Handler();
        actMain = this;



        //post delay method for state1
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {

                timer2 = new Timer();
                 /*--some state3 code --*/

                //  north south to red
                imageView1.setImageResource(R.mipmap.traffic_light_red);

                //setting north south yellow to black
                imageView2.setImageResource(R.mipmap.trafficlight);

                //east west to green
                imageView6.setImageResource(R.mipmap.traffic_light_green);

                //setting east west red to black
                imageView4.setImageResource(R.mipmap.trafficlight);
                timer2.schedule(new AutoTaskaSecond(actMain), 0, (UPDATE_INTERVAL * 1000));

            }
        }, ((progressTimeseekbar1 * 1000) + 300));

        actMain = this;

        //post delay method for state3

        handler.postDelayed(new Runnable() {
            @Override
            public void run() {


                updateControlsHandlercreate();

            }
        }, (((progressTimeseekbar1 + progressTimeseekbar2) * 1000) + 500));

        //seekbar1 changelistener
        seekBar1.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int newValue = 0;

            public void onProgressChanged(SeekBar seekBar,
                                          int progress, boolean fromUser) {
                newValue = progress;
                if (newValue < PROGRESS_INTIAL_VALUE) {
                    seekBar1.setProgress(PROGRESS_INTIAL_VALUE);
                    seekbar1progress.setText(Integer.toString(PROGRESS_INTIAL_VALUE));
                } else {
                    seekBar1.setProgress(newValue);
                    seekbar1progress.setText(Integer.toString(newValue));
                }

            }

            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });

        //seekbar2 changelistener
        seekBar2.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            int newValue = 0;

            public void onProgressChanged(SeekBar seekBar,
                                          int progress, boolean fromUser) {
                newValue = progress;
                if (newValue < PROGRESS_INTIAL_VALUE) {
                    seekBar2.setProgress(PROGRESS_INTIAL_VALUE);
                    seekbar2progress.setText(Integer.toString(PROGRESS_INTIAL_VALUE));
                } else {
                    seekBar2.setProgress(newValue);
                    seekbar2progress.setText(Integer.toString(newValue));
                }

            }

            public void onStartTrackingTouch(SeekBar seekBar) {
            }

            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


    }

    /*--------------------------------------------------------------
               updateControlsHandlercreate
     --------------------------------------------------------------
    */

    public void updateControlsHandlercreate() {

        System.out.println("inside update control");

        trafficControl("success");

    }

    /*--------------------------------------------------------------
            updateControlsHandlerAutoTask for east west
  --------------------------------------------------------------
 */
    public Handler updateControlsHandlerAutoTask = new Handler() {


        //------------------------------------------------------------
        // handleMessage
        //------------------------------------------------------------
        @Override
        public void handleMessage(Message msg) {

            System.out.println("inside seekbar2 controleer");
            Random rand = new Random();
            if (progressTimeseekbar2 >= 1) {

                //setting the clock time
                clock.setText(Integer.toString(clockTime2));
                timeLeft.setText(Integer.toString(progressTimeseekbar2));
                progressTimeseekbar2--;
                clockTime2++;

                //moving traffic east west
                    headingEast.setText(Integer.toString(Math.abs(Integer.parseInt(headingEast.getText().toString()) - rand.nextInt(20))));
                    headingWest.setText(Integer.toString(Math.abs(Integer.parseInt(headingWest.getText().toString()) - rand.nextInt(20))));

                //calling color change
                colorchange();


            } else {

                System.out.println("progressTimeseekbar2 in elase part" + progressTimeseekbar2 + "timer" + clockTime);
                /*--state4---  */

                //  north south to red
                imageView1.setImageResource(R.mipmap.traffic_light_red);

                // east west to yellow
                imageView5.setImageResource(R.mipmap.traffic_light_yellow);


                // east west green to black
                imageView6.setImageResource(R.mipmap.trafficlight);

                //adding traffic to north south
                headingNorth.setText(Integer.toString(rand.nextInt(5) + Integer.parseInt(headingNorth.getText().toString())));
                headingSouth.setText(Integer.toString(rand.nextInt(5) + Integer.parseInt(headingSouth.getText().toString())));

                //setting clock time
                clock.setText(Integer.toString(clockTime2));
                timeLeft.setText(Integer.toString(progressTimeseekbar2));


                progressTimeseekbar2 = 0;
                clockTime2 = 0;
                colorchange();

                if (timer2 != null) timer2.cancel();
            }
        }

    };

     /*--------------------------------------------------------------
            updateControlsHandler for north -south

  --------------------------------------------------------------
 */

    public Handler updateControlsHandler = new Handler() {


        //------------------------------------------------------------
        // handleMessage
        //------------------------------------------------------------
        @Override
        public void handleMessage(Message msg) {
            Random rand = new Random();
            System.out.println("inside seekbar1 controleer");
            if (progressTimeseekbar1 >= 1) {

                //setting the clock time
                clock.setText(Integer.toString(clockTime));
                timeLeft.setText(Integer.toString(progressTimeseekbar1));
                progressTimeseekbar1--;
                clockTime++;

                //moving the north south traffic
                headingNorth.setText(Integer.toString(Math.abs(Integer.parseInt(headingNorth.getText().toString()) - rand.nextInt(20))));
                headingSouth.setText(Integer.toString(Math.abs(Integer.parseInt(headingSouth.getText().toString()) - rand.nextInt(20))));

                //calling color change
                colorchange();

            } else {


                System.out.println("progressTimeseekbar1 in else part" + progressTimeseekbar2 + "clock time " + clockTime);

                /*--state2--*

                 North-South light to yellow*/
                imageView2.setImageResource(R.mipmap.traffic_light_yellow);


                //east-west to red
                imageView4.setImageResource(R.mipmap.traffic_light_red);

                //north south --  green to black
                imageView3.setImageResource(R.mipmap.trafficlight);

                //setting the east west traffic
                headingEast.setText(Integer.toString(rand.nextInt(5) + Integer.parseInt(headingEast.getText().toString())));
                headingWest.setText(Integer.toString(rand.nextInt(5) + Integer.parseInt(headingWest.getText().toString())));

                //setting the clock time
                clock.setText(Integer.toString(clockTime));
                timeLeft.setText(Integer.toString(progressTimeseekbar1));

                //calling color change
                colorchange();


                progressTimeseekbar1 = 0;
                clockTime = 0;



                if (timer1 != null)
                    timer1.cancel();
            }
        }

    };


    //------------------------------------------------------------
    // colorchange
    //------------------------------------------------------------

    private void colorchange() {
        System.out.println("inside color change ");
        int north;
        int east;
        int south;
        int west;

        east = Integer.parseInt(headingEast.getText().toString());
        west = Integer.parseInt(headingWest.getText().toString());
        north = Integer.parseInt(headingNorth.getText().toString());
        south = Integer.parseInt(headingSouth.getText().toString());

        if (east <= 10) {
            headingEast.setBackgroundResource(R.color.light_green);
            headingEast.setTextColor(getResources().getColor(R.color.black));
        } else if (east <= 20) {
            headingEast.setBackgroundResource(R.color.gold);
            headingEast.setTextColor(getResources().getColor(R.color.black));
        } else if (east > 20) {
            headingEast.setBackgroundResource(R.color.red);
            headingEast.setTextColor(getResources().getColor(R.color.white));

        }


        if (west <= 10) {
            headingWest.setBackgroundResource(R.color.light_green);
            headingWest.setTextColor(getResources().getColor(R.color.black));
        } else if (west <= 10) {
            headingWest.setBackgroundResource(R.color.gold);
            headingWest.setTextColor(getResources().getColor(R.color.black));
        } else if (west > 20) {
            headingWest.setBackgroundResource(R.color.red);
            headingWest.setTextColor(getResources().getColor(R.color.white));

        }


        if (north <= 10) {
            headingNorth.setBackgroundResource(R.color.light_green);
            headingNorth.setTextColor(getResources().getColor(R.color.black));
        } else if (north <= 20) {
            headingNorth.setBackgroundResource(R.color.gold);
            headingNorth.setTextColor(getResources().getColor(R.color.black));
        } else if (north > 20) {
            headingNorth.setBackgroundResource(R.color.red);
            headingNorth.setTextColor(getResources().getColor(R.color.white));

        }
        if (south <= 10) {
            headingSouth.setBackgroundResource(R.color.light_green);
            headingSouth.setTextColor(getResources().getColor(R.color.black));
        } else if (south <= 20) {
            headingSouth.setBackgroundResource(R.color.gold);
            headingSouth.setTextColor(getResources().getColor(R.color.black));
        } else if (south > 20) {
            headingSouth.setBackgroundResource(R.color.red);
            headingSouth.setTextColor(getResources().getColor(R.color.white));

        }

    }


}
