package mei.meiweatherapp.asynctasks;

import android.content.Context;
import android.os.AsyncTask;
import android.widget.ImageView;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import mei.meiweatherapp.R;
import mei.meiweatherapp.Utils;
import mei.meiweatherapp.contratos.Condicoes;
import mei.meiweatherapp.contratos.Praia;
import mei.meiweatherapp.webservice.AccuWeatherWebService;

/**
 * Created by Hugo on 11-Sep-16.
 */
public class AccuweatherCurrentConditions extends AsyncTask<Praia, Void, Praia> {
    Context ctx;

    TextView txtLat;
    TextView txtLong;
    TextView txtAdress;
    ImageView imgTemp;
    TextView txtTemp;

    public AccuweatherCurrentConditions(Context ctx, ImageView imgTemp, TextView txtAdress, TextView txtLat, TextView txtLong, TextView txtTemp) {
        this.ctx = ctx;
        this.imgTemp = imgTemp;
        this.txtAdress = txtAdress;
        this.txtLat = txtLat;
        this.txtLong = txtLong;
        this.txtTemp = txtTemp;
    }

    @Override
    protected Praia doInBackground(Praia... praias) {
        Praia p = new Praia();

        AccuWeatherWebService aw = new AccuWeatherWebService();
        p = aw.doSearchLocationGeoPosition(praias[0]);

        String dataJson = aw.getCurrentConditions(praias[0], false);
        if(dataJson!=null)
        {
            Condicoes condicoes = new Condicoes();
            try
            {
                JSONArray ja = new JSONArray(dataJson);
                JSONObject jo = ja.getJSONObject(0);
                condicoes.setWeatherText(jo.getString("WeatherText"));
                condicoes.setWeatherIcon(jo.getInt("WeatherIcon"));
                p.setCondicoesActuais(condicoes);
            }
            catch (JSONException e)
            {
                e.printStackTrace();
                p = null;
            }
            return p;
        }
        else
        {
            return null;
        }
    }

    @Override
    protected void onPostExecute(Praia praia) {
        super.onPostExecute(praia);

        Condicoes c = praia.getCondicoesActuais();

        this.txtLat.setText(praia.getLatitude());
        this.txtLong.setText(praia.getLongitude());
        this.txtAdress.setText(praia.getMorada());
        this.txtTemp.setText(c.getWeatherText());
        int id = ctx.getResources().getIdentifier(Utils.MakeAWImageString(c.getWeatherIcon()),"drawable", ctx.getPackageName());
        this.imgTemp.setImageResource(id);
    }
}
