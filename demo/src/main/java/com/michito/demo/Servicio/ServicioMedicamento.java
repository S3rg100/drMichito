package com.michito.demo.Servicio;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.michito.demo.Entidades.Medicamento;
import com.michito.demo.Repositorio.MedicamentoRepositorio;

import jakarta.annotation.PostConstruct;

@Service
public class ServicioMedicamento {

    @Autowired
    private MedicamentoRepositorio medicamentoRepositorio;

    @PostConstruct
    public void init() {
        try {
            cargarDatosDesdeExcel();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void cargarDatosDesdeExcel() throws Exception {
        // Carga el archivo Excel desde la carpeta resources
        InputStream inputStream = getClass().getClassLoader().getResourceAsStream("data/MEDICAMENTOS_VETERINARIA.xlsx");

        // Verifica si el archivo existe
        if (inputStream == null) {
            throw new RuntimeException("No se encontró el archivo medicamentos.xlsx en la carpeta resources");
        }

        List<Medicamento> medicamentos = new ArrayList<>();
        Workbook workbook = new XSSFWorkbook(inputStream);
        Sheet sheet = workbook.getSheetAt(0); // Accede a la primera hoja

        // Itera sobre las filas, comenzando desde la fila 1 (ignorando la fila de encabezado)
        for (int i = 1; i < sheet.getPhysicalNumberOfRows(); i++) {
            Row row = sheet.getRow(i);

            String nombre = row.getCell(0).getStringCellValue();
            double precioVenta = row.getCell(1).getNumericCellValue();
            double precioCompra = row.getCell(2).getNumericCellValue();
            int unidadesDisponibles = (int) row.getCell(3).getNumericCellValue();
            int unidadesVendidas = (int) row.getCell(4).getNumericCellValue();

            // Crea una nueva instancia de Medicamento
            Medicamento medicamento = new Medicamento(nombre, precioVenta, precioCompra, unidadesDisponibles, unidadesVendidas);

            // Agrega el medicamento a la lista
            medicamentos.add(medicamento);
        }

        // Guarda la lista de medicamentos en la base de datos
        medicamentoRepositorio.saveAll(medicamentos);

        // Cierra el workbook
        workbook.close();
    }
}