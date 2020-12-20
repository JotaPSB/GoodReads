package cat.itb.goodreads;

import android.annotation.SuppressLint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.navigation.NavDirections;
import androidx.navigation.Navigation;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.BookHolder> {

    List<Book> books;
    View.OnClickListener onClickListener;
    public BookAdapter(List<Book> books) {
        this.books = books;

    }

    public void setBooks(List<Book> books) {
        this.books = books;
        notifyDataSetChanged();
    }
    @NonNull
    @Override
    public BookHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.book_list_fragment_item,parent, false);
        return new BookHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull BookHolder holder, int position) {
        holder.bindData(books.get(position));
    }

    @Override
    public int getItemCount() {
        return books.size();
    }

    class BookHolder extends RecyclerView.ViewHolder{
        TextView bookTitle;
        TextView bookAuthor;
        TextView bookStatus;
        TextView bookGrade;
        String[] status = new String[]{
                "reading",
                "read",
                "want to read"
        };
        public BookHolder(@NonNull View itemView) {
            super(itemView);
            bookTitle = itemView.findViewById(R.id.this_book_title);
            bookAuthor = itemView.findViewById(R.id.this_book_author);
            bookStatus = itemView.findViewById(R.id.this_book_status);
            bookGrade = itemView.findViewById(R.id.this_book_grade);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    NavDirections listToDetailDirections =  BookListFragmentDirections.actionListToFragment(books.get(getAdapterPosition()), getAdapterPosition());
                    Navigation.findNavController(v).navigate(listToDetailDirections);
                }
            });
        }

        @SuppressLint("SetTextI18n")
        public  void bindData(Book book){
            if(book != null){
                bookTitle.setText(book.getTitle());
                bookAuthor.setText(book.getAuthor());
                bookStatus.setText(status[book.getStatus()]);
                if(book.getGrade()>-1){
                    bookGrade.setVisibility(View.VISIBLE);
                    bookGrade.setText(book.getGrade()+"/5");
                }
            }
        }
    }

}
