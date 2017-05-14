package dongting.bwei.com.dongting1503d20170509;


import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import org.xutils.x;

import java.util.List;

/**
 * 作者:${董婷}
 * 日期:2017/5/9
 * 描述:
 */

public class MyAdapter extends BaseAdapter {
    private List<News.DataBean> mDatalist;
    private Context context;

    public MyAdapter(List<News.DataBean> mDatalist, Context context) {
        this.mDatalist = mDatalist;
        this.context = context;
    }

    @Override
    public int getCount() {
        return mDatalist.size();
    }

    @Override
    public Object getItem(int position) {
        return mDatalist.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final ViewHodler hodler;
        if (convertView==null){
            convertView =View.inflate(context, R.layout.list_item,null);
            hodler = new ViewHodler();
            hodler.mImageView = (ImageView) convertView.findViewById(R.id.image);
            hodler.text1 = (TextView) convertView.findViewById(R.id.text);
            hodler.text2 = (TextView) convertView.findViewById(R.id.text2);
            convertView.setTag(hodler);
        }else {
            hodler = (ViewHodler) convertView.getTag();
        }
        hodler.text1.setText(mDatalist.get(position).getNews_title());
        hodler.text2.setText(mDatalist.get(position).getNews_summary());
        x.image().bind(hodler.mImageView,mDatalist.get(position).getPic_url());
        return convertView;
    }
    class ViewHodler{
        ImageView mImageView;
        TextView text1,text2;
    }
}
