package com.shrinvi.parkingapp.ui;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.shrinvi.parkingapp.R;
import com.shrinvi.parkingapp.model.ParkingSpace;
import com.shrinvi.parkingapp.model.ParkingSystem;

import static com.shrinvi.parkingapp.ui.MainActivity.PARKING_CAPACITY;

public class ParkingAdapter extends RecyclerView.Adapter {
    private ParkingSystem mParkingSystem;
    private Context mContext;

    public  ParkingAdapter(Context context, ParkingSystem parkingSystem) {
        mParkingSystem = parkingSystem;
        mContext = context;
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(mContext).inflate(R.layout.grid_item_template, null, false);
        MyHolder viewHolder = new MyHolder(itemView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        MyHolder myHolder = (MyHolder) holder;
        final ParkingSpace space = mParkingSystem.getTotalSpaces().get(position);
        myHolder.mImageView.setVisibility(space.isEmpty() ? View.INVISIBLE : View.VISIBLE);
        View.OnClickListener listener = (view) -> {
            if (space.isEmpty()) {
                mParkingSystem.park("234", position);
                myHolder.mImageView.setVisibility(View.VISIBLE);
            } else {
                mParkingSystem.unPark("234", position);
                myHolder.mImageView.setVisibility(View.INVISIBLE);
            }

        };
        myHolder.itemView.setOnClickListener(listener);
        myHolder.mImageView.setOnClickListener(listener);
    }

    @Override
    public int getItemCount() {
        return PARKING_CAPACITY;
    }

    class MyHolder extends RecyclerView.ViewHolder {
        public ImageView mImageView;

        public MyHolder(View view) {
            super(view);
            mImageView = view.findViewById(R.id.image_view);
        }
    }
}



