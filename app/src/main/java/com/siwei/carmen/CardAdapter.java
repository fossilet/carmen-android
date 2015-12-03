package com.siwei.carmen;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by wkd on 15-12-3.
 */
public class CardAdapter extends ArrayAdapter<Card>{
    private  int resourceId;

    public CardAdapter(Context context, int viewResourceId, List<Card> objects){
        super(context,viewResourceId,objects);
        this.resourceId = viewResourceId;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Card card = getItem(position);
        View view = LayoutInflater.from(getContext()).inflate(resourceId, null);

        TextView tvAlias = (TextView) view.findViewById(R.id.tvAlias);
        tvAlias.setText(card.getAlias());

        TextView tvBillDay = (TextView) view.findViewById(R.id.tvBillDay);
        tvBillDay.setText("记账日：每月"+card.getBillDay()+"日");

        TextView tvDueDay = (TextView) view.findViewById(R.id.tvDueDay);
        tvDueDay.setText("还款日：每月"+card.getDueDay()+"日");

        return view;

    }
}
