package net.devwool.cyanwool.api.lang;

public interface LanguageManager {

    public String getLocale(String code, String unlocalizeName);

    public void setLocale(String code, String unlocalizeName);

    public boolean hasLocale(String code, String unlocalizeName);
}