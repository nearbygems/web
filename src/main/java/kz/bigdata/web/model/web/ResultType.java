package kz.bigdata.web.model.web;

public enum ResultType {
  APPROVED("Одобрение"),
  REJECTED("Отказ");

  private final String text;

  ResultType(String text) {
    this.text = text;
  }

  public String text() {
    return text;
  }
}
