package kim.zzang.webservice.api.v1.sample.controller;

;
import kim.zzang.webservice.config.annotation.TimeLogging;
import kim.zzang.webservice.config.common.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@RestController
@RequestMapping("/v1/sample")
public class TimeLogController {

    private final Response response;

    public TimeLogController(Response response) {
        this.response = response;
    }


    @GetMapping("/time-no-log")
    public ResponseEntity<?> noLogging() {
        log.warn("no logging");
        return response.success("타임로깅 안찍힙니다.");
    }

}
