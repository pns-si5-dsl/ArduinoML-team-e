package internal.interfaces;

public interface NextStateDefinable {
    /**
     * Define the next state of the transition.
     * @param nextStateName The next state of the transition.
     */
    void then(String nextStateName);
}
