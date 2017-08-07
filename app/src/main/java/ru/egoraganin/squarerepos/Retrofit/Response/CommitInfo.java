package ru.egoraganin.squarerepos.Retrofit.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class CommitInfo {

    @SerializedName("author")
    @Expose
    private AuthorInfo author;
    @SerializedName("message")
    @Expose
    private String message;

    public AuthorInfo getAuthor() {
        return author;
    }

    public String getMessage() {
        return message;
    }
}