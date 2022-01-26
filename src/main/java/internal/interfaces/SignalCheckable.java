package internal.interfaces;

import kernel.model.values.SIGNAL;

public interface SignalCheckable {
    NextStateDefinable is(SIGNAL signal);
}
