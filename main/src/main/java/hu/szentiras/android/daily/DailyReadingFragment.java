package hu.szentiras.android.daily;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import hu.szentiras.android.R;

/**
 * Created by Bertalan on 5/30/2014.
 */
public class DailyReadingFragment extends Fragment {

    private Lectures lectures;

    public DailyReadingFragment(Lectures lectures) {
        this.lectures = lectures;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_daily_reading, container, false);
        ViewPager pager = (ViewPager) view.findViewById(R.id.pager);
        pager.setAdapter(new ReadingPagerAdapter(getChildFragmentManager(), lectures));
        return view;
    }

    public static class Lectures {
        private Lecture[] lectures;

        public Lecture[] getLectures() {
            return lectures;
        }

        public void setLectures(Lecture[] lectures) {
            this.lectures = lectures;
        }
    }

    public static class Lecture {
        private String text;
        private String ref;

        public String getText() {
            return text;
        }

        public void setText(String text) {
            this.text = text;
        }

        public String getRef() {
            return ref;
        }

        public void setRef(String ref) {
            this.ref = ref;
        }
    }

}
