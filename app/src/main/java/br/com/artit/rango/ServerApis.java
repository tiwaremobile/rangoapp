package br.com.artit.rango;

import android.app.Activity;
import android.os.AsyncTask;

import org.json.JSONArray;

public class ServerApis {
    Activity activity;
    private static final String URL_RESTAURANTES =
            "http://tiwaremobile.com/rango/restaurantes.php";

    public ServerApis(Activity activity) {
        this.activity = activity;
    }

    public void getRestaurantes() {

        AsyncTask<Void, Void, Void> mWSTask = new AsyncTask<Void, Void, Void>() {
            private JSONArray dataResponse = null;

            @Override
            protected Void doInBackground(Void... params) {
                WebService webService = new WebService();

                dataResponse = webService.get(URL_RESTAURANTES);

                return null;
            }

            @Override
            protected void onPostExecute(Void result) {
                MainActivity mainActivity = (MainActivity) activity;
                mainActivity.onFinishGetRestaurantes(dataResponse);
            }
        };
        mWSTask.execute(null, null, null);

    }


}
