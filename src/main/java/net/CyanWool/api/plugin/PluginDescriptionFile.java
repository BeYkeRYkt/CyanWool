package net.CyanWool.api.plugin;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLClassLoader;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

import net.CyanWool.api.configuration.file.YamlConfiguration;

public class PluginDescriptionFile {

    private File file;
    private URL url;
    private YamlConfiguration yml;
    private URLClassLoader loader;

    // plugin.yml
    private String name = "UNKNOWN-PLUGIN";
    private String description = "";
    private String author = "";
    private String version = "";
    private String mainClass = "";

    public PluginDescriptionFile(File file) {
        try {
            this.file = file;
            this.url = file.toURI().toURL(); // new URL("file://" +
                                             // file.getAbsolutePath());
            this.loader = new URLClassLoader(new URL[] { getURL() }, PluginManager.class.getClassLoader());

            ZipFile zipFile = new ZipFile(file.getAbsolutePath());
            ZipEntry entry = zipFile.getEntry("plugin.yml");
            InputStream inputStream = zipFile.getInputStream(entry);
            this.yml = YamlConfiguration.loadConfiguration(inputStream);
            inputStream.close();
            zipFile.close();

            name = this.yml.getString("name");
            description = this.yml.getString("description");
            author = this.yml.getString("author");
            version = this.yml.getString("version");
            mainClass = this.yml.getString("mainClass");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public URL getURL() {
        return this.url;
    }

    public URLClassLoader getClassLoader() {
        return this.loader;
    }

    public String getName() {
        return name;
    }

    public String getAuthor() {
        return author;
    }

    public String getDescription() {
        return description;
    }

    public String getVersion() {
        return version;
    }

    public String getMainClass() {
        return mainClass;
    }

}