package internal.annotations;

public @interface State {
    boolean initial() default false;
}
