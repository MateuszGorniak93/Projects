package zad1;

import java.io.*;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;

public class Futil {
    public static void processDir(String dirName, String resultFileName) {
        SimpleFileVisitor<Path> visitor = new SimpleFileVisitor<Path>() {
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
                BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(new FileInputStream(file.toString()), "cp1250"));
                String str;
                while ((str = bufferedReader.readLine()) != null) {
                    try (PrintWriter printWriter = new PrintWriter(new BufferedWriter(new FileWriter(resultFileName, false)))) {
                        printWriter.println(str);
                        printWriter.flush();
                    }
                }
                return FileVisitResult.CONTINUE;
            }
        };

        try {
            Files.walkFileTree(Paths.get(dirName), visitor);
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
}
