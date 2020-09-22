package com.example.jasonexample;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import static android.content.Context.MODE_PRIVATE;

public class RecyclerAdapter2 extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int TYPE = 1;
    private final Context context;
    private final List<Object> listRecyclerItem;




    public RecyclerAdapter2(Context context, List<Object> listRecyclerItem) {
        this.context = context;
        this.listRecyclerItem = listRecyclerItem;

    }

    public class ItemViewHolder2 extends RecyclerView.ViewHolder {

        private TextView post, id, name, email, body;
        private Button comment, editComment;
        private final LinearLayout mLinearLayout;

        public ItemViewHolder2(@NonNull View itemView) {
            super(itemView);
            post = (TextView) itemView.findViewById(R.id.post);
            id = (TextView) itemView.findViewById(R.id.idd2);
            name = (TextView) itemView.findViewById(R.id.name);
            email = (TextView) itemView.findViewById(R.id.email);
            body = (TextView) itemView.findViewById(R.id.body2);
            comment = itemView.findViewById(R.id.button2);
            editComment = itemView.findViewById(R.id.editCommentButton);

            mLinearLayout = itemView.findViewById(R.id.layoutVer2);
        }
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        switch (i) {
            case TYPE:

            default:

                View layoutView = LayoutInflater.from(viewGroup.getContext()).inflate(
                        R.layout.list_comments, viewGroup, false);

                return new RecyclerAdapter2.ItemViewHolder2((layoutView));
        }

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder viewHolder, int i) {

        int viewType = getItemViewType(i);

        switch (viewType) {
            case TYPE:
            default:

                final RecyclerAdapter2.ItemViewHolder2 itemViewHolder = (RecyclerAdapter2.ItemViewHolder2) viewHolder;
                final Comments holidays = (Comments) listRecyclerItem.get(i);

                itemViewHolder.post.setText(holidays.getPost());
                itemViewHolder.id.setText(holidays.getId());
                itemViewHolder.name.setText(holidays.getName());
                itemViewHolder.email.setText(holidays.getEmail());
                itemViewHolder.body.setText(holidays.getBody());
                itemViewHolder.comment.setOnClickListener(new View.OnClickListener() {
                                                              // The code in this method will be executed when the family category is clicked on.
                                                              @Override
                                                              public void onClick(View view) {
                                                                  // Create a new intent to open the {@link FamilyActivity}
                                                                  Intent familyIntent = new Intent(context, MainActivity.class);

                                                                  // Start the new activity
                                                                  context.startActivity(familyIntent);
                                                              }
                                                          }
                );

                itemViewHolder.editComment.setOnClickListener(new View.OnClickListener() {
                                                                  // The code in this method will be executed when the family category is clicked on.
                                                                  @Override
                                                                  public void onClick(View view) {
                                                                      final EditText lEditText = new EditText(context);
                                                                      final Button saveB = new Button(context);
                                                                      saveB.setText("Save");
                                                                      lEditText.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.FILL_PARENT,
                                                                              LinearLayout.LayoutParams.WRAP_CONTENT));
                                                                      lEditText.setText(holidays.getBody());
                                                                      lEditText.setHint("Comment");
                                                                      itemViewHolder.mLinearLayout.addView(lEditText);
                                                                      itemViewHolder.mLinearLayout.addView(saveB);
                                                                      saveB.setOnClickListener(new View.OnClickListener() {
                                                                                                   @Override
                                                                                                   public void onClick(View view) {
                                                                                                       String newPost = lEditText.getText().toString();
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
