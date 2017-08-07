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
import ru.egoraganin.squarerepos.Activity.MainActivity;
import ru.egoraganin.squarerepos.Adapters.CommitsAdapter;
import ru.egoraganin.squarerepos.Adapters.ReposAdapter;
import ru.egoraganin.squarerepos.R;
import ru.egoraganin.squarerepos.Retrofit.Response.AuthorAvatarUrl;
import ru.egoraganin.squarerepos.Retrofit.Response.Commit;
import ru.egoraganin.squarerepos.Retrofit.Response.Repo;
import ru.egoraganin.squarerepos.Retrofit.RestApi;

/**
 * Created by Егор on 06.08.2017.
 */

public class CommitsListFragment extends Fragment {

    private RecyclerView rvCommitsList;

    static final String URL = "URL";
    static final String NAME = "NAME";
    static final String TEXT = "TEXT";
    static final String DATE = "DATE";

    public CommitsListFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_commits_list, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        rvCommitsList = (RecyclerView) view.findViewById(R.id.rv_commits_list);
        rvCommitsList.setLayoutManager(new LinearLayoutManager(getContext()));
        loadCommits();
    }

    private void loadCommits() {
        Callback<List<Commit>> callback = new Callback<List<Commit>>() {
            @Override
            public void onResponse(Call<List<Commit>> call, Response<List<Commit>> response) {
                if (response.isSuccessful()) {
                    rvCommitsList.setAdapter(new CommitsAdapter(response.body(), new CommitsAdapter.OnCommitClickListener() {
                        @Override
                        public void OnClick(Commit commit) {
                            replaceCommitInfoFragment(commit);
                        }
                    }));
                }
            }

            @Override
            public void onFailure(Call<List<Commit>> call, Throwable t) {

            }
        };
        RestApi api = RestApi.getRestApi();
        api.getCommits(callback, getActivity().getIntent().getStringExtra(RepoInfoFragment.URL));
    }


    private CommitInfoFragment newInstance(String avatarUrl, String name, String text, String date) {
        CommitInfoFragment fragment = new CommitInfoFragment();
        Bundle arguments = new Bundle();
        arguments.putString(URL, avatarUrl);
        arguments.putString(NAME, name);
        arguments.putString(TEXT, text);
        arguments.putString(DATE, date);
        fragment.setArguments(arguments);
        return fragment;
    }

    private void replaceCommitInfoFragment(Commit commit) {
        if (commit.getAuthor()==null){
            commit.setAuthor(new AuthorAvatarUrl());
        }
        CommitInfoFragment repoInfoFragment = newInstance
                (commit.getAuthor().getAvatarUrl(), commit.getCommit().getAuthor().getName(), commit.getCommit().getMessage(), commit.getCommit().getAuthor().getDate());
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.container_commits_contributors, repoInfoFragment).addToBackStack("commit_info");
        transaction.commit();
    }

}
