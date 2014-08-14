package org.yaourtcorp.testspringboot.web;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import org.yaourtcorp.testspringboot.bo.Add;
import org.yaourtcorp.testspringboot.service.AddService;

import com.google.common.base.Optional;
import com.google.common.collect.ImmutableList;

@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@SpringApplicationConfiguration (classes = {TestContext.class})
public class AddsControllerTest {

	private static final String BASE_URI = "/adds";
	private MockMvc mockMvc;

	@Autowired
	private WebApplicationContext webApplicationContext;

	@Autowired
	private AddService addService;

	@Before
	public void setUp() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	@Test
	public void listAllAdds() throws Exception {
		// 1. PREPARE DATA	 
		final List<Add> expected = ImmutableList.of(
				new Add(1, "add 1", "content 1"),
				new Add(2, "add 2", "content 2"));

		// 2. MOCK
		when(addService.listAdds()).thenReturn(expected);

		// 3. TEST
		mockMvc.perform(get(BASE_URI)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().contentType("application/json;charset=UTF-8"))
				.andExpect(jsonPath("$", hasSize(2)))
				.andExpect(jsonPath("$[0].id", is(1)))
				.andExpect(jsonPath("$[0].name", is("add 1")))
				.andExpect(jsonPath("$[0].content", is("content 1")))
				.andExpect(jsonPath("$[1].id", is(2)))
				.andExpect(jsonPath("$[1].name", is("add 2")))
				.andExpect(jsonPath("$[1].content", is("content 2")));

		// 4. VERIFY
		verify(addService, times(1)).listAdds();
		verifyNoMoreInteractions(addService);
	}

	@Test
	public void testFindAdd() throws Exception {
		// 1. PREPARE DATA	 
		final int id = 1;
		final Optional<Add> expected = Optional.of(new Add(id, "add 1", "content 1"));

		// 2. MOCK
		when(addService.find(id)).thenReturn(expected);

		// 3. TEST
		mockMvc.perform(get(BASE_URI + "/{id}", id)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().contentType("application/json;charset=UTF-8"))
				.andExpect(jsonPath("$.id", is(id)))
				.andExpect(jsonPath("$.name", is("add 1")))
				.andExpect(jsonPath("$.content", is("content 1")));

		// 4. VERIFY
		verify(addService, times(1)).find(id);
		verifyNoMoreInteractions(addService);
	}

	@Test
	public void testFindAdd_notFound() throws Exception {
		// 1. PREPARE DATA	 
		final int id = 0;
		final Optional<Add> expected = Optional.absent();
		
		// 2. MOCK
		when(addService.find(id)).thenReturn(expected);
		
		// 3. TEST
		mockMvc.perform(get(BASE_URI + "/{id}",id)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isNotFound());
		
		// 4. VERIFY
		verify(addService, times(1)).find(id);
		verifyNoMoreInteractions(addService);
	}

	@Test
	@Ignore
	public void testFindAddByName() throws Exception { //FIXME to fix
		// 1. PREPARE DATA	 
		final String name = "add 1";
		final Optional<Add> expected = Optional.of(new Add(1, name, "content 1"));

		// 2. MOCK
		when(addService.find(name)).thenReturn(expected);

		// 3. TEST
		mockMvc.perform(get(BASE_URI + "?name={name}", name)
				.accept(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(content().contentType("application/json;charset=UTF-8"))
				.andExpect(jsonPath("$.id", is(1)))
				.andExpect(jsonPath("$.name", is(name)))
				.andExpect(jsonPath("$.content", is("content 1")));

		// 4. VERIFY
		verify(addService, times(1)).find(name);
		verifyNoMoreInteractions(addService);
	}

}
