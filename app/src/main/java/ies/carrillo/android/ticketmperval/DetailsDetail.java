package ies.carrillo.android.ticketmperval;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import client.GoldenRaceApiClient;
import models.Details;
import models.Ticket;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
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

        Intent intent = getIntent();
        Details details = (Details) intent.getSerializableExtra("details");
        Ticket ticket = (Ticket) intent.getSerializableExtra("ticket");

        GoldenRaceApiService goldenRaceApiService = GoldenRaceApiClient.getClient().create(GoldenRaceApiService.class);

        IdDetails.setText(details.getId().toString());
        tvDescriptionDD.setText(details.getDescription());
        tvTotalDD.setText(details.getAmount().toString());
        tvTicketId.setText(details.getTicketId().toString());


        Call<Details> detailsCall = goldenRaceApiService.putDetail(details.getId(), details);

        update.setOnClickListener(v -> {
            details.setDescription(tvDescriptionDD.getText().toString());
            details.setAmount(Double.parseDouble(tvTotalDD.getText().toString()));
            details.setId(details.getId());
            details.setTicketId(details.getTicketId());
            details.setTicket(ticket);

            detailsCall.enqueue(new Callback<Details>() {

                @Override
                public void onResponse(Call<Details> call, Response<Details> response) {
                    if(response.isSuccessful()){
                        Details details1 = response.body();
                    }
                }
                @Override
                public void onFailure(Call<Details> call, Throwable t) {

                }
            });
            Intent intent1 = new Intent(DetailsDetail.this, DetailsTicket.class);
            intent1.putExtra("detail", details);
            intent1.putExtra("ticket", ticket);
            Toast.makeText(getApplicationContext(), "DETALLE ACTUALIZADO CORRECTAMENTE ", Toast.LENGTH_SHORT).show();
            startActivity(intent1);
        });
        delete.setOnClickListener(v -> {

            // Creo un cuadro de dialogo de confirmacion.
            AlertDialog.Builder builder = new AlertDialog.Builder(DetailsDetail.this);
            builder.setTitle("Confirmar eliminación");
            builder.setMessage("¿Estás seguro de que deseas eliminar este ticket?");

            // Configurar los botones del cuadro de dialogo
            builder.setPositiveButton("Si", (dialog, which) -> {

                // Si el usuario hace clic en "Si", procedo con la eliminacion del ticket
                Call<Void> deleteDetails = goldenRaceApiService.deleteDetail(details.getId());

                deleteDetails.enqueue(new Callback<Void>() {
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
                Intent intent2 = new Intent(DetailsDetail.this, DetailsTicket.class);
                startActivity(intent2);
            });

            // Si el usuario hace clic en "No", simplemente cierro el cuadro de dialogo
            builder.setNegativeButton("No", (dialog, which) -> dialog.dismiss());
            // Mostrar el cuadro de diálogo
            builder.show();
        });
        back.setOnClickListener(v -> {
            Intent intent2 = new Intent(DetailsDetail.this, DetailsTicket.class);
            startActivity(intent2);
        });
    }
}