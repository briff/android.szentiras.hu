package hu.szentiras.android.daily;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

/**
 * Created by Bertalan on 6/3/2014.
 */
public class ReadingPagerAdapter extends FragmentPagerAdapter {

    private DailyReadingFragment.Lectures lectures;

    public ReadingPagerAdapter(FragmentManager fm, DailyReadingFragment.Lectures lectures) {
        super(fm);
        this.lectures = lectures;
    }

    @Override
    public Fragment getItem(int position) {
        return new ReadingFragment(lectures.getLectures()[position]);
    }

    @Override
    public int getCount() {
        if (lectures != null) {
            return lectures.getLectures().length;
        } else {
            return 0;
        }
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return lectures.getLectures()[position].getRef();
    }
}
