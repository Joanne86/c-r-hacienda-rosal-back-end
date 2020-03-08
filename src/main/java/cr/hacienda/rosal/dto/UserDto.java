package cr.hacienda.rosal.dto;

public class UserDto {

    private String documentNumber;
    private String name;
    private String cellphone;
    private String towerNumberHome;
    private int userType;
    private int months;
    private float debt;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public int getUserType() {
        return userType;
    }

    public int getMonths() {
        return months;
    }

    public float getDebt() {
        return debt;
    }

    public void setDebt(float debt) {
        this.debt = debt;
    }

    public void setMonths(int months) {
        this.months = months;
    }

    public void setUserType(int userType) {
        this.userType = userType;
    }

    public String getDocumentNumber() {
        return documentNumber;
    }

    public void setDocumentNumber(String documentNumber) {
        this.documentNumber = documentNumber;
    }

    public String getCellphone() {
        return cellphone;
    }

    public void setCellphone(String cellphone) {
        this.cellphone = cellphone;
    }

    public String getTowerNumberHome() {
        return towerNumberHome;
    }

    public void setTowerNumberHome(String towerNumberHome) {
        this.towerNumberHome = towerNumberHome;
    }
}
