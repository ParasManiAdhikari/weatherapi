package com.example.weatherapi;

import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.ui.AppBarConfiguration;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.example.weatherapi.databinding.ActivityMainBinding;

import org.json.JSONArray;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration appBarConfiguration;
    private ActivityMainBinding binding;

    private List<WLocation> locations = new ArrayList<WLocation>();
    private String[] data = {"Apple", "Banana", "Cherry", "Charchoal","Date", "Grapes", "Kiwi", "Lemon"};


    private static final String BASE_URL = "https://dataservice.accuweather.com/locations/v1/cities/autocomplete?apikey=tsMvHvK38vNx7sb7gvBAqI7a7K7j0QTe&q=new";
    private static final String API_KEY = "tsMvHvK38vNx7sb7gvBAqI7a7K7j0QTe";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        binding = ActivityMainBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        ArrayAdapter<String> adapter = new ArrayAdapter<>(MainActivity.this, androidx.appcompat.R.layout.support_simple_spinner_dropdown_item, data);
        binding.dataInput.setAdapter(adapter);


        binding.addLocationButton.setOnClickListener(v -> {
            // VOLLEY
            RequestQueue queue = Volley.newRequestQueue(MainActivity.this);
            JsonArrayRequest request = new JsonArrayRequest(Request.Method.GET, BASE_URL, null,
                    response -> Toast.makeText(MainActivity.this, response.toString(), Toast.LENGTH_LONG).show(),
                    error -> Toast.makeText(MainActivity.this, "Error", Toast.LENGTH_SHORT).show());
            queue.add(request);
        }
        );

        binding.dataInput.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String selectedItem = (String) parent.getItemAtPosition(position);
                // Handle the selected item as needed

            }
        });
    }
}