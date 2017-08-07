package ru.egoraganin.squarerepos.Retrofit.Response;

import android.support.annotation.Nullable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * Created by Егор on 06.08.2017.
 */

public class AuthorAvatarUrl {
    @SerializedName("avatar_url")
    @Expose
    private String avatarUrl;

    public String getAvatarUrl() {
        if (avatarUrl == null) {
            avatarUrl = "https://assets1.bmstatic.com/assets/users-userpics-fingerprint/65/4c/d69f3f23ad4d8847494b59b2112b09e5-profile.jpg";
        }
        return avatarUrl;
    }


}
