package com.michito.demo.e2e;
import static org.junit.Assert.assertThat;

import java.time.*;
import java.util.*;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
//
import org.openqa.selenium.chrome.ChromeDriver;
//
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;

import io.github.bonigarcia.wdm.WebDriverManager;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
@ActiveProfiles("test")
@DirtiesContext
public class casoUso1Test {
    private WebDriver driver;
    private WebDriverWait wait;
    private final String URL = "http://localhost:4200";

    @BeforeEach
    public void init(){
        WebDriverManager.chromedriver().setup();
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--disable-notifications--");
        chromeOptions.addArguments("--disable-extensions--");
        this.driver = new ChromeDriver(chromeOptions);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }
    @AfterEach
    public void tearDown(){
        driver.quit();
    }

    @Test
    public void SystemTest_casoUso1Test(){
        //Veterinario intenta ingresar al sistema y se equivoca
            driver.get(URL);
            wait.until(ExpectedConditions.presenceOfElementLocated(By.id("loginPortalInternoBtn")));
            WebElement btnLoginPorttalInterno = driver.findElement(By.id("loginPortalInternoBtn"));
            btnLoginPorttalInterno.click();
            WebElement inputUsuarioVet = driver.findElement(By.id("usuario"));
            WebElement inputContrasenaoVet = driver.findElement(By.id("contrasena"));
            inputUsuarioVet.sendKeys("abc");
            inputContrasenaoVet.sendKeys("ABC");
            WebElement btnLoginPorttal = driver.findElement(By.id("btnLogin"));
            btnLoginPorttal.click();
            wait.until(ExpectedConditions.presenceOfElementLocated(By.id("ErrorLogin")));
            WebElement ErrorLogin = driver.findElement(By.id("ErrorLogin"));
            Assertions.assertThat(ErrorLogin.getText()).isEqualTo("Error: El nombre de usuario o la contraseña son incorrectos.");
        //Veterinario entra al sistema
            for(int i=0;i<3;i++){
                inputUsuarioVet.sendKeys(Keys.BACK_SPACE);
                inputContrasenaoVet.sendKeys(Keys.BACK_SPACE);
            }
            inputUsuarioVet.sendKeys("vet");
            inputContrasenaoVet.sendKeys("vet");
            btnLoginPorttal.click();
            wait.until(ExpectedConditions.presenceOfElementLocated(By.className("idMascotas")));
            List<WebElement> tablaMascotas = driver.findElements(By.className("idMascotas"));
            Assertions.assertThat(tablaMascotas).isNotNull();
            Assertions.assertThat(tablaMascotas.size()).isGreaterThan(0);
        //Veterinario intenta registrar cliente y se equivoca
            WebElement btnClientes = driver.findElement(By.id("clientes"));
            btnClientes.click();
            WebElement CrearClientebtn = driver.findElement(By.id("CrearClientebtn"));
            CrearClientebtn.click();
            wait.until(ExpectedConditions.presenceOfElementLocated(By.id("Add")));
            WebElement btnAdd = driver.findElement(By.id("Add"));
            WebElement inputCedula = driver.findElement(By.id("cedula"));
            WebElement inputNombre = driver.findElement(By.id("nombre"));
            WebElement inputCorreo = driver.findElement(By.id("correo"));
            WebElement inputCelilar = driver.findElement(By.id("celular"));
            inputCedula.sendKeys("321456");
            inputNombre.sendKeys("PEDRO");
            inputCorreo.sendKeys("Pedro@gmail.com");
            inputCelilar.sendKeys(Keys.BACK_SPACE);
            inputCelilar.sendKeys("1234");        
            btnAdd.click();
            wait.until(ExpectedConditions.presenceOfElementLocated(By.id("ERRORCLIENTE")));
            WebElement resp = driver.findElement(By.id("ERRORCLIENTE"));
            Assertions.assertThat(resp.getText()).isEqualTo("Ya existe un cliente con la cédula 321456");
        //Veterinario registra un cliente satisfactoriamente
            for(int i=0;i<6;i++)    
                inputCedula.sendKeys(Keys.BACK_SPACE);
            inputCedula.sendKeys("999");
            btnAdd.click();
            wait.until(ExpectedConditions.presenceOfElementLocated(By.className("idClientes")));
            List<WebElement> list = driver.findElements(By.className("idClientes"));
            Assertions.assertThat(list.size()).isEqualTo(5);
        //Veterinario asocia una mascota al cliente registrado
            WebElement btnMascotas = driver.findElement(By.id("mascotas"));
            btnMascotas.click();
            WebElement btnAddMascotas = driver.findElement(By.id("btnAddMascotas"));
            btnAddMascotas.click();
            wait.until(ExpectedConditions.presenceOfElementLocated(By.id("ADDMascota")));
            WebElement btnAddMascota = driver.findElement(By.id("ADDMascota"));        
            WebElement inputCedulaCliente = driver.findElement(By.id("cedulaCliente"));
            WebElement inputNombreMascota = driver.findElement(By.id("nombre"));
            WebElement inputEdadMascota = driver.findElement(By.id("edad"));
            WebElement inputPesoMascota = driver.findElement(By.id("peso"));
            WebElement inputFotoMascota = driver.findElement(By.id("foto"));
            inputEdadMascota.sendKeys(Keys.BACK_SPACE);
            inputPesoMascota.sendKeys(Keys.BACK_SPACE);
            inputCedulaCliente.sendKeys("999");
            inputNombreMascota.sendKeys("garfield");
            inputEdadMascota.sendKeys("2");
            inputPesoMascota.sendKeys("2");
            inputFotoMascota.sendKeys("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRoN0ClLdF0KguaLrjxctlMQEwLFpmeiL7bKA&s");
            btnAddMascota.click();
            wait.until(ExpectedConditions.presenceOfElementLocated(By.className("idMascotas")));
            List<WebElement> listMascotas = driver.findElements(By.className("idMascotas"));
            Assertions.assertThat(listMascotas.size()).isEqualTo(5);
        //Cliente ingresa y ve los datos de su mascota
            WebElement btnSalir = driver.findElement(By.id("salir"));
            btnSalir.click();
            WebElement loginCTE = driver.findElement(By.id("loginCTE"));
            loginCTE.click();
            WebElement inputCedulaLogin = driver.findElement(By.id("cedula"));
            inputCedulaLogin.sendKeys("999");
            WebElement loginCTEinto = driver.findElement(By.id("LoginCTEinto"));
            loginCTEinto.click();

            wait.until(ExpectedConditions.presenceOfElementLocated(By.className("BtnDetallesMascota")));
            List<WebElement> BtnDetallesMascota = driver.findElements(By.className("BtnDetallesMascota"));
            int x =BtnDetallesMascota.size();
            x--;
            BtnDetallesMascota.get(x).click();
            wait.until(ExpectedConditions.presenceOfElementLocated(By.className("NombreMascota")));
            wait.until(ExpectedConditions.presenceOfElementLocated(By.className("PesoMascota")));
            wait.until(ExpectedConditions.presenceOfElementLocated(By.className("EdadMascota")));
            wait.until(ExpectedConditions.presenceOfElementLocated(By.className("foto_mascota")));
            WebElement DatoNombreMascota = driver.findElement(By.className("NombreMascota"));
            WebElement DatoPesoMascota = driver.findElement(By.className("PesoMascota"));
            WebElement DatoEdadMascota = driver.findElement(By.className("EdadMascota"));
            WebElement DatoFotoMascota = driver.findElement(By.className("foto_mascota"));
            Assertions.assertThat(DatoNombreMascota.getText()).isEqualTo("garfield");
            Assertions.assertThat(DatoPesoMascota.getText()).isEqualTo("2 kg");
            Assertions.assertThat(DatoEdadMascota.getText()).isEqualTo("2 años");
            String fotoSrc = DatoFotoMascota.getAttribute("src");
            String expectedLink = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRoN0ClLdF0KguaLrjxctlMQEwLFpmeiL7bKA&s";
            Assertions.assertThat(fotoSrc).isEqualTo(expectedLink);
            
    }
    

