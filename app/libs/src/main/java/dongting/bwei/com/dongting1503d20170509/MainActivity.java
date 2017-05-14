package dongting.bwei.com.dongting1503d20170509;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.view.View;
import android.widget.HorizontalScrollView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.google.gson.Gson;

import org.xutils.view.annotation.ContentView;
import org.xutils.view.annotation.ViewInject;
import org.xutils.x;

import java.util.ArrayList;
import java.util.List;



@ContentView(R.layout.activity_main)
public class MainActivity  extends FragmentActivity implements View.OnClickListener{

    private List<String> hor=new ArrayList<>();

    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
           /* if(msg.what==0){
               msg.obj;

               hor.add(list);
            }*/
        }
    };

    @ViewInject(R.id.texttitle)
    TextView textViewtitle;

    @ViewInject(R.id.horizontalscrollView)
    HorizontalScrollView horizontalScrollView;

    @ViewInject(R.id.titles)
    LinearLayout line;

    @ViewInject(R.id.viewpager)
    ViewPager viewPager;
    private News news;

    private String[]typeArray =new String[]{"gj","ss","cj","kj",
            "js","ty","yl","gn","shehui","tt"};

    private String[]titleArray = new String[]{"推荐","收藏","热点","视频","北京","","热点","热点","娱乐","体育", "科技","财经","军事"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState) ;
        //setContentView(R.layout.activity_main);

    //使用Xutils3.0中注解方式初始化View
        x.view().inject(this);

        //解析数据
        news = initData();

        //获取 HorizontalScrollView 的数据
              initHorizontalScrollView();

        initViewpagerIndicator();

        //初始化viewpager
        initviewpager();

        initLeftRight();
    }

    private void initviewpager() {
        viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                //获取fragmen并传参数
                return NewsFragment.getInstance(typeArray[position]);
            }

            @Override
            public int getCount() {
                return titleArray.length;
            }
        });
        viewPager.setOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                //让顶部指示器跟着动
                for(int i=0;i<line.getChildCount();i++){
                    View childAt = line.getChildAt(i);
                    TextView tv_title = (TextView) childAt.findViewById(R.id.tv_title);
                    TextView tv_indicator= (TextView) childAt.findViewById(R.id.tv_indicator);
                    if(i==position){
                        //切换颜色
                        tv_indicator.setVisibility(View.VISIBLE);
                        tv_title.setTextColor(Color.RED);
                        //点击条目时，viewpager切换页面
                        viewPager.setCurrentItem(i);
                    }else {
                        tv_indicator.setVisibility(View.GONE);
                        tv_title.setTextColor(Color.BLACK);
                    }
                }
                //拿到当前条目的位置,让HorizontalScrollView跟着动,
                line.scrollTo((int)line.getChildAt(position).getX(), 0);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

    }

    private News initData() {
        News news;
        try {
            String result = new MyAsync().execute().get();

            Gson gson =new Gson();
             news = gson.fromJson(result, News.class);

            return news;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    private void initHorizontalScrollView() {
        Message message = Message.obtain();
        message.what=0;
        message.obj=news.getData();

        handler.sendMessage(message);
    }

    @Override
    public void onClick(View v) {
        //字体颜色变，indicator显示隐藏，viewpager选中某一个对应的界面
        for(int i=0;i<line.getChildCount();i++){
            View childAt = line.getChildAt(i);
            TextView tv_title = (TextView) childAt.findViewById(R.id.tv_title);
            TextView tv_indicator= (TextView) childAt.findViewById(R.id.tv_indicator);
            if(v==childAt){
                //切换颜色
                tv_indicator.setVisibility(View.VISIBLE);
                tv_title.setTextColor(Color.RED);
                //点击条目时，viewpager切换页面
                viewPager.setCurrentItem(i);
            }else {
                tv_indicator.setVisibility(View.GONE);
                tv_title.setTextColor(Color.BLACK);
            }
        }
    }

    private void initViewpagerIndicator() {
        //定义布局参数，设置标题条目是包裹内容
        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT);
        //设置外边距
        layoutParams.setMargins(10,5,10,5);
        //动态的为线性布局添加view条目
        for (int i=0;i<hor.size();i++){
            View view = View.inflate(this, R.layout.title, null);
            TextView tv_title = (TextView) view.findViewById(R.id.tv_title);
            tv_title.setText(hor.get(i));
            TextView tv_indicator = (TextView) view.findViewById(R.id.tv_indicator);
            if(i==0){
                tv_title.setTextColor(Color.RED);
                tv_indicator.setVisibility(View.VISIBLE);
            }else {
                tv_title.setTextColor(Color.BLACK);
                tv_indicator.setVisibility(View.GONE);
            }
            view.setOnClickListener(this);
            line.addView(view,layoutParams);
        }
    }
    private void initLeftRight() {
        MenuLeftFragment menuLeftFragment=new MenuLeftFragment();
        setBehindContentView(R.layout.frame_leftmenu);
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_leftmenu_id,menuLeftFragment).commit();

        slidingMenu = getSlidingMenu();
        // 设置slidingmenu滑动的方式
        slidingMenu.setMode(SlidingMenu.LEFT_RIGHT);

        // 设置触摸屏幕的模式
        slidingMenu.setTouchModeAbove(SlidingMenu.TOUCHMODE_MARGIN);
        // 设置阴影的宽度
        slidingMenu.setShadowWidthRes(R.dimen.shadow_width);
        // 设置slidingmenu边界的阴影图片
        slidingMenu.setShadowDrawable(R.drawable.shadow);
        // 设置滑动菜单视图的宽度
        slidingMenu.setBehindOffsetRes(R.dimen.slidingmenu_offset);
        // 设置渐入渐出效果的值
        slidingMenu.setFadeDegree(0.35f);
        slidingMenu.setBackgroundResource(R.drawable.login_background_introduce);
        MenuRightFragment menuRightFragment=new MenuRightFragment();
        //设置右边（二级）侧滑菜单
        slidingMenu.setSecondaryMenu(R.layout.frame_rightmenu);
        getSupportFragmentManager().beginTransaction().replace(R.id.frame_rightmenu_id,menuRightFragment).commit();



    }
}