package tw.idv.holybible.expense;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

public class Page2Fragment extends Fragment {

    private int mPosition;

    public Page2Fragment() {

    }

    public static Fragment newInstance(int position) {
        Page2Fragment fragement = new Page2Fragment();
        Bundle args = new Bundle();
        args.putInt("position", position);
        fragement.setArguments(args);
        return fragement;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mPosition = getArguments() != null ? getArguments().getInt("position") : 2;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View page2 = inflater.inflate(R.layout.fragment_page2, container, false);
        return page2;
    }
}
