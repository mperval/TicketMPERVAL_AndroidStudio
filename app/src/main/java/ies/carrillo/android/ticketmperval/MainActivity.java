package ies.carrillo.android.ticketmperval;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.widget.ListView;

import java.util.List;

import client.GoldenRaceApiClient;
import models.NamedAPIResourceList;
import models.Ticket;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import service.GoldenRaceApiService;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

       ListView lvTicket = findViewById(R.id.LvTicket);

        GoldenRaceApiService goldenRaceApiService = GoldenRaceApiClient.getClient().create(GoldenRaceApiService.class);

        Call ticketCall = goldenRaceApiService.getTicket();


    }
}