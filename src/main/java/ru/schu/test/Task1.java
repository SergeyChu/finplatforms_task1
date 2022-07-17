package ru.schu.test;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Comparator;
import java.util.stream.Stream;

public class Task1 {
    public static final String HELP_MESSAGE = "Please specify exactly one argument with correct absolute path to the root folder";
    public static final String INCORRECT_PATH_MESSAGE = "The specified path doesn't exist";
    public static final String OUTPUT_FILE_NAME = "output.txt";

    public static void main(String... args) throws IOException {

        if (args.length != 1) {
            throw new IllegalArgumentException(HELP_MESSAGE);
        }

        String inputPath = args[0];
        if (!checkPathExists(inputPath)) {
            throw new IllegalArgumentException(INCORRECT_PATH_MESSAGE);
        }

        Comparator<Path> pathComparator = Comparator.comparing(path -> path.getFileName().toString());
        String outFilePath = inputPath + File.separator + OUTPUT_FILE_NAME;
        try (Stream<Path> paths = Files.walk(Paths.get(inputPath));
             FileWriter fileWriter = new FileWriter(outFilePath)) {

            paths
                    .filter(Files::isRegularFile)
                    .filter(path -> path.getFileName().toString().endsWith(".txt"))
                    .sorted(pathComparator)
                    .forEach(path -> readAndAppendContent(path, fileWriter));
        }

        System.out.println("Done, please check " + outFilePath);
    }

    private static boolean checkPathExists(String absolutePath) {
        Path path = Paths.get(absolutePath);
        return Files.exists(path);
    }

    private static void readAndAppendContent(Path pathToRead, FileWriter writer)  {
        try (Stream<String> lines = Files.lines(pathToRead)){
            lines.forEachOrdered(string -> Task1.writeString(writer, string));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void writeString(FileWriter writer, String stringToWrite) {
        try {
            writer.write(stringToWrite);
            writer.write(System.getProperty("line.separator"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
