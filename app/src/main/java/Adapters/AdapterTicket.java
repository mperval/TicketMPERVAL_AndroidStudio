package Adapters;


import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

import ies.carrillo.android.ticketmperval.R;
import models.Ticket;

public class AdapterTicket extends ArrayAdapter<Ticket> {
    private List<Ticket> ticketList;

    public AdapterTicket(Context context, List<Ticket> ticketList) {
        super(context, 0, ticketList);
        this.ticketList = ticketList;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Ticket ticket = getItem(position);

        if (convertView == null) {
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_ticket, parent, false);
        }

        TextView id = convertView.findViewById(R.id.tvId);
        TextView dataTime = convertView.findViewById(R.id.tvDate);
        TextView total = convertView.findViewById(R.id.tvTotal);

        id.setText(ticket.getId());
        dataTime.setText(ticket.getDataTime());
        total.setText(ticket.getTotal());

        return convertView;
    }
}
