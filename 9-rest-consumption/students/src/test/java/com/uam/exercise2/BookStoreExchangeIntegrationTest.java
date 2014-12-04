package com.uam.exercise2;

import com.uam.model.Book;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.*;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.web.client.RestTemplate;

import com.uam.Application;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
public class BookStoreExchangeIntegrationTest {

	@Autowired
	private RestTemplate restTemplate;

	@Test
	public void shouldReturnListOfBooksAsJsonString() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Accept", MediaType.APPLICATION_JSON_VALUE);
        HttpEntity<String> httpEntity = new HttpEntity<String>(httpHeaders);
        ResponseEntity<String> result = restTemplate.exchange(Book.URL, HttpMethod.GET, httpEntity, String.class);
        System.out.println(result.getStatusCode());
        System.out.println(result.getHeaders());
        System.out.println(result.getBody());
    }

	@Test
	public void shouldReturnListOfBooksAsJsonObject() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Accept", MediaType.APPLICATION_JSON_VALUE);
        HttpEntity<String> httpEntity = new HttpEntity<String>(httpHeaders);
        ParameterizedTypeReference<List<Book>> responseType = new ParameterizedTypeReference<List<Book>>() {};
        ResponseEntity<List<Book>> result = restTemplate.exchange(Book.URL, HttpMethod.GET, httpEntity, responseType);
        System.out.println(result.getStatusCode());
        System.out.println(result.getHeaders());
        System.out.println(result.getBody());
	}

	@Test
	public void shouldReturnListOfBooksAsXmlString() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Accept", MediaType.APPLICATION_XML_VALUE);
        HttpEntity<String> httpEntity = new HttpEntity<String>(httpHeaders);
        ResponseEntity<String> result = restTemplate.exchange(Book.URL, HttpMethod.GET, httpEntity, String.class);
        System.out.println(result.getStatusCode());
        System.out.println(result.getHeaders());
        System.out.println(result.getBody());
	}

	@Test
	public void shouldReturnListOfBooksAsXmlObject() {
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Accept", MediaType.APPLICATION_XML_VALUE);
        HttpEntity<String> httpEntity = new HttpEntity<String>(httpHeaders);
        ParameterizedTypeReference<List<Book>> responseType = new ParameterizedTypeReference<List<Book>>() {};
        ResponseEntity<List<Book>> result = restTemplate.exchange(Book.URL, HttpMethod.GET, httpEntity, responseType);
        System.out.println(result.getStatusCode());
        System.out.println(result.getHeaders());
        System.out.println(result.getBody());
	}
}

