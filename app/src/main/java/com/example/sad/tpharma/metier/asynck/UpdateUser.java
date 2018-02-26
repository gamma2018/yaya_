package com.example.sad.tpharma.metier.asynck;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;

import com.example.sad.tpharma.metier.entite.User;
import com.example.sad.tpharma.metier.traitement.Model;


public class UpdateUser extends AsyncTask<Void, Void, Boolean> {

    private String username;
    private User user;
    private ProgressDialog pD;

    public UpdateUser(String username, User user, ProgressDialog pD) {
        this.username = username;
        this.user = user;
        this.pD = pD;
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        pD.setTitle("Traitement...");
        pD.setMessage("Veillez patienter");
        pD.show();
    }

    @Override
    protected void onPostExecute(Boolean aBoolean) {
        super.onPostExecute(aBoolean);

        if (aBoolean)
        {
            pD.dismiss();
        }
    }

    @Override
    protected void onCancelled() {
        super.onCancelled();
    }

    @Override
    protected Boolean doInBackground(Void... voids) {

        new Model().updateUser(username, user);
        return true;
    }
}
