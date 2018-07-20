package com.example.michel.lostandfoundufms.utils;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.PopupMenu;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.michel.lostandfoundufms.R;
import com.example.michel.lostandfoundufms.model.ObjectItem;
import com.example.michel.lostandfoundufms.view.ObjectCommentsActivity;
import com.example.michel.lostandfoundufms.view.ObjectRegisterActivity;
import com.squareup.picasso.Picasso;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class ObjectItemAdapter extends RecyclerView.Adapter<ObjectItemAdapter.ViewHolder> {

    private List<ObjectItem> objectList;
    private Context context;
    private UserSession userSession;

    public ObjectItemAdapter(List<ObjectItem> objectList, Context context) {
        this.objectList = objectList;
        this.context = context;
        userSession = new UserSession(context);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_object, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final ObjectItem object = objectList.get(position);

        holder.id.setText(String.valueOf(object.getId()));
        holder.user_id.setText(String.valueOf(object.getUser_id()));
        holder.title.setText(object.getTitle());
        holder.solved.setText(object.getSolved());
        holder.type.setText(object.getType());

        DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
        try {

            Date date = format.parse(object.getDate());
            DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
            String result = df.format(date);
            holder.date.setText(result);

        } catch (ParseException e) {
            e.printStackTrace();
        }

        Picasso.get().load(object.getImage()).into(holder.image);
        holder.description.setText(object.getDescription());
        holder.created.setText(object.getCreated());
        holder.modified.setText(object.getModified());

        holder.homeObjectWrap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(context, ObjectCommentsActivity.class);
                Bundle bundleExtras = new Bundle();
                bundleExtras.putString("objectId", String.valueOf(object.getId()));
                bundleExtras.putString("objectUserId", String.valueOf(object.getUser_id()));
                intent.putExtras(bundleExtras);
                context.startActivity(intent);
            }
        });

        holder.objectContextMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popupMenu = new PopupMenu(context, holder.objectContextMenu);
                popupMenu.inflate(R.menu.objects_cardview_menu);
                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.objectsCardViewMenuComments:
                                Intent intent = new Intent(context, ObjectCommentsActivity.class);
                                intent.putExtra("objectId", String.valueOf(object.getId()));
                                context.startActivity(intent);
                                break;
                            case R.id.objectsCardViewMenuEdit:
                                Intent it = new Intent(context, ObjectRegisterActivity.class);

                                Bundle bundle = new Bundle();
                                bundle.putString("objectId", String.valueOf(object.getId()));
                                bundle.putString("objectTitle", object.getTitle());
                                DateFormat format = new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
                                try {

                                    Date date = format.parse(object.getDate());
                                    DateFormat df = new SimpleDateFormat("dd/MM/yyyy");
                                    String result = df.format(date);
                                    bundle.putString("objectDate", result);

                                } catch (ParseException e) {
                                    e.printStackTrace();
                                }
                                bundle.putString("objectSituation", object.getSolved());
                                bundle.putString("objectClassification", object.getType());
                                bundle.putString("objectImage", object.getImage());
                                bundle.putString("objectDescription", object.getDescription());
                                it.putExtras(bundle);

                                context.startActivity(it);
                                break;
                        }
                        return false;
                    }
                });
                popupMenu.show();
            }
        });

        if(object.getUser_id() != userSession.getUserSessionId()){
            holder.objectContextMenu.setVisibility(View.GONE);
        }

    }

    @Override
    public int getItemCount() {
        return objectList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        private TextView id;
        private TextView user_id;
        private TextView title;
        private TextView solved;
        private TextView type;
        private TextView date;
        private ImageView image;
        private TextView description;
        private TextView created;
        private TextView modified;
        private LinearLayout homeObjectWrap;
        private ImageView objectContextMenu;


        public ViewHolder(View itemView) {
            super(itemView);

            id = itemView.findViewById(R.id.homeObjectId);
            user_id = itemView.findViewById(R.id.homeObjectUserId);
            title = itemView.findViewById(R.id.homeObjectTitle);
            solved = itemView.findViewById(R.id.homeObjectSolved);
            type = itemView.findViewById(R.id.homeObjectType);
            date = itemView.findViewById(R.id.homeObjectDate);
            image = itemView.findViewById(R.id.homeObjectImage);
            description = itemView.findViewById(R.id.homeObjectDescription);
            created = itemView.findViewById(R.id.homeObjectCreated);
            modified = itemView.findViewById(R.id.homeObjectModified);
            homeObjectWrap = itemView.findViewById(R.id.homeObjectWrap);
            objectContextMenu = itemView.findViewById(R.id.objectContextMenu);
        }
    }
}
