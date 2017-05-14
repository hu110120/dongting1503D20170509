package dongting.bwei.com.dongting1503d20170509;

import android.content.Context;
import android.widget.ListView;

import com.google.gson.Gson;

import org.xutils.common.Callback;
import org.xutils.http.RequestParams;
import org.xutils.x;

import java.util.List;



public class MyXutils {
    private Context context;
    private ListView mLv;
    private List<News.DataBean> mDatalist;

    public MyXutils(Context context, ListView lv) {
        this.context = context;
        mLv = lv;
    }

    public  void  getXutil(String uri){
        RequestParams params = new RequestParams(uri);

        x.http().get(params, new Callback.CommonCallback<String>() {
            public void onSuccess(String result) {
                // System.out.println(result);
                Gson gson=new Gson();
                News bean = gson.fromJson(result, News.class);
                mDatalist = bean.getData();
                mLv.setAdapter(new MyAdapter(mDatalist,context));
            }

            @Override
            public void onError(Throwable ex, boolean isOnCallback) {

            }

            @Override
            public void onCancelled(CancelledException cex) {

            }

            @Override
            public void onFinished() {

            }

        });
    }
}
