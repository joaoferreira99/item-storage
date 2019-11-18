package com.training.springboot.itemstorage.service;

import static org.mockito.Mockito.when;

import com.training.springboot.itemstorage.entity.model.Item;
import com.training.springboot.itemstorage.repository.ItemRepository;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Optional;
import javax.persistence.EntityNotFoundException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

@SpringBootTest
@RunWith(SpringRunner.class)
public class ItemServiceTest {

	@MockBean
	private ItemRepository itemRepository;

	@Autowired
	private ItemService itemService;

	private static final long ID = 1l;

	@Test
	public void save() {
		Item item = Item.builder().
				name("banana").priceTag(BigDecimal.ONE).stock(BigInteger.ONE).build();

				Item persist = item;
				persist.setItemUid(1l);
		when(itemRepository.save(item)).thenReturn(persist);
		item = itemService.save(item);
		assertThat(item.getItemUid(),is(1l));
	}

	@Test(expected = EntityNotFoundException.class)
	public void errorGet() {
		when(itemRepository.findById(ID)).thenReturn(Optional.empty());
		itemService.get(ID);

	}

}