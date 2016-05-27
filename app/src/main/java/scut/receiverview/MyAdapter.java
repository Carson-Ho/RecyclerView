package scut.receiverview;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by yany on 2016/4/11.
 */
public class MyAdapter extends RecyclerView.Adapter {
    private LayoutInflater inflater;
    private ArrayList<HashMap<String, Object>> listItem;
    private MyItemClickListener myItemClickListener;

    public MyAdapter(Context context, ArrayList<HashMap<String, Object>> listItem) {
        inflater = LayoutInflater.from(context);
        this.listItem = listItem;
    }//构造函数，传入数据


    //定义Viewholder
    class Viewholder extends RecyclerView.ViewHolder  {
        private TextView Title, Text;
        private ImageView ima;

        public Viewholder(View root) {
            super(root);
            Title = (TextView) root.findViewById(R.id.Itemtitle);
            Text = (TextView) root.findViewById(R.id.Itemtext);
            ima = (ImageView) root.findViewById(R.id.ItemImage);
            root.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (myItemClickListener != null)
                        myItemClickListener .onItemClick(v,getPosition());
                }

            }//监听到点击就回调MainActivity的onItemClick函数
            );

        }

        public TextView getTitle() {
            return Title;
        }

        public TextView getText() {
            return Text;
        }

        public ImageView getIma() {
            return ima;
        }


    }

        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            return new Viewholder(inflater.inflate(R.layout.list_cell, null));
        }//在这里把ViewHolder绑定Item的布局

        @Override
        public void onBindViewHolder(RecyclerView.ViewHolder holder, final int position) {
            Viewholder vh = (Viewholder) holder;
            vh.Title.setText((String) listItem.get(position).get("ItemTitle"));
            vh.Text.setText((String) listItem.get(position).get("ItemText"));
            vh.ima.setImageResource((Integer) listItem.get(position).get("ItemImage"));
        }//在这里绑定数据到ViewHolder里面

        @Override
        public int getItemCount() {
            return listItem.size();
        }//返回Item数目

        public void setOnItemClickListener(MyItemClickListener listener){
        myItemClickListener = listener;
        }//绑定MainActivity传进来的点击监听器
}

