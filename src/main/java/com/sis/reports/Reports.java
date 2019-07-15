package com.sis.reports;

import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.expression.ParseException;
import com.ibm.icu.util.GregorianCalendar;

import net.sf.jasperreports.engine.JREmptyDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;

public class Reports {

	private static final String INGRESO_TEXT = "INGRESO";
	private static final String PDF_REPORTS_DIR = "src/main/java/com/sis/reports/";
	private static final String REPORT_TEMPLATE_FILENAME = "https://firebasestorage.googleapis.com/v0/b/body-fitnes-gym.appspot.com/o/progresos.jrxml?alt=media&token=81a199bd-a7f6-4a3a-9856-fcee3a3e375f";
	private static final String REPORT_TEMPLATE_CONTABILIDAD = "https://firebasestorage.googleapis.com/v0/b/body-fitnes-gym.appspot.com/o/Blank_A4.jrxml?alt=media&token=c56ad74b-01e1-4278-83b8-e57a12e21c72";
	private static final String REPORT_TEMPLATE_LIST_STUDENT_BY_SCHEDULE = "https://firebasestorage.googleapis.com/v0/b/body-fitnes-gym.appspot.com/o/ListStudent.jrxml?alt=media&token=4115cb1b-c6db-4f89-a961-a0fd3a531448";

	/**
	 * Retorna un PDF con los progresos del alumno, el alumno debe tener una lista
	 * de progresos.
	 * 
	 * @param alumno El alumno al que se le quiere generar sus progresos
	 * @return PDF en un vector de bytes
	 * @throws ClassNotFoundException
	 * @throws IOException
	 * @throws ParseException
	 * @throws JRException
	 */
//	public static byte[] generarProgresosPDF(Alumno alumno)
//			throws ClassNotFoundException, IOException, ParseException, JRException {
//		URL url = new URL(REPORT_TEMPLATE_FILENAME);
//		JasperReport jasperReport = JasperCompileManager.compileReport(url.openStream());
//		Map<String, Object> parameters = new HashMap<String, Object>();
//		List<ProgresoImagen> imagens = alumno.getHistorialProgresoImagen();
//		Collections.sort(imagens, new Comparator<ProgresoImagen>() {
//			public int compare(ProgresoImagen o1, ProgresoImagen o2) {
//				return o1.getFechaProgresoImagen().compareTo(o2.getFechaProgresoImagen());
//			}
//		});
//
//		parameters.put("NOMBRE", alumno.getNombreAlumno());
//		parameters.put("urlImgUser", alumno.getUrlImagenUsuario());
//		parameters.put("CHART_DATASET", new JRBeanCollectionDataSource(imagens));
//		parameters.put("CHART_DATASET_GRASA", new JRBeanCollectionDataSource(imagens));
//		parameters.put("urlFirtsIMG", imagens.get(0).getUrl());
//		parameters.put("urlSecondIMG", imagens.get(imagens.size() - 1).getUrl());
//		JasperPrint print = JasperFillManager.fillReport(jasperReport, parameters, new JREmptyDataSource());
//		// Make sure the output directory exists
//
//		// +persona.getDNI()+persona.getPrimerApellido()+"_"+persona.getPrimerNombre()+"_Carnet.pdf";
//		return JasperExportManager.exportReportToPdf(print);
//	}
//
//	public static byte[] generarMovimientoPDF(List<MovimientoCaja> movimientos)
//			throws ClassNotFoundException, IOException, ParseException, JRException {
//		URL url = new URL(REPORT_TEMPLATE_CONTABILIDAD);
//		JasperReport jasperReport = JasperCompileManager.compileReport(url.openStream());
//		Map<String, Object> parameters = new HashMap<String, Object>();
//
//		parameters.put("DatasetTable", new JRBeanCollectionDataSource(movimientos));
//		parameters.put("TOTAL", getTotal(movimientos));
//		JasperPrint print = JasperFillManager.fillReport(jasperReport, parameters, new JREmptyDataSource());
//		// Make sure the output directory exists
//		return JasperExportManager.exportReportToPdf(print);
//
//	}
//
//	/**
//	 * Genera una lista de los alumno que estan inscritos para la asistencia en ese
//	 * determinado horario
//	 * 
//	 * @param horario
//	 * @return pdf con la lista de alumnos en un byte[]
//	 * @throws ClassNotFoundException
//	 * @throws IOException
//	 * @throws ParseException
//	 * @throws JRException
//	 */
//	public static byte[] generarListaAlumnosPorHorarioPDF(Horario horario)
//			throws ClassNotFoundException, IOException, ParseException, JRException {
//		URL url = new URL(REPORT_TEMPLATE_LIST_STUDENT_BY_SCHEDULE);
//		JasperReport jasperReport = JasperCompileManager.compileReport(url.openStream());
//		Map<String, Object> parameters = new HashMap<String, Object>();
//
//		parameters.put("LIST_STUDENT", new JRBeanCollectionDataSource(horario.getAsistencia()));
//		parameters.put("FECHA_HORARIO", horario.getHoraInicio());
//		JasperPrint print = JasperFillManager.fillReport(jasperReport, parameters, new JREmptyDataSource());
//		// Make sure the output directory exists
//		return JasperExportManager.exportReportToPdf(print);
//
//	}
//
//	public static byte[] getPDFAsByte(String filePath) throws IOException {
//		Path pdfPath = Paths.get(filePath);
//		return Files.readAllBytes(pdfPath);
//	}
//
//	private static double getTotal(List<MovimientoCaja> movimientoCajas) {
//		double count = 0;
//		for (MovimientoCaja movimientoCaja : movimientoCajas) {
//			if (movimientoCaja.getTipo().equalsIgnoreCase(INGRESO_TEXT)) {
//				count += movimientoCaja.getValor();
//			} else {
//				count -= movimientoCaja.getValor();
//			}
//		}
//		return count;
//	}
//
	}
