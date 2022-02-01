package external;

import external.antlr.ArduinoMLLexer;
import external.antlr.ArduinoMLParser;
import external.arduinoML.builder.ModelBuilder;
import external.arduinoML.StopErrorListener;
import kernel.generator.Generator;
import kernel.generator.Visitor;
import kernel.model.App;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.tree.ParseTreeWalker;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class Main {

    public static void main(String[] args) throws Exception {
        System.out.println("\n\nRunning the ANTLR compiler for ArduinoML");

        CharStream stream = getCharStream(args);
        App theApp = buildModel(stream);
        exportToCode(theApp, args);
    }

    private static CharStream getCharStream(String[] args) throws IOException {
        if (args.length < 1) throw new RuntimeException("no input file");
        Path input = Paths.get(new File(args[0]).toURI());
        System.out.println("Using input file: " + input);
        return CharStreams.fromPath(input);
    }

    private static App buildModel(CharStream stream) throws Exception {
        ArduinoMLLexer lexer   = new ArduinoMLLexer(stream);
        lexer.removeErrorListeners();
        lexer.addErrorListener(new StopErrorListener());

        ArduinoMLParser parser  = new ArduinoMLParser(new CommonTokenStream(lexer));
        parser.removeErrorListeners();
        parser.addErrorListener(new StopErrorListener());

        ParseTreeWalker walker  = new ParseTreeWalker();
        ModelBuilder builder = new ModelBuilder();

        walker.walk(builder, parser.program()); // parser.program() is the entry point of the grammar

        return builder.build();
    }

    private static void exportToCode(App theApp, String[] args) throws IOException {
        if (args.length < 2) throw new RuntimeException("no output file name");
        String fileName = new File(args[0]).getParentFile().getAbsolutePath() + "/" + args[1];
        String extension = ".ino";
        Visitor<StringBuffer> codeGenerator = new Generator();
        theApp.accept(codeGenerator);
        File file = new File(fileName+extension);
        for (int i = 1; !file.createNewFile(); i++) {
            file = new File(fileName + "(" + i + ")" + extension);
        }
        BufferedWriter writer = new BufferedWriter(new FileWriter(file));
        writer.write(codeGenerator.getGeneratedCode().toString());
        System.out.println("Wrote generated code to the output file: " + file.getAbsolutePath());
        writer.close();
    }

}
