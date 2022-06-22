package com.example.madapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import com.example.madapplication.R;

import java.net.ContentHandler;
import java.util.ArrayList;

public class MyTimelineActivity extends AppCompatActivity {
    ListView listView;
    Button gotoPostButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_timeline);

        ArrayList<Post> allPosts = new ArrayList<Post>();

        PostsAdapter adapter = new PostsAdapter(this, allPosts);

        listView = (ListView) findViewById(R.id.timelinelv);
        listView.setAdapter(adapter);


        Post post1 = new Post("Bla bla", "balsdfjalsd;kffjaksd", "User1");
        Post post2 = new Post("Fancy title", "balsdfjalsd;kffjaksd", "User2");

        adapter.add(post1);
        adapter.add(post2);

        gotoPostButton = (Button) findViewById(R.id.gotopostbutton);

        gotoPostButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent= new Intent(getApplicationContext(), PostActivity.class);
                startActivity(intent);
            }
        });
    }

    public class PostsAdapter extends ArrayAdapter<Post> {
        public PostsAdapter(Context context, ArrayList<Post> posts) {
            super(context, 0, posts);
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            // get data item
            Post post = getItem(position);

            if (convertView == null) {
                convertView = LayoutInflater.from(getContext()).inflate(R.layout.post_list_view, parent, false);
            }
            // get textviews
            TextView postTitle = (TextView) convertView.findViewById(R.id.posttitle);
            TextView postUser = (TextView) convertView.findViewById(R.id.postuser);
            TextView postBody = (TextView) convertView.findViewById(R.id.postbody);

            postTitle.setText(post.title);
            postUser.setText(post.postedUserId);
            postBody.setText(post.body);

            return convertView;
        }
    }
}

