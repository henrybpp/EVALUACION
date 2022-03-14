package com.hry.evaluacion;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.hry.core.common.util.Util;
import com.hry.core.common.wrapper.Response;
import com.hry.core.domain.FilterDTO;
import com.hry.evaluacion.controller.ProductController;
import com.hry.evaluacion.service.ProductService;
import com.hry.persistence.mongo.document.ProductModel;
import com.hry.persistence.mongo.repository.ProductRepository;

@RunWith(SpringRunner.class)
@WebMvcTest(controllers = ProductController.class)
public class EvaluacionControllerTest {

	@Autowired
    MockMvc mockMvc;
	
	@MockBean
    ProductService productService;

    @MockBean
    ProductRepository productRepository;

	@Before
	public void setUp() {}
	
	@Test
    public void testPing() throws Exception {
        mockMvc.perform(get("/products/"))
        .andDo(print())
        .andExpect(status().isOk())
        .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE));
    }
    
    @Test
   	public void testGetAll() throws Exception {       	
   	   given(productService.getAll())
   	   	.willReturn(new Response<List<ProductModel>>());

          mockMvc.perform(
                  get("/products/getall")
                  .contentType(MediaType.APPLICATION_JSON_VALUE))
                  .andDo(print())
                  .andExpect(status().isOk())
                  .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE));
   	}    
    
    @Test
   	public void testGetById() throws Exception {       	
   	   given(productService.getById("622e4280d2a229d94df6199f"))
   	   	.willReturn(new Response<ProductModel>());

          mockMvc.perform(
                  get("/products/getbyid/622e4280d2a229d94df6199f")
                  .contentType(MediaType.APPLICATION_JSON_VALUE))
                  .andDo(print())
                  .andExpect(status().isOk())
                  .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
          ;
   	}
    
    @Test
   	public void testGetByPalindromeCondition() throws Exception {     	
       	FilterDTO filterDTO=FilterDTO.builder()
       		.brand("aba")
       		.description("ala")
       		.build();
       	      	
   	   given(productService.getByPalindromeCondition(filterDTO))
   	   	.willReturn(new Response<List<ProductModel>>());

   	   String json = Util.objectToJson(filterDTO);
          mockMvc.perform(
                  post("/products/getbycondition")
                  .contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                  .content(json))
                  .andDo(print())
                  .andExpect(status().isOk());
   	}
}
