package ru.egoraganin.squarerepos.Activity;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import ru.egoraganin.squarerepos.Fragments.CommitsListFragment;
import ru.egoraganin.squarerepos.Fragments.ContributorsListFragment;
import ru.egoraganin.squarerepos.Fragments.RepoInfoFragment;
import ru.egoraganin.squarerepos.R;

public class CommitsContributorsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_commits_contributors);
        if (savedInstanceState == null) {
            addFragmentCommitsContributorsList();
        }
    }

    private void addFragmentCommitsContributorsList(){
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        switch (getIntent().getStringExtra(RepoInfoFragment.BUTTON)){
            case RepoInfoFragment.BUTTON_COMMITS:
                CommitsListFragment commitsListFragment = new CommitsListFragment();
                transaction.add(R.id.container_commits_contributors, commitsListFragment);
                transaction.commit();
                break;
            case RepoInfoFragment.BUTTON_CONTRIBUTORS:
                ContributorsListFragment contributorsListFragment = new ContributorsListFragment();
                transaction.add(R.id.container_commits_contributors, contributorsListFragment);
                transaction.commit();
                break;
        }
    }
}
