package tests;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit;

public class BaseTest {
    WebDriver driver;


    @BeforeMethod
    public void setuop() {
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://demowebshop.tricentis.com/");
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
    }

    @Test(priority = 1)
    public void DemoWebShop() throws InterruptedException, IOException {

        //2-ingresar el email, pass y dar click en el boton log in
        String email = "harcorrs@gmail.com";
        String pass = "jamon157";
        driver.findElement(By.xpath("//a[@class='ico-login']")).click();
        driver.findElement(By.xpath("//input[@id='Email']")).sendKeys(email);
        driver.findElement(By.xpath("//input[@id='Password']")).sendKeys(pass);
        driver.findElement(By.xpath("//input[@class='button-1 login-button']")).click();

        //take_screanshot(driver);

        //3-Darl click en el menu appareal shoes
        driver.findElement(By.xpath("(//a[@href='/apparel-shoes'])[1]")).click();
        //take_screanshot(driver);

        //4-seleccionar el producto blue Jeans
        driver.findElement(By.xpath("//div[@class='product-item']//img[@title='Show details for Blue Jeans']")).click();
        //take_screanshot(driver);

        //5-Agregar la cantidad de 5 pantalones y dar clic en el boton add to car
        WebElement agregarcarrito = driver.findElement(By.xpath("//input[@id='add-to-cart-button-36']"));
        for (int i = 1; i <= 5; i++) {
            agregarcarrito.click();
            System.out.println("Clic numero" + i);
            Thread.sleep(1000);
        }
        //take_screanshot(driver);

        //6-Validar que aparesca el mensaje "The product has been added to your shopping cart,  validar con assetions "
        WebElement mensaje = driver.findElement(By.xpath("//p[@class='content']"));
        String TextoEsperado = "The product has been added to your shopping cart";
        String textoActual = mensaje.getText();
        Assert.assertEquals(textoActual, TextoEsperado);
        //take_screanshot(driver);

        //7-dar click al link de shopong cart
        driver.findElement(By.xpath("//span[normalize-space()='Shopping cart']")).click();
        //take_screanshot(driver);

        //8-Marcar la opcion de acuerdo con los terminos y condiciones y dar click en el boton chekout
        driver.findElement(By.xpath("//input[@id='termsofservice']")).click();
        driver.findElement(By.xpath("//button[@id='checkout']")).click();
        //take_screanshot(driver);

        //9-Capturar la informacion y dar clic en continuar
        driver.findElement(By.xpath("//input[@id='BillingNewAddress_Company']")).sendKeys("capgemini");
        driver.findElement(By.xpath("//select[@id='BillingNewAddress_CountryId']")).click();
        driver.findElement(By.xpath("//option[text()='Mexico']")).click();
        driver.findElement(By.xpath("//input[@id='BillingNewAddress_City']")).sendKeys("Durango");
        driver.findElement(By.xpath("//input[@id='BillingNewAddress_Address1']")).sendKeys("Fracc Alamedas #456");
        driver.findElement(By.xpath("//input[@id='BillingNewAddress_ZipPostalCode']")).sendKeys("34567");
        driver.findElement(By.xpath("//input[@id='BillingNewAddress_PhoneNumber']")).sendKeys("1234567878");
        //take_screanshot(driver);

        //10-Dar clic en el boton continuar
        driver.findElement(By.xpath("//input[@onclick='Billing.save()']")).click();
        //take_screanshot(driver);

        //11-Seleccionar la opcion "Ground (10.00)" y dar click en el boton continuar
        driver.findElement(By.xpath("//input[@id='PickUpInStore']")).click();
        driver.findElement(By.xpath("//input[@onclick='Shipping.save()']")).click();
        //take_screanshot(driver);

        //12-Seleccionar la opcion tarjeta de credito y dar click en continuar
        driver.findElement(By.xpath("//input[@id='paymentmethod_2']")).click();
        driver.findElement(By.xpath("//input[@class='button-1 payment-method-next-step-button']")).click();
        //take_screanshot(driver);

        //13-Capturar todos los datos solicitados y dar clic en continuar
        driver.findElement(By.xpath("//input[@id='CardholderName']")).sendKeys("Abraham");
        driver.findElement(By.xpath("//input[@id='CardNumber']")).sendKeys("4013 5406 8274 6260");
        driver.findElement(By.xpath("//input[@id='CardCode']")).sendKeys("356");
        driver.findElement(By.xpath("//input[@onclick='PaymentInfo.save()']")).click();
        //take_screanshot(driver);

        //14- Confirmar orden
        //Validar que los metosos que aparecen en payment method y shoping method
        //seab los mismos que seleccionamos en las secciones anteriores
        //Validar que la suma del Subtotal + shoping tax = Total
        //Dar click en el boton "confirm"

        String subtotal = driver.findElement(By.xpath("(//span[@class='product-price'])[1]")).getText();
        String Shoping = driver.findElement(By.xpath("(//span[@class='product-price'])[2]")).getText();
        String tax = driver.findElement(By.xpath("(//span[@class='product-price'])[3]")).getText();

        double sub = Double.parseDouble(subtotal);
        double shop = Double.parseDouble(Shoping);
        double tax2 = Double.parseDouble(tax);
        double compra = sub + shop + tax2;
        String total = driver.findElement(By.xpath("//span[@class='product-price order-total']")).getText();
        double total2 = Double.parseDouble(total);

        Assert.assertEquals(compra, total2);
        driver.findElement(By.xpath("//input[@value='Confirm']")).click();
        //take_screanshot(driver);

        //15-validar que aparezca el mensaje " " y dar click en el boton continuar
        String orden = driver.findElement(By.xpath("//strong[normalize-space()='Your order has been successfully processed!']")).getText();
        String ordenEsp = "Your order has been successfully processed!";
        Assert.assertEquals(ordenEsp, orden);
        driver.findElement(By.xpath("//input[@value='Continue']")).click();
        //take_screanshot(driver);

        //16-Dar click en logout
        driver.findElement(By.xpath("//a[normalize-space()='Log out']")).click();
        //take_screanshot(driver);


        Thread.sleep(4000);

    }

    public static void take_screanshot(WebDriver driver) throws IOException {
        //metodo para tomar captura de pantalla y que el nombre sea dinamico
        //Genera un nombre unico con fecha y hora
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String filename = "Screanshot" + timestamp + ".png";

        //Guardar la captura en una ubicacion especifica
        File Scranshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        File destinoFile = new File("ScreanShot" + filename);
        FileUtils.copyFile(Scranshot, destinoFile);


    }

    @Test(priority = 2)
    public void DeleteAdress() throws InterruptedException {
        String email = "harcorrs@gmail.com";
        String pass = "jamon157";
        driver.findElement(By.xpath("//a[@class='ico-login']")).click();
        driver.findElement(By.xpath("//input[@id='Email']")).sendKeys(email);
        driver.findElement(By.xpath("//input[@id='Password']")).sendKeys(pass);
        driver.findElement(By.xpath("//input[@class='button-1 login-button']")).click();

        //eliminar direccion guardada
        driver.findElement(By.xpath("(//a[@class='account'])[1]")).click();
        driver.findElement(By.xpath("//a[@class='inactive'][normalize-space()='Addresses']")).click();
        driver.findElement(By.xpath("//input[@value='Delete']")).click();

        driver.switchTo().alert().accept();
        Thread.sleep(10000);

    }


    @AfterMethod
    public void closeBrowser() {
        if (driver != null) {
            driver.quit();
        }
    }
}
