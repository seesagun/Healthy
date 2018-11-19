package com.example.lab203_26.healthy.Post;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;


import com.example.lab203_26.healthy.R;
import com.example.lab203_26.healthy.weight.Weight;

import java.util.ArrayList;
import java.util.List;

public class PostAdapter extends ArrayAdapter {
    List<Post> post = new ArrayList<Post>();
    Context context;

    public PostAdapter(@NonNull Context context, int resourse, @NonNull List<Post> objects){
        super(context, resourse, objects);
        this.post = objects;
        this.context = context;
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent){
        View _weightItem = LayoutInflater.from(context).inflate(
                R.layout.fragment_post_item,parent,false
        );
        TextView title = _weightItem.findViewById(R.id.post_title);
        TextView _body = _weightItem.findViewById(R.id.post_body);
        Post _row = post.get(position);
        title.setText(_row.getId()+" : "+_row.getTitle());
        _body.setText(_row.getBody());
        return _weightItem;
    }

}
