package io.mischke.logmein.challenge;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
public class ShoppingListItem {
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private long id;

  private String description;

  public ShoppingListItem(String description) {
    this.description = description;
  }
}
