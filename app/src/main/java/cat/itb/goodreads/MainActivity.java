package cat.itb.goodreads;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import android.os.Bundle;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Fragment bookListFragment =  getSupportFragmentManager().findFragmentById(R.id.nav_host_fragment_container);

        if (bookListFragment == null) {
            bookListFragment = new BookListFragment();
            getSupportFragmentManager().beginTransaction().add(R.id.nav_host_fragment_container, bookListFragment).commit();
        }
    }
}