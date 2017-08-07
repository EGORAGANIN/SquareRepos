package ru.egoraganin.squarerepos.Retrofit;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import retrofit2.http.Url;
import ru.egoraganin.squarerepos.Retrofit.Response.Commit;
import ru.egoraganin.squarerepos.Retrofit.Response.Contributor;
import ru.egoraganin.squarerepos.Retrofit.Response.Repo;

/**
 * Created by Егор on 29.07.2017.
 */

public interface GitHubApi {
    @GET("users/{username}/repos")
    Call<List<Repo>> getRepos(@Path("username") String username);

    @GET
    Call<List<Commit>> getCommits (@Url String commitUrl);

    @GET
    Call<List<Contributor>> getContributors (@Url String contributorUrl);
}
