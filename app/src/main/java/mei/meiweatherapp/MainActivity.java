package mei.meiweatherapp;

import android.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.common.api.Status;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.location.places.ui.PlaceAutocompleteFragment;
import com.google.android.gms.location.places.ui.PlaceSelectionListener;
import com.google.android.gms.maps.model.LatLng;

import org.w3c.dom.Text;

import mei.meiweatherapp.contratos.Praia;

public class MainActivity extends FragmentActivity {

    String TAG = "<MyAutoComplete Google>";
    TextView txtLat;
    TextView txtLong;
    TextView txtAdress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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

                txtLat = (TextView) findViewById(R.id.txtLat);
                txtLong = (TextView) findViewById(R.id.txtLong);
                txtAdress = (TextView) findViewById(R.id.txtAdress);

                txtLat.setText(praia.getLatitude());
                txtLong.setText(praia.getLongitude());
                txtAdress.setText(praia.getMorada());
            }

            @Override
            public void onError(Status status) {
                Log.i(TAG, "An error occurred: " + status);
                Toast.makeText(MainActivity.this, "Erro ao obter localização", Toast.LENGTH_LONG).show();
            }
        });

    }
}
