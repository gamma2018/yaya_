package com.example.sad.tpharma;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.GridView;

import com.example.sad.tpharma.adapter.HistoriqueGridAdapter;
import com.example.sad.tpharma.adapter.NoteFraisGridAdapter;
import com.example.sad.tpharma.entite.HistoriqueItem;

import java.util.ArrayList;

public class NoteFraisActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_note_frais);

        GridView gridView = (GridView) findViewById(R.id.gridNoteFrais);
        NoteFraisGridAdapter adapter = new NoteFraisGridAdapter(this, R.layout.custum_grid_note_frais, getData());
        gridView.setAdapter(adapter);

    }

    private ArrayList<HistoriqueItem> getData()
    {
        ArrayList<HistoriqueItem> items = new ArrayList<HistoriqueItem>();

        HistoriqueItem item = new HistoriqueItem();

        item.setLibelle("Paracetmol");
        item.setCodeProd("#P001");
        item.setMontant(50000);
        item.setDate("12/02/2018");
        items.add(item);

        item = new HistoriqueItem();
        item.setLibelle("Paracetmol");
        item.setCodeProd("#P001");
        item.setMontant(50000);
        item.setDate("12/02/2018");
        items.add(item);

        item = new HistoriqueItem();
        item.setLibelle("Paracetmol");
        item.setCodeProd("#P001");
        item.setMontant(50000);
        item.setDate("12/02/2018");
        items.add(item);

        item = new HistoriqueItem();
        item.setLibelle("Paracetmol");
        item.setCodeProd("#P001");
        item.setMontant(50000);
        item.setDate("12/02/2018");
        items.add(item);

        item = new HistoriqueItem();
        item.setLibelle("Paracetmol");
        item.setCodeProd("#P001");
        item.setMontant(50000);
        item.setDate("12/02/2018");
        items.add(item);

        item = new HistoriqueItem();
        item.setLibelle("Paracetmol");
        item.setCodeProd("#P001");
        item.setMontant(50000);
        item.setDate("12/02/2018");
        items.add(item);

        item = new HistoriqueItem();
        item.setLibelle("Paracetmol");
        item.setCodeProd("#P001");
        item.setMontant(50000);
        items.add(item);

        item = new HistoriqueItem();
        item.setLibelle("Paracetmol");
        item.setCodeProd("#P001");
        item.setMontant(50000);
        item.setDate("12/02/2018");
        items.add(item);

        item = new HistoriqueItem();
        item.setLibelle("Paracetmol");
        item.setCodeProd("#P001");
        item.setMontant(50000);
        items.add(item);

        item = new HistoriqueItem();
        item.setLibelle("Paracetmol");
        item.setCodeProd("#P001");
        item.setMontant(50000);
        item.setDate("12/02/2018");
        items.add(item);




        return items;
    }
}
