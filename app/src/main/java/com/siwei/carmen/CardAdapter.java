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
            tvDueDay.setText("到期还款日：每月" + card.getDueDay() + "日");



            /*convertView.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View v, MotionEvent event) {
                    ImageView iv = (ImageView) v.findViewById(R.id.ivDelete);
                    switch (event.getAction()){
                        case MotionEvent.ACTION_DOWN:
                            downX = event.getX();
                            Log.i("iv","DOWN");
                            break;
                        case MotionEvent.ACTION_UP:
                            Log.i("iv","UP");
                            upX = event.getX();
                            if(upX-downX>35)
                            {
                                iv.setVisibility(View.GONE);

                            }
                            if(downX-upX > 35){
                                iv.setVisibility(View.VISIBLE);
                            }

                            downX = upX =0;
                            break;

                    }

                    return true;
                }
            });*/

  /*          ImageView ivEdit = (ImageView)convertView.findViewById(R.id.ivEdit);
            ivEdit.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Card tmpCard = getItem(position);
                    CardsFragment cf =(CardsFragment) MainActivity.getInstance().getFragmentCards();
                    cf.startEditCard(tmpCard);

                }
            });

            ImageView ivDelete = (ImageView) convertView.findViewById(R.id.ivDelete);
            ivDelete.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    ImageView iv = (ImageView) v;
                    final Card card = getItem(position);
                    new AlertDialog.Builder(hostContext)
                            .setTitle("确认")
                            .setMessage("确定删除"+card.getAlias()+"吗？")
                            .setPositiveButton("是", new DialogInterface.OnClickListener() {
                                @Override
                                public void onClick(DialogInterface dialog, int which) {
                                    CardDAO dao = new CardDAO(MainActivity.getInstance().getDbHelper());
                                    if (dao.DeleteCard(card)) {
                                        cardList.remove(position);
                                        notifyDataSetChanged();

                                    }
                                }
                            })
                            .setNegativeButton("否",null)
                            .show();

                }
            });*/
        }




        return convertView;

    }
}
