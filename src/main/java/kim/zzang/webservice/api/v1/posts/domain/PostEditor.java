package kim.zzang.webservice.api.v1.posts.domain;

import lombok.Builder;
import lombok.Getter;

@Getter
public class PostEditor {

    private final String title;
    private final String content;

    @Builder
    public PostEditor(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
