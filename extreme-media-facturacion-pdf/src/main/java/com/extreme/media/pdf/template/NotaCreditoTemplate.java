package com.extreme.media.pdf.template;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;



public class NotaCreditoTemplate {

	public void test() {

		/*
		 * JasperPrint jasperPrint = JasperFillManager.
		 * fillReport("C:\\Users\\Ecodeup\\JaspersoftWorkspace\\Reportes Escuela\\ReporteAlumnos.jasper"
		 * ,null,null); JRPdfExporter exp = new JRPdfExporter();
		 * exp.setExporterInput(new SimpleExporterInput(jasperPrint));
		 * exp.setExporterOutput(new
		 * SimpleOutputStreamExporterOutput("ReporteAlumnos.pdf"));
		 * SimplePdfExporterConfiguration conf = new
		 * SimplePdfExporterConfiguration(); exp.setConfiguration(conf);
		 * exp.exportReport();
		 */
	}

	public static void execute() {
		//try {
			// Crear el mapa de parametros
			/*Map<String, Object> parameters = new HashMap<String, Object>();
			parameters.put("variable", new String("Este es un String para pasar por parametro"));
			InputStream reportStream = new FileInputStream("C:\\reportes\\notaCreditoFinal.jrxml");

			// Iniciar reporte
			JasperReport report = JasperCompileManager.compileReport(reportStream);
			JasperPrint jasperPrint = new JasperPrint();

			// Llenar el reporte donde se le pasa en el tercer argumento el mapa
			// ya creado
			JasperFillManager.fillReportToFile(report, "C:\\reportes\\reporte.jrprint",
					(Map<String, Object>) parameters, new JREmptyDataSource());
			reportStream.close();

			// Generar PDF
			List listJasper = new ArrayList();
			listJasper.add(JRLoader.loadObjectFromFile("C:\\reportes\\reporte.jrprint"));
			JRPdfExporter exp = new JRPdfExporter();
			exp.setParameter(JRExporterParameter.JASPER_PRINT_LIST, listJasper);
			exp.setParameter(JRPdfExporterParameter.IS_CREATING_BATCH_MODE_BOOKMARKS, Boolean.TRUE);
			exp.setParameter(JRExporterParameter.OUTPUT_FILE_NAME, "C:\\reportes\\reporte.pdf");
			exp.exportReport();

		} catch (Exception e) {
			e.printStackTrace();
		}*/
	}

	public void generarReporte(Connection con)
			throws SQLException, ClassNotFoundException {
		/*String OUT_PUT = "C:\\reportes\\myreport.docx";
		Map<String, Object> parameters = new HashMap<String, Object>();
		parameters.put("RUC", "EJEMPLO");
		try {

			//JasperReport jr = JasperCompileManager
			//		.compileReport( getClass().getResourceAsStream("C:/reportes/notaCreditoFinal.jasper"));
			InputStream is = new FileInputStream("C:/reportes/notaCreditoFinal.jasper");
			JasperPrint jp = JasperFillManager.fillReport(is, parameters, con);
			JRDocxExporter export = new JRDocxExporter();
			export.setExporterInput(new SimpleExporterInput(jp));
			export.setExporterOutput(new SimpleOutputStreamExporterOutput(new File(OUT_PUT)));
			SimpleDocxReportConfiguration config = new SimpleDocxReportConfiguration();
			export.setConfiguration(config);
			export.exportReport();
		} catch (Exception ex) {
			// ex.printStackTrace();
			System.out.println("error"+ex);
		}*/

	}

}
