package internal.annotations;

import kernel.model.values.SIGNAL;

public @interface Output {
    int pin();
    SIGNAL init();
}
