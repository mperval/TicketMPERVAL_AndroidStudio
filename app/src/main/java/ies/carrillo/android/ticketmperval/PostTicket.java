package ies.carrillo.android.ticketmperval;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import client.GoldenRaceApiClient;
import models.Ticket;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import service.GoldenRaceApiService;

public class PostTicket extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.post_ticket);

        Button btnInsert = findViewById(R.id.btnInsert);
        Button btnAtrasPost = findViewById(R.id.btnAtrasPost);
        EditText etPrecio = findViewById(R.id.etPrecio);

        Ticket ticket = new Ticket();

        //Formateo de fecha para añadir DataTime al ticket
        LocalDateTime now = LocalDateTime.now();
        String pattern = "dd/MM/yyyy HH:mm:ss";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);
        String formatedDate = now.format(formatter);

        //Inicializacion del servicio
        GoldenRaceApiService goldenRaceApiService = GoldenRaceApiClient.getClient().create(GoldenRaceApiService.class);

        //Botn añadir ticket
        btnInsert.setOnClickListener(v -> {
            ticket.setId(0);
            ticket.setDataTime(formatedDate);
            ticket.setTotal(String.valueOf(etPrecio.getText()));

            //Llamada al servicio para añadir ticket
            Call<Ticket> postTicket = goldenRaceApiService.postTicket(ticket);

            postTicket.enqueue(new Callback<Ticket>() {
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
            Intent intent = new Intent(PostTicket.this, MainActivity.class);
            intent.putExtra("PostTicket", ticket);
            Toast.makeText(getApplicationContext(), "TICKET INSERTADO CORRECTAMENTE ", Toast.LENGTH_SHORT).show();
            startActivity(intent);
        });
        btnAtrasPost.setOnClickListener(v -> {
            Intent intent = new Intent(PostTicket.this, MainActivity.class);
            startActivity(intent);
        });
    }
}