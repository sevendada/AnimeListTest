package com.nss.animelisttest.service.custom;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.res.Resources;

import com.nss.animelisttest.R;


/**
 * Created by mac on 2/22/2017 AD.
 */

public class SimpleAlertDialog {

    Context context;
    Resources resources;

    public SimpleAlertDialog(Context context) {
        this.context = context;
    }

    private void setResource(){
        this.resources = context.getResources();
    }

    private AlertDialog.Builder defaultDialog(){
        setResource();
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
        alertDialogBuilder.setTitle(resources.getString(R.string.alert_dialog_title));
        return alertDialogBuilder;
    }

    public AlertDialog createConfirmDialogButtonPositiveCustomEvent(String strMessage,String strButtonPositive,String strButtonNegative,DialogInterface.OnClickListener onClickListener){
        AlertDialog.Builder alertDialogBuilder = defaultDialog();
        alertDialogBuilder.setMessage(strMessage);
        alertDialogBuilder.setCancelable(false);
        alertDialogBuilder.setPositiveButton(strButtonPositive == null ? resources.getString(R.string.alert_dialog_bt_positive_string_button) : strButtonPositive, onClickListener);
        alertDialogBuilder.setNegativeButton(strButtonNegative == null ? resources.getString(R.string.alert_dialog_bt_negative_string_button) : strButtonNegative, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        });
        AlertDialog alertDialog = alertDialogBuilder.create();
        return alertDialog;
    }

    public AlertDialog createDialogButtonPositiveCustomEvent(String strMessage,String strButtonPositive,DialogInterface.OnClickListener onClickListener){
        AlertDialog.Builder alertDialogBuilder = defaultDialog();
        alertDialogBuilder.setMessage(strMessage);
        alertDialogBuilder.setCancelable(false);
        alertDialogBuilder.setPositiveButton(strButtonPositive == null ? resources.getString(R.string.alert_dialog_bt_positive_string_button) : strButtonPositive, onClickListener);
        AlertDialog alertDialog = alertDialogBuilder.create();
        return alertDialog;
    }

    public AlertDialog createDialogWithFinishActivity(String strMessage,String strButton,final boolean isFinishActivity){
        AlertDialog.Builder alertDialogBuilder = defaultDialog();
        alertDialogBuilder.setMessage(strMessage);
        alertDialogBuilder.setCancelable(false);
        DialogInterface.OnClickListener onClickListener = new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
                if(isFinishActivity){
                    if(context instanceof Activity) {
                        ((Activity)context).finish();
                    }
                }
            }
        };
        alertDialogBuilder.setPositiveButton(strButton == null ? resources.getString(R.string.alert_dialog_bt_finish_activity) : strButton,onClickListener);
        AlertDialog alertDialog = alertDialogBuilder.create();
        return alertDialog;
    }

}
