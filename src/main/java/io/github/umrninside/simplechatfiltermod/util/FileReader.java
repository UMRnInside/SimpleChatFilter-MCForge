package io.github.umrninside.simplechatfiltermod.util;

import java.io.*;
import java.lang.reflect.Array;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class FileReader {
    public static ArrayList<String> readFile(String path) {
        ArrayList<String> result = new ArrayList<String>();
        File file = new File(path);
        try (BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8))) {
            while (br.ready()) {
                result.add(br.readLine());
            }
        } catch (IOException exception) {
            // Do nothing, fail silently
        }
        return result;
    }
}
