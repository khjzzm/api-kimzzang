package kim.zzang.webservice.api.v1.sample.service;

import kim.zzang.webservice.api.v1.sample.repository.RetryRepository;
import kim.zzang.webservice.config.annotation.Retry;
import kim.zzang.webservice.config.annotation.Trace;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class RetryService {

    private static int seq = 0;

//    private final RetryRepository repository;
//
//    public RetryService(RetryRepository repository) {
//        this.repository = repository;
//    }

    // test/../PostsServiceTest.java  테스트
    @Trace
    @Retry(value = 4)
    public String read(String phoneNum) {
        seq++;
        if (seq % 5 == 0) {
            throw new IllegalStateException("예외발생");
        }else{
            log.info("실패하는 케이스 작성 필요");
        }
        return "ok";
    }


}
