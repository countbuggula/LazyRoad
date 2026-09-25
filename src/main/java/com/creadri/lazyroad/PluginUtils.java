package com.creadri.lazyroad;

import java.io.*;
import java.util.zip.*;
import org.bukkit.plugin.java.JavaPlugin;

public class PluginUtils {
    public static void extractResourceZip(JavaPlugin plugin, String resourceName, File destination) {
        InputStream is = plugin.getResource(resourceName);
        if (is == null) {
            plugin.getLogger().warning("Resource " + resourceName + " not found!");
            return;
        }
        try {
            ZipInputStream zis = new ZipInputStream(is);
            ZipEntry ze;
            byte[] buffer = new byte[1024];
            while ((ze = zis.getNextEntry()) != null) {
                File f = new File(destination, ze.getName());
                if (ze.isDirectory()) {
                    f.mkdirs();
                } else {
                    File parent = f.getParentFile();
                    if (parent != null && !parent.exists()) {
                        parent.mkdirs();
                    }
                    if (!f.exists()) {
                        f.createNewFile();
                    }
                    FileOutputStream fos = new FileOutputStream(f);
                    int read;
                    while ((read = zis.read(buffer, 0, buffer.length)) > 0) {
                        fos.write(buffer, 0, read);
                    }
                    fos.close();
                }
            }
            zis.close();
        } catch (Exception ex) {
            plugin.getLogger().warning("Error extracting " + resourceName + ": " + ex.getMessage());
        }
    }
}