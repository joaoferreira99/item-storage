package com.training.springboot.itemstorage.repository;

import com.training.springboot.itemstorage.entity.model.Item;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ItemRepository extends JpaRepository<Item, Long> {

	public Optional<Item> findById(Long id);
}
