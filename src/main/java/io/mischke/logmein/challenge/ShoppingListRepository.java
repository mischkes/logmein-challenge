package io.mischke.logmein.challenge;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ShoppingListRepository extends JpaRepository<ShoppingListItem, Long> {

}
