package kemar.port.dtms;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.card.MaterialCardView;

import java.util.List;

public class MainActivityAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private Context mContext;
    private List<MainActivityOptionsModel> activityOptionsModelList;

    public MainActivityAdapter(Context mContext, List<MainActivityOptionsModel> activityOptionsModelList) {
        this.mContext = mContext;
        this.activityOptionsModelList = activityOptionsModelList;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder;
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        viewHolder = getViewHolder(parent, inflater);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {

        ItemViewHolder viewHolder = (ItemViewHolder) holder;
        viewHolder.mImage.setImageResource(activityOptionsModelList.get(position).getImage());
        viewHolder.mTitle.setText(activityOptionsModelList.get(position).getName());
        viewHolder.mTitle.setTag(activityOptionsModelList.get(position).getName());

        viewHolder.cardview.setOnClickListener(view -> {
            String tag = viewHolder.mTitle.getTag().toString();
            if (tag.equals("Commercial")) {
                mContext.startActivity(new Intent(mContext, CommercialActivity.class));
            } else if (tag.equals("Train")) {
                mContext.startActivity(new Intent(mContext, TrainActivity.class));
            } else if (tag.equals("Inventory")) {
                mContext.startActivity(new Intent(mContext, InventoryActivity.class));
            }
        });

    }

    private RecyclerView.ViewHolder getViewHolder(ViewGroup parent, LayoutInflater inflater) {
        RecyclerView.ViewHolder viewHolder;
        View v1 = inflater.inflate(R.layout.item_main_menu_new, parent, false);
        viewHolder = new ItemViewHolder(v1);
        return viewHolder;
    }

    @Override
    public int getItemCount() {
        return activityOptionsModelList.size();
    }


    class ItemViewHolder extends RecyclerView.ViewHolder {

        ImageView mImage;
        TextView mTitle;
        MaterialCardView cardview;

        ItemViewHolder(View itemView) {
            super(itemView);

            mImage = itemView.findViewById(R.id.ivImage);
            mTitle = itemView.findViewById(R.id.tvTitle);
            cardview = itemView.findViewById(R.id.cardview);
        }
    }


}
