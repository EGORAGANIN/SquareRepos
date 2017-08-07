package ru.egoraganin.squarerepos.Retrofit.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Егор on 06.08.2017.
 */

public class AuthorInfo {

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("date")
    @Expose
    private String date;

    public String getName() {
        return name;
    }

    public String getDate() {
        return date.substring(0,10);
    }

}
