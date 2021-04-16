package io.mischke.logmein.challenge;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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
  ShoppingListRepository repository;


  @Test
  void getItemsList_shouldReturnEmptyList_whenNoDataWasInserted() throws Exception {
    mvc.perform(get("/shoppingList"))
        .andExpect(status().isOk())
        .andExpect(content().json("[]"));
  }

  @Transactional
  @Test
  void getItemsList_shouldReturnFullList_whenItemsInDatabase() throws Exception {
    repository.save(new ShoppingListItem("2x milk"));
    repository.save(new ShoppingListItem("A medium sized cow"));

    mvc.perform(get("/shoppingList"))
        .andExpect(status().isOk())
        .andExpect(content().json("[{\"description\": \"2x milk\"},"
            + "{\"description\": \"A medium sized cow\"}]"));
  }

  @Transactional
  @Test
  void create_shouldReturnIdOfObject() throws Exception {
    String result = mvc.perform(
        post("/createItem")
            .contentType(MediaType.APPLICATION_JSON)
            .content("{\"description\": \"2x milk\"}"))
        .andExpect(status().is(201))
        .andReturn().getResponse().getContentAsString();

    assertThat(Long.parseLong(result)).isInstanceOf(Long.class);
  }
}