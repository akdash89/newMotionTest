package com.starwars.test;

import java.io.IOException;

import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.Assert;
import org.junit.Test;

import com.starwars.bo.Film;
import com.starwars.bo.People;
import com.starwars.util.RetrieveUtil;

public class SWApiTest {

	private final String BASE_URL = "https://swapi.dev/api/";

	@Test
	public void testGetPeopleAPIById() throws ClientProtocolException, IOException {

		HttpUriRequest request = new HttpGet(BASE_URL + "people/1/");
		HttpResponse response = HttpClientBuilder.create().build().execute(request);
		People resource = RetrieveUtil.retrieveResourceFromResponse(response, People.class);
		String contentType = null;
		for (Header header : response.getAllHeaders()) {
			if (header.getName().equals("Content-Type")) {
				contentType = header.getValue();
				break;
			}
		}
		Assert.assertEquals("application/json", contentType);
		Assert.assertNotNull(resource);
		Assert.assertNotNull(resource.getName());
	}

	@Test
	public void testGetFilmById() throws ClientProtocolException, IOException {

		HttpUriRequest request = new HttpGet(BASE_URL + "films/1/");
		HttpResponse response = HttpClientBuilder.create().build().execute(request);
		Film resource = RetrieveUtil.retrieveResourceFromResponse(response, Film.class);
		String contentType = null;
		for (Header header : response.getAllHeaders()) {
			if (header.getName().equals("Content-Type")) {
				contentType = header.getValue();
				break;
			}
		}
		Assert.assertEquals("application/json", contentType);
		Assert.assertNotNull(resource);
		Assert.assertEquals("A New Hope", resource.getTitle());

	}

}
