package com.github.michalsenkyr.courses.scala.duplicatefinder.java;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import java.util.List;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * Duplicate file finder in Java 8
 */
public class DuplicateFinderJava8 {
    private static MessageDigest digest;

    public static void main(String[] args) {
        System.out.println(
                filterDuplicates(
                        Arrays.stream(args)
                                .map(Paths::get)
                                .flatMap(p -> {
                                    try {
                                        return Files.walk(p);
                                    } catch (IOException e) {
                                        throw new RuntimeException(e);
                                    }
                                })
                                .filter(Files::isRegularFile),
                        path -> {
                            try {
                                return Files.size(path);
                            } catch (IOException e) {
                                throw new RuntimeException(e);
                            }
                        })
                        .flatMap(list ->
                                filterDuplicates(list.stream(), path -> {
                                    try {
                                        return hash(path);
                                    } catch (NoSuchAlgorithmException | IOException e) {
                                        throw new RuntimeException(e);
                                    }
                                }))
                        .map(list -> list.stream().map(Path::toString).collect(Collectors.joining(", ")))
                        .collect(Collectors.joining("\n", "Duplicate files:\n", "")));
    }

    private static String hash(Path path) throws NoSuchAlgorithmException, IOException {
        if (digest == null) digest = MessageDigest.getInstance("SHA-1");
        return new String(digest.digest(Files.readAllBytes(path)));
    }

    private static <K, T> Stream<List<T>> filterDuplicates(Stream<T> stream, Function<T, K> keyFunction) {
        return stream
                .collect(Collectors.groupingBy(keyFunction, Collectors.toList()))
                .values().stream()
                .filter(list -> list.size() > 1);
    }
}
