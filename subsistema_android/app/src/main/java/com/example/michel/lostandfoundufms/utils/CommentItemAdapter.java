package com.example.michel.lostandfoundufms.utils;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.michel.lostandfoundufms.R;
import com.example.michel.lostandfoundufms.model.CommentItem;
import com.example.michel.lostandfoundufms.presenter.ObjectCommentsPresenter;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class CommentItemAdapter extends RecyclerView.Adapter<CommentItemAdapter.ViewHolder>{

    private List<CommentItem> commentItemList;
    private UserSession userSession;
    private Context context;
    ObjectCommentsPresenter presenter;

    public CommentItemAdapter(List<CommentItem> commentItemList, Context context) {
        this.context = context;
        this.commentItemList = commentItemList;
        userSession = new UserSession(context);
        presenter = new ObjectCommentsPresenter();
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_comment, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final CommentItem commentItem = commentItemList.get(position);

        holder.objectCommentsTitle.setText(commentItem.getObjectItem().getTitle());
        holder.objectCommentsText.setText(commentItem.getComment());
        if(commentItem.getUserItem().getUsername().equals(userSession.getUserSessionName())){
            holder.objectCommentsAuthor.setText("Você");
        }else{
            holder.objectCommentsAuthor.setText(commentItem.getUserItem().getUsername());
        }

        DateFormat format = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.ENGLISH);
        try {

            Date date = format.parse(commentItem.getCreated());
            DateFormat df = new SimpleDateFormat("dd/MM/yyyy HH:mm");
            String newDateCreated = df.format(date);

            holder.objectCommentsDate.setText(newDateCreated);

        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Override
    public int getItemCount() {
        return commentItemList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView objectCommentsTitle;
        private TextView objectCommentsText;
        private TextView objectCommentsDate;
        private TextView objectCommentsAuthor;

        public ViewHolder(View itemView) {
            super(itemView);
            objectCommentsTitle = itemView.findViewById(R.id.objectCommentsTitle);
            objectCommentsText = itemView.findViewById(R.id.objectCommentsText);
            objectCommentsDate = itemView.findViewById(R.id.objectCommentsDate);
            objectCommentsAuthor = itemView.findViewById(R.id.objectCommentsAuthor);
        }
    }
}
