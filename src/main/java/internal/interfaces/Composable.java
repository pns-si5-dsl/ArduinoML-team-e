package internal.interfaces;

import kernel.model.component.Sensor;

public interface Composable extends NextStateDefinable {
    /**
     * Continue the definition condition of the transition by adding a new condition to check
     * @param sensor the sensor to check
     */
    SignalCheckable and(Sensor sensor);
}
