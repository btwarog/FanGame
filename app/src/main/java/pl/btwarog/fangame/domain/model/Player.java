package pl.btwarog.fangame.domain.model;

import com.google.gson.annotations.SerializedName;

import java.math.BigDecimal;

/**
 * Created by bartlomiejtwarog on 09.05.2017.
 */

public class Player {

    String id;

    @SerializedName("first_name")
    String firstName;

    BigDecimal fppg;

    @SerializedName("last_name")
    String lastName;

    Image images;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public BigDecimal getFppg() {
        return fppg;
    }

    public void setFppg(BigDecimal fppg) {
        this.fppg = fppg;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Image getImages() {
        return images;
    }

    public void setImages(Image images) {
        this.images = images;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Player player = (Player) o;

        if (id != null ? !id.equals(player.id) : player.id != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
