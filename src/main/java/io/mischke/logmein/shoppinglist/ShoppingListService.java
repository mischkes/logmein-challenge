package io.mischke.logmein.shoppinglist;

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

  public List<ShoppingListStoredItem> getItems() {
    return repository.findAll();
  }

  public ShoppingListStoredItem create(ShoppingListItem item) {
    return repository.save(new ShoppingListStoredItem(item));
  }

  public void delete(long id) {
    repository.deleteById(id);
  }
}
