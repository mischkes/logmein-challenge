package io.mischke.logmein.challenge;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShoppingListService {
  private final ShoppingListRepository repository;

  @Autowired
  public ShoppingListService(ShoppingListRepository repository) {
    this.repository = repository;
  }

  public List<ShoppingListItem> getItems() {
    return repository.findAll();
  }

  public Long create(ShoppingListItem item) {
    return repository.save(item).getId();
  }
}
