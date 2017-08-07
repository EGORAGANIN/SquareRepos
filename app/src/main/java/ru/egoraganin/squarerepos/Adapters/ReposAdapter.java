package ru.egoraganin.squarerepos.Adapters;

import android.support.v7.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import ru.egoraganin.squarerepos.R;
import ru.egoraganin.squarerepos.Retrofit.Response.Repo;

public class ReposAdapter extends RecyclerView.Adapter<ReposAdapter.ViewHolder> {
    private List<Repo> repoList;
    private OnRepoClickListener itemListener;

    public ReposAdapter(List<Repo> repoList, OnRepoClickListener itemListener) {
        this.repoList = repoList;
        this.itemListener = itemListener;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.item_repo, parent, false);
        return new ViewHolder(itemView, itemListener);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.bindTo(repoList.get(position));
    }

    @Override
    public int getItemCount() {
        return repoList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvRepoName;
        TextView tvStarCount;
        TextView tvForkCount;

        Repo repo;

        public ViewHolder(View itemView, final OnRepoClickListener itemListner) {
            super(itemView);
            tvRepoName = (TextView) itemView.findViewById(R.id.tv_RepoName);
            tvStarCount = (TextView) itemView.findViewById(R.id.tv_StarCount);
            tvForkCount = (TextView) itemView.findViewById(R.id.tv_ForkCount);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    itemListner.OnClick(repo);
                }
            });
        }

        private void bindTo(Repo repo) {
            this.repo = repo;
            tvRepoName.setText(repo.getName());
            tvStarCount.setText(repo.getStargazersCount());
            tvForkCount.setText(repo.getForksCount());
        }
    }

   public interface OnRepoClickListener {
        void OnClick(Repo repo);
    }
}
