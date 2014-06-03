package hu.szentiras.android.daily;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
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

    public DailyReadingFragment() {

    }


    public DailyReadingFragment(Lectures lectures) {
        this.lectures = lectures;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_daily_reading, container, false);
        ViewPager pager = (ViewPager) view.findViewById(R.id.pager);
        if (savedInstanceState != null) {
            lectures = savedInstanceState.getParcelable("lectures");
        }
        pager.setAdapter(new ReadingPagerAdapter(getChildFragmentManager(), lectures));

        return view;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable("lectures", lectures);
    }

    public static class Lectures implements Parcelable {
        private Lecture[] lectures;

        public Lecture[] getLectures() {
            return lectures;
        }

        public void setLectures(Lecture[] lectures) {
            this.lectures = lectures;
        }

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeArray(lectures);
        }

        public static final Parcelable.Creator<Lectures> CREATOR = new Parcelable.Creator<Lectures>() {
            @Override
            public Lectures createFromParcel(Parcel source) {
                Lectures lectures = new Lectures();
                lectures.setLectures((Lecture[]) source.readArray(ClassLoader.getSystemClassLoader()));
                return lectures;
            }

            @Override
            public Lectures[] newArray(int size) {
                return new Lectures[size];
            }
        };
    }

    public static class Lecture implements Parcelable {
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

        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel dest, int flags) {
            dest.writeString(text);
            dest.writeString(ref);
        }

        public static final Parcelable.Creator<Lecture> CREATOR = new Parcelable.Creator<Lecture>() {
            public Lecture createFromParcel(Parcel in) {
                Lecture lecture = new Lecture();
                lecture.setText(in.readString());
                lecture.setRef(in.readString());
                return lecture;
            }

            @Override
            public Lecture[] newArray(int size) {
                return new Lecture[size];
            }

        };

    }
}