    //PRUEBAS SEPARADAS POR PARTES
    //Veterinario intenta ingresar al sistema y se equivoca
    @Test
    public void SystemTest_casoUso1Test_VeterinarioLoginFail(){
        driver.get(URL);

        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("loginPortalInternoBtn")));

        WebElement btnLoginPorttalInterno = driver.findElement(By.id("loginPortalInternoBtn"));
        btnLoginPorttalInterno.click();
        WebElement inputUsuarioVet = driver.findElement(By.id("usuario"));
        WebElement inputContrasenaoVet = driver.findElement(By.id("contrasena"));
        inputUsuarioVet.sendKeys("abc");
        inputContrasenaoVet.sendKeys("ABC");
        WebElement btnLoginPorttal = driver.findElement(By.id("btnLogin"));
        btnLoginPorttal.click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("ErrorLogin")));
        WebElement ErrorLogin = driver.findElement(By.id("ErrorLogin"));
        Assertions.assertThat(ErrorLogin.getText()).isEqualTo("Error: El nombre de usuario o la contraseña son incorrectos.");
    }
    //Veterinario entra al sistema
    @Test
    public void SystemTest_casoUso1Test_VeterinarioLogin(){
        driver.get(URL+"/loginPortalInterno");//NO VA
        WebElement inputUsuarioVet = driver.findElement(By.id("usuario"));
        WebElement inputContrasenaoVet = driver.findElement(By.id("contrasena"));
        inputUsuarioVet.sendKeys("vet");
        inputContrasenaoVet.sendKeys("vet");
        WebElement btnLoginPorttal = driver.findElement(By.id("btnLogin"));
        btnLoginPorttal.click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.className("idMascotas")));
        List<WebElement> tablaMascotas = driver.findElements(By.className("idMascotas"));
        Assertions.assertThat(tablaMascotas).isNotNull();
        Assertions.assertThat(tablaMascotas.size()).isGreaterThan(0);

    }
    //Veterinario intenta registrar cliente y se equivoca
    @Test
    public void SystemTest_casoUso1Test_ClienteCreateFail(){
        driver.get(URL+"/Clientes");//NO VA
        
        WebElement CrearClientebtn = driver.findElement(By.id("CrearClientebtn"));
        CrearClientebtn.click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("Add")));
        WebElement btnAdd = driver.findElement(By.id("Add"));
        WebElement inputCedula = driver.findElement(By.id("cedula"));
        WebElement inputNombre = driver.findElement(By.id("nombre"));
        WebElement inputCorreo = driver.findElement(By.id("correo"));
        WebElement inputCelilar = driver.findElement(By.id("celular"));
        inputCedula.sendKeys("321456");
        inputNombre.sendKeys("PEDRO");
        inputCorreo.sendKeys("Pedro@gmail.com");
        inputCelilar.sendKeys(Keys.BACK_SPACE);
        inputCelilar.sendKeys("1234");        
        btnAdd.click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("ERRORCLIENTE")));
        WebElement resp = driver.findElement(By.id("ERRORCLIENTE"));
        Assertions.assertThat(resp.getText()).isEqualTo("Ya existe un cliente con la cédula 321456");

    }
    //Veterinario registra un cliente
    @Test
    public void SystemTest_casoUso1Test_ClienteCreate(){
        driver.get(URL+"/Clientes");//NO VA
        
        WebElement CrearClientebtn = driver.findElement(By.id("CrearClientebtn"));
        CrearClientebtn.click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("Add")));
        WebElement btnAdd = driver.findElement(By.id("Add"));
        WebElement inputCedula = driver.findElement(By.id("cedula"));
        WebElement inputNombre = driver.findElement(By.id("nombre"));
        WebElement inputCorreo = driver.findElement(By.id("correo"));
        WebElement inputCelilar = driver.findElement(By.id("celular"));
        inputCedula.sendKeys("999");
        inputNombre.sendKeys("PEDRO");
        inputCorreo.sendKeys("Pedro@gmail.com");
        inputCelilar.sendKeys(Keys.BACK_SPACE);
        inputCelilar.sendKeys("1234");        
        btnAdd.click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.className("idClientes")));
        List<WebElement> list = driver.findElements(By.className("idClientes"));
        Assertions.assertThat(list.size()).isEqualTo(5);

    }
    //Veterinario asocia una mascota al cliente registrado
    @Test
    public void SystemTest_casoUso1Test_MascotaCreate(){
        driver.get(URL+"/Mascotas");//NO VA
        WebElement btnAddMascotas = driver.findElement(By.id("btnAddMascotas"));
        btnAddMascotas.click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("ADDMascota")));
        WebElement btnAddMascota = driver.findElement(By.id("ADDMascota"));        
        WebElement inputCedulaCliente = driver.findElement(By.id("cedulaCliente"));
        WebElement inputNombreMascota = driver.findElement(By.id("nombre"));
        WebElement inputEdadMascota = driver.findElement(By.id("edad"));
        WebElement inputPesoMascota = driver.findElement(By.id("peso"));
        WebElement inputFotoMascota = driver.findElement(By.id("foto"));
        inputEdadMascota.sendKeys(Keys.BACK_SPACE);
        inputPesoMascota.sendKeys(Keys.BACK_SPACE);
        inputCedulaCliente.sendKeys("321456");
        inputNombreMascota.sendKeys("garfield");
        inputEdadMascota.sendKeys("2");
        inputPesoMascota.sendKeys("2");
        inputFotoMascota.sendKeys("https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcRoN0ClLdF0KguaLrjxctlMQEwLFpmeiL7bKA&s");
        btnAddMascota.click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.className("idMascotas")));
        List<WebElement> listMascotas = driver.findElements(By.className("idMascotas"));
        
        Assertions.assertThat(listMascotas.size()).isEqualTo(5);
        


    }
    //Cliente ingresa y ve los datos de su mascota
    @Test
    public void SystemTest_casoUso1Test_MascotasRead(){
        driver.get(URL);//NO VA
        WebElement loginCTE = driver.findElement(By.id("loginCTE"));
        loginCTE.click();
        WebElement inputCedulaLogin = driver.findElement(By.id("cedula"));
        inputCedulaLogin.sendKeys("321456");
        WebElement loginCTEinto = driver.findElement(By.id("LoginCTEinto"));
        loginCTEinto.click();

        wait.until(ExpectedConditions.presenceOfElementLocated(By.className("BtnDetallesMascota")));
        List<WebElement> BtnDetallesMascota = driver.findElements(By.className("BtnDetallesMascota"));
        int x =BtnDetallesMascota.size();
        x--;
        BtnDetallesMascota.get(x).click();
        wait.until(ExpectedConditions.presenceOfElementLocated(By.className("NombreMascota")));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.className("PesoMascota")));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.className("EdadMascota")));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.className("foto_mascota")));
        WebElement DatoNombreMascota = driver.findElement(By.className("NombreMascota"));
        WebElement DatoPesoMascota = driver.findElement(By.className("PesoMascota"));
        WebElement DatoEdadMascota = driver.findElement(By.className("EdadMascota"));
        WebElement DatoFotoMascota = driver.findElement(By.className("foto_mascota"));
        Assertions.assertThat(DatoNombreMascota.getText()).isEqualTo("Toby");
        Assertions.assertThat(DatoPesoMascota.getText()).isEqualTo("12 kg");
        Assertions.assertThat(DatoEdadMascota.getText()).isEqualTo("2 años");
        String fotoSrc = DatoFotoMascota.getAttribute("src");
        String expectedLink = "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTJyLofiao7YQ07txy7F9i3wuwyb9cjtEWEGg&s";
        Assertions.assertThat(fotoSrc).isEqualTo(expectedLink);

    }

    
    

}
