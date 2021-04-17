package io.mischke.logmein.shoppinglist;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class ShoppingListStoredItem {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;

  private String description;

  ShoppingListStoredItem(String description) {
    this.description = description;
  }

  public ShoppingListStoredItem(ShoppingListItem item) {
    this.description = item.getDescription();
  }
}
