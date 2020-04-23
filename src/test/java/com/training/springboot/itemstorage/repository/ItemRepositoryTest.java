package com.training.springboot.itemstorage.repository;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertTrue;

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
public class ItemRepositoryTest {

	@Autowired
	private ItemRepository itemRepository;


	@Sql("/delete_all.sql")
	@Test(expected = DataIntegrityViolationException.class)
	public void createDuplicateItemTest() {
		// TODO: Complete having in mind the AAA approach
		// Arrange - Prepare variables and mock responses
		// Act - Behaviour to test
		// Assert - Verify that all criterias are met
	}

	@Sql("/delete_all.sql")
	@Test
	public void createItemTest() {
		// TODO: Complete having in mind the AAA approach
		// Arrange - Prepare variables and mock responses
		// Act - Behaviour to test
		// Assert - Verify that all criterias are met
	}

	@Test
	public void getItemsTest() {
		// TODO: Complete having in mind the AAA approach
		// Arrange - Prepare variables and mock responses
		// Act - Behaviour to test
		// Assert - Verify that all criterias are met
	}

}