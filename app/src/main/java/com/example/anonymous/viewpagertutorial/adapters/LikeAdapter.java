package com.example.anonymous.viewpagertutorial.adapters;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.anonymous.viewpagertutorial.R;
import com.example.anonymous.viewpagertutorial.model.Follower_Item;

import java.util.List;

/**
 * Created by Anonymous on 12/4/2017.
 */

public class LikeAdapter extends RecyclerView.Adapter<LikeAdapter.LikeViewHolder> {
    private List<Follower_Item> listFlower;
    private Context context;
    private OnListDeleteItem onListDeleteItem;

    public void setOnListDeleteItem(OnListDeleteItem onListDeleteItem) {
        this.onListDeleteItem = onListDeleteItem;
    }

    public interface OnListDeleteItem{
        void sendItem(Follower_Item itemDeleted, int position);
    }


    public LikeAdapter(Context context, List<Follower_Item> listFlower) {
        this.context = context;
        this.listFlower = listFlower;
    }

    @Override
    public LikeAdapter.LikeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.item_home,parent,false);
        return new LikeAdapter.LikeViewHolder(view);
    }


    @Override
    public void onBindViewHolder(final LikeAdapter.LikeViewHolder holder, final int position) {
        holder.tvNameProduct.setText(listFlower.get(position).getNameItem());
        holder.tvOriginalPrice.setText(listFlower.get(position).getOriginalPrice());
        holder.tvNewPrice.setText(listFlower.get(position).getSalePrice());
        holder.imPictureItem.setImageResource(listFlower.get(position).getPictureItem());
        holder.imCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onListDeleteItem.sendItem(listFlower.get(position),position);
                Toast.makeText(context,holder.tvNameProduct.getText().toString()
                        ,Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return this.listFlower.size();
    }

    public class LikeViewHolder extends RecyclerView.ViewHolder{
        private TextView tvOriginalPrice;
        private TextView tvNewPrice;
        private ImageView imPictureItem;
        private TextView tvNameProduct;
        private ImageView imCart;


        public LikeViewHolder(View itemView) {
            super(itemView);
            tvOriginalPrice = itemView.findViewById(R.id.oldMoney);
            tvNewPrice = itemView.findViewById(R.id.newMoney);
            imPictureItem = itemView.findViewById(R.id.imProduct);
            tvNameProduct = itemView.findViewById(R.id.nameProduct);
            imCart = itemView.findViewById(R.id.cart);
        }
    }
    public void updateList(List<Follower_Item> listFlower){
        this.listFlower = listFlower;
        this.notifyDataSetChanged();
    }

}
