package ies.carrillo.android.ticketmperval;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import client.GoldenRaceApiClient;
import models.Details;
import models.Ticket;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import service.GoldenRaceApiService;

public class PostDetail extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.post_detail);

        EditText descripcion = findViewById(R.id.etDescriptionPD);
        EditText total = findViewById(R.id.etTotalPD);
        //Boton
        Button agregar = findViewById(R.id.btnAgregarPD);
        Button atras = findViewById(R.id.btnAtrasDDD);

        Intent intent = getIntent();
        Ticket ticket = (Ticket) intent.getSerializableExtra("ticket");

        Log.i("idTicket", String.valueOf(ticket.getId()));
        int idTicket = ticket.getId();

        GoldenRaceApiService goldenRaceApiService = GoldenRaceApiClient.getClient().create(GoldenRaceApiService.class);

        agregar.setOnClickListener(v -> {
            Details details = new Details();

            details.setTicket(ticket);
            details.setId(0);
            details.setDescription(descripcion.getText().toString());
            details.setAmount(Double.parseDouble(total.getText().toString()));


            Call<Details> postDetail = goldenRaceApiService.postDetail(details);

            postDetail.enqueue(new Callback<Details>() {
                @Override
                public void onResponse(Call<Details> call, Response<Details> response) {
                    if(response.isSuccessful()){
                        Details details1 = response.body();
                    }else {
                        Log.e("error", "error");
                    }
                }
                @Override
                public void onFailure(Call<Details> call, Throwable t) {
                    Log.e("Error", "The resquest cloud not be made");
                }
            });
            Intent intent1 = new Intent(PostDetail.this, DetailsTicket.class);
            intent1.putExtra("PutTicket", ticket);
            intent1.putExtra("PutDetail", details);
            Toast.makeText(getApplicationContext(), "DETALLE TICKET INSERTADO CORRECTAMENTE ", Toast.LENGTH_SHORT).show();
            startActivity(intent1);
        });
        atras.setOnClickListener(v -> {
            Intent intent2 = new Intent(PostDetail.this, DetailsTicket.class);
            intent2.putExtra("PutTicket", ticket);
            startActivity(intent2);
        });
    }
}