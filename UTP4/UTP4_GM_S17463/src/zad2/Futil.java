package zad2;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.stream.*;

public class Futil {

    public static void processDir(String dirName, String resultFileName) {

        try {
            Files.walkFileTree(Paths.get(dirName), new SimpleFileVisitor<Path>() {
                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {

                    Stream<String> stream = Files.lines(file, Charset.forName("cp1250"));

                    stream.forEach(line -> {
                        try {
                            String str = new String(line.getBytes("cp1250"), "cp1250");
                            try (PrintWriter printWriter = new PrintWriter(new BufferedWriter(new FileWriter(resultFileName, false)))) {
                                printWriter.println(str);
                                printWriter.flush();
                            } catch (IOException e) {
                                e.printStackTrace();
                            }
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        }
                    });

                    return FileVisitResult.CONTINUE;
                }
            });
        } catch(IOException e) {
            e.printStackTrace();
        }
    }
}