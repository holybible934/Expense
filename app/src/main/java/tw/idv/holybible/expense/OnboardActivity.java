package tw.idv.holybible.expense;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

public class OnboardActivity extends FragmentActivity {

    private static final int ITEMS = 4;

    MyAdapter fragAdapter;
    ViewPager mPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_onboard);

        fragAdapter = new MyAdapter(getSupportFragmentManager());
        mPager = findViewById(R.id.pager);
        mPager.setAdapter(fragAdapter);
    }

    public static class MyAdapter extends FragmentPagerAdapter {

        public MyAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                case 2:
                default:
                    return Page1Fragment.newInstance(position);
                case 1:
                case 3:
                    return Page2Fragment.newInstance(position);
            }
        }

        @Override
        public int getCount() {
            return ITEMS;
        }
    }
}
