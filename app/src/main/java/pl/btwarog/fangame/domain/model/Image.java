package pl.btwarog.fangame.domain.model;

import com.google.gson.annotations.SerializedName;

/**
 * Created by bartlomiejtwarog on 09.05.2017.
 */

public class Image {

    @SerializedName("default")
    Media defaultImage;

    public Media getDefaultImage() {
        return defaultImage;
    }

    public void setDefaultImage(Media defaultImage) {
        this.defaultImage = defaultImage;
    }
}
