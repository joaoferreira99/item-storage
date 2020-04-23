package com.training.springboot.itemstorage.service;

import com.training.springboot.itemstorage.error.EntityNotFoundException;
import com.training.springboot.itemstorage.repository.ItemRepository;
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

	private static Long ITEM_UID = 1l;
	@Autowired
	private ItemService itemService;
	@MockBean
	private ItemRepository itemRepository;

	@Before
	public void setUp() {
	}

	@After
	public void tearDown() {
	}

	@Test
	public void list() {
		// TODO: Complete having in mind the AAA approach
		// Arrange - Prepare variables and mock responses
		// Act - Behaviour to test
		// Assert - Verify that all criterias are met
	}

	@Test
	public void testList() {
		// TODO: Complete having in mind the AAA approach
		// Arrange - Prepare variables and mock responses
		// Act - Behaviour to test
		// Assert - Verify that all criterias are met
	}

	@Test
	public void get() {
		// TODO: Complete having in mind the AAA approach
		// Arrange - Prepare variables and mock responses
		// Act - Behaviour to test
		// Assert - Verify that all criterias are met
	}

	@Test(expected = EntityNotFoundException.class)
	public void getError() {
		// TODO: Complete having in mind the AAA approach
		// Arrange - Prepare variables and mock responses
		// Act - Behaviour to test
		// Assert - Verify that all criterias are met
	}

}