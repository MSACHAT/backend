package MSACHAT.backend;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc(addFilters = false)
public class PostControllerTest {

	@LocalServerPort
	private int port;

	@Autowired
	private MockMvc mockMvc;
	@Test
	public void testGetPosts() throws Exception {

		String url = "http://localhost:" + port + "/posts?pageNum=0&pageSize=5";


		mockMvc.perform(get(url)
						.header("Authorization", "Bearer eyJhbGciOiJIUzM4NCJ9.eyJVc2VySWQiOjEsInN1YiI6IjE3MzU0NDM2MzRAcXEuY29tIiwiaWF0IjoxNzA1Mjk2ODcxLCJleHAiOjE3MDU5MDE2NzF9.ryhM5J20M5vBtqox-GDcO5fZSE_XI0EVjX0Mm9Mbrmy2RSs3ohiP0ZIdgKpxoBJ5")
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.totalPages").exists())
				.andExpect(jsonPath("$.posts").isNotEmpty());

	}

}
