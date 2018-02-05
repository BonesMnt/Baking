package com.mnt.bones.baking;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment implements AsyncTaskDelegate{

    @BindView(R.id.tv_hello_world) TextView helloWorldTextview;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);

        ButterKnife.bind(this, rootView);

        RecipeService recipeService = new RecipeService(getContext(), this);
        recipeService.execute();

        helloWorldTextview.setText("Ola mundo estou vivo");

        return rootView;
    }

    @Override
    public void onPreStart() {

    }

    @Override
    public void onFinish(Object output) {

    }
}
