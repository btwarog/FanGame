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
}
