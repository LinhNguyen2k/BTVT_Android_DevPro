package com.example.oderfood;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

public class FoodAdapter extends RecyclerView.Adapter<FoodAdapter.ViewHolder> {
    List<Food> foodList;
    IonClickFood ionClickFood;

    public void setIonClickFood(IonClickFood ionClickFood) {
        this.ionClickFood = ionClickFood;
    }

    public FoodAdapter(List<Food> foodList) {
        this.foodList = foodList;
    }

    @NonNull
    @Override
    public FoodAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View view = inflater.inflate(R.layout.food_item, parent, false);
        ViewHolder viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull FoodAdapter.ViewHolder holder, int position) {
        final Food food = foodList.get(position);
        holder.imgFood.setImageResource(food.getImgFood());
        holder.tvNameFood.setText(food.getNameFood());
        holder.tvPrice.setText(String.valueOf(food.getPriceFood()));
        holder.tvAmount.setText(String.valueOf(food.getAmount()));
        holder.layout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ionClickFood.onClickLayout(food);
            }
        });
        holder.imgFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ionClickFood.onClickImg(food);
            }
        });
        holder.tvNameFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ionClickFood.onClickName(food);
            }
        });
        if(food.getAmount()>0){
            holder.tvAmount.setVisibility(View.VISIBLE);
            holder.tvright.setVisibility(View.VISIBLE);
            holder.tvleft.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public int getItemCount() {
        return foodList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tvNameFood,tvPrice,tvAmount,tvleft,tvright;
        CircleImageView imgFood;
        RelativeLayout layout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            imgFood = itemView.findViewById(R.id.imgFood);
             tvNameFood = itemView.findViewById(R.id.tvNamefood);
             tvPrice = itemView.findViewById(R.id.tvPrice);
             tvAmount = itemView.findViewById(R.id.tvAmount);
             tvleft = itemView.findViewById(R.id.left);
             tvright = itemView.findViewById(R.id.right);
             layout = itemView.findViewById(R.id.layoutItem);
        }
    }
//
//    Context context;
//
//    public FoodAdapter(List<Food> foodList, Context context) {
//        this.foodList = foodList;
//        this.context = context;
//    }
//
//    @Override
//    public int getCount() {
//        return foodList.size();
//    }
//
//    @Override
//    public Object getItem(int position) {
//        return foodList.get(position);
//    }
//
//    @Override
//    public long getItemId(int position) {
//        return position;
//    }
//
//    @Override
//    public View getView(int position, View convertView, ViewGroup parent) {
//        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
//        View view = inflater.inflate(R.layout.food_item,parent,false);
//
//        return view;
//    }
}
