package ui;

public class TableGridUI {
    public static final String PAGINATION_PAGE_BY_NUMBER ="//li[@class='qgrd-pagination-page']/a[text()='%s']";
    public static final String PAGINATION_PAGE_ACTIVE_BY_NUMBER ="//li[@class='qgrd-pagination-page']/a[text()='%s']";
    public static final String HEADER_TEXTBOX_BY_LABEL = "//div[@class='qgrd-header-text' and text()='%s']/parent::div/following-sibling::input";
    public static final String TOTAL_PAGINATION = "//ul[@class='qgrd-pagination-ul']/li[@class='qgrd-pagination-page']";
    public static final String PAGINATION_PAGE_BY_INDEX = "//ul[@class='qgrd-pagination-ul']/li[@class='qgrd-pagination-page'][%s]/a";
    public static final String ALL_ROW_EACH_PAGE = "//tbody/tr";
    public static final String ALL_ROW_COUNTRY_EACH_PAGE = "//tbody/tr/td[@data-key='country']";

}
