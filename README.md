# ControlTrafficLight

You've been hired by the Wayne County Road Commission to write an Android application to monitor and control the traffic light at a busy intersection.  The application simulates traffic moving through the lights and the lights changing colors.  It enables the user to monitor how much traffic is waiting in each direction and to control how long the green light is in each direction.  The application has a single screen containing the following elements:
	Inputs
	● Two seek bars that control how long the green light lasts in the north-south direction and the east-west direction.  Restrict the range of the seek bars from 3 to 30 clock ticks.  Note that the default and unchangeable minimum value for a seek bar is 0 so any selected value less than 3 should be adjusted to 3.
Outputs
	● Two traffic lights built from three image views each.  There are four states that the traffic lights sequence through.
		 At the start of each state, set all traffic lights to black.
 At the end of each state, add vehicles to each direction by adding random numbers (0 to 5) for each clock tick, and show the vehicles waiting in each direction.
	● Four labels/text boxes showing the vehicles waiting in each direction.  Set the text color and background color of each one based on this table:
● A label/text box showing the clock (starts at 0 when application starts).
	● A label/text box showing the clock ticks left for the current green or yellow light.

