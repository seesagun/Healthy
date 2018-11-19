package com.example.lab203_26.healthy.Comment;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.example.lab203_26.healthy.Post.Post;
import com.example.lab203_26.healthy.R;

import java.util.ArrayList;
import java.util.List;

public class CommentAdapter extends ArrayAdapter {
    List<Comment> comments = new ArrayList<Comment>();
    Context context;

    public CommentAdapter(@NonNull Context context, int resourse, @NonNull List<Comment> objects){
        super(context, resourse, objects);
        this.comments = objects;
        this.context = context;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent){
        View _weightItem = LayoutInflater.from(context).inflate(
                R.layout.fragment_comment_item,parent,false
        );
        TextView title = _weightItem.findViewById(R.id.comment_title);
        TextView _body = _weightItem.findViewById(R.id.comment_body);
        TextView _name = _weightItem.findViewById(R.id.comment_name);
        TextView _email = _weightItem.findViewById(R.id.comment_email);
        Comment _row = comments.get(position);
        title.setText(_row.getPostId()+" : "+_row.getId());
        _body.setText(_row.getBody());
        _name.setText(_row.getName());
        _email.setText("("+_row.getEmail()+")");
        return _weightItem;
    }
}
