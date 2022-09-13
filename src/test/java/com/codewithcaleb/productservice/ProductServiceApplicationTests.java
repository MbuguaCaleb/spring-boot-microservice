package com.codewithcaleb.productservice;

import com.codewithcaleb.productservice.dto.ProductRequest;
import com.codewithcaleb.productservice.repository.ProductRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.testcontainers.containers.MongoDBContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.math.BigDecimal;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@Testcontainers
@AutoConfigureMockMvc
class ProductServiceApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private ObjectMapper objectMapper;

	@Container
	static MongoDBContainer mongoDBContainer= new MongoDBContainer("mongo:4.4.2");

	//when running the tests the MongodbURI in project will be replaced by the one in my test container
	//Dynamic property source will add the property to our spring context dynamically at the time of running the tests
	@DynamicPropertySource
	static void setProperties(DynamicPropertyRegistry dynamicPropertyRegistry){
		dynamicPropertyRegistry.add("spring.data.mongodb.uri",mongoDBContainer::getReplicaSetUrl);
	}


	//Mockmvc Object helps me make test to rest endpoints
	//@MockMvc POST Request
	//It is going to HIT an Endpoint,then insert a record
	@Test
	void shouldCreateProduct() throws Exception {

		ProductRequest productRequest = getProductRequest();

		//POJO Object to JSON
		String productRequestString = objectMapper.writeValueAsString(productRequest);

		//content will take the request body only as a String
		mockMvc.perform(MockMvcRequestBuilders.post("/api/product")
				                              .contentType(MediaType.APPLICATION_JSON)
				                              .content(productRequestString))
				                              .andExpect(status().isCreated());

		Assertions.assertEquals(1,productRepository.findAll().size());

	}

	private ProductRequest getProductRequest() {
		return ProductRequest.builder()
				.name("Iphone 13")
				.description("Iphone 13")
				.price(BigDecimal.valueOf(1200))
				.build();
	}


}
