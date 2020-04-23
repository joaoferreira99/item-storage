package com.training.springboot.itemstorage.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.training.springboot.itemstorage.entity.model.Item;
import com.training.springboot.itemstorage.enums.EnumItemState;
import com.training.springboot.itemstorage.service.ItemService;
import com.training.springboot.itemstorage.utils.interceptor.LoggingHandler;
import com.training.springboot.itemstorage.utils.interceptor.MdcInitHandler;
import java.io.FileReader;
import java.math.BigDecimal;
import java.math.BigInteger;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.core.io.Resource;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.util.FileCopyUtils;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ItemControllerTest {

	private static Long ITEM_UID = 1l;
	@Autowired
	private ItemController itemController;
	@Autowired
	private RestControllerAdvice restControllerAdvice;
	@Autowired
	private MdcInitHandler mdcInitHandler;
	@Autowired
	private LoggingHandler loggingHandler;
	@Value("classpath:samples/requests/createItemWhenValidReturn200Ok.json")
	private Resource createItemWhenValidReturn200OkRequest;
	@Value("classpath:samples/responses/createItemWhenValidReturn200Ok.json")
	private Resource createItemWhenValidReturn200OkResponse;
	@MockBean
	private ItemService itemService;
	private MockMvc mockMvc;

	@Before
	public void setUp() {

		mockMvc = MockMvcBuilders.standaloneSetup(itemController)
				.setControllerAdvice(restControllerAdvice)
				.addInterceptors(mdcInitHandler,
						loggingHandler)
				.build();
	}

	@Test
	public void createItem() throws Exception {

		Item persistedItem = Item.builder()
				.itemUid(ITEM_UID)
				.name("Item1")
				.description("description")
				.state(EnumItemState.AVAILABLE.name())
				.market("PT")
				.priceTag(BigDecimal.TEN)
				.stock(BigInteger.ONE).build();

		when(itemService.save(any(Item.class))).thenReturn(persistedItem);

		String requestContent = FileCopyUtils.copyToString(new FileReader(createItemWhenValidReturn200OkRequest.getFile()));

		mockMvc.perform(post("/items")
				.accept(MediaType.APPLICATION_JSON)
				.contentType(MediaType.APPLICATION_JSON)
				.content(requestContent)
		)
				.andExpect(status().isCreated())
				.andExpect(jsonPath("$.itemUid").value(ITEM_UID));

	}
}