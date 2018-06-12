package com.shrinvi.parkingapp.ui;

import android.content.Context;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.shrinvi.parkingapp.BR;
import com.shrinvi.parkingapp.R;
import com.shrinvi.parkingapp.model.ParkingSpace;
import com.shrinvi.parkingapp.model.ParkingSystem;
import com.shrinvi.parkingapp.model.Vehicle;
import com.shrinvi.parkingapp.viewmodel.SpaceItemViewModel;

import static com.shrinvi.parkingapp.ui.MainActivity.PARKING_CAPACITY;

public class ParkingAdapter extends RecyclerView.Adapter<ParkingAdapter.ParkingSpaceHolder> {
    private ParkingSystem mParkingSystem;
    private Context mContext;

    public ParkingAdapter(Context context, ParkingSystem parkingSystem) {
        mParkingSystem = parkingSystem;
        mContext = context;
    }

    @Override
    public ParkingSpaceHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        LayoutInflater layoutInflater =
                LayoutInflater.from(parent.getContext());
        ViewDataBinding binding = DataBindingUtil.inflate(layoutInflater, R.layout.grid_item_template, parent, false);
        return new ParkingSpaceHolder(binding);
    }

    @Override
    public int getItemViewType(int position) {
        return R.layout.grid_item_template;
    }

    @Override
    public void onBindViewHolder(ParkingSpaceHolder holder, int position) {
               ViewDataBinding binding = holder.getBinding();

        final ParkingSpace space = mParkingSystem.getTotalSpaces().get(position);
        SpaceItemViewModel spaceItemViewModel = new SpaceItemViewModel();
        spaceItemViewModel.setParkingSpace(space);
        binding.setVariable(BR.spaceItemViewModel, spaceItemViewModel);
        binding.setVariable(BR.parkingSpace, space);
    }

    @Override
    public int getItemCount() {
        return PARKING_CAPACITY;
    }

    class ParkingSpaceHolder extends RecyclerView.ViewHolder {
        private final ViewDataBinding mBinding;
        public ImageView mImageView;

        public ParkingSpaceHolder(ViewDataBinding viewDataBinding) {
            super(viewDataBinding.getRoot());
            this.mBinding = viewDataBinding;
            mImageView = mBinding.getRoot().findViewById(R.id.image_view);
        }

        public ViewDataBinding getBinding() {
            return mBinding;
        }
    }
}



