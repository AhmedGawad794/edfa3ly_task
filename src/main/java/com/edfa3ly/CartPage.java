package com.edfa3ly;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.util.List;


public class CartPage
{
    //region WebDriver
    private WebDriver driver;
    private WebDriverWait wait;
    //endregion

    // region Constructor
    public CartPage(WebDriver driver)
    {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, 15);
    }
    // endregion

    // region Elements inside the page
    private By itemLinkField = By.name("url");
    private By selectCategoryField = By.xpath("//*[contains(text(), 'Select Category')]");
    private By itemNameField = By.name("itemName");
    private By productColorField = By.id("product-color-text");
    private By productSizeField = By.id("product-size-text");
    private By addButton = By.xpath("//*[@class='containueButtom borderRadius3 F18']");
    private By unitPriceField = By.id("product-price");
    private By loaderIcon = By.id("loading-bar-spinner");
    private By categoriesLocator = By.xpath("//*[contains(@class, 'rest ng-binding')]");
    private By searchField = By.id("searchCategories");
    private By cartDetails = By.xpath("//*[contains(text(),'Cart Details')]");
    private By colorMenu = By.name("color");
    private By sizeMenu = By.name("size");
    private By notAvailableMessage = By.xpath("//*[contains(text(), 'we apologize')]");
    List <WebElement> searchResults;
    //endregion

    // region Methods to handle WebElements
    /* using these methods directly is more recommended than using getters and setters for each element then interacting with that element using other commands */

    public void setItemLink(String link) { driver.findElement(itemLinkField).sendKeys(link); }
    public void setItemName(String name) { driver.findElement(itemNameField).sendKeys(name); }
    public void setUnitPrice(double price) { driver.findElement(unitPriceField).sendKeys(String.valueOf(price)); }
    public void setColor(String color) { driver.findElement(productColorField).sendKeys(color); }
    public void setSize(String size) { driver.findElement(productSizeField).sendKeys(size); }
    public void clickAddButton() { driver.findElement(addButton).click(); }
    // endregion

    // region Wait Methods
    /* These methods are used to wait for an event to occur*/
    public void waitForLoaderToDisplay() { wait.until(ExpectedConditions.presenceOfElementLocated(loaderIcon)); }
    public void waitForLoaderToDisappear() { wait.until(ExpectedConditions.invisibilityOf(driver.findElement(loaderIcon))); }
    public void waitForCategoriesToBeDisplayed() { wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(categoriesLocator)); }
    public void waitForAddButton() { wait.until(ExpectedConditions.elementToBeClickable(addButton)); }
    public void waitForAddURLField() { wait.until(ExpectedConditions.elementToBeClickable(itemLinkField)); }
    public void waitForCategoryListButton() { wait.until(ExpectedConditions.elementToBeClickable(selectCategoryField)); }
    public void waitForLabel() { wait.until(ExpectedConditions.presenceOfElementLocated(notAvailableMessage)); }
    public void waitForPageToBeLoaded(){
        try {
            Thread.sleep(1500);
        } catch(InterruptedException ex) {
            System.out.println("got interrupted!");
        }
    }
    //endregion

    // region Methods to simulate steps
    public void openCategoryList() { driver.findElement(selectCategoryField).click(); }
    public void setSearch(String text)
    {
        driver.findElement(searchField).clear();
        driver.findElement(searchField).sendKeys(text);
    }
    public void selectCategory(String category)
    {
        searchResults = driver.findElements(By.xpath("//*[contains(@ng-repeat,'category in categories')]"));
        Actions action = new Actions(driver);
        for (int i=0; i<searchResults.size();i++ )
        {
            WebElement e = searchResults.get(i);
            String text = e.getText().trim();
            if (text.matches(category.trim()))
            {
                waitForPageToBeLoaded();
                action.moveByOffset(e.getLocation().x, e.getLocation().y).click().build().perform();
            }
        }
    }
    public void selectColor(int indexNum)
    {
        Select _colorDropDown= new Select(driver.findElement(colorMenu));
        _colorDropDown.selectByIndex(indexNum);
    }
    public void selectSize(int indexNum)
    {
        Select _sizeDropDown= new Select(driver.findElement(sizeMenu));
        _sizeDropDown.selectByIndex(indexNum);
    }
    public void addItemLink(String _link)
    {
        waitForAddURLField();
        setItemLink(_link);
        waitForLoaderToDisappear();
    }
    // endregion

    // region Methods used directly in test class
    public void addNonAutomatedItem(String _link, String _name, double _price, String _color, String _size, String _category)
    {
        addItemLink(_link);
        waitForCategoryListButton();
        openCategoryList();
        waitForCategoriesToBeDisplayed();
        setSearch(_category);
        selectCategory(_category);
        waitForPageToBeLoaded();
        setItemName(_name);
        setUnitPrice(_price);
        setColor(_color);
        setSize(_size);
        waitForAddButton();
        waitForPageToBeLoaded();
        clickAddButton();
        waitForLoaderToDisplay();
        waitForLoaderToDisappear();
    }
    public void addAutomatedItem(String _link, int _colorIndex, int _sizeIndex)
    {
        addItemLink(_link);
        selectColor(_colorIndex);
        selectSize(_sizeIndex);
        clickAddButton();
        waitForLoaderToDisplay();
        waitForLoaderToDisappear();
    }
    public void addProhibitedItem(String _link)
    {
        addItemLink(_link);
        waitForLabel();
    }
    public boolean getCartDetails()
    {
        WebElement element = driver.findElement(cartDetails);
        if (element.isDisplayed())
        {
            return true;
        }
        else {
            return false;
        }
    }
    public boolean getNotAvailableMessage()
    {
        WebElement element = driver.findElement(notAvailableMessage);
        if (element.isDisplayed())
        {
            return true;
        }
        else {
            return false;
        }
    }
    // endregion

}
