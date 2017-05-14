package dongting.bwei.com.dongting1503d20170509;

import android.os.AsyncTask;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * 作者:${董婷}
 * 日期:2017/5/9
 * 描述:异步任务类
 */

public class MyAsync extends AsyncTask<Object,Void,String> {

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
    }

    @Override
    protected String doInBackground(Object... params) {
        try {
            URL getUrl = new URL("http://api.expoon.com/AppNews/getNewsList/type/1/p/1");
            HttpURLConnection connection = (HttpURLConnection) getUrl.openConnection();

            connection.setRequestMethod("GET");
            connection.setConnectTimeout(5000);
            connection.setReadTimeout(5000);

            InputStream inputStream = connection.getInputStream();
            ByteArrayOutputStream bo = new ByteArrayOutputStream();

            int len = 0;
            byte[] buffer = new byte[1024];
            while ((len = inputStream.read(buffer)) != -1) {
                bo.write(buffer, 0, len);
            }
            return bo.toString();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
    @Override
    protected void onPostExecute(String s) {

    }
}
