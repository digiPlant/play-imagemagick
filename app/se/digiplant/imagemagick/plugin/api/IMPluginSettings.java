package se.digiplant.imagemagick.plugin.api;

import org.im4java.core.IMOperation;

import java.util.HashMap;

public class IMPluginSettings {

    public static HashMap<String, IMOperation> settings = new HashMap<>();

    public static void add(String key, IMOperation op) {
        settings.put(key, op);
    }

    public static IMOperation get(String setting) {
        if(settings.containsKey(setting)) {
            return settings.get(setting);
        }

        return getDefault(setting);
    }

    public static IMOperation getDefault(String key) {
        switch (key) {
            case "small": return resize(600, 400, null, 100.0);
            case "medium": return resize(1280, 800, null, 100.0);
            case "large": return resize(2560, 1600, null, 100.0);

            case "small_optimized": return resize(640, 480, null, 80.0);
            case "medium_optimized": return resize(1280, 800, null, 80.0);
            case "large_optimized": return resize(2560, 1600, null, 80.0);

            case "small_fit_to_width": return resize(640, null, null, 100.0);
            case "medium_fit_to_width": return resize(1280, null, null, 100.0);
            case "large_fit_to_width": return resize(2560, null, null, 100.0);

            case "small_fit_to_height": return resize(null, 480, null, 100.0);
            case "medium_fit_to_height": return resize(null, 800, null, 100.0);
            case "large_fit_to_height": return resize(null, 1600, null, 100.0);

            case "small_fit_exact": return resize(640, 400, '!', 100.0);
            case "medium_fit_to_exact": return resize(1280, 800, '!', 100.0);
            case "large_fit_to_exact": return resize(2560, 1600, '!', 100.0);

            case "small_cropped": return crop(640, 400, 0, 0, 100.0);
            case "medium_cropped": return crop(1280, 800, 0, 0, 100.0);
            case "large_cropped": return crop(2560, 1600, 0, 0, 100.0);

            case "small_cropped_optimized": return crop(640, 400, 0, 0, 80.0);
            case "medium_cropped_optimized": return crop(1280, 800, 0, 0, 80.0);
            case "large_cropped_optimized": return crop(2560, 1600, 0, 0, 80.0);

            case "small_thumbnail": return thumbnail(50, 50, 100.0);
            case "medium_thumbnail": return thumbnail(100, 100, 100.0);
            case "large_thumbnail": return thumbnail(150, 150, 100.0);

            case "small_thumbnail_optimized": return thumbnail(50, 50, 80.0);
            case "medium_thumbnail_optimized": return thumbnail(100, 100, 80.0);
            case "large_thumbnail_optimized": return thumbnail(150, 150, 80.0);

            default: return resize(800, 600, null, 100.0);
        }
    }

    public static IMOperation resize(Integer width, Integer height, Character flag, double quality) {
        IMOperation op = new IMOperation();

        op.addImage();
        op.resize(width, height, flag);
        op.quality(quality);
        op.addImage();

        return op;
    }

    public static IMOperation crop(Integer width, Integer height, Integer offsetX, Integer offsetY, double quality) {
        IMOperation op = new IMOperation();

        op.addImage();
        op.crop(width, height, offsetX, offsetY);
        op.quality(quality);
        op.addImage();

        return op;
    }

    public static IMOperation thumbnail(Integer width, Integer height, double quality) {
        IMOperation op = new IMOperation();

        op.addImage();
        op.thumbnail(width, height);
        op.quality(quality);
        op.addImage();

        return op;
    }


}
