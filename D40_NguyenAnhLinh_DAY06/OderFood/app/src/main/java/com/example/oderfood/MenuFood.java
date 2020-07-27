package com.example.oderfood;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class MenuFood extends AppCompatActivity {
    TextView tvUserName,tvCartCounter,tvPriceCounter;
    ImageView imgCart;
    Button btnOrder;
    RecyclerView lvFoodOrder;
    List<Food> listMenu,listCart;
    int mPossition;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu_food);
        ActionBar ac = getSupportActionBar();
        ac.hide();
        tvUserName = findViewById(R.id.tvUserName);
        tvCartCounter = findViewById(R.id.tvcartCounter);
        tvPriceCounter = findViewById(R.id.tvPriceCounter);
        imgCart = findViewById(R.id.imgCart);
        lvFoodOrder = findViewById(R.id.lvMenuFoodOrder);
        btnOrder = findViewById(R.id.btnOrder);
        //nhận intent
        final Intent intent = getIntent();
        String userName = intent.getStringExtra("username");
        tvUserName.setText(userName);

        //Xử lí RecycleView
        listMenu = new ArrayList<>();
        listCart = new ArrayList<>();
        listMenu.add(new Food(R.drawable.pizza,"Pizza Panda",20));
        listMenu.add(new Food(R.drawable.kfc,"KFC Super",30));
        listMenu.add(new Food(R.drawable.bread_egg,"Bread Eggs",15));
        listMenu.add(new Food(R.drawable.cup_cake,"Cup cake",12));
        listMenu.add(new Food(R.drawable.hamburger,"Hamburger",32));
        listMenu.add(new Food(R.drawable.hotdog,"Hotdog",35));
        listMenu.add(new Food(R.drawable.potato,"Potato",40));

        FoodAdapter adapter = new FoodAdapter(listMenu);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getBaseContext(),RecyclerView.VERTICAL,false);
        lvFoodOrder.setAdapter(adapter);
        lvFoodOrder.setLayoutManager(layoutManager);
        adapter.setIonClickFood(new IonClickFood() {
            @Override
            public void onClickName(Food food) {
                int counter = Integer.parseInt(tvCartCounter.getText().toString());
                counter++;
                tvCartCounter.setText(String.valueOf(counter));
                int priceCounter = Integer.parseInt(tvPriceCounter.getText().toString());
                priceCounter+=food.getPriceFood();
                tvPriceCounter.setText(String.valueOf(priceCounter));
                mPossition = listMenu.indexOf(food);
                if(listCart.contains(food)){
                    food.setAmount(food.getAmount()+1);
                }
                else{
                    food.setAmount(food.getAmount()+1);
                    listCart.add(food);
                }
            }

            @Override
            public void onClickImg(Food food) {
                int counter = Integer.parseInt(tvCartCounter.getText().toString());
                counter++;
                tvCartCounter.setText(String.valueOf(counter));
                int priceCounter = Integer.parseInt(tvPriceCounter.getText().toString());
                priceCounter+=food.getPriceFood();
                tvPriceCounter.setText(String.valueOf(priceCounter));
                mPossition = listMenu.indexOf(food);
                if(listCart.contains(food)){
                    food.setAmount(food.getAmount()+1);
                }
                else{
                    food.setAmount(food.getAmount()+1);
                    listCart.add(food);
                }
            }

            @Override
            public void onClickLayout(Food food) {
                int counter = Integer.parseInt(tvCartCounter.getText().toString());
                counter++;
                tvCartCounter.setText(String.valueOf(counter));
                int priceCounter = Integer.parseInt(tvPriceCounter.getText().toString());
                priceCounter+=food.getPriceFood();
                tvPriceCounter.setText(String.valueOf(priceCounter));
                mPossition = listMenu.indexOf(food);
                if(listCart.contains(food)){
                    food.setAmount(food.getAmount()+1);
                }
                else{
                    food.setAmount(food.getAmount()+1);
                    listCart.add(food);
                }
            }
        });
        //Button Order
        btnOrder.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getBaseContext(),"Đơn hàng của bạn đã được gửi lên nhà hàng",Toast.LENGTH_SHORT).show();
                tvCartCounter.setText("0");
                tvPriceCounter.setText("0");
                listCart.clear();
            }
        });
        //OnclickCart
        imgCart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(getBaseContext(),Cart.class);
                intent1.putExtra("totalPrice",tvPriceCounter.getText().toString());
//               intent1.putExtra("object",listMenu.get(mPossition));
                intent1.putParcelableArrayListExtra("list", (ArrayList<Food>) listCart);
                startActivity(intent1);

            }
        });
    }
}
