package mei.meiweatherapp;

import android.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocompleteFragment;
import com.google.android.gms.location.places.ui.PlaceSelectionListener;
import com.google.android.gms.maps.model.LatLng;

import org.w3c.dom.Text;

import mei.meiweatherapp.asynctasks.AccuweatherCurrentConditions;
import mei.meiweatherapp.contratos.APIData;
import mei.meiweatherapp.contratos.Praia;

public class MainActivity extends FragmentActivity {

    String TAG = "<MyAutoComplete Google>";
    TextView txtLat;
    TextView txtLong;
    TextView txtAdress;

    ImageView imgTemp;
    TextView txtTemp;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtLat = (TextView) findViewById(R.id.txtLat);
        txtLong = (TextView) findViewById(R.id.txtLong);
        txtAdress = (TextView) findViewById(R.id.txtAdress);
        imgTemp = (ImageView) findViewById(R.id.imgTemp);
        txtTemp = (TextView) findViewById(R.id.txtTemp);



        PlaceAutocompleteFragment autocompleteFragment = (PlaceAutocompleteFragment) getFragmentManager().findFragmentById(R.id.place_autocomplete_fragment);

        autocompleteFragment.setOnPlaceSelectedListener(new PlaceSelectionListener()
        {

            @Override
            public void onPlaceSelected(Place place) {
                Log.i(TAG, "Place: " + place.getName());

                Praia praia = new Praia();
                praia.setNome(place.getName().toString());
                LatLng ll = place.getLatLng();
                praia.setLatitude(Double.toString(ll.latitude));
                praia.setLongitude(Double.toString(ll.longitude));
                praia.setMorada(place.getAddress().toString());

                AccuweatherCurrentConditions awcc = new AccuweatherCurrentConditions(MainActivity.this, imgTemp, txtAdress, txtLat, txtLong, txtTemp);
                awcc.execute(praia);
            }

            @Override
            public void onError(Status status) {
                Log.i(TAG, "An error occurred: " + status);
                Toast.makeText(MainActivity.this, "Erro ao obter localização", Toast.LENGTH_LONG).show();
            }
        });

    }
}
