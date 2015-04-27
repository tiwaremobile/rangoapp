package br.com.artit.rango;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;


public class MainActivity extends ActionBarActivity {
    private EditText txt;
    private Button btn;
    private Context context;
    private ArrayList <RestauranteModel> arrayRestaurante;
    private ListView listRestaurantes;
    private AlertDialog alerta;
    private ServerApis serverApis;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        context = this.getBaseContext();

        serverApis = new ServerApis(this);

        serverApis.getRestaurantes();

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
        } else if ( id == R.id.action_reload) {
            serverApis.getRestaurantes();
        }

        return super.onOptionsItemSelected(item);
    }

    public void onClickRestaurante(final RestauranteModel restauranteModel) {
        ArrayList<String> itensAlert = new ArrayList<String>();
        itensAlert.add(context.getString(R.string.navegar)); 		// value=0
        itensAlert.add(context.getString(R.string.compartilhar)); 		// value=1

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, R.layout.click_options, itensAlert);

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle(this.getString(R.string.on_click_restaurante)); // Titulo
        builder.setSingleChoiceItems(adapter, 0,
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface arg0, int arg1) {

                        if (arg1 == 0) { //Navegar
                            openGmaps(restauranteModel.getEndereco());

                        } else if (arg1 == 1) { //Compartilhar
                            shareTextAllApps( restauranteModel.getNome() +
                                              " - " + restauranteModel.getEndereco()
                                    , "Compartilhar");
                        }
                        alerta.dismiss();

                    }
                });
        alerta = builder.create();
        alerta.show();
    }

    private void shareTextAllApps(String textoCompartilhar, String tituloIntent) {
        Intent sendIntent = new Intent(Intent.ACTION_SEND);
        sendIntent.setType("text/plain");
        sendIntent.putExtra(Intent.EXTRA_TEXT, textoCompartilhar);
        startActivity(Intent.createChooser(sendIntent, tituloIntent));
    }

    private void openGmaps(String endereco) {
        Intent intent = new Intent(android.content.Intent.ACTION_VIEW,
                Uri.parse("http://maps.google.com/maps?daddr=" + endereco));
        startActivity(intent);
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

    public void onFinishGetRestaurantes(JSONArray dataResponse) {
        if (dataResponse != null && dataResponse.length() > 0) {

            listRestaurantes = (ListView) findViewById(R.id.listRestaurantes);
            arrayRestaurante = new ArrayList<RestauranteModel>();

            for (int i = 0; i < dataResponse.length(); i++) {

                try {
                    JSONObject json = dataResponse.getJSONObject(i);

                    RestauranteModel restaurante = new RestauranteModel(
                            json.getString("nome"),
                            json.getString("endereco"),
                            json.getString("distancia"),
                            json.getString("latitude"),
                            json.getString("longitude"));
                    arrayRestaurante.add(restaurante);

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            RestauranteAdapter adapter =
                    new RestauranteAdapter(this, arrayRestaurante);
            listRestaurantes.setAdapter(adapter);

        }
    }







}
