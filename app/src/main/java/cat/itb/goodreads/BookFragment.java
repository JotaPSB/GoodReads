package cat.itb.goodreads;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;

public class BookFragment extends Fragment {
    EditText editTextTitle;
    EditText editTextAuthor;
    RatingBar ratingBar;
    Spinner spinner;
    Button add;
    Button update;
    TextView statusText;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.book_fragment ,container,false);

        assert getArguments() != null;
        final Bundle bundle = getArguments();
        final Book book = bundle.getParcelable("book");
        editTextTitle = v.findViewById(R.id.editTextTitle);
        editTextAuthor = v.findViewById(R.id.editTextAuthor);
        ratingBar = v.findViewById(R.id.rating_bar);
        spinner = v.findViewById(R.id.spinner);
        add = v.findViewById(R.id.add_button);

        update = v.findViewById(R.id.update_button);
        statusText = v.findViewById(R.id.textView5);
        final ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(v.getContext(),R.array.status,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);
        if (book!=null){
            editTextTitle.setText(book.getTitle());
            editTextAuthor.setText(book.getAuthor());
            spinner.setSelection(book.getStatus());
            if (book.getGrade()!=-1){
                ratingBar.setRating((float) book.getGrade());
            }
            update.setVisibility(View.VISIBLE);
            add.setVisibility(View.INVISIBLE);

        }else{
            update.setVisibility(View.INVISIBLE);
            add.setVisibility(View.VISIBLE);
        }

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(position==1){
                    ratingBar.setVisibility(View.VISIBLE);
                    statusText.setVisibility(View.VISIBLE);
                }else {
                    ratingBar.setVisibility(View.INVISIBLE);
                    statusText.setVisibility(View.INVISIBLE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                double grade;
                String title = editTextTitle.getText().toString();
                String author = editTextAuthor.getText().toString();

                if (!title.isEmpty() && !author.isEmpty()){
                    if (spinner.getSelectedItemPosition() == 1) {
                        grade = ratingBar.getRating();
                    } else {
                        grade = -1;
                    }
                    Book book = new Book(title,author,spinner.getSelectedItemPosition(),grade);
                    BookViewModel.booksList.add(book);
                    NavDirections fragmentToList = BookFragmentDirections.actionFragmentToList();
                    Navigation.findNavController(v).navigate(fragmentToList);
                }
            }
        });
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = editTextTitle.getText().toString();
                String author = editTextAuthor.getText().toString();
                int position = bundle.getInt("position");
                if (!title.isEmpty() && !author.isEmpty()){
                    int status = spinner.getSelectedItemPosition();
                    float grade;
                    if(status==1){
                        grade = ratingBar.getRating();
                    }else {
                        grade = -1;
                    }
                    BookViewModel.booksList.set(position, new Book(title,author,status,grade));
                }
                NavDirections fragmentToList = BookFragmentDirections.actionFragmentToList();
                Navigation.findNavController(v).navigate(fragmentToList);
            }
        });
        return v;
    }
}
