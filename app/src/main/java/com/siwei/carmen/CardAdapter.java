package com.siwei.carmen;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.joda.time.LocalDate;

import java.util.List;

/**
 * Created by wkd on 15-12-3.
 */
public class CardAdapter extends ArrayAdapter<Card>{
    private  int resourceId;
    private List<Card> cardList;
    //手指点下是的X坐标
    private float downX;
    //手指离开时的X坐标
    private float upX;
    private Context hostContext;

    public CardAdapter(Context context, int viewResourceId, List<Card> objects){
        super(context,viewResourceId,objects);

        this.hostContext = context;
        this.resourceId = viewResourceId;
        cardList = objects;

    }

    public  class ViewHolder{
        TextView tvAlias;
        TextView tvBillDay;
        TextView tvDueDay;
        TextView tvIFP;

    }

    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;

        if(convertView == null){
            convertView = LayoutInflater.from(getContext()).inflate(resourceId, null);
            holder = new ViewHolder();
            holder.tvAlias = (TextView) convertView.findViewById(R.id.tvAlias);
            holder.tvBillDay = (TextView) convertView.findViewById(R.id.tvBillDay);
            holder.tvDueDay = (TextView) convertView.findViewById(R.id.tvDueDay);
            holder.tvIFP = (TextView)convertView.findViewById(R.id.tvIFP);

            convertView.setTag(holder);
        }
        else
        {
            holder = (ViewHolder)convertView.getTag();
        }
        Card card = getItem(position);
        holder.tvAlias.setText(card.getAlias());
        holder.tvBillDay.setText("账单日：每月"+card.getBillDay()+"日");
        holder.tvDueDay.setText("到期还款日：每月" + card.getDueDay() + "日");
        int tmpIFP = card.getIFP(new LocalDate());
        holder.tvIFP.setText("免息期："+tmpIFP+"天");

        return convertView;

    }
}



