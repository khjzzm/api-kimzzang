package kim.zzang.webservice.api.v1.posts.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import kim.zzang.webservice.api.v1.posts.domain.Post;
import kim.zzang.webservice.api.v1.posts.request.PostSearch;
import lombok.RequiredArgsConstructor;

import java.util.List;

import static kim.zzang.webservice.api.v1.posts.domain.QPost.post;


@RequiredArgsConstructor
public class PostRepositoryImpl implements PostRepositoryCustom {

    private final JPAQueryFactory jpaQueryFactory;

    @Override
    public List<Post> getList(PostSearch postSearch) {
        return jpaQueryFactory.selectFrom(post)
                .limit(postSearch.getSize())
                .offset(postSearch.getOffset())
                .orderBy(post.id.desc())
                .fetch();
    }
}
