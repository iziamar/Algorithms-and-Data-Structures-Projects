import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;

public class Bookshelf {
   private Book[] books;
   private int size;
   private int nextEmpty;

   public Bookshelf() {
      size = 20;
      books = new Book[size];
      nextEmpty = 0;
   }

   public Bookshelf(int size) {
      this.size = size;
      books = new Book[size];
      nextEmpty = 0;
   }

   public boolean add(Book newBook) {
      if (nextEmpty < books.length) {
         books[nextEmpty] = newBook;
         nextEmpty++;
         return true;
      } else return false;
   }

   public Bookshelf getBooksByAuthor(String author) {
      Bookshelf authorBooks = new Bookshelf();
      for (int i = 0; i < books.length; i++) {
         if ((books[i].getAuthor()).equals(author)) {
            authorBooks.add(books[i]);
         }
      }
      return authorBooks;
   }

   public String toString() {
      String bookString = "";
      for (int i = 0; i < nextEmpty; i++) {
         bookString += (books[i].getTitle() + "," + books[i].getAuthor() + "," + books[i].getRating() + "\n");
      }
      return bookString;
   }

   public void sort(char sortBy) {
      int i, j;
      Book n;
      for (i = 1; i < nextEmpty; i++) {
         n = books[i];
         for (j = i - 1; j >= 0 && n.compareTo(books[j], sortBy) < 0; j--) {
            books[j + 1] = books[j];
         }
         books[j + 1] = n;

      }
   }
   public static Bookshelf readBooksFromFile(String fileName) {
      File f = new File(fileName);
      Scanner scanner;
      try {
         scanner = new Scanner(f);
      } catch (Exception e) {
         System.out.println(e);
         return null;
      }
      int i = 0;
      Bookshelf myBooks = new Bookshelf();
      while(scanner.hasNextLine()){
         String book = scanner.nextLine();
         String[] book1 = book.split(", ");
         String bookTitle = book1[0];
         String bookAuthor = book1[1];
         double bookRating = Double.parseDouble(book1[2]);
         myBooks.add(new Book(bookTitle, bookAuthor, bookRating));
         i++;
      }
      return myBooks;
   }
   public static void writeShelfToFile(Bookshelf b, String fileName) {
      Scanner s = null;
      PrintWriter p = null;
      try {
         p = new PrintWriter(new File(fileName));
         p.println(b);
         p.close();
      } catch (Exception e) {
         System.out.println(e);
         return;
      }
   }

   public static void main(String[] args) {
      Bookshelf bs = readBooksFromFile("bookinput.txt");
      bs.sort('r');
      System.out.println(bs);
      //writeShelfToFile(bs, "output.txt" );
   }
}
