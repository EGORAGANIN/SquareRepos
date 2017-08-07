package ru.egoraganin.squarerepos.Retrofit.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Contributor {

    @SerializedName("login")
    @Expose
    private String login;
    @SerializedName("avatar_url")
    @Expose
    private String avatarUrl;

    public String getLogin() {
        return login;
    }


    public String getAvatarUrl() {
        return avatarUrl.concat("&s=230");
    }


}