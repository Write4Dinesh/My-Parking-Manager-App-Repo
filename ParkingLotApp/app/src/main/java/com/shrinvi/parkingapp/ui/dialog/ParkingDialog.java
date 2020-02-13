package com.shrinvi.parkingapp.ui.dialog;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;

public class ParkingDialog extends DialogFragment {
    private DialogInterface.OnClickListener mNegativeButtonListener;
    private DialogInterface.OnClickListener mPositiveButtonListener;
    private String mNegativeButtonLabel;
    private String mPositiveButtonLabel;
    private String mMessage;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setMessage(mMessage)
                .setPositiveButton(mPositiveButtonLabel, mPositiveButtonListener)
                .setNegativeButton(mNegativeButtonLabel, mNegativeButtonListener);
        // Create the AlertDialog object and return it
        return builder.create();
    }

    public void setNegativeButton(String buttonLabel, DialogInterface.OnClickListener listener) {
        mNegativeButtonLabel = buttonLabel;
        mNegativeButtonListener = listener;
    }

    public void setPositiveButton(String buttonLabel, DialogInterface.OnClickListener listener) {
        mPositiveButtonLabel = buttonLabel;
        mPositiveButtonListener = listener;
    }

    public void setMessage(String message) {
        mMessage = message;
    }

}