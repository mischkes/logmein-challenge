package io.mischke.logmein.shoppinglist;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.fasterxml.jackson.databind.ObjectMapper;
import javax.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

@AutoConfigureMockMvc
@SpringBootTest
class ShoppingListControllerIT {

  @Autowired
  private MockMvc mvc;

  @Autowired
  private ShoppingListRepository repository;

  @Autowired
  private ObjectMapper objectMapper;


  @Test
  void getItemsList_shouldReturnEmptyList_whenNoDataWasInserted() throws Exception {
    mvc.perform(get("/shoppingList"))
        .andExpect(status().isOk())
        .andExpect(content().json("[]"));
  }

  @Transactional
  @Test
  void getItemsList_shouldReturnFullList_whenItemsInDatabase() throws Exception {
    repository.save(new ShoppingListStoredItem("2x milk"));
    repository.save(new ShoppingListStoredItem("A medium sized cow"));

    String contentAsString = mvc.perform(get("/shoppingList"))
        .andExpect(status().isOk())
        .andExpect(content().json("[{\"description\": \"2x milk\"},"
            + "{\"description\": \"A medium sized cow\"}]"))
        .andReturn().getResponse().getContentAsString();

    System.out.println(contentAsString);
  }

  @Transactional
  @Test
  void create_shouldCreateObjectAndReturnIt() throws Exception {

    String content = mvc.perform(
        put("/createItem")
            .contentType(MediaType.APPLICATION_JSON)
            .content("{\"description\": \"2x milk\"}"))
        .andExpect(status().is(201))
        .andReturn().getResponse().getContentAsString();

    ShoppingListStoredItem item = objectMapper.readValue(content, ShoppingListStoredItem.class);
    assertThat(item.getDescription()).isEqualTo("2x milk");
    assertThat(repository.getOne(item.getId())).isEqualTo(item);
  }

  @Test
  void delete_shouldReturnNoContent_ifObjectWasDeleted() throws Exception {
    ShoppingListStoredItem item = repository.save(new ShoppingListStoredItem("2x milk"));

    mvc.perform(delete("/deleteItem/" + item.getId()))
        .andExpect(status().is(204));

    assertThat(repository.findById(item.getId())).isEmpty();
  }

  @Test
  void delete_shouldReturnNoContent_ifNoObjectWasDeleted() throws Exception {
    ShoppingListStoredItem item = repository.save(new ShoppingListStoredItem("2x milk"));

    mvc.perform(delete("/deleteItem/42"))
        .andExpect(status().is(204));
  }
}