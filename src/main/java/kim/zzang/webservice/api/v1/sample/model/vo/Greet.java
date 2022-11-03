package kim.zzang.webservice.api.v1.sample.model.vo;

import lombok.Builder;
import lombok.Getter;

@Getter
public class Greet {
  private final Long id;
  private final String country;
  private final String say;

  @Builder
  public Greet(Long id, String country, String say) {
    this.id = id;
    this.country = country;
    this.say = say;
  }
}