package com.example.i_instagram.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.i_instagram.Post;
import com.example.i_instagram.R;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import java.util.List;

public class ProfileFragment extends PostsFragment {
    public static final String TAG = "Profile Fragment";

    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        Log.i(TAG, "onCreateView");
        return inflater.inflate(R.layout.fragment_posts, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        Log.i(TAG, "onViewCreated");
        super.onViewCreated(view, savedInstanceState);
        queryPost();
        Log.i(TAG, "queryPost() called");
    }

    @Override
    protected void queryPost() {
        Log.i(TAG, "Entering query post");
        ParseQuery<Post> postQuery = new ParseQuery<Post>(Post.class);
        postQuery.include(Post.KEY_USER);
//        postQuery.setLimit(20);
//        postQuery.addDescendingOrder(Post.KEY_CREATED_AT);
        postQuery.whereEqualTo(Post.KEY_USER, ParseUser.getCurrentUser());

        postQuery.findInBackground(new FindCallback<Post>() {
            @Override
            public void done(List<Post> posts, ParseException e) {
                if ( e != null) {
                    Log.e(TAG, "Error with query");
                    e.printStackTrace();
                    return;
                }
                posts.addAll(posts);
                Log.i(TAG, "Adding all posts");
                adapter.notifyDataSetChanged();
                Log.i(TAG, "Notifying the adapter");

                for (int i = 0; i < posts.size(); i++) {
                    Log.i(TAG, "iterator: " + i);
                    Post post = posts.get(i);
                    Log.d(TAG, "Post: " + post.getDescription() +
                            ", username: " + post.getUser().getUsername());
                }
            }

        });
        Log.i(TAG, "findInBackground: done");
    }
}
