package pl.gregorymartin.as9.service;

import com.opencsv.CSVReader;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.HeaderColumnNameTranslateMappingStrategy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.StopWatch;
import pl.gregorymartin.as9.model.*;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.*;

@Service
public class DataService {

    private String fileName = "src/main/resources/MOCK_DATA.csv";

    private List<Data> list;
    private List<DataSql> listSql;
    private List<DataMongo> listMongo;

    Logger logger = LoggerFactory.getLogger(DataService.class);

    private DataSqlRepository sqlRepository;
    private DataMongoRepository mongoRepository;
    private DataCountRepository countRepository;

    List<Long> sqlSave;
    List<Long> sqlLoad;
    List<Long> mongoSave;
    List<Long> mongoLoad;


    public DataService(final DataSqlRepository sqlRepository, final DataMongoRepository mongoRepository, final DataCountRepository countRepository) {
        this.sqlRepository = sqlRepository;
        this.mongoRepository = mongoRepository;
        this.countRepository = countRepository;
        list = new ArrayList<>();
        listSql = new ArrayList<>();
        listMongo = new ArrayList<>();
        this.mapFile(fileName);
        sqlSave = new ArrayList<>();
        sqlLoad = new ArrayList<>();
        mongoSave = new ArrayList<>();
        mongoLoad = new ArrayList<>();

    }

    public List<DataSql> getListSql() {
        return listSql;
    }

    public void setListSql(final List<DataSql> listSql) {
        this.listSql = listSql;
    }

    public List<DataMongo> getListMongo() {
        return listMongo;
    }

    public void setListMongo(final List<DataMongo> listMongo) {
        this.listMongo = listMongo;
    }

    public void mapFile(String fileName){

        Map<String, String> mapping = new
                HashMap<String, String>();
        mapping.put("id", "id");
        mapping.put("first_name", "firstName");
        mapping.put("last_name", "lastName");
        mapping.put("email", "email");
        mapping.put("gender", "gender");
        mapping.put("ip_address", "ipAddress");


        HeaderColumnNameTranslateMappingStrategy<Data> strategy =
                new HeaderColumnNameTranslateMappingStrategy<Data>();
        strategy.setType(Data.class);
        strategy.setColumnMapping(mapping);

        CSVReader csvReader = null;
        try {
            csvReader = new CSVReader(new FileReader
                    (fileName));
        }
        catch (FileNotFoundException e) {

            e.printStackTrace();
        }
        CsvToBean csvToBean = new CsvToBean();


        list = csvToBean.parse(strategy, csvReader);



        for (Data e : list) {
            listSql.add(new DataSql(e.getId(),e.getFirstName(),e.getLastName(),e.getEmail(),e.getGender(),e.getIpAddress()));
            listMongo.add(new DataMongo(e.getId(),e.getFirstName(),e.getLastName(),e.getEmail(),e.getGender(),e.getIpAddress()));
        }
        logger.info("files mapped!");
    }

    public List<DataCount> checkResult(){
        return countRepository.findAll();
    }

    public List<DataCount> test(int number){
        for(int i = 0; i < number; i++ ){
            saveToMongoDb(getListMongo());
            loadFromMongo();
            //
            saveToSqlDb(getListSql());
            loadFromSql();
        }
        DataCount count = new DataCount();

        OptionalDouble averageSqlSave = sqlSave
                .stream()
                .mapToLong(a -> a)
                .average();
        count.setSaveSql(averageSqlSave.getAsDouble());

        OptionalDouble averageSqlload = sqlLoad
                .stream()
                .mapToLong(a -> a)
                .average();
        count.setLoadSql(averageSqlload.getAsDouble());

        OptionalDouble averageMongoSave = mongoSave
                .stream()
                .mapToLong(a -> a)
                .average();
        count.setSaveMongo(averageMongoSave.getAsDouble());

        OptionalDouble averageMongoLoad = mongoLoad
                .stream()
                .mapToLong(a -> a)
                .average();
        count.setLoadMongo(averageMongoLoad.getAsDouble());

        count.setNumberOfTests(number);

        countRepository.save(count);


        return countRepository.findAll();
    }

    public List<DataSql> saveToSqlDb(List<DataSql> source){
        StopWatch s = new StopWatch();
        s.start();
        source.forEach(x -> sqlRepository.save(x));
        s.stop();

        DataCount count = new DataCount();
        sqlSave.add(s.getLastTaskTimeMillis());
        return sqlRepository.findAll();
    }
    public List<DataSql> loadFromSql(){
        StopWatch s = new StopWatch();
        s.start();
        List<DataSql> result = sqlRepository.findAll();
        s.stop();

        sqlLoad.add(s.getLastTaskTimeMillis());
        return result;
    }
    public List<DataMongo> saveToMongoDb(List<DataMongo> source){
        StopWatch s = new StopWatch();
        s.start();
        source.forEach(x -> mongoRepository.save(x));
        s.stop();

        mongoSave.add(s.getLastTaskTimeMillis());
        return mongoRepository.findAll();
    }
    public List<DataMongo> loadFromMongo(){
        StopWatch s = new StopWatch();
        s.start();
        List<DataMongo> result = mongoRepository.findAll();
        s.stop();

        mongoLoad.add(s.getLastTaskTimeMillis());
        return result;
    }


}
