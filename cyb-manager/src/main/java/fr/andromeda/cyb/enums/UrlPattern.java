package fr.andromeda.cyb.enums;

public enum UrlPattern {

    ID(Urls.PATH_ID);

    private final String path;

    UrlPattern(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }

}
