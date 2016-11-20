package cz.seznam.courses.scala.classes;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Example of usage where ClassTags would be required
 */
public class ClassTagsJavaExample {
    public static void main(String[] args) {
        printType(Arrays.asList("abc", "def"));
        printType(Arrays.asList(12, 34));
        printType(Arrays.asList(12, 34L));
        printType(Collections.<String>emptyList());
    }

    static <T> String printType(List<T> list) {
        // return "List of " + T.class;         // Does not compile
        throw new UnsupportedOperationException("Type parameter unknown");
    }
}
