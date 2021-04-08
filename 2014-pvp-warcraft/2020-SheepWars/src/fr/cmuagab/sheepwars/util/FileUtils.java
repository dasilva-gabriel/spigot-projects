package fr.cmuagab.sheepwars.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class FileUtils {
    public static void copyFolder(final File src, final File dest) throws IOException {
        if (src.isDirectory()) {
            if (!dest.exists()) {
                dest.mkdir();
            }
            final String files[] = src.list();
            for (final String file : files) {
                final File srcFile = new File(src, file);
                final File destFile = new File(dest, file);
                FileUtils.copyFolder(srcFile, destFile);
            }
        } else {
            final InputStream in = new FileInputStream(src);
            final OutputStream out = new FileOutputStream(dest);
            final byte[] buffer = new byte[1024];
            int length;
            while ((length = in.read(buffer)) > 0) {
                out.write(buffer, 0, length);
            }
            in.close();
            out.close();
        }
    }

    public static boolean delete(final File path) {
        if (path.exists()) {
            final File files[] = path.listFiles();
            for (final File file : files) {
                if (file.isDirectory()) {
                    FileUtils.delete(file);
                } else {
                    file.delete();
                }
            }
        }
        return path.delete();
    }
}
