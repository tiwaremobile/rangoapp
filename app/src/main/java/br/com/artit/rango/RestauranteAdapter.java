package br.com.artit.rango;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class RestauranteAdapter extends BaseAdapter implements View.OnClickListener {

    private Activity activity;
    private ArrayList arrayList;
    private static LayoutInflater inflater=null;

    public RestauranteAdapter(Activity activity, ArrayList arrayList) {
        this.activity = activity;
        this.arrayList = arrayList;
        inflater = ( LayoutInflater )activity.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public int getCount() {
        return arrayList.size();
    }

    public Object getItem(int position) {
        //....
        return position;
    }

    public long getItemId(int position) {
        return position;
    }

    public static class ViewHolder{
        public TextView txtNomeRestaurante;
        public TextView txtEnderecoRestaurante;
        public TextView txtDistanciaRestaurante;
    }

    public View getView(int position, View convertView, ViewGroup parent) {

        View vi = convertView;
        ViewHolder holder;

        if(vi == null){
            vi = inflater.inflate(R.layout.layout_item_restaurante, null);
            holder = new ViewHolder();


            holder.txtNomeRestaurante = (TextView) vi.findViewById(R.id.txtNomeRestaurante);
            holder.txtEnderecoRestaurante = (TextView) vi.findViewById(R.id.txtEnderecoRestaurante);
            holder.txtDistanciaRestaurante = (TextView) vi.findViewById(R.id.txtDistanciaRestaurante);

            vi.setTag( holder );
        } else {
            holder=(ViewHolder)vi.getTag();
        }


        if(arrayList.size()<=0) {
            //holder.text.setText("No Data");
        } else {
            final RestauranteModel restauranteModel = ( RestauranteModel ) arrayList.get( position );
            final int mPosition = position;

            holder.txtNomeRestaurante.setText( restauranteModel.getNome() );
            holder.txtEnderecoRestaurante.setText( restauranteModel.getEndereco() );
            holder.txtDistanciaRestaurante.setText( restauranteModel.getDistancia() );

            vi.setOnClickListener(new OnItemClickListener( position ));
        }
        return vi;
    }

    @Override
    public void onClick(View v) {}


    private class OnItemClickListener implements View.OnClickListener {
        private int mPosition;

        OnItemClickListener(int position){
            mPosition = position;
        }

        @Override
        public void onClick(View arg0) {
            RestauranteModel restauranteModel = (RestauranteModel) arrayList.get(mPosition);

            MainActivity sct = (MainActivity) activity;
            sct.onClickRestaurante(restauranteModel);

        }
    }

}
