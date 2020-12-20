package cat.itb.goodreads;

import androidx.lifecycle.ViewModel;

import java.util.ArrayList;
import java.util.List;

public class BookViewModel extends ViewModel {
    static List<Book> booksList = new ArrayList<Book>(){
        {
            add(new Book("The Maze Runner", "James Dashner", 2, -1));
            add(new Book("Mistborn: The Final Empire", "Brandon Sanderson", 0, -1));
            add(new Book("El nombre del viento", "Patrick Rothfuss", 1, 5));
            add(new Book("Mort", "Terry Pratchet", 1, 5));
            add(new Book("Las aventuras de Tom Sawyer", "Mark Twain", 2, -1));
            add(new Book("The Hunger Games", "Suzanne Collins", 0, -1));
            add(new Book("Divergente", "Veronica Roth", 2, -1));
            add(new Book("Battle Royale", "Koushun Takami", 2, -1));
        }
    };

    public BookViewModel(){

    }
    public void add(Book b){
        booksList.add(b);
    }
    public void set(int position, Book book){
        booksList.set(position, book);
    }




}
