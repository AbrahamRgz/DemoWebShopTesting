package tests;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.TimeUnit; 

public class DemoWebTestng extends BasePage{

    public DemoWebTestng() {
        super(driver);

    }
    @BeforeTest
    public void  setuop(){
        navigateTo("https://demowebshop.tricentis.com/");
        MaxiWindow();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
    }


    @Test(priority = 1)
    public void DemoWebShopTest() throws InterruptedException, IOException {

        //2-ingresar el email, pass y dar click en el boton log in
        String email= "harcorrs@gmail.com";
        String pass ="jamon157";
        clickElemnt("//a[@class='ico-login']");
        Write("//input[@id='Email']",email);
        Write("//input[@id='Password']",pass);
        clickElemnt("//input[@class='button-1 login-button']");

        take_screanshot(driver);

        //3-Darl click en el menu appareal shoes
        clickElemnt("(//a[@href='/apparel-shoes'])[1]");
        take_screanshot(driver);

        //4-seleccionar el producto blue Jeans
        clickElemnt("//div[@class='product-item']//img[@title='Show details for Blue Jeans']");
        take_screanshot(driver);

        //5-Agregar la cantidad de 5 pantalones y dar clic en el boton add to car
        WebElement agregarcarrito = Find("//input[@id='add-to-cart-button-36']");
        for (int i = 1; i <= 5; i++){
            agregarcarrito.click();
            System.out.println("Clic numero" + i);
            Thread.sleep(1000);
        }
        take_screanshot(driver);

        //6-Validar que aparesca el mensaje "The product has been added to your shopping cart,  validar con assetions "
        WebElement mensaje = Find("//p[@class='content']");
        String TextoEsperado = "The product has been added to your shopping cart";
        String textoActual = mensaje.getText();
        Assert.assertEquals(textoActual,TextoEsperado);
        take_screanshot(driver);

        //7-dar click al link de shopong cart
        clickElemnt("//span[normalize-space()='Shopping cart']");
        take_screanshot(driver);

        //8-Marcar la opcion de acuerdo con los terminos y condiciones y dar click en el boton chekout
        clickElemnt("//input[@id='termsofservice']");
        clickElemnt("//button[@id='checkout']");
        take_screanshot(driver);

        //9-Capturar la informacion y dar clic en continuar
        Write("//input[@id='BillingNewAddress_Company']","capgemini");
        clickElemnt("//select[@id='BillingNewAddress_CountryId']");
        clickElemnt("//option[text()='Mexico']");
        Write("//input[@id='BillingNewAddress_City']","Durango");
        Write("//input[@id='BillingNewAddress_Address1']","Fracc Alamedas #456");
        Write("//input[@id='BillingNewAddress_ZipPostalCode']","34567");
        Write("//input[@id='BillingNewAddress_PhoneNumber']","1234567878");
        take_screanshot(driver);

        //10-Dar clic en el boton continuar
        clickElemnt("//input[@onclick='Billing.save()']");
        //take_screanshot(driver);

        //11-Seleccionar la opcion "Ground (10.00)" y dar click en el boton continuar
        clickElemnt("//input[@id='PickUpInStore']");
        clickElemnt("//input[@onclick='Shipping.save()']");
        take_screanshot(driver);

        //12-Seleccionar la opcion tarjeta de credito y dar click en continuar
        clickElemnt("//input[@id='paymentmethod_2']");
        clickElemnt("//input[@class='button-1 payment-method-next-step-button']");
        take_screanshot(driver);

        //13-Capturar todos los datos solicitados y dar clic en continuar
        Write("//input[@id='CardholderName']","Abraham");
        Write("//input[@id='CardNumber']","4013 5406 8274 6260");
        Write("//input[@id='CardCode']","356");
        clickElemnt("//input[@onclick='PaymentInfo.save()']");
        take_screanshot(driver);

        //14- Confirmar orden
        //Validar que los metosos que aparecen en payment method y shoping method
        //seab los mismos que seleccionamos en las secciones anteriores
        //Validar que la suma del Subtotal + shoping tax = Total
        //Dar click en el boton "confirm"

        String subtotal = driver.findElement(By.xpath("(//span[@class='product-price'])[1]")).getText();
        String Shoping = GetText("(//span[@class='product-price'])[2]");
        String tax = GetText("(//span[@class='product-price'])[3]");

        double sub = Double.parseDouble(subtotal);
        double shop = Double.parseDouble(Shoping);
        double tax2 = Double.parseDouble(tax);
        double compra = sub + shop + tax2;
        String total = GetText("//span[@class='product-price order-total']");
        double total2 = Double.parseDouble(total);

        Assert.assertEquals(compra,total2,1e-6);
        clickElemnt("//input[@value='Confirm']");
        take_screanshot(driver);

        //15-validar que aparezca el mensaje " " y dar click en el boton continuar
        String orden = GetText("//strong[normalize-space()='Your order has been successfully processed!']");
        String ordenEsp = "Your order has been successfully processed!";
        Assert.assertEquals(ordenEsp,orden);
        clickElemnt("//input[@value='Continue']");
        //take_screanshot(driver);

        //16-Dar click en logout
        clickElemnt("//a[normalize-space()='Log out']");
        take_screanshot(driver);

        Thread.sleep(4000);

    }

    //Espacio para ingresar metodo de Screanshot
    public static void take_screanshot(WebDriver driver) throws IOException {
        //metodo para tomar captura de pantalla y que el nombre sea dinamico
        //Genera un nombre unico con fecha y hora
        String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        String filename = "Screanshot" + timestamp + ".png";

        //Guardar la captura en una ubicacion especifica
        File Scranshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        File destinoFile = new File("capturas\\" + filename);
        FileUtils.copyFile(Scranshot, destinoFile);

    }

    @Test(priority =2)
    public void DeleteAdressTest() throws InterruptedException {
        String email= "harcorrs@gmail.com";
        String pass ="jamon157";
        clickElemnt("//a[@class='ico-login']");
        Write("//input[@id='Email']",email);
        Write("//input[@id='Password']",pass);
        clickElemnt("//input[@class='button-1 login-button']");

        //eliminar direccion guardada
        clickElemnt("(//a[@class='account'])[1]");
        clickElemnt("//a[@class='inactive'][normalize-space()='Addresses']");
        clickElemnt("//input[@value='Delete']");

        driver.switchTo().alert().accept();
        Thread.sleep(10000);

    }

    @AfterTest
    public void closeBrowser(){
        if (driver != null){
            driver.quit();
        }


}
}
