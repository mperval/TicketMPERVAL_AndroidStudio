package ies.carrillo.android.ticketmperval;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import java.util.List;

import Adapters.AdapterTicket;
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

        Call<NamedAPIResourceList> ticketCall = goldenRaceApiService.getTicket();

        ticketCall.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {
                NamedAPIResourceList namedAPIResourceList = (NamedAPIResourceList) response.body();

                List<Ticket> ticketList = namedAPIResourceList.getTickets();

                AdapterTicket adapterTicket = new AdapterTicket(MainActivity.this, ticketList);

                lvTicket.setAdapter(adapterTicket);

                for (Ticket ti :
                        namedAPIResourceList.getTickets()) {
                    Log.i("Tickets", ti.toString());
                }
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                Log.e("Error", "The resquest cloud not be made");
            }
        });
    }
}