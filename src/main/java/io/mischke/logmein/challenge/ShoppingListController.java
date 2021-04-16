package io.mischke.logmein.challenge;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

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
  public List<ShoppingListItem> getItems() {
    return shoppingListService.getItems();
  }

  @ApiOperation("Add item to shopping list")
  @ApiResponse(code = 201, message = "Succesfully created new item")
  @PostMapping(value = "/createItem", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Long> create(@RequestBody ShoppingListItem item) {
    return new ResponseEntity<>(shoppingListService.create(item), HttpStatus.CREATED);
  }
}
