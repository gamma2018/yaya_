package com.example.sad.tpharma.adapter;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.EditText;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.sad.tpharma.R;
import com.example.sad.tpharma.entite.HomeItem;
import com.example.sad.tpharma.metier.asynck.UpdateClient;
import com.example.sad.tpharma.metier.entite.Client;
import com.example.sad.tpharma.metier.traitement.Model;

import java.util.ArrayList;


public class ClientGridAdapter extends BaseAdapter implements Filterable{

    Context c;
    int layout;
    ArrayList<HomeItem> items;

    ArrayList<HomeItem> filterList;
    ClientCustomFilter filter;

    ProgressDialog pD;
    EditText editNomClient, editPrenomClient, editTelephoneClient, editNomMutuelle;
    Client client;

    public ClientGridAdapter(Context c, int layout, ArrayList<HomeItem> items) {
        this.c = c;
        this.layout = layout;
        this.items = items;
        this.filterList = items;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int position) {
        return items.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) c.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

        if (convertView == null) {

            convertView = inflater.inflate(layout, null);
        }

            ImageView imageV = (ImageView) convertView.findViewById(R.id.photoClient);
            TextView libelleV = (TextView) convertView.findViewById(R.id.nomClient);
            TextView nameV = (TextView) convertView.findViewById(R.id.prenomClient);
            TextView mutuelle = (TextView) convertView.findViewById(R.id.mutuelle);


            //Chargement
            imageV.setImageResource(items.get(position).getImage());
            libelleV.setText(items.get(position).getNomClient());
            nameV.setText(items.get(position).getTelephoneClient());
            mutuelle.setText(items.get(position).getNomClientMutuelle());

            convertView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    final CustomAlertDialogBuilder alertDialog = new CustomAlertDialogBuilder(c);
                    LayoutInflater inflater = LayoutInflater.from(c);
                    View infosGrossisteLayout = inflater.inflate(R.layout.infos_client_layout, null);

                    TextView nom = (TextView) infosGrossisteLayout.findViewById(R.id.valeurNomClient);
                    TextView prenom = (TextView) infosGrossisteLayout.findViewById(R.id.valeurPrenomClient);
                    TextView tel = (TextView) infosGrossisteLayout.findViewById(R.id.valeurTelephoneClient);
                    TextView mutuelle = (TextView) infosGrossisteLayout.findViewById(R.id.valeurMutuelleClient);

                    client = new Client();
                    client = new Model().getClientByID(items.get(position).getIdclient());

                    nom.setText(client.getNomClient());
                    prenom.setText(client.getPrenomClient());
                    tel.setText(client.getTelClient());
                    mutuelle.setText(client.getNomMutuelle());



                    alertDialog.setTitle(items.get(position).getNomClient()+" "+items.get(position).getPrenomClient());
                    alertDialog.setView(infosGrossisteLayout);
                    alertDialog.setPositiveButton("Editer", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            //Chargement d'un nouvelle boite de dialogue.
                            final CustomAlertDialogBuilder dlg = new CustomAlertDialogBuilder(c);
                            LayoutInflater inflater = LayoutInflater.from(c);
                            View updateClientLayout = inflater.inflate(R.layout.edit_infos_client_layout, null);

                            editNomClient = (EditText) updateClientLayout.findViewById(R.id.editValeurNomClient);
                            editPrenomClient = (EditText) updateClientLayout.findViewById(R.id.editValeurPrenomClient);
                            editTelephoneClient = (EditText) updateClientLayout.findViewById(R.id.editTelephoneClient);
                            editNomMutuelle = (EditText) updateClientLayout.findViewById(R.id.editMutuelleClient);

                            pD = new ProgressDialog(c, ProgressDialog.STYLE_SPINNER);

                            editNomClient.setText(client.getNomClient());
                            editPrenomClient.setText(client.getPrenomClient());
                            editTelephoneClient.setText(client.getTelClient());
                            editNomMutuelle.setText(client.getNomMutuelle());


                            dlg.setTitle(client.getNomClient());
                            dlg.setView(updateClientLayout);
                            dlg.setPositiveButton("Valider", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {

                                    new UpdateClient(client.getIdClient(),
                                            new Client(
                                                    editNomMutuelle.getText().toString(),
                                                    editNomClient.getText().toString()
                                                    ,editPrenomClient.getText().toString(),
                                                    editTelephoneClient.getText().toString()
                                            ),
                                            pD
                                    ).execute((Void) null);
                                    dialog.dismiss();
                                    Toast.makeText(c, "Les informations sont mise à jour avec succès.", Toast.LENGTH_SHORT).show();

                                }
                            });

                            dlg.setNegativeButton("Annuler", null);
                            dlg.setCanceledOnTouchOutside(false);
                            dlg.show();
                        }
                    });
                    alertDialog.setNeutralButton("Supprimer", null);
                    alertDialog.setCanceledOnTouchOutside(false);
                    alertDialog.show();
                }
            });

        return convertView;
    }

    @Override
    public Filter getFilter() {
        if (filter == null)
        {
            filter = new ClientCustomFilter();
        }
        return filter;
    }

    private class ClientCustomFilter extends Filter
    {

        @Override
        protected FilterResults performFiltering(CharSequence constraint) {

            FilterResults results = new FilterResults();
            if (constraint != null && constraint.length() > 0)
            {
                constraint = constraint.toString().toUpperCase();
                ArrayList<HomeItem> filters = new ArrayList<HomeItem>();
                for (int i=0; i<filterList.size(); i++)
                {
                    if (filterList.get(i).getNomClient().toUpperCase().contains(constraint))
                    {
                        HomeItem item = new HomeItem(
                                filterList.get(i).getImage(),
                                filterList.get(i).getIdclient(),
                                filterList.get(i).getNomClient(),
                                filterList.get(i).getPrenomClient(),
                                filterList.get(i).getTelephoneClient(),
                                filterList.get(i).getNomClientMutuelle());
                        filters.add(item);
                    }
                }
                results.count = filters.size();
                results.values = filters;
            }
            else
            {
                results.count = filterList.size();
                results.values = filterList;
            }

            return results;
        }

        @Override
        protected void publishResults(CharSequence constraint, FilterResults results) {

            items = (ArrayList<HomeItem>) results.values;
            notifyDataSetChanged();
        }
    }
}
