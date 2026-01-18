package io.github.ceakins.gamedaemondeck.plugins;

import java.util.List;

public class ServerConfigField {
    private String name;
    private String label;
    private String type; // text, number, boolean, select
    private String defaultValue;
    private String description;
    private List<String> options; // For select type

    public ServerConfigField() {
    }

    public ServerConfigField(String name, String label, String type, String defaultValue, String description) {
        this.name = name;
        this.label = label;
        this.type = type;
        this.defaultValue = defaultValue;
        this.description = description;
    }

    public ServerConfigField(String name, String label, String type, String defaultValue, String description, List<String> options) {
        this(name, label, type, defaultValue, description);
        this.options = options;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDefaultValue() {
        return defaultValue;
    }

    public void setDefaultValue(String defaultValue) {
        this.defaultValue = defaultValue;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getOptions() {
        return options;
    }

    public void setOptions(List<String> options) {
        this.options = options;
    }
}
