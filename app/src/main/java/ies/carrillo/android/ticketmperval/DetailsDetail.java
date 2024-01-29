package ies.carrillo.android.ticketmperval;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import client.GoldenRaceApiClient;
import service.GoldenRaceApiService;

public class DetailsDetail extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.details_detail);

        TextView IdDetails = findViewById(R.id.tvIdDetails);
        EditText tvDescriptionDD = findViewById(R.id.tvDescriptionDD);
        EditText tvTotalDD = findViewById(R.id.tvTotalDD);
        TextView tvTicketId = findViewById(R.id.tvTicketId);

        Button update = findViewById(R.id.btnUpdateDD);
        Button delete = findViewById(R.id.btnDeleteDD);
        Button back = findViewById(R.id.btnAtrasDD);

        GoldenRaceApiService goldenRaceApiService = GoldenRaceApiClient.getClient().create(GoldenRaceApiService.class);

    }
}