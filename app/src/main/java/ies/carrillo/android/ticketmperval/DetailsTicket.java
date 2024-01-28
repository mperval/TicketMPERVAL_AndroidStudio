package ies.carrillo.android.ticketmperval;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

import Adapters.AdapterDetails;
import models.Details;
import retrofit2.Call;

import android.util.Log;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

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

        Button btnUpdate = findViewById(R.id.btnUpdate);
        TextView id = findViewById(R.id.tvIdDetails);
        TextView date = findViewById(R.id.tvDateDetils);
        TextView total = findViewById(R.id.tvTotal);
        TextView version = findViewById(R.id.tvVersion);
        //ListView
        ListView lvTicketDetails = findViewById(R.id.lvTicketDetailsDD);

        Intent intent = getIntent();

        Ticket ticket = (Ticket) intent.getSerializableExtra("ticket");
        Integer idTicket = ticket.getId();

        id.setText(String.valueOf(ticket.getId()));
        date.setText(ticket.getDataTime());
        total.setText(ticket.getTotal());
        version.setText(ticket.getVersion());
        Log.i("Id TICKET", String.valueOf(ticket.getId()));

        //Muestro en un ListView la lista de detalles del ticket atraves del id.
        GoldenRaceApiService goldenRaceApiService = GoldenRaceApiClient.getClient().create(GoldenRaceApiService.class);

        Call<List<Details>> detailTicketCall = goldenRaceApiService.getDetailsTicket(idTicket);

        detailTicketCall.enqueue(new Callback<List<Details>>() {
            @Override
            public void onResponse(Call<List<Details>> call, Response<List<Details>> response) {

                if (response.isSuccessful()) {
                    List<Details> detailsList = (List<Details>) response.body();

                    if (detailsList != null) {
                        // Adaptador.
                        AdapterDetails adapterDetails = new AdapterDetails(DetailsTicket.this, detailsList);
                        lvTicketDetails.setAdapter(adapterDetails);

                        // Mostrar la lista en el Log.
                        for (Details details : detailsList) {
                            Log.d("LISTADO DE DETALLES:", details.toString());
                        }
                    } else {
                        Log.e("Error", "Details list is null");
                    }
                } else {
                    Log.e("Error", "Response not successful. Code: " + response.code());
                }
            }
            @Override
            public void onFailure(Call<List<Details>> call, Throwable t) {

            }
        });
        //Aqui es subida de notas.
        lvTicketDetails.setOnItemClickListener(((parent, view, position, id1) -> {
            Details details = (Details) parent.getItemAtPosition(position);

            //Intent intent1 = new Intent(DetailsTicket.this, )
        }));
        /**
         * Actualizacion del ticket
         */
        btnUpdate.setOnClickListener(v -> {
            Intent intent1 = new Intent(DetailsTicket.this, PutTicket.class);
            intent1.putExtra("PutTicket", ticket);
            startActivity(intent1);
        });
    }
}