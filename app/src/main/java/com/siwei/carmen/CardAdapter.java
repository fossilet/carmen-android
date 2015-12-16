package com.siwei.carmen;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;

import java.util.List;

/**
 * Created by wkd on 15-12-3.
 */
public class CardAdapter extends ArrayAdapter<Card>{
    private  int resourceId;
    private List<Card> cardList;

    public CardAdapter(Context context, int viewResourceId, List<Card> objects){
        super(context,viewResourceId,objects);
        this.resourceId = viewResourceId;
        cardList = objects;
    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        if(convertView == null){
        Card card = getItem(position);
        convertView = LayoutInflater.from(getContext()).inflate(resourceId, null);

        TextView tvAlias = (TextView) convertView.findViewById(R.id.tvAlias);
        tvAlias.setText(card.getAlias());

        TextView tvBillDay = (TextView) convertView.findViewById(R.id.tvBillDay);
        tvBillDay.setText("账单日：每月"+card.getBillDay()+"日");

        TextView tvDueDay = (TextView) convertView.findViewById(R.id.tvDueDay);
        tvDueDay.setText("到期还款日：每月"+card.getDueDay()+"日");

        }

        convertView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                Button btn = (Button) v.findViewById(R.id.btnDeleteCard);
                btn.setVisibility(View.VISIBLE);

                return false;
            }
        });

        Button btnDelete = (Button) convertView.findViewById(R.id.btnDeleteCard);
        btnDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Button btn = (Button)v;
                Card card = getItem(position);
                CardDAO dao = new CardDAO(MainActivity.getInstance().getDbHelper());
                if(dao.DeleteCard(card)){
                    cardList.remove(position);
                    notifyDataSetChanged();

                }



                btn.setVisibility(View.GONE);
            }
        });


        return convertView;

    }
}
