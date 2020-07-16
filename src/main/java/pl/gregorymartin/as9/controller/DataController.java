package pl.gregorymartin.as9.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import pl.gregorymartin.as9.model.DataCount;
import pl.gregorymartin.as9.model.DataMongo;
import pl.gregorymartin.as9.model.DataSql;
import pl.gregorymartin.as9.service.DataService;

import java.util.List;

@RestController
public class DataController {
    Logger logger = LoggerFactory.getLogger(DataController.class);
    private DataService service;

    //

    public DataController(final DataService service) {
        this.service = service;
    }

    //

    @GetMapping("/test/{id}")
    ResponseEntity<List<DataCount>> test(
            @PathVariable int id
    ) {
        service.test(id);
        return ResponseEntity.ok(service.checkResult());
    }

    //

    @GetMapping("/result")
    ResponseEntity<List<DataCount>> checkResult() {
        return ResponseEntity.ok(service.checkResult());
    }

    //

    @GetMapping("/mongo/save")
    ResponseEntity<List<DataMongo>> saveToMongo() {
        return ResponseEntity.ok(service.saveToMongoDb(service.getListMongo()));
    }
    @GetMapping("mongo/load")
    ResponseEntity<List<DataMongo>> loadFromMongo() {
        return ResponseEntity.ok(service.loadFromMongo());
    }

    //

    @GetMapping("sql/save")
    ResponseEntity<List<DataSql>> saveToSql() {
        return ResponseEntity.ok(service.saveToSqlDb(service.getListSql()));
    }

    @GetMapping("sql/load")
    ResponseEntity<List<DataSql>> loadFromSql() {
        return ResponseEntity.ok(service.loadFromSql());
    }


}
