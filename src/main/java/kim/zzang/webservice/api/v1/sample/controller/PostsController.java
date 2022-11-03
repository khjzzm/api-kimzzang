package kim.zzang.webservice.api.v1.sample.controller;

import kim.zzang.webservice.api.v1.sample.model.req.PostsCreateDto;
import kim.zzang.webservice.config.common.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping("/v1/no/posts")
public class PostsController {

    private final Response response;

    public PostsController(Response response) {
        this.response = response;
    }


    // test/../PostsControllerTest.java or ./PostsController.http 테스트
    @PostMapping
    public ResponseEntity<?> post(@RequestBody @Valid PostsCreateDto postsCreateDto){
        log.info("in /posts {}", postsCreateDto);
        return response.success("성공");
    }

}
