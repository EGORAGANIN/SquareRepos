package ru.egoraganin.squarerepos.Fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import ru.egoraganin.squarerepos.Activity.CommitsContributorsActivity;
import ru.egoraganin.squarerepos.R;
import ru.egoraganin.squarerepos.Retrofit.Response.Commit;
import ru.egoraganin.squarerepos.Retrofit.Response.Contributor;
import ru.egoraganin.squarerepos.Retrofit.RestApi;

public class RepoInfoFragment extends Fragment {

    private TextView tvRepoTitle, tvRepoDescription;
    private Button btnCommits, btnContributors;

    public static final String URL = "URL";
    public static final String BUTTON = "BUTTON";
    public static final String BUTTON_COMMITS = "BUTTON_COMMITS";
    public static final String BUTTON_CONTRIBUTORS = "BUTTON_CONTRIBUTORS";

    public RepoInfoFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_repo_info, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        final Bundle arguments = getArguments();
        findTextView(view);
        setText(arguments);
        findButton(view);
        setButtonListener(arguments);

    }

    private void findButton(View view) {
        btnCommits = (Button) view.findViewById(R.id.btn_commits);
        btnContributors = (Button) view.findViewById(R.id.btn_contributors);
    }

    private void setButtonListener(final Bundle arguments) {
        View.OnClickListener clickListener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                switch (view.getId()){
                    case R.id.btn_commits:
                        startCommitsContributorsActivity(BUTTON_COMMITS, arguments.getString(ReposListFragment.BUNDLE_COMMITS));
                        break;
                    case R.id.btn_contributors:
                        startCommitsContributorsActivity(BUTTON_CONTRIBUTORS, arguments.getString(ReposListFragment.BUNDLE_CONTRIBUTORS));
                        break;
                }

            }
        };
        btnCommits.setOnClickListener(clickListener);
        btnContributors.setOnClickListener(clickListener);
    }

    private void startCommitsContributorsActivity(String buttonName ,String buttonUrl) {
        Intent intent = new Intent(getContext(), CommitsContributorsActivity.class);
        intent.putExtra(BUTTON, buttonName);
        intent.putExtra(URL, buttonUrl);
        startActivity(intent);

    }

    private void findTextView(View view) {
        tvRepoTitle = (TextView) view.findViewById(R.id.tv_RepoTitle);
        tvRepoDescription = (TextView) view.findViewById(R.id.tv_RepoDescription);
    }

    private void setText(Bundle textBundle) {
        tvRepoTitle.setText(textBundle.getString(ReposListFragment.BUNDLE_REPO_TITLE));
        tvRepoDescription.setText(textBundle.getString(ReposListFragment.BUNDLE_REPO_DESCRIPTION));
    }
}
