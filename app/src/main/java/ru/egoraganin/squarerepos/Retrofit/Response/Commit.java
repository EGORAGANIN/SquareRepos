package ru.egoraganin.squarerepos.Retrofit.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Commit {

    @SerializedName("commit")
    @Expose
    private CommitInfo commit;

    @SerializedName("author")
    @Expose
    private AuthorAvatarUrl author;

    public CommitInfo getCommit() {
        return commit;
    }
    public AuthorAvatarUrl getAuthor() {
        return author;
    }

    public void setAuthor(AuthorAvatarUrl author) {
        this.author = author;
    }
}