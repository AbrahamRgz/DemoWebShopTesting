package tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class BasePage {

    protected static WebDriver driver;

    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

    static {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
    }
    // este es el contructor de base page que acepta un objeto webdriver como argumento
    public  BasePage (WebDriver driver){
        BasePage.driver = driver;
    }
    //metodo para vanegar a la URL
    public static void navigateTo(String Url){
        driver.get(Url);
    }

    //MEtodo para maximizar la ventana
    public  static void MaxiWindow(){
        driver.manage().window().maximize();
    }

    //Metodo para cerrar la instancia del driver
    public  static void CloseBroser(){
        driver.quit();
    }

    //Metodo find encuentra y devuelve un web elemnt por xpath
    public WebElement Find(String locator){
        return
        //wait.until(ExpectedConditions.presenceOfElementLocated(By.xpath(locator)));
        driver.findElement(By.xpath(locator));
    }

    //metodo para dar click a un elemento
    public void  clickElemnt(String Locator){
        Find(Locator).click();
    }

    //Metodo para escribir en un Web elemnt
    public  void Write(String Locator, String KeySend){
        Find(Locator).clear();
        Find(Locator).sendKeys(KeySend);
    }

    //metodo para obtener el texto de un web element
    public String GetText (String Locator){
        return
        Find(Locator).getText();

    }




}
