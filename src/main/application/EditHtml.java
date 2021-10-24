package main.application;

import org.w3c.dom.Document;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class EditHtml {
    public static void main(String[] args) {
        System.out.println(htmlToString("src\\main\\application\\resource\\WordView.html"));
    }

    public static String htmlToString(String filePath) {
        StringBuilder contentBuilder = new StringBuilder();
        try {
            BufferedReader in = new BufferedReader(new FileReader(filePath));
            String str;
            while ((str = in.readLine()) != null) {
                contentBuilder.append(str);
            }
            in.close();
        } catch (IOException ignored) {
        }
        return contentBuilder.toString();
    }
}
