package internal;

import internal.annotations.ArduinoML;
import internal.builders.EmbeddedApplication;
import internal.examples.*;
import kernel.generator.Generator;
import kernel.generator.Visitor;
import kernel.model.App;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Set;
import java.util.stream.Collectors;

public class Main {
    private static String packageName = "internal.examples";
    private static String outputDir = "./out";

    public static void main(String[] args) throws IOException {
        readArgs(args);
        Set<EmbeddedApplication> apps = findAndCreateApplicationInPackage(packageName);
        for(EmbeddedApplication app : apps){
            System.out.printf("\tBuilding app of class '%s'...\n", app.getClass().getName());
            exportToCode(app.build());
        }
        System.out.println("Done.");
    }

    private static void readArgs(String[] args) {
        switch (args.length){
            case 0: break;
            case 2:
                packageName = args[1];
            case 1:
                outputDir = args[0];
                break;
            default:
                throw new IllegalArgumentException(
                    "Error : bad usage"
                );
        }
        System.out.printf("Parsing classes in package %s...\n", packageName);
        System.out.printf("Creating files in dir %s...\n", outputDir);
    }

    private static void exportToCode(App theApp) throws IOException {
        Visitor<StringBuffer> codeGenerator = new Generator();
        theApp.accept(codeGenerator);

        File file = new File(outputDir+"/"+theApp.getName()+".ino");
        Files.createDirectories(Path.of(file.getParent()));
        Files.deleteIfExists(file.toPath());
        file.createNewFile();

        Files.writeString(file.toPath(), codeGenerator.getGeneratedCode());
    }

    private static Set<EmbeddedApplication> findAndCreateApplicationInPackage(String packageName){
        return findAllClassesUsingClassLoader(packageName)
                .stream()
                .filter(c -> c.isAnnotationPresent(ArduinoML.class))
                .map(c -> {
                    try {
                        return (EmbeddedApplication) c.newInstance();
                    } catch (ClassCastException d) {
                        System.err.printf(
                            "The class '%s' is define by annotation @ArduinoML " +
                            "but do not implement EmbeddedApplication class.",
                            c.getName()
                        );
                    } catch (InstantiationException | IllegalAccessException e) {
                        e.printStackTrace();
                    }
                    throw new RuntimeException();
                })
                .collect(Collectors.toSet());
    }

    private static Set<Class> findAllClassesUsingClassLoader(String packageName) {
        InputStream stream = ClassLoader.getSystemClassLoader()
                .getResourceAsStream(packageName.replaceAll("[.]", "/"));
        assert stream != null;
        BufferedReader reader = new BufferedReader(new InputStreamReader(stream));
        return reader.lines()
                .filter(line -> line.endsWith(".class"))
                .map(line -> getClass(line, packageName))
                .collect(Collectors.toSet());
    }

    private static Class getClass(String className, String packageName) {
        try {
            return Class.forName(packageName + "."
                    + className.substring(0, className.lastIndexOf('.')));
        } catch (ClassNotFoundException ignored) {}
        return null;
    }
}
