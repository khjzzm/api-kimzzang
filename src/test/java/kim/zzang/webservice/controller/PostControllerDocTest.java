package kim.zzang.webservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import kim.zzang.webservice.api.v1.posts.domain.Post;
import kim.zzang.webservice.api.v1.posts.repository.PostRepository;
import kim.zzang.webservice.api.v1.posts.request.PostCreate;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.restdocs.AutoConfigureRestDocs;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.restdocs.RestDocumentationExtension;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.http.MediaType.APPLICATION_JSON;
import static org.springframework.restdocs.mockmvc.MockMvcRestDocumentation.document;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.post;
import static org.springframework.restdocs.payload.PayloadDocumentation.*;
import static org.springframework.restdocs.request.RequestDocumentation.parameterWithName;
import static org.springframework.restdocs.request.RequestDocumentation.pathParameters;
import static org.springframework.restdocs.snippet.Attributes.key;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureRestDocs(uriScheme = "https", uriHost = "api.kimzznag.com", uriPort = 443)
@ExtendWith(RestDocumentationExtension.class)
public class PostControllerDocTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private PostRepository postRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    @Disabled
    @DisplayName("??? ?????? ??????")
    void test1() throws Exception {
        // given
        Post post = Post.builder()
                .title("??????")
                .content("??????")
                .build();
        postRepository.save(post);

        // expected
        mockMvc.perform(get("/v1/posts/{postId}", 1L)
                        .accept(APPLICATION_JSON))
                .andDo(print())
                .andExpect(status().isOk())
                .andDo(document("post-inquiry",
                        pathParameters(
                                parameterWithName("postId").description("????????? ID")
                        ),
                        responseFields(
                                fieldWithPath("id").description("????????? ID"),
                                fieldWithPath("title").description("??????"),
                                fieldWithPath("content").description("??????")
                        )
                ));
    }

    @Test
    @DisplayName("??? ??????")
    void test2() throws Exception {
        // given
        PostCreate request = PostCreate.builder()
                .title("?????????")
                .content("????????????")
                .build();

        String json = objectMapper.writeValueAsString(request);

        // expected
        mockMvc.perform(post("/v1/posts")
                        .contentType(APPLICATION_JSON)
                        .accept(APPLICATION_JSON)
                        .content(json))
                .andDo(print())
                .andExpect(status().isOk())
                .andDo(document("post-create",
                        requestFields(
                                fieldWithPath("title").description("??????")
                                        .attributes(key("constraint").value("???????????? ??????????????????.")),
                                fieldWithPath("content").description("??????").optional()
                        )
                ));
    }
}






