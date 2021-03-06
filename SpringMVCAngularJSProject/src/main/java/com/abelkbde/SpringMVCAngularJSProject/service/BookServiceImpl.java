package com.abelkbde.SpringMVCAngularJSProject.service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.atomic.AtomicLong;

import org.springframework.stereotype.Service;

import com.abelkbde.SpringMVCAngularJSProject.model.Book;

@Service("bookService")
public class BookServiceImpl implements BookService {

	private static final AtomicInteger counter = new AtomicInteger();

	private static List<Book> books;

	static {
		books = populateDummyBooks();
	}

	@Override
	public Book findBookById(Integer id) {
		for (Book book : books) {
			if (book.getId().equals(id)) {
				return book;
			}
		}
		return null;
	}

	@Override
	public Book findBookByTitle(String title) {
		for (Book book : books) {
			if (book.getTitle().equalsIgnoreCase(title)) {
				return book;
			}
		}
		return null;
	}

	@Override
	public void saveBook(Book book) {
		book.setId(counter.incrementAndGet());
		books.add(book);

	}

	@Override
	public void updateBook(Book book) {
		int index = books.indexOf(book);
		books.set(index, book);

	}

	@Override
	public void deleteBookById(Integer id) {
		for(Iterator<Book> iterator = books.iterator(); iterator.hasNext();) {
			Book book = iterator.next();
			if(book.getId().equals(id)) {
				iterator.remove();
			}
		}
		
		}

	@Override
	public List<Book> findAllBooks() {
		return books;
	}

	@Override
	public void deleteAllBooks() {
		books.clear();

	}

	@Override
	public boolean isBookExist(Book book) {
		return (findBookByTitle(book.getTitle()) != null);
	}

	private static List<Book> populateDummyBooks() {
		List<Book> books = new ArrayList<Book>();
		books.add(new Book(counter.incrementAndGet(), "Les Miserables", "Victor Hugo"));
		books.add(new Book(counter.incrementAndGet(), "Ana Kerenina", "Leo Tolstoy"));
		books.add(new Book(counter.incrementAndGet(), "Crime and Punishment", "Fyodor Dostoevsky"));
		books.add(new Book(counter.incrementAndGet(), "Atlas Shrugged", "Ayn Rand"));

		return books;
	}

}
