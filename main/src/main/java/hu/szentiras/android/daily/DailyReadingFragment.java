package hu.szentiras.android.daily;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import hu.szentiras.android.MainActivity;
import hu.szentiras.android.R;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

/**
 * Fragment to display all daily readings.
 */
public class DailyReadingFragment extends Fragment {

    private Lectures lectures;

    public DailyReadingFragment() {

    }


    public DailyReadingFragment(Lectures lectures) {
        this.lectures = lectures;
    }

    private class HttpRequestTask extends AsyncTask<Void, Void, Lectures> {

        @Override
        protected Lectures doInBackground(Void... params) {
            try {
                String url = "http://" + getString(R.string.server_host) + "/api/lectures";
                RestTemplate restTemplate = new RestTemplate();
                restTemplate.getMessageConverters().add(new MappingJackson2HttpMessageConverter());
                Lectures lectures = restTemplate.getForObject(url, Lectures.class);
                return lectures;
            } catch (Exception e) {
                Log.e("MainActivity", e.getMessage(), e);
            }

            return null;
        }

        @Override
        protected void onPostExecute(Lectures lectures) {
            updateWithLectures(lectures);
        }

    }

    private void updateWithLectures(Lectures lectures) {
        View progressIndicator = getView().findViewById(R.id.progressIndicatorFrame);
        progressIndicator.setVisibility(View.GONE);
        ViewPager pager = (ViewPager) getView().findViewById(R.id.pager);
        pager.setAdapter(new ReadingPagerAdapter(getChildFragmentManager(), lectures));
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view;
        view = inflater.inflate(R.layout.fragment_daily_reading, container, false);
        return view;
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (savedInstanceState != null) {
            lectures = savedInstanceState.getParcelable("lectures");
        }
        if (lectures == null) {
            new HttpRequestTask().execute();
        } else {
            updateWithLectures(lectures);
        }
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

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        ((MainActivity) activity).onSectionAttached(1);
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
