package se.digiplant.imagemagick.plugin;

import java.io.File;
import play.libs.*;

public class IMPlugin {

    /**
     * Creates and caches image in local cache directory
     * @param path The path to where the images are stored
     * @param file The filePath relative to the path variable (the same as the play Assets Controller)
     * @return a File if everything when well
     */
    public static File get(String path, String file, String setting) {
        return Scala.orNull(se.digiplant.imagemagick.plugin.api.IMPlugin.get(path, file, setting));
    }

    public static File get(String path, String file) {
        return get(path, file, "default");
    }

    /**
     *
     * @param fileuid The unique play-res file identifier
     * @param source The play-res source name
     * @return a File if everything when well
     */
    public static File getRes(String fileuid, String source, String setting) {
        return Scala.orNull(se.digiplant.imagemagick.plugin.api.IMPlugin.getRes(fileuid, source, setting));
    }

    public static File getRes(String fileuid, String source) {
        return getRes(fileuid, source, "default");
    }
}
