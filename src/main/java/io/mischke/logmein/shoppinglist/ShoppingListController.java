package io.mischke.logmein.shoppinglist;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Api
@RestController
public class ShoppingListController {
  private final ShoppingListService shoppingListService;

  @Autowired
  public ShoppingListController(ShoppingListService shoppingListService) {
    this.shoppingListService = shoppingListService;
  }

  @ApiOperation("Get all shopping list items")
  @ApiResponse(code = 200, message = "Succesfully retrieved items")
  @GetMapping(value = "/shoppingList", produces = MediaType.APPLICATION_JSON_VALUE)
  public List<ShoppingListStoredItem> getItems() {
    return shoppingListService.getItems();
  }

  @ApiOperation("Add item to shopping list")
  @ApiResponse(code = 201, message = "Succesfully created new item")
  @PutMapping(value = "/createItem", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<ShoppingListStoredItem> create(@RequestBody ShoppingListItem item) {
    return new ResponseEntity<>(shoppingListService.create(item), HttpStatus.CREATED);
  }

  @ApiOperation("Delete shopping list item")
  @ApiResponse(code = 204, message = "Succesfully deleted item or no deletion necessary")
  @DeleteMapping(value = "/deleteItem/{id}")
  @ResponseStatus(HttpStatus.NO_CONTENT)
  public void delete(@PathVariable long id) {
    try {
      shoppingListService.delete(id);
    } catch (EmptyResultDataAccessException e) {
      return; //no deletion necessary
    }
  }
}
