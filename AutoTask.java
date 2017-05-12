package wsu.csc5991.waynecountyroadcommission;

/**
 * Created by ussha on 6/29/2016.
 */
// Import Java packages
import java.util.TimerTask;

//--------------------------------------------------------------------
// class AutoTask
//--------------------------------------------------------------------
public class AutoTask extends TimerTask
{

    // Declare variables
    private ActMain parent;


    //--------------------------------------------------------------------
    // AutoTask constructor
    //--------------------------------------------------------------------
    public AutoTask(ActMain parent)
    {
        this.parent = parent;


    }


    //--------------------------------------------------------------------
    // run
    //--------------------------------------------------------------------
    public void run()
    {
        parent.updateControlsHandler.sendEmptyMessage(0);

    }

}