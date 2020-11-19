package com.nss.animelisttest.service.base;

import android.app.AlertDialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.widget.Toast;

import com.nss.animelisttest.R;

import org.apache.commons.lang3.StringUtils;

import com.nss.animelisttest.service.custom.SimpleAlertDialog;
import com.nss.animelisttest.service.custom.SimpleProgressDialog;

/**
 * Created by mac on 2/22/2017 AD.
 */

public abstract class BaseTask<T> extends AsyncTask<Void, Void, ResultTask<T>> {

    Context context;
    ProgressDialog progressDialog;
    boolean dismissProgressDialog = false;

    public BaseTask(Context context) {
        this.context = context;
        this.progressDialog =  new SimpleProgressDialog(context).defaultProgressDialog();
    }

    public void setDismissProgressDialog(boolean dismissProgressDialog) {
        this.dismissProgressDialog = dismissProgressDialog;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        if(!dismissProgressDialog) progressDialog.show();
    }

    @Override
    protected ResultTask<T> doInBackground(Void... params) {

        ResultTask<T> resultTask = new ResultTask<T>();

        try {
            T result = prepareCallAPI();
            resultTask.setResultOK(true);
            resultTask.setResult(result);
            return resultTask;
        } catch (Exception e) {
            e.printStackTrace();
            resultTask.setResultOK(false);
            resultTask.setExceptionClass(e.getClass());
            resultTask.setExceptionMessage(e.toString());
            return resultTask;
        }
    }

    @Override
    protected void onPostExecute(ResultTask<T> result) {
        super.onPostExecute(result);
        if(!dismissProgressDialog) progressDialog.dismiss();
        try{
            if(result.isResultOK()) {
                onResultSuccess(result.getResult());
            }else{
                handlerError(true,result.getExceptionClass(), result.getExceptionMessage());
            }
        }catch(Exception e){
            e.printStackTrace();
            handlerError(false,e.getClass(),e.toString());
        }
    }

    public abstract T prepareCallAPI() throws Exception;
    public abstract void onResultSuccess(T result);

    private void handlerError(boolean isGoOn,Class<?> exceptionClass,String exceptionMessage) {
        if(isGoOn) {
            onResultError(exceptionClass, exceptionMessage);
        }else{
            errorOnToast(exceptionMessage);
        }
    }

    public void onResultError(Class<?> exceptionClass,String exceptionMessage){
        /**
         * if want show error to user please Override this method.
         */
    }

    public void errorOnToast(String exceptionMessage) {
        Toast.makeText(context, StringUtils.isBlank(exceptionMessage) ? context.getString(R.string.text_error_00) : exceptionMessage, Toast.LENGTH_LONG).show();
    }

    public AlertDialog errorOnAlertDialog(String textButton, boolean isFinishActivity, String exceptionMessage) {
        return new SimpleAlertDialog(context).createDialogWithFinishActivity(exceptionMessage, textButton, isFinishActivity);
    }

}
