package br.com.soulit.starwars.bo.types;

public enum SceneSettingsSeparatorType {
    INT("INT."),
    EXT("EXT."),
    SLASH("/"),
    FULL(INT.getSeparator() + SLASH.getSeparator() + EXT.getSeparator()),
    DASH("-");
    
    private String separator;

    private SceneSettingsSeparatorType(String separator) {
        this.separator = separator;
    }

    public String getSeparator() {
        return separator;
    }
}
