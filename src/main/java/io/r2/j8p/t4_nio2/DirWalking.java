package io.r2.j8p.t4_nio2;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.EnumSet;
import java.util.stream.Stream;

/**
 * Recursively access a directory tree by using the DirectoryStream and FileVisitor interfaces
 */
public class DirWalking {

    static void list(String startDir) throws Exception {
        Path start = Paths.get(startDir);

        Files.list(start).forEach(System.out::println);
    }

    static void find(String startDir, int maxDepth, String globPattern) throws Exception {
        Path start = Paths.get(startDir);

        PathMatcher matcher = FileSystems.getDefault().getPathMatcher(globPattern);

        Stream<Path> found = Files.find(start, maxDepth, (path, basicAttr) -> {
            return basicAttr.isRegularFile() && matcher.matches(path);
        }, FileVisitOption.FOLLOW_LINKS /* this is optional */);

        found.limit(5).map(Path::toString).forEach(System.out::println);
    }

    static void dirStream(String startDir) throws Exception {
        Path start = Paths.get(startDir);

        // alternatives: no filter, or glob pattern (glob:**/*.java)
        try (DirectoryStream<Path> stream = Files.newDirectoryStream(start, (path) -> {
            return path.getFileName().toString().startsWith("a");
        })) {
            // Note: this is not a stream, but an iterable object
            for (Path entry : stream) {
                System.out.println(entry);
            }
        };
    }

    static void walkStream(String startDir, String contentType) throws Exception {
        Path start = Paths.get(startDir);

        // walk produces a stream
        Files.walk(start, 2 /* max depth, optional */)
                .filter((x) -> {
                    try {
                        return Files.isRegularFile(x) && contentType.equals(Files.probeContentType(x));
                    }
                    catch (IOException ie) {
                        return false;
                    }
                })
                .forEach(System.out::println);
    }

    void walkFileTree(String startDir) throws Exception {
        Path start = Paths.get(startDir);

        // or simply with start dir and visitor
        Files.walkFileTree(start, EnumSet.noneOf(FileVisitOption.class) /* options, eg. FOLLOW_LINKS */, 4 /* maxdepth */, new Visitor());
    }

    /*
     * A  FileVisitor can process operations before or after a directory, for any file, or for any failed file
     */
    public class Visitor extends SimpleFileVisitor<Path> {
        @Override
        public FileVisitResult preVisitDirectory(Path dir, BasicFileAttributes attrs) throws IOException {
            System.out.println("pre: "+dir.toString());
            if (dir.getFileName().toString().matches("^[0-9]")) {
                // skip entire subtree if starts with number
                return FileVisitResult.SKIP_SUBTREE;
            }
            else {
                return FileVisitResult.CONTINUE;
            }
        }

        @Override
        public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) throws IOException {
            System.out.println("file: "+file.toString());
            // only 1 file / dir, skip all siblings
            return FileVisitResult.SKIP_SIBLINGS;
        }

        @Override
        public FileVisitResult visitFileFailed(Path file, IOException exc) throws IOException {
            System.out.println("failed: "+file.toString());
            // finish
            return FileVisitResult.TERMINATE;
        }

        @Override
        public FileVisitResult postVisitDirectory(Path dir, IOException exc) throws IOException {
            System.out.println("post: "+dir.toString());
            // continue normally
            return FileVisitResult.CONTINUE;
        }
    }

}
