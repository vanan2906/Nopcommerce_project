public class String_Format {
    public static  String REGISTER_LINK = "//a[@class='ico-register']";
    public static  String LOGIN_LINK = "//a[@class='ico-login']";
    public static  String LOGIN_SUCCESS_TEXT = "//a[@class='ico-account']";
    public static String LOGOUT_LINK = "//a[@class='ico-logout']";
    public static String DYNAMIC_LINK_BY_PAGE_NAME = "//div[contains(@class,'%d')]//a[text()='%s']";
    public static void main(String[] args) {
//        System.out.println(String.format(DYNAMIC_LINK_BY_PAGE_NAME, "Customer info"));
//        System.out.println(String.format(DYNAMIC_LINK_BY_PAGE_NAME, "Addresses"));
//        System.out.println(String.format(DYNAMIC_LINK_BY_PAGE_NAME, "My Product Reviews"));
//        System.out.println(String.format(DYNAMIC_LINK_BY_PAGE_NAME, "Reward Points"));
        clickToSideBarLink(DYNAMIC_LINK_BY_PAGE_NAME,"","Customer info");
        clickToSideBarLink(DYNAMIC_LINK_BY_PAGE_NAME,"account-navigation","Addresses");
        clickToSideBarLink(DYNAMIC_LINK_BY_PAGE_NAME,"","My Product Reviews");
        clickToSideBarLink(DYNAMIC_LINK_BY_PAGE_NAME,"","Reward Points");



    }

    private static void clickToSideBarLink(String locator) {

    }
    // 1 tham số động
    public static void clickToSideBarLink(String dynamicLocator, String pageName) {
        String locator = String.format(dynamicLocator, pageName);

    }
    // 2 tham số động
    public static void clickToSideBarLink(String dynamicLocator, String areaName, String    pageName) {
        String locator = String.format(dynamicLocator, areaName, pageName);

    }
    // n tham so động
    public static void clickToSideBarLink(String dynamicLocator, String...params) {
        String locator = String.format(dynamicLocator, (Object) params);

    }
}
