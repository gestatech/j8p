package io.r2.j8p.t4_nio2;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.channels.SeekableByteChannel;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

/**
 * Use Java SE 8 I/O improvements, including Files.find(), Files.walk(), and lines() methods
 */
public class FilesAdvancedOps {

    public void charsets() {
        // charsets are represented by java.nio.charset.Charset

        Charset.defaultCharset();
        Charset.availableCharsets();
        Charset.forName("cp1250");

        // also use StandardCharsets
        Charset c = StandardCharsets.UTF_8;
    }

    public void lines(Path path) throws Exception {
        // get lines of a file as a stream of strings

        Files.lines(path, StandardCharsets.UTF_8 /* optional */)
                .filter( s -> s.length() > 0)
                .sorted()
                .distinct()
                .forEach(System.out::println);
    }

    public void readall(Path path) throws Exception {
        byte[] allFile = Files.readAllBytes(path);
        List<String> allLines = Files.readAllLines(path);
    }

    public void ioShortHand(Path path) throws Exception {
        // Files contains some great shorthands for opening a channel, buffered reader/writer or input/output stream
        try (SeekableByteChannel c = Files.newByteChannel(path)) {}

        try (BufferedReader b = Files.newBufferedReader(path)) {}
        try (BufferedWriter b = Files.newBufferedWriter(path, StandardCharsets.UTF_8)) {}

        try (InputStream s = Files.newInputStream(path)) {}

        try (OutputStream s = Files.newOutputStream(path)) {}

    }
}
