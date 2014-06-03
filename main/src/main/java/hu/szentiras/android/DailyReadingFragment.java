package hu.szentiras.android;

import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

/**
 * Created by Bertalan on 5/30/2014.
 */
public class DailyReadingFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_daily_reading, container, false);
        ViewPager pager = (ViewPager) view.findViewById(R.id.pager);
        pager.setAdapter(buildAdapter());

        return view;
    }

    private PagerAdapter buildAdapter() {
        return (new SampleAdapter(getActivity(), getChildFragmentManager()));
    }

    public static class SampleAdapter extends FragmentPagerAdapter {
        Context ctxt = null;

        public SampleAdapter(Context ctxt, FragmentManager mgr) {
            super(mgr);
            this.ctxt = ctxt;
        }

        @Override
        public int getCount() {
            return (10);
        }

        @Override
        public Fragment getItem(int position) {
            return (EditorFragment.newInstance(position));
        }

        @Override
        public String getPageTitle(int position) {
            return (EditorFragment.getTitle(ctxt, position));
        }
    }

    public static class EditorFragment extends Fragment {
        private static final String KEY_POSITION = "position";

        static EditorFragment newInstance(int position) {
            EditorFragment frag = new EditorFragment();
            Bundle args = new Bundle();

            args.putInt(KEY_POSITION, position);
            frag.setArguments(args);

            return (frag);
        }

        static String getTitle(Context ctxt, int position) {
            return (String.format(ctxt.getString(R.string.hint), position + 1));
        }

        @Override
        public View onCreateView(LayoutInflater inflater,
                                 ViewGroup container,
                                 Bundle savedInstanceState) {
            View result = inflater.inflate(R.layout.daily_reader, container, false);

            new HttpRequestTask(result).execute();

            return result;
        }
    }

    private static class HttpRequestTask extends AsyncTask<Void, Void, Lectures> {

        private View view;

        public HttpRequestTask(View view) {

            this.view = view;
        }

        @Override
        protected Lectures doInBackground(Void... params) {
            try {
                String url = ("http://10.0.2.2/api/lectures");
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
            WebView webView = (WebView) view.findViewById(R.id.webView);
            webView.loadData(lectures.lectures[0].getText(), "text/html", "UTF-8");
        }

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
