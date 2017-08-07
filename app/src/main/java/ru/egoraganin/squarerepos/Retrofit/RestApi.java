package ru.egoraganin.squarerepos.Retrofit;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import ru.egoraganin.squarerepos.Retrofit.Response.Commit;
import ru.egoraganin.squarerepos.Retrofit.Response.Contributor;
import ru.egoraganin.squarerepos.Retrofit.Response.Repo;

public class RestApi {
    private static final String BASE_URL = "https://api.github.com/";
    private static RestApi restApi;
    private static GitHubApi gitHubApi;

    private static Retrofit createRetrofit() {
        return new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
    }

    private RestApi() {
    }

    public static RestApi getRestApi() {
        if (restApi == null) {
            restApi = new RestApi();
        }
        return restApi;
    }

    private static GitHubApi createGitHubApi() {
        if (gitHubApi == null) {
            gitHubApi = createRetrofit().create(GitHubApi.class);
        }
        return gitHubApi;
    }

    public void getRepos(Callback<List<Repo>> callback, String username) {
        Call<List<Repo>> listCall = createGitHubApi().getRepos(username);
        listCall.enqueue(callback);
    }

    public void getCommits(Callback<List<Commit>> callback, String commitUrl) {
        Call<List<Commit>> listCall = createGitHubApi().getCommits(commitUrl);
        listCall.enqueue(callback);
    }

    public void getContributors(Callback<List<Contributor>> callback, String contributorUrl) {
        Call<List<Contributor>> listCall = createGitHubApi().getContributors(contributorUrl);
        listCall.enqueue(callback);
    }
}
