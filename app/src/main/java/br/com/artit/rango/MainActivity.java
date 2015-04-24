package br.com.artit.rango;

import android.app.Dialog;
import android.content.Context;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;


public class MainActivity extends ActionBarActivity {
    private EditText txt;
    private Button btn;
    private Context context;
    private ArrayList <RestauranteModel> arrayRestaurante;
    private ListView listRestaurantes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this.getBaseContext();

        carregaListaRestaurantes();
    }

    private void xxx(int pos) {
        final RestauranteModel restauranteModel = ( RestauranteModel )
                arrayRestaurante.get( pos );


    }

    private void carregaListaRestaurantes() {
        listRestaurantes = (ListView) findViewById(R.id.listRestaurantes);
        arrayRestaurante = new ArrayList<RestauranteModel>();

        RestauranteModel rest = new RestauranteModel("Villani",
                "Rua xxxxx,100",
                "100 metros",
                "21",
                "20");
        arrayRestaurante.add(rest);


        RestauranteModel rest1 = new RestauranteModel("Pizza Hut",
                "Rua yyyyy,200",
                "50 metros",
                "21",
                "20");
        arrayRestaurante.add(rest1);


        RestauranteModel rest2 = new RestauranteModel("Bar Brahma",
                "Rua zzzz,300",
                "550 metros",
                "21",
                "20");
        arrayRestaurante.add(rest2);


        RestauranteAdapter adapter =
                new RestauranteAdapter(this, arrayRestaurante);

        listRestaurantes.setAdapter(adapter);

    }

    private void exibeToast(String msg) {
        Toast.makeText(context, msg, Toast.LENGTH_LONG).show();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_settings) {
            return true;
        } else if ( id == R.id.action_dialog) {
                abrirDialog();
        }

        return super.onOptionsItemSelected(item);
    }

    private void abrirDialog() {
        final Dialog dialog = new Dialog(this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.layout_dialog);

        Button btnSair = (Button) dialog.findViewById(R.id.btnSair);

        btnSair.setText("MARCOS");

        btnSair.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dialog.dismiss();
            }
        });

        dialog.show();
    }













}
