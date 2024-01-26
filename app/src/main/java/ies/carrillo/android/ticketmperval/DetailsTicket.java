package ies.carrillo.android.ticketmperval;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import models.Ticket;

public class DetailsTicket extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.details_ticket);

        TextView id = findViewById(R.id.tvIdDetails);
        TextView date = findViewById(R.id.tvDateDetils);
        TextView total = findViewById(R.id.tvTotal);
        TextView version = findViewById(R.id.tvVersion);

        Intent intent = getIntent();

        Ticket ticket = (Ticket) intent.getSerializableExtra("ticket");

        id.setText(ticket.getId());
        date.setText(ticket.getDataTime());
        total.setText(ticket.getTotal());
        version.setText(ticket.getVersion());

    }
}