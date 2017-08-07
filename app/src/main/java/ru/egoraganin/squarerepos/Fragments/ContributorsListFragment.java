package ru.egoraganin.squarerepos.Fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
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
import ru.egoraganin.squarerepos.Adapters.CommitsAdapter;
import ru.egoraganin.squarerepos.Adapters.ContributorsAdapter;
import ru.egoraganin.squarerepos.R;
import ru.egoraganin.squarerepos.Retrofit.Response.Commit;
import ru.egoraganin.squarerepos.Retrofit.Response.Contributor;
import ru.egoraganin.squarerepos.Retrofit.RestApi;

public class ContributorsListFragment extends Fragment {

    private RecyclerView rvContributorList;

    public ContributorsListFragment() {
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_contributors_list, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        rvContributorList = (RecyclerView) view.findViewById(R.id.rv_contributors_list);
        rvContributorList.setLayoutManager(new LinearLayoutManager(getContext()));
        loadContributors();
    }

    private void loadContributors() {
        Callback<List<Contributor>> callback = new Callback<List<Contributor>>() {
            @Override
            public void onResponse(Call<List<Contributor>> call, Response<List<Contributor>> response) {
                if (response.isSuccessful()) {
                    rvContributorList.setAdapter(new ContributorsAdapter(response.body(), new ContributorsAdapter.OnContributorClickListener() {
                        @Override
                        public void OnClick(Contributor contributor) {
                            Toast.makeText(getContext(),contributor.getLogin(),Toast.LENGTH_LONG).show();
                        }
                    }));
                }
            }

            @Override
            public void onFailure(Call<List<Contributor>> call, Throwable t) {

            }
        };
        RestApi api = RestApi.getRestApi();
        api.getContributors(callback, getActivity().getIntent().getStringExtra(RepoInfoFragment.URL));
    }

}
