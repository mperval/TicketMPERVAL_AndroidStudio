package ies.carrillo.android.ticketmperval;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import client.GoldenRaceApiClient;
import models.Ticket;
import okhttp3.internal.Version;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import service.GoldenRaceApiService;

public class PutTicket extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.put_ticket);

        TextView id = findViewById(R.id.tvIdDetailsPut);
        TextView date = findViewById(R.id.tvDateDetilsPut);
        EditText total = findViewById(R.id.etTotalPut);
        TextView version = findViewById(R.id.tvVersionPut);
        TextView TotalAmountPut = findViewById(R.id.tvTotalAmountPut);
        Button btnUpdatePut = findViewById(R.id.btnUpdatePut);

        //Recogida del objeto de la clase MainActivity
        Intent intent = getIntent();
        Ticket ticket = (Ticket) intent.getSerializableExtra("PutTicket");

        //Inicializo el servicio.
        GoldenRaceApiService goldenRaceApiService = GoldenRaceApiClient.getClient().create(GoldenRaceApiService.class);

        //inserto los datos al los editText
        id.setText(String.valueOf(ticket.getId()));
        date.setText(ticket.getDataTime());
        version.setText(ticket.getVersion());
        TotalAmountPut.setText(ticket.getTotal());

        // Parseo la fecha para cuando se ha actualizado por ultima vez
        LocalDateTime now = LocalDateTime.now();
        String pattern = "dd/MM/yyyy HH:mm:ss";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        String formatedDate = now.format(formatter);

        /**
         * Hago la llamada pasandole tambien el IdTicket.
         */
        btnUpdatePut.setOnClickListener(v -> {
            ticket.setTotal(String.valueOf(total.getText()));
            ticket.setDataTime(formatedDate);

            Call <Ticket> putTicket = goldenRaceApiService.putTicket(ticket.getId(), ticket);
            putTicket.enqueue(new Callback<Ticket>() {
                @Override
                public void onResponse(Call<Ticket> call, Response<Ticket> response) {
                    if(response.isSuccessful()){
                        Ticket ticket1 = response.body();
                    }else{
                        Log.e("error", "error");
                    }
                }
                @Override
                public void onFailure(Call<Ticket> call, Throwable t) {
                    Log.e("Error", "The resquest cloud not be made");
                }
            });
            //Lo llevo al main cuando es actualizado.
            Intent intent1 = new Intent(PutTicket.this, MainActivity.class);
            intent1.putExtra("PutTicket", ticket);
            Toast.makeText(getApplicationContext(), "TICKET ACTUALIZADO CORRECTAMENTE ", Toast.LENGTH_SHORT).show();
            startActivity(intent1);
        });
    }
}