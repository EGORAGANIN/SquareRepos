package ru.egoraganin.squarerepos.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import ru.egoraganin.squarerepos.R;

public class CommitInfoFragment extends Fragment {

    private TextView tvNameCommitter, tvTextCommit, tvDateCommit;
    private ImageView ivAvatarCommitter;

    public CommitInfoFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_commit_info, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        findView(view);
        Bundle arguments = getArguments();
        setImage(arguments);
        setText(arguments);

    }

    private void findView(View view) {
        tvNameCommitter = (TextView) view.findViewById(R.id.tv_name_committer);
        tvTextCommit = (TextView) view.findViewById(R.id.tv_text_commit);
        tvDateCommit = (TextView) view.findViewById(R.id.tv_date_commit);
        ivAvatarCommitter = (ImageView) view.findViewById(R.id.iv_avatar_committer);
    }

    private void setText(Bundle arguments) {
        tvNameCommitter.setText(arguments.getString(CommitsListFragment.NAME));
        tvTextCommit.setText(arguments.getString(CommitsListFragment.TEXT));
        tvDateCommit.setText(arguments.getString(CommitsListFragment.DATE));
    }

    private void setImage(Bundle arguments) {
        Glide
                .with(getContext())
                .load(arguments.getString(CommitsListFragment.URL))
                .crossFade()
                .fitCenter()
                .into(ivAvatarCommitter);
    }

}
