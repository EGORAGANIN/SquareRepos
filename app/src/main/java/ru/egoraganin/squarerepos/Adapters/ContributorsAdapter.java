package ru.egoraganin.squarerepos.Adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import ru.egoraganin.squarerepos.R;
import ru.egoraganin.squarerepos.Retrofit.Response.Contributor;

/**
 * Created by Егор on 06.08.2017.
 */

public class ContributorsAdapter extends RecyclerView.Adapter<ContributorsAdapter.ViewHolder> {
    private List<Contributor> contributorList;
    private ContributorsAdapter.OnContributorClickListener itemListener;

    public ContributorsAdapter(List<Contributor> contributorList, ContributorsAdapter.OnContributorClickListener itemListener) {
        this.contributorList = contributorList;
        this.itemListener = itemListener;
    }

    @Override
    public ContributorsAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.item_contributor, parent, false);
        return new ContributorsAdapter.ViewHolder(itemView, itemListener);
    }

    @Override
    public void onBindViewHolder(ContributorsAdapter.ViewHolder holder, int position) {
        holder.bindTo(contributorList.get(position));
    }

    @Override
    public int getItemCount() {
        return contributorList.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvContributor;
        ImageView ivContributor;

        Contributor contributor;

        public ViewHolder(View itemView, final ContributorsAdapter.OnContributorClickListener itemListner) {
            super(itemView);
            tvContributor = (TextView) itemView.findViewById(R.id.tv_contributor);
            ivContributor = (ImageView) itemView.findViewById(R.id.iv_contributor);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    itemListner.OnClick(contributor);
                }
            });
        }

        private void bindTo(Contributor contributor) {
            this.contributor = contributor;
            tvContributor.setText(contributor.getLogin());
            Glide
                    .with(itemView.getContext())
                    .load(contributor.getAvatarUrl())
                    .crossFade()
                    .fitCenter()
                    .into(ivContributor);
        }
    }

    public interface OnContributorClickListener {
        void OnClick(Contributor contributor);
    }
}
