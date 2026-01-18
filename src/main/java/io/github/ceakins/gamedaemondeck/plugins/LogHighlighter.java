package io.github.ceakins.gamedaemondeck.plugins;

public class LogHighlighter {
    private String regex;
    private String color; // CSS color or class

    public LogHighlighter() {
    }

    public LogHighlighter(String regex, String color) {
        this.regex = regex;
        this.color = color;
    }

    public String getRegex() {
        return regex;
    }

    public void setRegex(String regex) {
        this.regex = regex;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
