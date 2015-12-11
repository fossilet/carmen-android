package com.siwei.carmen;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by wkd on 15-11-30.
 */
public class CardsFragment extends Fragment {

    private Button btnNewCard;
    private CardAdapter mAdapter;
    private ListView lvCards;
    View contentView;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        contentView  = inflater.inflate(R.layout.cards,container,false);
        lvCards = (ListView) contentView.findViewById(R.id.lvCards);

        refreshCardList();

        btnNewCard = (Button) contentView.findViewById(R.id.btnNewCard);
        btnNewCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Card newCard = new Card("我的信用卡",1,25);


                Intent intent = new Intent(getContext(),CardManageActivity.class);
                intent.putExtra("NEWCARD", (Serializable) newCard);
                startActivityForResult(intent,1);



            }
        });

        return contentView;
    }

    /**
     * reload the cardlist and refresh listview.
     */
    private void refreshCardList(){
        //从数据库中获取信用卡列表
        CardDAO dao = new CardDAO(((MainActivity)this.getActivity()).getDbHelper());
        List<Card> cardList = dao.FetchCardList(null);
        mAdapter = new CardAdapter(contentView.getContext(),R.layout.card_item,cardList);
        lvCards.setAdapter(mAdapter);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        //Toast.makeText(getContext(),"返回",Toast.LENGTH_SHORT).show();
        switch (requestCode){
            case 1:  //CardManagerActivity go back to here.
                if(resultCode == getActivity().RESULT_OK){
                    refreshCardList();
                }
                break;
        }
    }
}
