package com.example.i_instagram.fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.example.i_instagram.Post;
import com.example.i_instagram.PostsAdapter;
import com.example.i_instagram.R;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseQuery;

import java.util.ArrayList;
import java.util.List;

public class PostsFragment extends Fragment {

    public static final String TAG = "Posts Fragment";
    private RecyclerView rvPosts;

    protected PostsAdapter adapter;
    protected List<Post> posts;

    SwipeRefreshLayout swipeContainer;
    //EndlessRecyclerViewScrollListener scrollListener;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_posts, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        rvPosts = view.findViewById(R.id.rvPosts);
        swipeContainer = view.findViewById(R.id.swipeContainer);
        // create the data source
        posts = new ArrayList<>();
        // create the adapter
        adapter = new PostsAdapter(getContext(), posts);
        // set the adapter in the recycler view
        rvPosts.setAdapter(adapter);
        // set the layout manager on the recycler view

        LinearLayoutManager layoutManager = new LinearLayoutManager(getContext());
        rvPosts.setLayoutManager(layoutManager);

        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                Log.i(TAG, "setOnRefreshListener(): Fetching new data");
                queryPost();
            }
        });

        // scrollListener = new EndlessRecyclerViewScrollListener(layoutManager) {
//            @Override
//            public void onLoadMore(int page, int totalItemsCount, RecyclerView view) {
//                Log.i(TAG, "onLoadMore: " + page);
//                loadMoreData();
//            }
//        };
//        queryPost();
//        Log.i(TAG, "queryPost() called");
    }

//    private void loadMoreData() {
//        return;
//    }


    protected void queryPost() {
        final ParseQuery<Post> postQuery = new ParseQuery<Post>(Post.class);
        postQuery.include(Post.KEY_USER);
        Log.i(TAG, "include: KEY USER");
        postQuery.setLimit(20);
        Log.i(TAG, "setLimit(20)");
        postQuery.addDescendingOrder(Post.KEY_CREATED_AT);
        Log.i(TAG, "addDescendingOrder");

        postQuery.findInBackground(new FindCallback<Post>() {
            @Override
            public void done(List<Post> newPosts, ParseException e) {
                Log.i(TAG, "done called");
                if ( e != null) {
                    Log.e(TAG, "Error with query");
                    e.printStackTrace();
                    return;
                }
                posts.addAll(newPosts);
                Log.i(TAG, "post.addAll(posts)");
                adapter.notifyDataSetChanged();
                swipeContainer.setRefreshing(false);

                Log.i(TAG, "notifyDataSetChanged()");

                for (int i = 0; i < posts.size(); i++) {
                    Log.i(TAG, "post size: " + posts.size());
                    Log.i(TAG, "iteration i = " + i);
                    Post post = posts.get(i);
                    Log.d(TAG, "Post: " + post.getDescription() +
                            ", username: " + post.getUser().getUsername());
                }
            }
        });
    }
}
