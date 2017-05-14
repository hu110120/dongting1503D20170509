package dongting.bwei.com.dongting1503d20170509;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import java.util.List;

/**
 * 作者:${董婷}
 * 日期:2017/5/9
 * 描述:
 */

public class NewsFragment extends Fragment {
    private View mView;
    String path = "http://api.expoon.com/AppNews/getNewsList/type/1/p/1";
    private ListView mLv;
    private List<News.DataBean> mDatalist;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mView = inflater.inflate(R.layout.fragment, null);
        initview();
        return mView;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        //获取传过来的数据
        String type = getArguments().getString("type");
        String url = path + type;
        MyXutils myXutils = new MyXutils(getActivity(), mLv);
        myXutils.getXutil(url);
    }

    private void initview() {
        mLv = (ListView) mView.findViewById(R.id.listview);
    }

    public static NewsFragment getInstance(String type){
        NewsFragment newsFragment = new NewsFragment();
        Bundle bundle = new Bundle();
        bundle.putString("type",type);
        newsFragment.setArguments(bundle);
        return newsFragment;
    }
}
