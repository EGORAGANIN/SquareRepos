package ru.egoraganin.squarerepos.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ru.egoraganin.squarerepos.Adapters.ReposAdapter;
import ru.egoraganin.squarerepos.Activity.MainActivity;
import ru.egoraganin.squarerepos.R;
import ru.egoraganin.squarerepos.Retrofit.Response.Repo;
import ru.egoraganin.squarerepos.Retrofit.RestApi;

public class ReposListFragment extends Fragment {

    private RecyclerView rvReposList;
    private String username = "square";

    static final String BUNDLE_REPO_TITLE = "bundle_repo_title";
    static final String BUNDLE_REPO_DESCRIPTION = "bundle_repo_description";
    static final String BUNDLE_COMMITS = "bundle_commits";
    static final String BUNDLE_CONTRIBUTORS = "bundle_contributors";

    public ReposListFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_repos_list, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        rvReposList = (RecyclerView) view.findViewById(R.id.rv_repos_list);
        rvReposList.setLayoutManager(new LinearLayoutManager(getContext()));
        loadRepos();
    }


    private void loadRepos() {
        Callback<List<Repo>> callback = new Callback<List<Repo>>() {
            @Override
            public void onResponse(Call<List<Repo>> call, Response<List<Repo>> response) {
                if (response.isSuccessful()) {
                    rvReposList.setAdapter(new ReposAdapter(response.body(), new ReposAdapter.OnRepoClickListener() {
                        @Override
                        public void OnClick(Repo repo) {
                            replaceRepoInfoFragment(repo);
                        }
                    }));
                }
            }

            @Override
            public void onFailure(Call<List<Repo>> call, Throwable t) {
                Toast.makeText(getContext(), "Ошибка загрузки данных ", Toast.LENGTH_LONG).show();
            }
        };
        RestApi api = RestApi.getRestApi();
        api.getRepos(callback,username);
    }



    private RepoInfoFragment newInstance(String title, String description, String commits, String contributors) {
        RepoInfoFragment fragment = new RepoInfoFragment();
        Bundle arguments = new Bundle();
        arguments.putString(BUNDLE_REPO_TITLE, title);
        arguments.putString(BUNDLE_REPO_DESCRIPTION, description);
        arguments.putString(BUNDLE_COMMITS, commits);
        arguments.putString(BUNDLE_CONTRIBUTORS, contributors);
        fragment.setArguments(arguments);
        return fragment;
    }

    private void replaceRepoInfoFragment(Repo repo) {
        Fragment repoInfoFragment = newInstance(repo.getName(),repo.getDescription(),repo.getCommitsUrl(),repo.getContributorsUrl());
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.main_activity_container, repoInfoFragment).addToBackStack("repos_info");
        transaction.commit();
    }


}
