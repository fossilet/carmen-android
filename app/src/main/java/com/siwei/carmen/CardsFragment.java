package com.siwei.carmen;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.media.Image;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by wkd on 15-11-30.
 */
public class CardsFragment extends Fragment {

    private CardAdapter mAdapter;
    private ListView lvCards;
    private ImageView ivAdd;
    View contentView;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        contentView  = inflater.inflate(R.layout.cards,container,false);
        lvCards = (ListView) contentView.findViewById(R.id.lvCards);
        lvCards.setOnCreateContextMenuListener(this);

        refreshCardList();

        ivAdd = (ImageView) contentView.findViewById(R.id.ivAdd);
        ivAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Card newCard = new Card(-1, "我的信用卡", 1, 25);
                Intent intent = new Intent(getActivity(), CardManageActivity.class);
                intent.putExtra("NEWCARD", (Serializable) newCard);
                startActivityForResult(intent, 1);
            }
        });



        return contentView;
    }

    public void startEditCard(Card pEditCard){
        Intent intent = new Intent(getActivity(),CardManageActivity.class);
        intent.putExtra("EDITCARD", (Serializable)pEditCard);
        startActivityForResult(intent, 1);
    }
    /**
     * reload the cardlist and refresh listview.
     */
    private void refreshCardList(){
        //从数据库中获取信用卡列表
        CardDAO dao = new CardDAO(((MainActivity)this.getActivity()).getDbHelper());
        List<Card> cardList = dao.FetchCardList(null);
        mAdapter = new CardAdapter(this.getActivity(),R.layout.card_item,cardList);
        lvCards.setAdapter(mAdapter);

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        //Toast.makeText(getContext(),"返回",Toast.LENGTH_SHORT).show();
        switch (requestCode) {
            case 1:  //CardManagerActivity go back to here.
                if (resultCode == getActivity().RESULT_OK) {
                    refreshCardList();
                }
                break;
        }
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {

        super.onCreateContextMenu(menu, v, menuInfo);
        menu.add(0, 0, 0, "删除").setIcon(R.drawable.trash);
        menu.add(0, 1, 1, "编辑").setIcon(R.drawable.pencil);
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {
        Log.d("TEST", item.getTitle().toString());
        Log.d("TEST",item.getItemId()+"");
        if(item.getMenuInfo() instanceof AdapterView.AdapterContextMenuInfo) {
            AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo)item.getMenuInfo();
            Log.d("TEST", info.position + "--");
            final Card c =(Card) lvCards.getAdapter().getItem(info.position);
            if(item.getItemId() == 1){
                this.startEditCard(c);
            }
            if(item.getItemId()==0){
                new AlertDialog.Builder(this.getActivity())
                        .setTitle("确认")
                        .setMessage("确定删除"+c.getAlias()+"吗？")
                        .setPositiveButton("是", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                CardDAO dao = new CardDAO(MainActivity.getInstance().getDbHelper());
                                if (dao.DeleteCard(c)) {
                                    refreshCardList();
                                }
                            }
                        })
                        .setNegativeButton("否",null)
                        .show();
            }
        }
        return super.onContextItemSelected(item);
    }
}
