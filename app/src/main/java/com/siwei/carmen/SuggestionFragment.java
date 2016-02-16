package com.siwei.carmen;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import org.joda.time.LocalDate;

import java.util.List;

/**
 * Created by wkd on 15-11-30.
 */
public class SuggestionFragment extends Fragment {
    TextView tvCardName;
    TextView tvCardIFP;
    TextView tvSuggestionTitle;
    View contentView;


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        contentView = inflater.inflate(R.layout.suggestion,container,false);
        tvCardName = (TextView)contentView.findViewById(R.id.tvCardName);
        tvCardIFP = (TextView)contentView.findViewById(R.id.tvCardIFP);
        tvSuggestionTitle = (TextView)contentView.findViewById(R.id.tvSuggestionTitle);


        
        return  contentView;
    }


    @Override
    public void onResume() {

        super.onResume();


        CardDAO dao = new CardDAO(MainActivity.getInstance().getDbHelper());
        List<Card> cards = dao.FetchCardList(null);


        Card usedCard = null;
        int maxIFP = 0;
        if(cards!=null) {
            for (Card item : cards) {
                int tmpIFP = item.getIFP(new LocalDate());
                if (tmpIFP > maxIFP) {
                    maxIFP = tmpIFP;
                    usedCard = item;
                }
            }
        }

        if(usedCard != null)
        {
            tvSuggestionTitle.setText("今日优先使用");
            tvCardName.setText(usedCard.getAlias());
            tvCardIFP.setText("免息期："+maxIFP+"天");
        }
        else
        {
            tvSuggestionTitle.setText("");
            tvCardName.setText("你还没有信用卡哦！");
            tvCardIFP.setText("");
        }

    }
}
