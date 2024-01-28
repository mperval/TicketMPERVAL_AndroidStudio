package ies.carrillo.android.ticketmperval;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.ListView;

import java.util.List;

import Adapters.AdapterTicket;
import client.GoldenRaceApiClient;
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
        //List
        ListView lvTicket = findViewById(R.id.LvTicket);
        //Boton
        Button btnAddTicket = findViewById(R.id.btnAddTicket);

        GoldenRaceApiService goldenRaceApiService = GoldenRaceApiClient.getClient().create(GoldenRaceApiService.class);

        Call ticketCall = goldenRaceApiService.getTicket();

        ticketCall.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                Log.i("RESPONSE", response.toString());
                List<Ticket> ticketList = (List<Ticket>) response.body();

                AdapterTicket adapterTicket = new AdapterTicket(MainActivity.this, ticketList);
                lvTicket.setAdapter(adapterTicket);
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                Log.e("Error", "The resquest cloud not be made");
            }
        });
        //ListView
        lvTicket.setOnItemClickListener((parent, view, position, id) -> {
            Ticket ticket = (Ticket) parent.getItemAtPosition(position);

            Intent intent = new Intent(MainActivity.this, DetailsTicket.class);
            intent.putExtra("ticket", ticket);
            startActivity(intent);
        });
        //Boton
        btnAddTicket.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, PostTicket.class);
            startActivity(intent);
        });
    }
}