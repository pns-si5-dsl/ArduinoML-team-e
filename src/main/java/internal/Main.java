package internal;

import internal.examples.BlinkApplication;
import kernel.generator.Generator;
import kernel.generator.Visitor;
import kernel.model.App;

public class Main {
    public static void main(String[] args) {
        App app = new BlinkApplication().build();
        exportToCode(app);
    }

    private static void exportToCode(App theApp) {
        Visitor<StringBuffer> codeGenerator = new Generator();
        theApp.accept(codeGenerator);
        System.out.println(codeGenerator.getGeneratedCode());
    }
}
