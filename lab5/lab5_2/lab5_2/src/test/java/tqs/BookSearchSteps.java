package tqs;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.ParameterType;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
 
 
public class BookSearchSteps {
	Library library = new Library();
	List<Book> result = new ArrayList<>();
 
    @ParameterType("([0-9]{4})-([0-9]{2})-([0-9]{2})|[0-9]{4}")
	public LocalDateTime iso8601Date(String year, String month, String day){
		return LocalDateTime.of(Integer.parseInt(year), Integer.parseInt(month), Integer.parseInt(day),0, 0);
	}

	@Given("a book with the title {string}, written by {string}, published in {iso8601Date}")
	public void addNewBook(final String title, final String author, LocalDateTime published) {
		Book book = new Book(title, author, published);
		library.addBook(book);
	}

    @Given("another book with the title {string}, written by {string}, published in {iso8601Date}")
	public void addNewBook2(final String title, final String author, LocalDateTime published) {
		Book book = new Book(title, author, published);
		library.addBook(book);
	}

    @Given("I have the following books in store")
    public void addBooks(DataTable table){
        table.asMaps().forEach( data -> {
            Book book = new Book(data.get("title"), data.get("author"), data.get("date"));
		    library.addBook(book);
        } );
    }
 
	@When("the customer searches for books published between {iso8601Date} and {iso8601Date}")
	public void setSearchParameters(LocalDateTime from, LocalDateTime to) {
		result = library.findBooks(from, to);
	}
    

    @When("the customer searches for books by {string}")
    public void the_customer_searches_for_books_by(String author) {
        result = library.findBooksByAuthor(author);
    }

    @When("the customer searches for books titled {string}")
    public void searchByTitle(String title){
        result = library.findBooksByTitle(title);
    }

	@Then("{int} books should have been found")
	public void verifyAmountOfBooksFound(final int booksFound) {
		assertThat(result.size(), equalTo(booksFound));
	}
 
	@Then("Book {int} should have the title {string}")
	public void verifyBookAtPosition(final int position, final String title) {
		assertThat(result.get(position - 1).getTitle(), equalTo(title));
	}

}
