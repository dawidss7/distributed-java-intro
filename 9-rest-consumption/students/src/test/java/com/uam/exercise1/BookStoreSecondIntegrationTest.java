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
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

import com.uam.Application;
import com.uam.model.Book;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
public class BookStoreSecondIntegrationTest {

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
	public void bookShouldBeCreatedAndDeleted() {
        try {
            restTemplate.getForObject(Book.URL.concat(uniqueIsbn), Book.class);
        }catch(HttpClientErrorException ex){
            Assertions.assertThat(ex.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
        }
        Book result = restTemplate.postForObject(Book.URL, testBook, Book.class);
        Assertions.assertThat(result).isEqualTo(testBook);
        result = restTemplate.getForObject(Book.URL.concat(uniqueIsbn), Book.class);
        restTemplate.delete(Book.URL.concat(uniqueIsbn));
        try {
            restTemplate.getForObject(Book.URL.concat(uniqueIsbn), Book.class);
        }catch(HttpClientErrorException ex){
            Assertions.assertThat(ex.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
        }

    }
}
