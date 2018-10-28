package tw.idv.holybible.expense;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;

import java.util.ArrayList;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;


public class OnboardActivity extends FragmentActivity {

    private static final int ITEMS = 4;

    MyAdapter fragAdapter;
    ViewPager mPager;
    private static ArrayList<ImageView> mDots;
    private LinearLayout mIndicator;
    private FrameLayout mIndicatorsContainer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onboard);

        fragAdapter = new MyAdapter(getSupportFragmentManager());
        mPager = findViewById(R.id.pager);
        mPager.setAdapter(fragAdapter);

        mIndicatorsContainer = findViewById(R.id.indicators_container);
        mIndicator = (LinearLayout) View.inflate(this, R.layout.indicator, null);
        mIndicatorsContainer.addView(mIndicator);
        mDots = new ArrayList<>();
        for (int i = 0; i < ITEMS; i++) {
            ImageView dot = new ImageView(this);
            dot.setImageDrawable(getDrawable(R.drawable.indicator_dot_grey));
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT
            );
            mIndicator.addView(dot, params);
            mDots.add(dot);
        }
    }

    public class MyAdapter extends FragmentPagerAdapter {

        MyAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return PageFragment.newInstance(position);
        }

        @Override
        public void setPrimaryItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
            for (int i = 0; i < ITEMS; i++) {
                if (i == position) {
                    mDots.get(i).setImageDrawable(getDrawable(R.drawable.indicator_dot_red));
                } else {
                    mDots.get(i).setImageDrawable(getDrawable(R.drawable.indicator_dot_grey));
                }
            }
            super.setPrimaryItem(container, position, object);
        }

        @Override
        public int getCount() {
            return ITEMS;
        }
    }

}
