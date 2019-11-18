package com.training.springboot.itemstorage.repository;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

import com.training.springboot.itemstorage.entity.model.Item;
import java.math.BigDecimal;
import java.math.BigInteger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class TestItemRepository {

	@Autowired
	private ItemRepository itemRepository;

	@Sql("/delete_all.sql")
	@Test
	public void createItemTest() {
		Item item = itemRepository.save(Item.builder().name("my item").priceTag(BigDecimal.ONE).stock(BigInteger.ONE).build());
		assertThat(item.getName(), is("my item"));
	}

	@Test
	public void getItemsTest() {
		assertThat(itemRepository.findAll().size(), is(5));
	}

	@Sql("/delete_all.sql")
	@Test(expected = DataIntegrityViolationException.class)
	public void createDuplicateItemTest() {
		itemRepository.save(Item.builder().name("my item").priceTag(BigDecimal.ONE).stock(BigInteger.ONE).build());
		itemRepository.save(Item.builder().name("my item").priceTag(BigDecimal.ONE).stock(BigInteger.ONE).build());
	}


}
