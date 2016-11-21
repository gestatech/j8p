package io.r2.j8p.t4_nio2;

import java.io.File;
import java.nio.file.FileSystems;
import java.nio.file.LinkOption;
import java.nio.file.Path;
import java.nio.file.Paths;

/**
 * Operate on file and directory paths by using the Paths class
 * (Also review legacy File quickly)
 */
public class FilePath {

    public void file() throws Exception {
        // File is not nio, but a quick refresher is in order

        // abstract representation of a filename or directory name
        File f1 = new File("/some/path/file.txt");
        File f2 = new File("relative/file.txt");

        File d1 = new File("/some/dir");

        File f3 = new File(d1, "child");
        File f4 = new File("/parent", "child");

        // can be used to create temp file
        File t1 = File.createTempFile("prefix", ".suffix");
        File t2 = File.createTempFile("prefix", ".suffix", d1 /* optional directory */);

        // bunch of query methods
        f1.canExecute(); f1.canRead(); f1.canWrite();
        f1.createNewFile();
        f1.delete();
        f1.deleteOnExit(); // great of temp files
        f1.exists();
        f1.getFreeSpace(); f1.getTotalSpace(); f1.getUsableSpace();
        f1.isDirectory(); f1.isFile(); f1.isHidden();
        f1.lastModified();

        // same changer
        f1.setLastModified(System.currentTimeMillis());
        f1.setExecutable(true); f1.setWritable(true, true /* owner only */); f1.setReadable(false);


        // util
        f1.mkdir(); f1.mkdirs();
        f1.renameTo(f2);


        // navigate
        File p = f1.getParentFile();

        // convert to string
        f1.getParent();
        f1.getAbsolutePath();
        f1.getCanonicalPath();

        // list files in directory
        String[] filenames = f1.list(); // or with filter FilenameFilter
        File[] files = f1.listFiles(); // or with filter FilenameFilter or FileFilter

        // list file system roots
        File[] roots = File.listRoots();

        // and most importantly: convert to path for nio.2
        Path path = f1.toPath();
    }

    public void path() throws Exception {
        // how to create path?

        // from File
        Path p1 = new File("xxx.txt").toPath();

        // with Paths.get
        Path p2 = Paths.get("/xx/yyy.txt");
        Path p3 = Paths.get("xxx", "yyy", "zzz.txt");

        // from FileSystems
        FileSystems.getDefault().getPath("/xxx", "yyy.txt"); // this is the same as Paths.get

        // get elements
        p1.toString();
        p1.getFileName();
        p1.getName(0);
        p1.getNameCount();
        p1.subpath(0,2);

        // navigate
        p1.getParent();
        p1.getRoot();
        p1.resolve("c");
        p1.resolve(p3);
        p1.resolveSibling("xxx"); // p1.getParent().resolve("xxx")

        // create relative path
        p1.relativize(p2); // returns how to reach p2 from p1

        /// convert
        p1.toAbsolutePath();
        p1.toRealPath();
        p1.toRealPath(LinkOption.NOFOLLOW_LINKS);
        File f = p1.toFile();

    }
}
