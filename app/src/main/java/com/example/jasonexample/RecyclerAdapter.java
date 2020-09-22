package com.example.jasonexample;

import android.content.Context;
import android.content.Intent;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import android.widget.LinearLayout.LayoutParams;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;


import java.util.List;



public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int TYPE = 1;
    private final Context context;
    private final List<Object> listRecyclerItem;
    private static final String TAG = "RecyclerAdapter";
    public RecyclerAdapter(Context context, List<Object> listRecyclerItem) {
        this.context = context;
        this.listRecyclerItem = listRecyclerItem;
    }

    public class ItemViewHolder extends RecyclerView.ViewHolder {

        private TextView user, id, title, body;
        private Button comment,editPost,newPost;
        private final LinearLayout mLinearLayout;


        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);
            user = (TextView) itemView.findViewById(R.id.user);
            id = (TextView) itemView.findViewById(R.id.idd);
            title = (TextView) itemView.findViewById(R.id.title);
            body = (TextView) itemView.findViewById(R.id.body);
            comment = itemView.findViewById(R.id.button1);
            editPost=itemView.findViewById(R.id.editPostButton);

            mLinearLayout=itemView.findViewById(R.id.layoutVer);
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        switch (i) {
            case TYPE:

            default:

                View layoutView = LayoutInflater.from(viewGroup.getContext()).inflate(
                        R.layout.list_item, viewGroup, false);

                return new ItemViewHolder((layoutView));
        }

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {

        int viewType = getItemViewType(i);

        switch (viewType) {
            case TYPE:
            default:

                final ItemViewHolder itemViewHolder = (ItemViewHolder) viewHolder;
                final Holidays holidays = (Holidays) listRecyclerItem.get(i);

                itemViewHolder.user.setText(holidays.getUser());
                itemViewHolder.id.setText(holidays.getId());
                itemViewHolder.title.setText(holidays.getTitle());
                itemViewHolder.body.setText(holidays.getBody());
                itemViewHolder.comment.setOnClickListener(new View.OnClickListener() {
                    // The code in this method will be executed when the family category is clicked on.
                    @Override
                    public void onClick(View view) {
                        // Create a new intent to open the {@link FamilyActivity}
                        Intent familyIntent = new Intent(context, DetailsActivity.class);
                        familyIntent.putExtra("key",holidays.getId());
                        // Start the new activity
                        context.startActivity(familyIntent);
                    }
                }
                );

                itemViewHolder.editPost.setOnClickListener(new View.OnClickListener() {
                            // The code in this method will be executed when the family category is clicked on.
                            @Override
                            public void onClick(View view) {
                                final EditText lEditText = new EditText(context);
                                final Button saveB = new Button(context);
                                saveB.setText("Save");
                                lEditText.setLayoutParams(new LinearLayout.LayoutParams(LayoutParams.FILL_PARENT,
                                        LayoutParams.WRAP_CONTENT));

                                lEditText.setText (holidays.getBody());
                                lEditText.setHint("Post");
                                itemViewHolder.mLinearLayout.addView(lEditText);
                                itemViewHolder.mLinearLayout.addView(saveB);
                                saveB.setOnClickListener(new View.OnClickListener() {
                                                             @Override
                                                             public void onClick(View view) {
                                                                 String newPost=  lEditText.getText().toString();
                                                                 holidays.setBody(newPost);
                                                                 itemViewHolder.body.setText(holidays.getBody());
                                                                 // ChangeJSON(newPost,holidays.getId());
                                                                 itemViewHolder.mLinearLayout.removeView(lEditText);
                                                                 itemViewHolder.mLinearLayout.removeView(saveB);
                                                             }
                                                         }

                                );

                            }
                        }
                        );

        }

    }

    @Override
    public int getItemCount() {
        return listRecyclerItem.size();
    }


}


