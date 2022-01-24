package internal.interfaces;

import kernel.model.values.SIGNAL;

public interface TransitionBuilder {
    NextStateBuilder is(SIGNAL signal);
}
