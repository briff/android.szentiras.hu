package hu.szentiras.android.daily;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import hu.szentiras.android.R;

/**
 * Fragment for actually display a reading
 */
public class ReadingFragment extends Fragment {

    private DailyReadingFragment.Lecture lecture;

    public ReadingFragment() {
    }

    public ReadingFragment(DailyReadingFragment.Lecture lecture) {
        this.lecture = lecture;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View result = inflater.inflate(R.layout.daily_reader, container, false);
        if (savedInstanceState != null) {
            lecture = savedInstanceState.getParcelable("lecture");
        }
        if (lecture != null) {
            TextView readingText = (TextView) result.findViewById(R.id.readingText);
            readingText.setText(lecture.getText());
        }
        return result;
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putParcelable("lecture", lecture);
    }

}
