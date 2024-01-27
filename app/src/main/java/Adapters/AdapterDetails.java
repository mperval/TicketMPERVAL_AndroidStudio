package Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;

import java.util.List;

import ies.carrillo.android.ticketmperval.R;
import models.Details;

public class AdapterDetails extends ArrayAdapter<Details> {
    private List<Details> detailsList;
    public AdapterDetails(@NonNull Context context, @NonNull List<Details> detailsList) {
        super(context, 0, detailsList);
        this.detailsList = detailsList;
    }
    public View getView(int position, View convertView, ViewGroup parent){
        Details details = getItem(position);

        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(R.layout.item_details, parent, false);
        }

        TextView description = convertView.findViewById(R.id.tvDescription);
        TextView amount = convertView.findViewById(R.id.tvAmount);

        description.setText(details.getDescription());
        amount.setText(details.getAmount());

        return convertView;
    }
}
