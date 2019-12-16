package com.training.springboot.itemstorage.service;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;

import com.training.springboot.itemstorage.entity.model.Item;
import com.training.springboot.itemstorage.error.EntityNotFoundException;
import com.training.springboot.itemstorage.repository.ItemRepository;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Optional;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ItemServiceTest {

	@Autowired
	private ItemService itemService;

	@MockBean
	private ItemRepository itemRepository;

	private static Long ITEM_UID = 1l;

	@Before
	public void setUp() {
	}

	@After
	public void tearDown() {
	}

	@Test
	public void list() {

	}

	@Test
	public void testList() {
	}

	@Test
	public void get() {
		when(itemRepository.findById(ITEM_UID))
				.thenReturn(Optional.of(Item.builder()
						.itemUid(ITEM_UID)
						.name("my item")
						.stock(BigInteger.ONE)
						.priceTag(BigDecimal.TEN)
						.build()));
		Item item = itemService.get(ITEM_UID);
		assertThat(item.getName(), is("my item"));
	}

	@Test(expected = EntityNotFoundException.class)
	public void getError() {
		when(itemRepository.findById(ITEM_UID))
				.thenReturn(Optional.empty());
		itemService.get(ITEM_UID);
	}

}