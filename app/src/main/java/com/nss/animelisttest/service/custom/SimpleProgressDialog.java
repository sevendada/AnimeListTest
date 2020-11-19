package com.nss.animelisttest.service.custom;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.res.Resources;

import com.nss.animelisttest.R;


/**
 * Created by mac on 2/27/2017 AD.
 */

public class SimpleProgressDialog {

    Context context;
    Resources resources;

    public SimpleProgressDialog(Context context) {
        this.context = context;
    }

    private void setResource(){
        this.resources = context.getResources();
    }

    public ProgressDialog defaultProgressDialog(){
        setResource();
        ProgressDialog progressDialog = new ProgressDialog(context);
        progressDialog.setTitle(resources.getString(R.string.progress_dialog_title));
        progressDialog.setMessage(resources.getString(R.string.progress_dialog_message));
        progressDialog.setIndeterminate(true);
        progressDialog.setCancelable(false);
        progressDialog.setOnCancelListener(null);
        return progressDialog;
    }

    public ProgressDialog customCreate(String title,String message) {
        ProgressDialog progressDialog = new ProgressDialog(context);
        progressDialog.setTitle(title);
        progressDialog.setMessage(message);
        progressDialog.setIndeterminate(true);
        progressDialog.setCancelable(false);
        progressDialog.setOnCancelListener(null);
        return progressDialog;
    }

}
