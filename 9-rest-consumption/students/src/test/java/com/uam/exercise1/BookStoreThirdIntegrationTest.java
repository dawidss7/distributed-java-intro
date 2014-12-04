package com.uam.exercise1;

import java.util.concurrent.ThreadLocalRandom;

import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

import com.uam.Application;
import com.uam.model.Book;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
public class BookStoreThirdIntegrationTest {

	@Autowired
	private RestTemplate restTemplate;

	private String uniqueIsbn;
	private Book testBook;

	@Before
	public void setUp() {
		uniqueIsbn = Long.toString(ThreadLocalRandom.current().nextLong(Long.MAX_VALUE / 100, Long.MAX_VALUE));
		testBook = new Book(uniqueIsbn, "testTitle", "testDescription", "testAuthor");
	}

	@Test
	public void bookShouldNotBeAddedTwice() {
        try {
            restTemplate.getForObject(Book.URL.concat(uniqueIsbn), Book.class);
        }catch(HttpClientErrorException ex){
            Assertions.assertThat(ex.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
        }
        restTemplate.postForObject(Book.URL, testBook, Book.class);
        try {
            restTemplate.postForObject(Book.URL, testBook, Book.class);
        }catch(HttpClientErrorException ex){
            Assertions.assertThat(ex.getStatusCode()).isEqualTo(HttpStatus.CONFLICT);
        }
        restTemplate.delete(Book.URL.concat(uniqueIsbn));
        try {
            restTemplate.getForObject(Book.URL.concat(uniqueIsbn), Book.class);
        }catch(HttpClientErrorException ex){
            Assertions.assertThat(ex.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
        }
	}
}
