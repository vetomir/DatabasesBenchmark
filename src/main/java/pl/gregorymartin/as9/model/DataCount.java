package pl.gregorymartin.as9.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public
class DataCount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private Double saveSql;
    private Double loadSql;
    private Double saveMongo;
    private Double loadMongo;

    private Integer numberOfTests;

    public DataCount() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(final Integer id) {
        this.id = id;
    }

    public Double getSaveSql() {
        return saveSql;
    }

    public void setSaveSql(final Double saveSql) {
        this.saveSql = saveSql;
    }

    public Double getLoadSql() {
        return loadSql;
    }

    public void setLoadSql(final Double loadSql) {
        this.loadSql = loadSql;
    }

    public Double getSaveMongo() {
        return saveMongo;
    }

    public void setSaveMongo(final Double saveMongo) {
        this.saveMongo = saveMongo;
    }

    public Double getLoadMongo() {
        return loadMongo;
    }

    public void setLoadMongo(final Double loadMongo) {
        this.loadMongo = loadMongo;
    }

    public Integer getNumberOfTests() {
        return numberOfTests;
    }

    public void setNumberOfTests(final Integer numberOfTests) {
        this.numberOfTests = numberOfTests;
    }
}
