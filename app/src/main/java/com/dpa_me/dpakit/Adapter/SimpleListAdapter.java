package com.dpa_me.dpakit.Adapter;

import android.app.Activity;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.dpa_me.dpakit.Models.SimpleModel;
import com.dpa_me.dpakit.R;

import java.util.ArrayList;
import java.util.List;

import static com.dpa_me.dpakit.Units.HandleUnit.HandleApplication.GetResourceID;

public class SimpleListAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> implements Filterable {
    private ArrayList<SimpleModel> data;
    private ArrayList<SimpleModel> OrginalList;
    private Activity mActivity;
    private int mLayout;
    private boolean showSelected;

    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence charSequence) {
                String charString = charSequence.toString();
                if (charString.isEmpty()) {
                    data = OrginalList;
                } else {
                    ArrayList<SimpleModel> filteredList = new ArrayList<>();
                    for (SimpleModel row : OrginalList) {
                        if (row.getTitle().toLowerCase().contains(charString.toLowerCase())) {
                            filteredList.add(row);
                        }
                    }
                    data = filteredList;
                }

                FilterResults filterResults = new FilterResults();
                filterResults.values = OrginalList;
                return filterResults;
            }

            @Override
            protected void publishResults(CharSequence charSequence, FilterResults filterResults) {
                OrginalList = (ArrayList<SimpleModel>) filterResults.values;
                notifyDataSetChanged();
            }
        };
    }

    public interface IOpration {
        void onItemClick(SimpleModel model);
    }

    private IOpration onItemClick = null;

    public SimpleListAdapter setOnItemClick(IOpration onOpration) {
        this.onItemClick = onOpration;
        return this;
    }

    public SimpleListAdapter(Activity activity, ArrayList<SimpleModel> info) {
        this.data = new ArrayList<>();
        data.addAll(info);
        OrginalList = new ArrayList<>();
        OrginalList.addAll(info);
        mActivity = activity;
        mLayout = R.layout.item_simple_item;
        showSelected = false;
    }

    public SimpleListAdapter(Activity activity, ArrayList<SimpleModel> info, int layout) {
        this.data = new ArrayList<>();
        data.addAll(info);
        OrginalList = new ArrayList<>();
        OrginalList.addAll(info);
        mActivity = activity;
        mLayout = layout;
        showSelected = false;
    }

    public SimpleListAdapter(Activity activity, ArrayList<SimpleModel> info, int layout, boolean showSelected) {
        this.data = new ArrayList<>();
        data.addAll(info);
        OrginalList = new ArrayList<>();
        OrginalList.addAll(info);
        mActivity = activity;
        mLayout = layout;
        this.showSelected = showSelected;
    }

    public void Refresh(List<SimpleModel> info) {
        data.clear();
        data.addAll(info);
        notifyDataSetChanged();
    }

    public void AddData(List<SimpleModel> info) {
        data.addAll(info);
        notifyDataSetChanged();
    }

    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = ((LayoutInflater) mActivity.getSystemService(Context.LAYOUT_INFLATER_SERVICE)).
                inflate(mLayout, parent, false);
        return new ItemView(view);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ((ItemView) holder).SetBindData(data.get(position));
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class ItemView extends RecyclerView.ViewHolder {
        ImageView isi_img_image;
        TextView isi_txt_caption;
        TextView isi_txt_detail;
        View MyView;

        ItemView(View itemView) {
            super(itemView);

            isi_img_image = itemView.findViewById(R.id.isi_img_image);
            isi_txt_caption = itemView.findViewById(R.id.isi_txt_caption);
            isi_txt_detail = itemView.findViewById(R.id.isi_txt_detail);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onItemClick != null)
                        onItemClick.onItemClick(data.get(getAdapterPosition()));
                }
            });

            MyView = itemView;
        }

        void SetBindData(final SimpleModel info) {
            if (info.getDrawable() != null) {
                Glide.with(mActivity)
                        .load(mActivity.getResources().getDrawable(GetResourceID(mActivity, info.getDrawable(), "drawable")))
                        .into(isi_img_image);

                isi_img_image.setVisibility(View.VISIBLE);
            } else isi_img_image.setVisibility(View.GONE);

            if (isi_txt_detail != null) {
                if (info.getDetail_info().equals(""))
                    isi_txt_detail.setVisibility(View.GONE);
                else
                    isi_txt_detail.setVisibility(View.VISIBLE);

                isi_txt_detail.setText(info.getDetail_info());
            }

            isi_txt_caption.setText(info.getTitle());

            if (info.getColor() > 0)
                isi_txt_caption.setTextColor(mActivity.getResources().getColor(info.getColor()));

            if (showSelected && info.isSelected())
                isi_txt_caption.setTextColor(mActivity.getResources().getColor(R.color.colorPrimary));
        }
    }
}