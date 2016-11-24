package io.r2.j8p.t4_nio2;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.nio.file.FileStore;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.nio.file.attribute.*;
import java.time.Instant;

/**
 * Check, delete, copy, or move a file or directory by using the Files class
 */
public class FilesBasicOps {

    Path file1 = null;
    Path file2 = null;
    Path dir1 = null;
    Path dir2 = null;

    public void copy() throws Exception {
        // stream -> path
        Files.copy(new ByteArrayInputStream(new byte[0]), file1, StandardCopyOption.REPLACE_EXISTING);
        // path -> stream
        Files.copy(file1, new ByteArrayOutputStream());
        // path -> path
        Files.copy(file1, file2, StandardCopyOption.COPY_ATTRIBUTES, StandardCopyOption.ATOMIC_MOVE);
    }

    public void move() throws Exception {
        Files.move(file1, file2);
    }

    public void create() throws Exception {
        FileAttribute<?> attr = PosixFilePermissions.asFileAttribute(PosixFilePermissions.fromString("rwxr-x---"));
        Files.createFile(file1, attr);
        Files.createDirectories(dir1);
        Files.createDirectory(dir2);
        Files.createLink(file1 /* new */, file2 /* existing */); // create hard link
        Files.createSymbolicLink(file1 /* link */, file2 /* target */);

        Path tempDir = Files.createTempDirectory("prefix");
        Path tempFile = Files.createTempFile(tempDir /* optional */, "prefix", "suffix");
    }

    public void delete() throws Exception {
        Files.delete(file1);
        Files.deleteIfExists(dir1);
    }

    public void query() throws Exception {
        Files.exists(file1); Files.notExists(file1);
        Files.getLastModifiedTime(file1);
        Files.getPosixFilePermissions(file1); Files.getOwner(file1);
        Files.isHidden(file1); Files.isReadable(file1); Files.isExecutable(file1); Files.isReadable(file1);
        Files.isRegularFile(file1); Files.isDirectory(file1); Files.isSymbolicLink(file1);
        Files.readSymbolicLink(file1);

        Files.size(file1);

        Files.probeContentType(file1);

        // okay, this is a setter, but this is a special one
        Files.setLastModifiedTime(file1, FileTime.from(Instant.now()));

        // read system specific attributes
        Files.getAttribute(file1, "unix:uid");

        // gets all attributes in bulk
        // also can read Dos / Posix file attributes
        BasicFileAttributes attrs = Files.readAttributes(file1, BasicFileAttributes.class);
        BasicFileAttributeView v = Files.getFileAttributeView(file1, BasicFileAttributeView.class);
        // v can read attrs (see above) and write times
        // other views are available, like Posix, Dos, Acl, Owner


        // get file storage to query storage attributes
        FileStore fs = Files.getFileStore(dir1);
        fs.getTotalSpace(); fs.getUsableSpace(); fs.isReadOnly();
    }

}
