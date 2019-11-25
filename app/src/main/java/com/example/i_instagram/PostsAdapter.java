package com.example.i_instagram;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.parse.ParseFile;

import java.util.List;

public class PostsAdapter extends RecyclerView.Adapter<PostsAdapter.ViewHolder>{
    public static final String TAG = "Posts Adapter";
    private Context context;
    private List<Post> posts;

    public PostsAdapter(Context context, List<Post> posts) {
        Log.i(TAG, "Calling constructor");
        this.context = context;
        this.posts = posts;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        Log.i(TAG, "calling ViewHolder");
        View view = LayoutInflater.from(context).inflate(R.layout.item_post, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Log.i(TAG, "calling onBindViewHolder");
        Post post = posts.get(position);
        holder.bind(post);
    }

    @Override
    public int getItemCount() {
        Log.i(TAG, "posts size: " + posts.size());
        return posts.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {
        private TextView tvHandler;
        private ImageView ivImage;
        private TextView description;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            Log.i(TAG, "Calling ViewHolder");
            tvHandler = itemView.findViewById(R.id.tvHandler);
            ivImage = itemView.findViewById(R.id.ivImage);
            description = itemView.findViewById(R.id.tvDescription);
        }

        public void bind(Post post) {
            Log.i(TAG, "Binding");
            // to bind the view element of the post
            tvHandler.setText(post.getUser().getUsername());
            Log.i(TAG, "Setting username");
            ParseFile image = post.getImage();
            if (image != null) {
                Glide.with(context).load(image.getUrl()).into(ivImage);
                Log.i(TAG, "Setting image");
            }
            description.setText(post.getDescription());
            Log.i(TAG, "Setting description");
        }
    }
}