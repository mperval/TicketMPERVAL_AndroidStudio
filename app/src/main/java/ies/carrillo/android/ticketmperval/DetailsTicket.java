package ies.carrillo.android.ticketmperval;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import Adapters.AdapterDetails;
import models.Details;
import retrofit2.Call;

import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

import client.GoldenRaceApiClient;
import models.Ticket;
import retrofit2.Callback;
import retrofit2.Response;
import service.GoldenRaceApiService;

public class DetailsTicket extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.details_ticket);

        TextView id = findViewById(R.id.tvIdDetails);
        TextView date = findViewById(R.id.tvDateDetils);
        TextView total = findViewById(R.id.tvTotal);
        TextView version = findViewById(R.id.tvVersion);
        //ListView
        ListView lvTicketDetails = findViewById(R.id.lvTicketDetails);

        Intent intent = getIntent();

        Ticket ticket = (Ticket) intent.getSerializableExtra("ticket");

        id.setText(ticket.getId());
        date.setText(ticket.getDataTime());
        total.setText(ticket.getTotal());
        version.setText(ticket.getVersion());

        //Muestro en un ListView la lista de detalles del ticket atraves del id.
        GoldenRaceApiService goldenRaceApiService = GoldenRaceApiClient.getClient().create(GoldenRaceApiService.class);

        Call detailTicket = goldenRaceApiService.getDetailsTicket(ticket.getId());

        detailTicket.enqueue(new Callback() {
            @Override
            public void onResponse(Call call, Response response) {

                List<Details> detailsList = (List<Details>) response.body();

                //Adaptador.
                AdapterDetails adapterDetails = new AdapterDetails(DetailsTicket.this, detailsList);
                lvTicketDetails.setAdapter(adapterDetails);
            }

            @Override
            public void onFailure(Call call, Throwable t) {
                Log.e("Error", "The resquest cloud not be made");
            }
        });
        //Aqui es subida de notas.
        lvTicketDetails.setOnItemClickListener(((parent, view, position, id1) -> {
            Details details = (Details) parent.getItemAtPosition(position);

            //Intent intent1 = new Intent(DetailsTicket.this, )
        }));
    }
}