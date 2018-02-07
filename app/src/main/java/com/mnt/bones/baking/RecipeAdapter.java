package com.mnt.bones.baking;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by fabio.a on 07/02/18.
 */

public class RecipeAdapter extends RecyclerView.Adapter<RecipeAdapter.ViewHolder> {

    private static final String TAG = RecipeAdapter.class.getSimpleName();
    private Context mContext;
    private List<Recipe> mRecipeList;

    public static class ViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.tv_recipe_name)
        TextView recipeName;

        @BindView(R.id.tv_recipe_servings) TextView recipeServing;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }

    public RecipeAdapter(Context mContext, List<Recipe> mRecipeList) {
        this.mContext = mContext;
        this.mRecipeList = mRecipeList;
    }

    @Override
    public RecipeAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recipe_list_item, parent, false);
        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(RecipeAdapter.ViewHolder holder, int position) {

        final Recipe recipe = mRecipeList.get(position);

        Log.v(TAG, recipe.toString());

        holder.recipeName.setText(recipe.getName());
        holder.recipeServing.setText(recipe.getServing());

    }

    @Override
    public int getItemCount() {
        return mRecipeList.size();
    }
}
