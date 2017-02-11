package com.github.michalsenkyr.courses.scala.duplicatefinder.java;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

/**
 * Duplicate file finder in Java pre-8
 */
public class DuplicateFinder {
    private static MessageDigest digest;

    public static void main(String[] args) {
        // Group by size
        Map<Long, List<File>> bySize = new HashMap<>();
        for (String arg : args) {
            File path = new File(arg);
            for (File file : listAllFiles(path)) {
                long size = file.length();
                List<File> files = bySize.get(size);
                if (files == null) {
                    files = new LinkedList<>();
                    bySize.put(size, files);
                }
                files.add(file);
            }
        }

        // Group by hash
        Map<String, List<File>> byHash = new HashMap<>();
        for (List<File> files : bySize.values()) {
            if (files.size() > 1) {
                for (File file : files) {
                    String hash;
                    try {
                        hash = hash(file);
                    } catch (NoSuchAlgorithmException | IOException e) {
                        throw new RuntimeException(e);
                    }
                    List<File> hashList = byHash.get(hash);
                    if (hashList == null) {
                        hashList = new LinkedList<>();
                        byHash.put(hash, hashList);
                    }
                    hashList.add(file);
                }
            }
        }

        // Print output
        System.out.println("Duplicate files:");
        for (List<File> files : byHash.values()) {
            if (files.size() > 1) {
                for (Iterator<File> it = files.iterator(); it.hasNext(); ) {
                    File file = it.next();
                    if (it.hasNext()) {
                        System.out.print(file.getPath() + ", ");
                    } else {
                        System.out.println(file.getPath());
                    }
                }
            }
        }
    }

    private static List<File> listAllFiles(File dir) {
        List<File> files = new LinkedList<>();
        for (File file : dir.listFiles()) {
            if (file.isDirectory()) {
                files.addAll(listAllFiles(file));
            } else if (file.isFile()) {
                files.add(file);
            } else {
                throw new RuntimeException("Unknown file type: " + file);
            }
        }
        return files;
    }

    private static String hash(File file) throws NoSuchAlgorithmException, IOException {
        if (digest == null) digest = MessageDigest.getInstance("SHA-1");
        return new String(digest.digest(readBytes(file)));
    }

    private static final int BUFFER_SIZE = 4096;

    private static byte[] readBytes(File file) throws IOException {
        List<Byte> bytes = new LinkedList<>();
        try (FileInputStream input = new FileInputStream(file)) {
            byte[] buffer = new byte[BUFFER_SIZE];
            int read;
            while ((read = input.read(buffer)) != -1) {
                for (int i = 0; i < read; ++i) {
                    bytes.add(buffer[i]);
                }
            }
        }
        byte[] result = new byte[bytes.size()];
        for (int i = 0; i < result.length; ++i) {
            result[i] = bytes.get(i);
        }
        return result;
    }
}
