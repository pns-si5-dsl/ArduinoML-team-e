package external;

import external.antlr.ArduinoMLLexer;
import external.antlr.ArduinoMLParser;
import external.arduinoML.builder.ModelBuilder;
import kernel.model.App;
import org.antlr.v4.runtime.BaseErrorListener;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {

    public static void main(String[] args) throws Exception {
        System.out.println("\n\nRunning the ANTLR compiler for ArduinoML");

        CharStream stream = getCharStream(args);
        App theApp = buildModel(stream);
        //exportToCode(theApp);
    }

    private static CharStream getCharStream(String[] args) throws IOException {
        //if (args.length < 1) throw new RuntimeException("no input file");
        //Path input = Paths.get(new File(args[0]).toURI());
        Path input = Paths.get(new File("/home/ludovic/Bureau/Ecole/SI-5/DSL/ArduinoML-team-e/src/main/java/external/examples/example_1.txt").toURI());
        System.out.println("Using input file: " + input);
        return CharStreams.fromPath(input);
    }

    private static App buildModel(CharStream stream) throws Exception {
        ArduinoMLLexer lexer   = new ArduinoMLLexer(stream);
        lexer.removeErrorListeners();
        lexer.addErrorListener(new BaseErrorListener()); //TODO custom error listener

        ArduinoMLParser parser  = new ArduinoMLParser(new CommonTokenStream(lexer));
        parser.removeErrorListeners();
        parser.addErrorListener(new BaseErrorListener()); //TODO custom error listener

        ParseTreeWalker walker  = new ParseTreeWalker();
        ModelBuilder builder = new ModelBuilder();

        walker.walk(builder, parser.program()); // parser.program() is the entry point of the grammar

        return builder.build();
    }

/*    private static void exportToCode(App theApp) {
        Visitor codeGenerator = new ToWiring();
        theApp.accept(codeGenerator);
        System.out.println(codeGenerator.getResult());
    }*/

}
