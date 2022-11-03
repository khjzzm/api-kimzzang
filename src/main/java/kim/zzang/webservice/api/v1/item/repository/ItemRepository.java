package kim.zzang.webservice.api.v1.item.repository;


import kim.zzang.webservice.api.v1.item.domain.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item, Long> {

}
