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
 * Created by Anonymous on 12/1/2017.
 */

public class HomeAdapter extends RecyclerView.Adapter<HomeAdapter.HomeViewHolder> {

    private List<Follower_Item> listFlower;
    private Context context;
    private OnSentToHomeFrg onSentToHomeFrg;

    public HomeAdapter(Context context, List<Follower_Item> listFlower) {
        this.context = context;
        this.listFlower = listFlower;
    }

    @Override
    public HomeAdapter.HomeViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        Context context = parent.getContext();
        View view = LayoutInflater.from(context).inflate(R.layout.item_home,parent,false);
        return new HomeAdapter.HomeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final HomeViewHolder holder, final int position) {
        holder.tvNameProduct.setText(listFlower.get(position).getNameItem());
        holder.tvOriginalPrice.setText(listFlower.get(position).getOriginalPrice());
        holder.tvNewPrice.setText(listFlower.get(position).getSalePrice());
        holder.imPictureItem.setImageResource(listFlower.get(position).getPictureItem());
        holder.imCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(context,holder.tvNameProduct.getText().toString()
                        ,Toast.LENGTH_SHORT).show();
                onSentToHomeFrg.sendData(listFlower.get(position),position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return listFlower.size();
    }

    public class HomeViewHolder extends RecyclerView.ViewHolder{
        private TextView tvOriginalPrice;
        private TextView tvNewPrice;
        private ImageView imPictureItem;
        private TextView tvNameProduct;
        private ImageView imCart;


        public HomeViewHolder(View itemView) {
            super(itemView);
            tvOriginalPrice = itemView.findViewById(R.id.oldMoney);
            tvNewPrice = itemView.findViewById(R.id.newMoney);
            imPictureItem = itemView.findViewById(R.id.imProduct);
            tvNameProduct = itemView.findViewById(R.id.nameProduct);
            imCart = itemView.findViewById(R.id.cart);
        }
    }

    public interface OnSentToHomeFrg{
        void sendData(Follower_Item item, int position);

    }
    public void setOnSentToHomeFrg(OnSentToHomeFrg onSentToHomeFrg){
        this.onSentToHomeFrg = onSentToHomeFrg;
    }

    @Override
    public String toString() {
        return "HomeAdapter{" +
                "listFlower=" + listFlower +
                ", context=" + context +
                ", onSentToHomeFrg=" + onSentToHomeFrg +
                '}';
    }
}
