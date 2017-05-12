package wsu.csc5991.waynecountyroadcommission;

import java.util.TimerTask;

/**
 * Created by ussha on 6/30/2016.
 */
public class AutoTaskaSecond extends TimerTask
    {

        // Declare variables
        private ActMain parent;


        //--------------------------------------------------------------------
        // AutoTask constructor
        //--------------------------------------------------------------------
        public AutoTaskaSecond(ActMain parent)
        {
            this.parent = parent;


        }


        //--------------------------------------------------------------------
        // run
        //--------------------------------------------------------------------
    public void run()
    {
        parent.updateControlsHandlerAutoTask.sendEmptyMessage(0);

    }

}

