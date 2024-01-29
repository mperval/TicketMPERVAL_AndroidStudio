package ies.carrillo.android.ticketmperval;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
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
        Button btnDelete = findViewById(R.id.btnDelete);
        Button btnAtras = findViewById(R.id.btnAtras);
        Button btnAniadir = findViewById(R.id.btnAniadir);

        TextView id = findViewById(R.id.tvIdDetails);
        TextView date = findViewById(R.id.tvDateDetils);
        TextView total = findViewById(R.id.tvTotal);
        TextView version = findViewById(R.id.tvVersion);
        //ListView
        ListView lvTicketDetails = findViewById(R.id.lvTicketDetailsDD);

        Intent intent = getIntent();

        Ticket ticket = (Ticket) intent.getSerializableExtra("ticket");
        Integer idTicket = ticket.getId();
        //Inserccion de datos en los EdiTex
        id.setText(String.valueOf(ticket.getId()));
        date.setText(ticket.getDataTime());
        total.setText(ticket.getTotal());
        version.setText(ticket.getVersion());
        Log.i("Id TICKET", String.valueOf(ticket.getId()));

        //LLamada al servicio y le paso el id del Ticket.
        GoldenRaceApiService goldenRaceApiService = GoldenRaceApiClient.getClient().create(GoldenRaceApiService.class);
        Call<List<Details>> detailTicketCall = goldenRaceApiService.getDetailsTicket(idTicket);

        /**
         * Recojo todos los detalles del ticket y los muestro en un ListView.
         */
        detailTicketCall.enqueue(new Callback<List<Details>>() {
            @Override
            public void onResponse(Call<List<Details>> call, Response<List<Details>> response) {

                if (response.isSuccessful()) {
                    List<Details> detailsList = response.body();

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
        //TODO Aqui es subida de notas.
        lvTicketDetails.setOnItemClickListener(((parent, view, position, id1) -> {
            Details details = (Details) parent.getItemAtPosition(position);

            Intent intent1 = new Intent(DetailsTicket.this, DetailsDetail.class);
            intent1.putExtra("details", details);
            startActivity(intent1);

        }));
        /**
         * Actualizacion del ticket
         */
        btnUpdate.setOnClickListener(v -> {
            Intent intent1 = new Intent(DetailsTicket.this, PutTicket.class);
            intent1.putExtra("PutTicket", ticket);
            startActivity(intent1);
        });
        /**
         * elimina el ticket
         */
        btnDelete.setOnClickListener(v -> {
            // Creo un cuadro de dialogo de confirmacion.
            AlertDialog.Builder builder = new AlertDialog.Builder(DetailsTicket.this);
            builder.setTitle("Confirmar eliminación");
            builder.setMessage("¿Estás seguro de que deseas eliminar este ticket?");

            // Configurar los botones del cuadro de dialogo
            builder.setPositiveButton("Si", (dialog, which) -> {
                // Si el usuario hace clic en "Si", procedo con la eliminacion del ticket
                Call<Void> deleteTicket = goldenRaceApiService.deleteTicket(idTicket);

                deleteTicket.enqueue(new Callback<Void>() {
                    @Override
                    public void onResponse(Call<Void> call, Response<Void> response) {
                        if(response.isSuccessful()){
                            Toast.makeText(getApplicationContext(), "Ticket eliminado con éxito", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(getApplicationContext(), "Error al eliminar el ticket. Código: " + response.code(), Toast.LENGTH_SHORT).show();
                        }
                    }
                    @Override
                    public void onFailure(Call<Void> call, Throwable t) {
                        Toast.makeText(getApplicationContext(), "Error al eliminar el ticket. Verifica tu conexión a internet", Toast.LENGTH_SHORT).show();
                    }
                });

                // Despues de eliminar el ticket, iniciar la actividad principal
                Intent intent2 = new Intent(DetailsTicket.this, MainActivity.class);
                startActivity(intent2);
            });

            // Si el usuario hace clic en "No", simplemente cierro el cuadro de dialogo
            builder.setNegativeButton("No", (dialog, which) -> dialog.dismiss());
            // Mostrar el cuadro de diálogo
            builder.show();
        });
        btnAtras.setOnClickListener(v -> {
            Intent intent2 = new Intent(DetailsTicket.this, MainActivity.class);
            startActivity(intent2);
        });
        //TODO añadir detalle del ticket
        btnAniadir.setOnClickListener(v -> {

        });
    }
}