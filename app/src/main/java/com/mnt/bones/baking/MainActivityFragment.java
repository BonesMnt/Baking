package com.mnt.bones.baking;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment implements AsyncTaskDelegate{

    //@BindView(R.id.tv_hello_world) TextView helloWorldTextview;
    @BindView(R.id.rv_recipes) RecyclerView mRecipesRecycler;

    private RecipeAdapter mRecipeAdapter;
    private List<Recipe> mRecipeList;

    public MainActivityFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_main, container, false);

        ButterKnife.bind(this, rootView);

        mRecipeList = new ArrayList<>();

        RecyclerView.LayoutManager layoutManagerReview = new LinearLayoutManager(getContext());
        mRecipesRecycler.setLayoutManager(layoutManagerReview);

        mRecipeAdapter = new RecipeAdapter(getActivity(), mRecipeList);
        mRecipesRecycler.setAdapter(mRecipeAdapter);

        return rootView;
    }

    private void updateList(){
        RecipeService recipeService = new RecipeService(getContext(), this);
        recipeService.execute();
    }

    @Override
    public void onStart() {
        super.onStart();
        updateList();
    }

    @Override
    public void onPreStart() {

    }

    @Override
    public void onFinish(Object output) {
        if (output != null){
            ArrayList<Recipe> downloadedList = (ArrayList) output;

            mRecipeList.clear();
            mRecipeList.addAll(downloadedList);
            mRecipeAdapter.notifyDataSetChanged();
        }
    }
}
