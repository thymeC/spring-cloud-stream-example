package com.example.stream;

import com.example.stream.model.Dah;
import com.example.stream.model.Ro;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;

@Slf4j
@Repository
public class FakeRepository {

    public Ro findRo(String id) {
        log.info("finding ro");
        return new Ro();
    }

    public Dah findDah(String id) {
        log.info("finding dah");
        return new Dah();
    }

    public void save(Dah dah) {
        log.info("dah has been saved");
    }
}
