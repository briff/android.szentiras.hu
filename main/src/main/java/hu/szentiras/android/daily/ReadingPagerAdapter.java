package hu.szentiras.android.daily;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by Bertalan on 6/3/2014.
 */
public class ReadingPagerAdapter extends FragmentPagerAdapter {

    private int readingCount;

    public ReadingPagerAdapter(FragmentManager fm, DailyReadingFragment.Lectures lectures) {
        super(fm);
        if (lectures != null) {
            this.readingCount = lectures.getLectures().length;
        }
    }

    @Override
    public Fragment getItem(int position) {
        return new ReadingFragment();
    }

    @Override
    public int getCount() {
        return readingCount;
    }
}
