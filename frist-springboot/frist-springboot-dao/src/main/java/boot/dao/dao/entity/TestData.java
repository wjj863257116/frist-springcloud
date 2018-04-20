package boot.dao.dao.entity;


public class TestData {
    private String name;
    private String id_car;
    private String mb_ph;

    public String getId_car() {
        return id_car;
    }

    public void setId_car(String id_car) {
        this.id_car = id_car;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public String getMb_ph() {
        return mb_ph;
    }

    public void setMb_ph(String mb_ph) {
        this.mb_ph = mb_ph;
    }
}
