package com.example.android.example17dc.fragments;

import android.app.Fragment;
import android.app.NotificationManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.example.android.example17dc.R;
import com.example.android.example17dc.model.Ingridient;
import com.example.android.example17dc.provider.CategoryProvider;
import com.example.android.example17dc.provider.FoodProvider;

import java.io.IOException;
import java.io.InputStream;
import java.security.acl.Group;
import java.util.List;

public class DetailFragment extends Fragment {

    private static int NOTIFICATION_ID = 1;

    private int position = 0;

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {

        super.onActivityCreated(savedInstanceState);

            if (savedInstanceState != null) {
                position = savedInstanceState.getInt("position", 0);
            }

            // food - image
            ImageView ivFoodImage = (ImageView) getView().findViewById(R.id.iv_food);
            InputStream is = null;
            try {
                is = getActivity().getAssets().open(FoodProvider.getFoodById(position).getImage());
                Drawable drawable = Drawable.createFromStream(is, null);
                ivFoodImage.setImageDrawable(drawable);
            } catch (IOException e) {
                e.printStackTrace();
            }

            // food - name
            TextView foodName = (TextView) getView().findViewById(R.id.tv_food_name);
            foodName.setText(FoodProvider.getFoodById(position).getName());

            // rating bar
            RatingBar rbRating = (RatingBar) getView().findViewById(R.id.rb_rating);
            rbRating.setRating(FoodProvider.getFoodById(position).getRating());

            // food - description
            TextView foodDescription = (TextView) getView().findViewById(R.id.tv_destription_des);
            foodDescription.setText(FoodProvider.getFoodById(position).getDescription());

            // spinner for category
            Spinner category = (Spinner) getView().findViewById(R.id.sp_category);
            List<String> categories = CategoryProvider.getCategoryNames();
            ArrayAdapter<String> adapter = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_item, categories);
            category.setAdapter(adapter);
            category.setSelection((int) FoodProvider.getFoodById(position).getCategory().getId());

            // ListView for ingridients
            List<Ingridient> ingridients = (FoodProvider.getFoodById(position).getIngridients());
            ArrayAdapter<String> itemsAdapter = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, ingridients);
            ListView listView = (ListView) getView().findViewById(R.id.lv_list_view);
            listView.setAdapter(itemsAdapter);

            // food - calory
            TextView foodCalory = (TextView) getView().findViewById(R.id.tv_calorie_des);
            foodCalory.setText(FoodProvider.getFoodById(position).getCalory());

            // food - price
            TextView foodPrice = (TextView) getView().findViewById(R.id.tv_price);
            String stringdouble = Double.toString(FoodProvider.getFoodById(position).getPrice());
            foodPrice.setText(stringdouble);

            // button for order
            Button btnBuy = (Button) getView().findViewById(R.id.bt_button_order);
            btnBuy.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast toast = Toast.makeText(v.getContext(), "You've bought " + FoodProvider.getFoodById(position).getName() + "!", Toast.LENGTH_LONG);
                    toast.show();
                }
            });
        }



    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {
        super.onSaveInstanceState(savedInstanceState);
        savedInstanceState.putInt("position", position);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_detail, container, false);
        return view;
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }


    public void setContent(final int position) {

        this.position = position;
    }


    public void updateContent(final int position) {

        this.position = position;

        // food - image
        ImageView ivFoodImage = (ImageView) getView().findViewById(R.id.iv_food);
        InputStream is = null;
        try {
            is = getActivity().getAssets().open(FoodProvider.getFoodById(position).getImage());
            Drawable drawable = Drawable.createFromStream(is, null);
            ivFoodImage.setImageDrawable(drawable);
        } catch (IOException e) {
            e.printStackTrace();
        }

        // food - name
        TextView foodName = (TextView) getView().findViewById(R.id.tv_food_name);
        foodName.setText(FoodProvider.getFoodById(position).getName());

        // rating bar
        RatingBar rbRating = (RatingBar) getView().findViewById(R.id.rb_rating);
        rbRating.setRating(FoodProvider.getFoodById(position).getRating());

        // food - description
        TextView foodDescription = (TextView) getView().findViewById(R.id.tv_destription_des);
        foodDescription.setText(FoodProvider.getFoodById(position).getDescription());

        // spinner for category
        Spinner category = (Spinner) getView().findViewById(R.id.sp_category);
        List<String> categories = CategoryProvider.getCategoryNames();
        ArrayAdapter<String> adapter = new ArrayAdapter(getActivity(), android.R.layout.simple_spinner_item, categories);
        category.setAdapter(adapter);
        category.setSelection((int) FoodProvider.getFoodById(position).getCategory().getId());

        // ListView for ingridients
        List<Ingridient> ingridients = (FoodProvider.getFoodById(position).getIngridients());
        ArrayAdapter<String> itemsAdapter = new ArrayAdapter(getActivity(), android.R.layout.simple_list_item_1, ingridients);
        ListView listView = (ListView) getView().findViewById(R.id.lv_list_view);
        listView.setAdapter(itemsAdapter);

        // food - calory
        TextView foodCalory = (TextView) getView().findViewById(R.id.tv_calorie_des);
        foodCalory.setText(FoodProvider.getFoodById(position).getCalory());

        // food - price
        TextView foodPrice = (TextView) getView().findViewById(R.id.tv_price);
        String stringdouble = Double.toString(FoodProvider.getFoodById(position).getPrice());
        foodPrice.setText(stringdouble);

        // button for order
        Button btnBuy = (Button) getView().findViewById(R.id.bt_button_order);
        btnBuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast toast = Toast.makeText(v.getContext(), "You've bought " + FoodProvider.getFoodById(position).getName() + "!", Toast.LENGTH_LONG);
                toast.show();
            }
        });
    }

    // show notification
    private void showNotification() {

        NotificationCompat.Builder builder = new NotificationCompat.Builder(getActivity());
        Bitmap bitmap = BitmapFactory.decodeResource(getActivity().getResources(), R.drawable.ic_stat_buy);
        builder.setSmallIcon(R.drawable.ic_stat_buy);
        builder.setContentTitle(getActivity().getString(R.string.notification_title));
        builder.setContentText(getActivity().getString(R.string.notification_text));
        builder.setLargeIcon(bitmap);

        NotificationManager manager = (NotificationManager) getActivity().getSystemService(Context.NOTIFICATION_SERVICE);
        manager.notify(NOTIFICATION_ID, builder.build());
    }
}