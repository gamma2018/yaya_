package com.example.sad.tpharma;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.EditText;
import android.widget.GridView;

import com.example.sad.tpharma.adapter.MutuelleGridAdapter;
import com.example.sad.tpharma.entite.HomeItem;
import com.example.sad.tpharma.metier.entite.Mutuelle;
import com.example.sad.tpharma.metier.traitement.Model;

import java.util.ArrayList;

public class MutuelleActivity extends AppCompatActivity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mutuelle);

        GridView gridView = (GridView) findViewById(R.id.gridMutuelle);
        EditText edRecherche = (EditText) findViewById(R.id.rechercheClient);
        final MutuelleGridAdapter adapter = new MutuelleGridAdapter(this, R.layout.custum_grid_mutuelle, getData(new Model().getAllMutuelles()));
        gridView.setAdapter(adapter);

    }

    private ArrayList<HomeItem> getData(ArrayList<Mutuelle> pList)
    {
        ArrayList<HomeItem> items = new ArrayList<HomeItem>();

        HomeItem item;
        for (int i=0; i < pList.size(); i++){
            item =  new HomeItem();
            item.setNomMutuelle(pList.get(i).getNomMutuelle());
            item.setTelephoneMutuelle(pList.get(i).getPhoneMutuelle());
            item.setImage(R.drawable.bank_building);
            item.setEmailMutuelle(pList.get(i).getEmailMutuelle());
            items.add(item);
        }

        return items;
    }
}
