package cat.itb.goodreads;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;

public class BookListFragment extends Fragment {
    RecyclerView recyclerView;
    BookViewModel bookModel;
    List<Book> bookList;
    BookAdapter adapter;
    FloatingActionButton floatingActionButton;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        bookModel = new ViewModelProvider(getActivity()).get(BookViewModel.class);
        bookList = BookViewModel.booksList;
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.book_list_fragment, container, false);

        recyclerView = v.findViewById(R.id.recycler_view);
        adapter = new BookAdapter(BookViewModel.booksList);
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        recyclerView.setAdapter(adapter);
        floatingActionButton = v.findViewById(R.id.floatingActionButton);
        floatingActionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                NavDirections listToDetailDirections =  BookListFragmentDirections.actionListToFragment(null, -1);
                Navigation.findNavController(v).navigate(listToDetailDirections);
            }
        });

        return v;
    }


}
