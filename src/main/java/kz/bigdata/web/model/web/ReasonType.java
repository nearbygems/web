package kz.bigdata.web.model.web;

public enum ReasonType {
  BLACKLIST("Клиент находится в черном списке"),
  LOW_INCOME("Клиент не имеет достаточный уровень дохода");

  private final String text;

  ReasonType(String text) {
    this.text = text;
  }

  public String text() {
    return text;
  }
}
