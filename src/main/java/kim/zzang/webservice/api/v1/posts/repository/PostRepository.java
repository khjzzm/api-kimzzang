package kim.zzang.webservice.api.v1.posts.repository;

import kim.zzang.webservice.api.v1.posts.domain.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post, Long>, PostRepositoryCustom {

}
