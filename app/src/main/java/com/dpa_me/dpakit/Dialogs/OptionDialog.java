package com.dpa_me.dpakit.Dialogs;

import android.app.Activity;
import android.app.Dialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.widget.TextView;

import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.dpa_me.dpakit.Adapter.SimpleListAdapter;
import com.dpa_me.dpakit.Models.SimpleModel;
import com.dpa_me.dpakit.R;

import java.util.ArrayList;

public class OptionDialog extends Dialog {
    private RecyclerView doRcvMain;
    private ArrayList<SimpleModel> mList;
    private Activity mActivity;
    private TextView doTxtTitle;
    private String mTitle = "";
    private int mActiveIndex = -1;

    public interface IOpration {
        void onItemClick(SimpleModel model);
    }

    private IOpration onItemClick = null;

    public OptionDialog setOnItemClick(IOpration onOpration) {
        this.onItemClick = onOpration;
        return this;
    }

    public OptionDialog setTitle(String title) {
        this.mTitle = title;
        return this;
    }

    public OptionDialog setActiveIndex(int activeIndex) {
        this.mActiveIndex = activeIndex;
        return this;
    }

    public OptionDialog(Activity activity, ArrayList<SimpleModel> list) {
        super(activity);
        mActivity = activity;
        mList = list;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.dialog_option);
        getWindow().getAttributes().windowAnimations = R.style.DialogAnimation;
        getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        initView();

        doTxtTitle.setText(mTitle);
        if (!mTitle.equals(""))
            doTxtTitle.setVisibility(View.VISIBLE);

        if (mActiveIndex > -1)
            mList.get(mActiveIndex).setSelected(true);

        doRcvMain.setAdapter(new SimpleListAdapter(mActivity, mList, R.layout.simple_item_large, true)
                .setOnItemClick(new SimpleListAdapter.IOpration() {
                    @Override
                    public void onItemClick(SimpleModel model) {
                        if (onItemClick != null)
                            onItemClick.onItemClick(model);
                        dismiss();
                    }
                }));
        doRcvMain.setLayoutManager(new GridLayoutManager(mActivity, 1));
    }

    private void initView() {
        doRcvMain = findViewById(R.id.do_rcv_main);
        doTxtTitle = findViewById(R.id.do_txt_title);
    }
}
