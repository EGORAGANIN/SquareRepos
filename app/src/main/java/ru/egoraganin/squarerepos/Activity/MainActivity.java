package ru.egoraganin.squarerepos.Activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import ru.egoraganin.squarerepos.Fragments.ReposListFragment;
import ru.egoraganin.squarerepos.R;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if (savedInstanceState == null) {
            addFragmentReposList();
        }
    }

    public void addFragmentReposList() {
        Fragment reposListFragment = new ReposListFragment();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.main_activity_container, reposListFragment);
        transaction.commit();
    }
}
