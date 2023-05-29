public class Application {
    String organisationName;
    String inn;
    String partnerCode;
    String article;
    String articleName;
    String number;
    String permissionNumber;
    String date;

    public Application() {
    }

    public Application(String organisationName, String inn, String partnerCode, String article, String articleName, String number, String permissionNumber) {
        this.organisationName = organisationName;
        this.inn = inn;
        this.partnerCode = partnerCode;
        this.article = article;
        this.articleName = articleName;
        this.number = number;
        this.permissionNumber = permissionNumber;
    }

    public String getOrganisationName() {
        return organisationName;
    }

    public String getInn() {
        return inn;
    }

    public String getPartnerCode() {
        return partnerCode;
    }

    public String getArticle() {
        return article;
    }

    public String getArticleName() {
        return articleName;
    }

    public String getNumber() {
        return number;
    }

    public String getPermissionNumber() {
        return permissionNumber;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}