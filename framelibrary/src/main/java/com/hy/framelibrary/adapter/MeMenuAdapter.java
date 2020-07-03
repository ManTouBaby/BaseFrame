package com.hy.framelibrary.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hy.framelibrary.R;
import com.hy.framelibrary.bean.MeMenuData;

import java.util.ArrayList;
import java.util.List;

public class MeMenuAdapter extends RecyclerView.Adapter<BaseVH> {
    private List<MeMenuData> meMenuData;

    public MeMenuAdapter(List<MeMenuData> meMenuData) {
        this.meMenuData = meMenuData;
    }

    @Override
    public int getItemViewType(int position) {
        return meMenuData.get(position).itemType;
    }

    @Override
    public BaseVH onCreateViewHolder(ViewGroup parent, int viewType) {
        View view;
        if (viewType == MeMenuData.TYPE_ITEM) {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.frame_item_menu_type_item, null);
        } else {
            view = LayoutInflater.from(parent.getContext()).inflate(R.layout.frame_item_menu_type_title, null);
        }
        return new BaseVH(view);
    }

    @Override
    public void onBindViewHolder(BaseVH holder, int position) {
        MeMenuData menuData = meMenuData.get(position);
        switch (menuData.itemType) {
            case MeMenuData.TYPE_ITEM:
                holder.getImage(R.id.item_icon).setBackgroundResource(menuData.menuIcon);
                holder.getText(R.id.item_text).setText(menuData.menuTitle);
                holder.itemView.setOnClickListener(v -> {
                    if (mOnItemClickListener != null) {
                        mOnItemClickListener.onItemClick(menuData);
                    }
                });
                break;
            case MeMenuData.TYPE_TITLE:
                holder.getText(R.id.item_name).setText(menuData.menuTitle);
                break;
        }
    }

    @Override
    public int getItemCount() {
        return meMenuData.size();
    }

    private OnItemClickListener mOnItemClickListener;

    public void setOnItemClickListener(OnItemClickListener mOnItemClickListener) {
        this.mOnItemClickListener = mOnItemClickListener;
    }

    public interface OnItemClickListener {
        void onItemClick(MeMenuData meMenuData);
    }
}
