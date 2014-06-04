package hu.szentiras.android.daily;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import hu.szentiras.android.R;

/**
 * Created by Bertalan on 6/3/2014.
 */
public class ReadingFragment extends Fragment {

    private DailyReadingFragment.Lecture lecture;

    public ReadingFragment(DailyReadingFragment.Lecture lecture) {
        this.lecture = lecture;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View result = inflater.inflate(R.layout.daily_reader, container, false);
        WebView webView = (WebView) result.findViewById(R.id.webView);
        webView.loadData(lecture.getText(), "text/html; charset=UTF-8", null);
        return result;
    }
}
