package ru.egoraganin.squarerepos.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import ru.egoraganin.squarerepos.R;
import ru.egoraganin.squarerepos.Retrofit.Response.Commit;


public class CommitsAdapter extends RecyclerView.Adapter<CommitsAdapter.ViewHolder> {
    private List<Commit> commitList;
    private CommitsAdapter.OnCommitClickListener itemListener;

    public CommitsAdapter(List<Commit> commitList, CommitsAdapter.OnCommitClickListener itemListener) {
        this.commitList = commitList;
        this.itemListener = itemListener;
    }

    @Override
    public CommitsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.item_commit, parent, false);
        return new CommitsAdapter.ViewHolder(itemView, itemListener);
    }

    @Override
    public void onBindViewHolder(CommitsAdapter.ViewHolder holder, int position) {
        holder.bindTo(commitList.get(position));
    }

    @Override
    public int getItemCount() {
        return commitList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvCommit;

        Commit commit;

        public ViewHolder(View itemView, final CommitsAdapter.OnCommitClickListener itemListner) {
            super(itemView);
            tvCommit = (TextView) itemView.findViewById(R.id.tv_commit);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    itemListner.OnClick(commit);
                }
            });
        }

        private void bindTo(Commit commit) {
            this.commit = commit;
            tvCommit.setText(commit.getCommit().getMessage());
        }
    }

    public interface OnCommitClickListener {
        void OnClick(Commit commit);
    }
}
