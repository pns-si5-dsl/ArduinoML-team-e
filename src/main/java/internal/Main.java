package internal;

import internal.examples.BlinkApplication;
import kernel.generator.Generator;
import kernel.generator.Visitor;
import kernel.model.App;
import kernel.model.component.Sensor;

import java.util.Optional;

public class Main {
    public static void main(String[] args) {
        // Build.
        App app = new BlinkApplication().build();
        exportToCode(app);

        // Print.
        System.out.println("Name = " + app.getName());
        System.out.println("Bricks = ");
        app.getBricks().forEach(brick -> System.out.println(
            "- " + (brick instanceof Sensor ? "Sensor" : "Actuator") + ": " +
            "'" + brick.getName() + "' " +
            "on pin " + brick.getPin()
        ));
        System.out.println("States = ");
        app.getStates().forEach(state -> {
            System.out.println("- " + state.getName());
            state.getActions().forEach(action -> {
                System.out.println("--> " + action);
            });
            state.getTransitions().forEach(transition -> System.out.println("--> " + transition));
        });
        System.out.print("Initial = ");
        Optional.ofNullable(app.getInitial()).ifPresent(state -> System.out.println(state.getName()));
    }

    private static void exportToCode(App theApp) {
        Visitor<StringBuffer> codeGenerator = new Generator();
        theApp.accept(codeGenerator);
        System.out.println(codeGenerator.getGeneratedCode());
    }
}
