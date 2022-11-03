package kim.zzang.webservice.api.v1.posts.repository;

import kim.zzang.webservice.api.v1.posts.domain.Post;
import kim.zzang.webservice.api.v1.posts.request.PostSearch;

import java.util.List;

public interface PostRepositoryCustom {

    List<Post> getList(PostSearch postSearch);
}
