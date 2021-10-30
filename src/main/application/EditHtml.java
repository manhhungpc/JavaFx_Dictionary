package main.application;

import org.w3c.dom.Document;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

public class EditHtml {
    public static void main(String[] args) {

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

    public static class Part {
        String part;
        List<smallMeaning> smallMeanings = new ArrayList<>();

        public Part(String part, List<smallMeaning> meanings) {
            this.part = part;
            this.smallMeanings = meanings;
        }

        public Part(String unSplitPart) {
            String[] split = unSplitPart.split("-");
            for (int i = 0; i < split.length; i++) {
                if (i == 0) part = split[i];
                else smallMeanings.add(new smallMeaning(split[i]));
            }
        }
    }

    public static class smallMeaning {
        String meaning;
        List<String> examples = new ArrayList<>();

        public smallMeaning(String unSplitSmallMeaning) {
            unSplitSmallMeaning = unSplitSmallMeaning.substring(1);
            String[] split = unSplitSmallMeaning.split(Pattern.quote("="));
            for (int i = 0; i < split.length; i++) {
                if (i == 0) meaning = split[i];
                else examples.add(split[i]);
            }
        }

        public String htmlAddListExample() {
            StringBuilder sb = new StringBuilder();
            sb.append("<ul>");
            for (String i : examples) {
                sb.append("<li>").append(i).append("</li>");
            }
            sb.append("</ul>");
            return sb.toString();
        }
    }



    public static String getPartMeaning(List<smallMeaning> smallMeanings) {
        StringBuilder sb = new StringBuilder();
        sb.append("<ul>");
        for (smallMeaning sm : smallMeanings) {
            sb.append("<li style=\"list-style-type: circle; font-size: 16px; line-height: 150%;\">").append(sm.meaning).append("</li>");
            sb.append(sm.htmlAddListExample());
        }
        sb.append("</ul>");
        return sb.toString();
    }

    public static String getPartName(String part) {
        return "<li style=\"font-family: Cambria; color: black; font-size: 17px; line-height: 150%;\"><i>" + part + "</i></li>";
    }

    public static String getFinalMeaning(List<Part> parts) {
        StringBuilder sb = new StringBuilder();
        sb.append("<ul>");
        sb.append("<div class=\"b\">");
        for (Part partI : parts) {
            sb.append(getPartName(partI.part));
            sb.append(getPartMeaning(partI.smallMeanings));
        }
        sb.append("</ul>");
        sb.append("</div>");
        return sb.toString();
    }

    public static void splitMeaning(String unSplitMeaning, List<Part> parts) {
        unSplitMeaning = unSplitMeaning.substring(1);
        String[] split = unSplitMeaning.split(Pattern.quote("*"));
        for (String s : split) {
            parts.add(new Part(s));
        }
    }
}
