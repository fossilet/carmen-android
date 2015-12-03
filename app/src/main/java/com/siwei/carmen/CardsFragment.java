package com.siwei.carmen;

import android.content.Intent;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by wkd on 15-11-30.
 */
public class CardsFragment extends Fragment {

    private List<Card> cardList;
    private Button btnNewCard;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view  = inflater.inflate(R.layout.cards,container,false);
        initCards();
        CardAdapter adapter = new CardAdapter(view.getContext(),R.layout.card_item,cardList);
        ListView lvCards = (ListView) view.findViewById(R.id.lvCards);
        lvCards.setAdapter(adapter);

        btnNewCard = (Button) view.findViewById(R.id.btnNewCard);
        btnNewCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Card newCard = new Card("我的信用卡",1,25);


                Intent intent = new Intent(getContext(),CardManageActivity.class);
                intent.putExtra("newCard", (Serializable) newCard);
                startActivityForResult(intent,1);



            }
        });



        return view;
    }


    private void initCards(){
        //TODO: 将来从数据库中获取信用卡列表
        cardList = new ArrayList<Card>();
        cardList.add(new Card("工行行用卡",1,25));
        cardList.add(new Card("建行信用卡",10,30));
        cardList.add(new Card("兴业信用卡",5,28));
        cardList.add(new Card("招行信用卡",3,22));
        cardList.add(new Card("农行信用卡",4,27));


    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        Toast.makeText(getContext(),"返回",Toast.LENGTH_SHORT).show();
    }
}
