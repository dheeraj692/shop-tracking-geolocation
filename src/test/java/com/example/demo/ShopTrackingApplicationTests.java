package com.example.demo;

import static org.junit.Assert.*;

import java.net.URI;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.json.JSONException;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.context.WebApplicationContext;

import com.example.domain.Shop;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ShopTrackingApplicationTests {
	
	@Autowired
	private WebApplicationContext webApplicationContext;

	private MockMvc mockMvc;
	
	TestRestTemplate restTemplate = new TestRestTemplate();

	HttpHeaders headers = new HttpHeaders();
	
	//Required to Generate JSON content from Java objects
	  public static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();
	  public static final String REST_SERVICE_URI = "http://localhost:8080/";

	@Before
	public void setup() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	@Test
	public void testShopApi() throws Exception {
		Shop shop = new Shop();
		shop.setShopName("ABCD");
		shop.setShopAddress("CopaCabana,Omkar Society, Pimple Nilakh, Pimpri-Chinchwad, Maharashtra");
		shop.setShopPostalcode("411029");
//		HttpEntity<String> entity = new HttpEntity<String>(null, headers);
//
//		ResponseEntity<String> response = restTemplate.exchange(
//				"http://localhost:8080/shop",
//				HttpMethod.POST, entity, String.class);
//
//		String expected = "{id:Course1,name:Spring,description:10 Steps}";
//
//		JSONAssert.assertEquals(expected, response.getBody(), false);
		
		
		
//		Map<String, Object> requestBody = new HashMap<String, Object>();
//		  requestBody.put("shopAddress", "CopaCabana,Omkar Society, Pimple Nilakh, Pimpri-Chinchwad, Maharashtra");
//		  requestBody.put("shopName", "ABCD");
//		  requestBody.put("shopPostalcode", "411029");
//		  HttpHeaders requestHeaders = new HttpHeaders();
//		  requestHeaders.setContentType(MediaType.APPLICATION_JSON);
//
//		  //Creating http entity object with request body and headers
//		  HttpEntity<String> httpEntity =
//		      new HttpEntity<String>(OBJECT_MAPPER.writeValueAsString(requestBody), requestHeaders);
//
//		  //Invoking the API
//		  Map<String, Object> apiResponse =
//		      restTemplate.postForObject("http://localhost:8080/shop", httpEntity, Map.class, Collections.EMPTY_MAP);
//
//		  assertNotNull(apiResponse);
		
		System.out.println("Testing create Shop API----------");
        RestTemplate restTemplate = new RestTemplate();
        URI uri = restTemplate.postForLocation(REST_SERVICE_URI+"/shop/", shop, Shop.class);
        System.out.println("Location : "+uri.toASCIIString());


		
	}

}
