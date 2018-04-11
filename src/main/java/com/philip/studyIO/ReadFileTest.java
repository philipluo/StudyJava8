package com.philip.studyIO;

import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.stream.Stream;

public class ReadFileTest {

    /**
     * Study from http://www.baeldung.com/reading-file-in-java
     * 1. read file from classpath
     * 2. read file with JDK7
     * 3. read file with JDK8
     * 4. read file with FileUtils
     * 5. Read Content from URL
     * 6. Read File from JAR
     */
    @Test
    public void readFileTest() {
        String fileName = "sub/a.json";

        try {
            //Read File from JAR
            System.out.println("Method 1: bufferReader.readLine()");
            InputStream is = ReadFileTest.class.getClassLoader().getResourceAsStream(fileName);
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(is));
            String line = null;
            while ((line = bufferedReader.readLine()) != null) {
                System.out.println(line);
            }
            bufferedReader.close();

            //method 2
            System.out.println("Method 2: bufferReader.lines()");
            InputStream is2 = ReadFileTest.class.getClassLoader().getResourceAsStream(fileName);
            BufferedReader bufferedReader2 = new BufferedReader(new InputStreamReader(is2));
            bufferedReader2.lines().forEach(System.out::println);
            bufferedReader2.close();

            //method 3
            System.out.println("Method 3: Files.lines");
            Path path = Paths.get(getClass().getClassLoader().getResource(fileName).toURI());
            Stream<String> lines = Files.lines(path);
            lines.forEach(System.out::println);
            lines.close();

            //method 4, read file from current directory
            System.out.println("Method 4: bufferReader.lines()");
            String fileName4 = "com/philip/studyIO/ReadFileTest.class";
            InputStream is4 = ReadFileTest.class.getClassLoader().getResourceAsStream(fileName4);
            BufferedReader bufferedReader4 = new BufferedReader(new InputStreamReader(is4));
            bufferedReader4.lines().forEach(System.out::println);
            bufferedReader4.close();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (URISyntaxException e) {
            e.printStackTrace();
        }

    }
}
