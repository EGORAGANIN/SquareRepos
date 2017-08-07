package ru.egoraganin.squarerepos.Retrofit.Response;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Repo {

    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("contributors_url")
    @Expose
    private String contributorsUrl;
    @SerializedName("commits_url")
    @Expose
    private String commitsUrl;
    @SerializedName("stargazers_count")
    @Expose
    private String stargazersCount;
    @SerializedName("forks_count")
    @Expose
    private String forksCount;

    public String getName() {
        return name;
    }


    public String getDescription() {
        return description;
    }


    public String getContributorsUrl() {
        return contributorsUrl;
    }


    public String getCommitsUrl() {
        return commitsUrl.substring(0, commitsUrl.length() - 6);
    }

    public String getStargazersCount() {
        return "stars: ".concat(stargazersCount);
    }

    public String getForksCount() {
        return "forks: ".concat(forksCount);
    }

}