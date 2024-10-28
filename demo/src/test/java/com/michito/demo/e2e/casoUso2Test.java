package com.michito.demo.e2e;

import java.time.Duration;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
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
public class casoUso2Test {

    private WebDriver driver;
    private WebDriverWait wait;
    private final String URL = "http://localhost:4200";

    @BeforeEach
    public void init() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions chromeOptions = new ChromeOptions();
        chromeOptions.addArguments("--disable-notifications--");
        chromeOptions.addArguments("--disable-extensions--");
        this.driver = new ChromeDriver(chromeOptions);
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
    }

    @AfterEach
    public void tearDown() {
        driver.quit();
    }

    @Test
    public void SystemTest_casoUso2Test_VeterinarioAsignaTratamiento_ConVerificacionDeGanancia()
            throws InterruptedException {
        driver.get(URL);

        // Iniciar sesión como administrador y capturar valores iniciales de
        // medicamentos y ganancias
        WebElement btnLoginPortalInterno = wait
                .until(ExpectedConditions.presenceOfElementLocated(By.id("loginPortalInternoBtn")));
        btnLoginPortalInterno.click();
        WebElement inputUsuarioAdmin = driver.findElement(By.id("usuario"));
        WebElement inputContrasenaAdmin = driver.findElement(By.id("contrasena"));
        inputUsuarioAdmin.sendKeys("admin");
        inputContrasenaAdmin.sendKeys("admin");
        WebElement btnLoginPortal = driver.findElement(By.id("btnLogin"));
        btnLoginPortal.click();

        // Navegar al apartado de medicamentos y capturar valores de interés para
        // "ACOLAN"
        wait.until(ExpectedConditions.elementToBeClickable(By.id("medicamentos")));
        WebElement botonMedicamentos = driver.findElement(By.id("medicamentos"));
        botonMedicamentos.click();
        Thread.sleep(2000);

        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("searchBarMedicamentos")));
        WebElement searchBarMedicamento = driver.findElement(By.id("searchBarMedicamentos"));
        searchBarMedicamento.sendKeys("ACOLAN");
        searchBarMedicamento.sendKeys(Keys.ENTER);
        Thread.sleep(2000);

        // Capturar unidades vendidas y valores de compra/venta de "ACOLAN"
        wait.until(ExpectedConditions.presenceOfElementLocated(By.className("medicamentoUnidadesVendidas")));
        WebElement unidadesVendidasElement = driver.findElement(By.className("medicamentoUnidadesVendidas"));
        int unidadesVendidasInicial = Integer.parseInt(unidadesVendidasElement.getText().trim());

        WebElement precioCompraElement = driver.findElement(By.className("medicamentoPrecioCompra"));
        WebElement precioVentaElement = driver.findElement(By.className("medicamentoPrecioVenta"));
        double precioCompra = Double
                .parseDouble(precioCompraElement.getText().replace("$", "").replace(",", "").trim());
        double precioVenta = Double.parseDouble(precioVentaElement.getText().replace("$", "").replace(",", "").trim());

        // Calcular ganancia esperada por una unidad adicional
        double gananciaPorUnidad = precioVenta - precioCompra;

        // Navegar al dashboard y capturar las ganancias actuales
        wait.until(ExpectedConditions.elementToBeClickable(By.id("dashboard")));
        WebElement botonDashboard = driver.findElement(By.id("dashboard"));
        botonDashboard.click();
                
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("gananciasTotalesValor")));
        WebElement gananciasTotalesElement = driver.findElement(By.id("gananciasTotalesValor"));

        Assertions.assertThat(gananciasTotalesElement).isNotNull();
        Assertions.assertThat(gananciasTotalesElement.getText()).isEqualTo("741640");
        Double gananciasIniciales = Double.parseDouble(gananciasTotalesElement.getText());

                
        // Cerrar sesión del administrador
        WebElement botonSalir = driver.findElement(By.id("salir"));
        botonSalir.click();

        // Iniciar sesión como veterinario y agregar un tratamiento
        btnLoginPortalInterno = driver.findElement(By.id("loginPortalInternoBtn"));
        btnLoginPortalInterno.click();
        WebElement inputUsuarioVet = driver.findElement(By.id("usuario"));
        WebElement inputContrasenaVet = driver.findElement(By.id("contrasena"));
        inputUsuarioVet.sendKeys("vet");
        inputContrasenaVet.sendKeys("vet");
        btnLoginPortal = driver.findElement(By.id("btnLogin"));
        btnLoginPortal.click();

        // Buscar mascota en la barra de búsqueda y acceder a su detalle
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("searchBarMascotas")));
        WebElement searchBar = driver.findElement(By.id("searchBarMascotas"));
        searchBar.sendKeys("Luna");
        searchBar.sendKeys(Keys.ENTER);

        wait.until(ExpectedConditions.presenceOfElementLocated(By.className("detalleMascota")));
        List<WebElement> botonesVer = driver.findElements(By.className("detalleMascota"));
        Assertions.assertThat(botonesVer).isNotEmpty();
        botonesVer.get(0).click();

        wait.until(ExpectedConditions.presenceOfElementLocated(By.className("btnAgregarTratamiento")));
        WebElement btnAddTreatment = driver.findElement(By.className("btnAgregarTratamiento"));
        btnAddTreatment.click();

        // Agregar el tratamiento
        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("btnAddTratamiento")));
        WebElement btnAddTratamiento = driver.findElement(By.id("btnAddTratamiento"));
        WebElement inputmascota = driver.findElement(By.id("mascota"));
        WebElement inputMedicamento = driver.findElement(By.id("medicamento"));
        WebElement inputDescripcion = driver.findElement(By.id("descripcion"));

        inputmascota.sendKeys("Luna");
        wait.until(ExpectedConditions.presenceOfElementLocated(By.className("itemMascota")));
        List<WebElement> opcionesMascotas = driver.findElements(By.className("itemMascota"));
        Assertions.assertThat(opcionesMascotas).isNotEmpty();
        opcionesMascotas.get(0).click();

        inputMedicamento.sendKeys("ACOLAN");
        wait.until(ExpectedConditions.presenceOfElementLocated(By.className("itemMedicamento")));
        List<WebElement> opcionesMedicamentos = driver.findElements(By.className("itemMedicamento"));
        Assertions.assertThat(opcionesMedicamentos).isNotEmpty();
        opcionesMedicamentos.get(0).click();

        inputDescripcion.sendKeys("Desparacitamiento para la mascota de prueba");
        btnAddTratamiento.click();
        Thread.sleep(2000);

        // Cerrar sesión del veterinario
        botonSalir = driver.findElement(By.id("salir"));
        botonSalir.click();

        // Reingresar como administrador y verificar las actualizaciones
        btnLoginPortalInterno = driver.findElement(By.id("loginPortalInternoBtn"));
        btnLoginPortalInterno.click();
        inputUsuarioAdmin = driver.findElement(By.id("usuario"));
        inputContrasenaAdmin = driver.findElement(By.id("contrasena"));
        inputUsuarioAdmin.sendKeys("admin");
        inputContrasenaAdmin.sendKeys("admin");
        btnLoginPortal = driver.findElement(By.id("btnLogin"));
        btnLoginPortal.click();

        // Navegar nuevamente al apartado de medicamentos y verificar aumento de
        // unidades vendidas
        wait.until(ExpectedConditions.elementToBeClickable(By.id("medicamentos")));
        botonMedicamentos = driver.findElement(By.id("medicamentos"));
        botonMedicamentos.click();
        Thread.sleep(2000);

        searchBarMedicamento = driver.findElement(By.id("searchBarMedicamentos"));
        searchBarMedicamento.clear();
        searchBarMedicamento.sendKeys("ACOLAN");
        searchBarMedicamento.sendKeys(Keys.ENTER);
        Thread.sleep(2000);

        unidadesVendidasElement = driver.findElement(By.className("medicamentoUnidadesVendidas"));
        int unidadesVendidasFinal = Integer.parseInt(unidadesVendidasElement.getText().trim());
        Assertions.assertThat(unidadesVendidasFinal).isEqualTo(unidadesVendidasInicial + 1);

        // Navegar nuevamente al dashboard y verificar el cambio en las ganancias
        wait.until(ExpectedConditions.elementToBeClickable(By.id("dashboard")));
        botonDashboard = driver.findElement(By.id("dashboard"));
        botonDashboard.click();


        wait.until(ExpectedConditions.presenceOfElementLocated(By.id("gananciasTotalesValor")));
        gananciasTotalesElement = driver.findElement(By.id("gananciasTotalesValor"));
        double gananciasFinales = Double
                .parseDouble(gananciasTotalesElement.getText());

        // Verificar que las ganancias finales sean iguales a las iniciales más la
        // ganancia calculada por unidad
        Assertions.assertThat(gananciasFinales).isEqualTo(gananciasIniciales + gananciaPorUnidad);
    }

}
