package internal;

import internal.examples.BlinkApplication;
import kernel.model.App;

import java.util.Optional;

public class Main {
    public static void main(String[] args) {
        // Build.
        BlinkApplication embeddedApp = new BlinkApplication();
        App app = embeddedApp.build();

        // Print.
        System.out.println("Name = " + app.getName());
        System.out.println("Bricks = ");
        app.getBricks().forEach(brick -> System.out.println("- " + brick.getName() + " on pin " + brick.getPin()));
        System.out.println("States = ");
        app.getStates().forEach(state -> System.out.println("- " + state.getName()));
        System.out.println("Initial = ");
        Optional.ofNullable(app.getInitial()).ifPresent(state -> System.out.println(state.getName()));
        System.out.println("Code = ");
        System.out.println(embeddedApp.generateCode());
    }
}
