package co.com.simac.sed.report.pdf;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;

import co.com.simac.sed.report.dto.KPIHistoryDTO;

public class ExportToPdf {

//	@RequestMapping(value = "/pdfreport", method = RequestMethod.GET,
//            produces = MediaType.APPLICATION_PDF_VALUE)
//    public ResponseEntity<InputStreamResource> KPIHistoryReport(@RequestParam(value = "page", defaultValue = "1") int page,
//			@RequestParam(value = "size", defaultValue = "10") int size,
//			@RequestParam(value = "initialDate", required = false) @DateTimeFormat(pattern = "dd-MM-yyyy") Date initialDate,
//			@RequestParam(value = "finalDate", required = false) @DateTimeFormat(pattern = "dd-MM-yyyy") Date finalDate) throws IOException {
//
//		PageDTO<KPIHistoryDTO> resultPage=kpiHistoryService.getRecentsKPIHistoryPageDTO(page, size,
//				initialDate, finalDate);
//        List<KPIHistoryDTO> kpiHistoryDTOList = resultPage.getResults();
//
//        ByteArrayInputStream bis = ExportToPdf.KPIHistoryReport(kpiHistoryDTOList);
//
//        HttpHeaders headers = new HttpHeaders();
//        headers.add("Content-Disposition", "inline; filename=citiesreport.pdf");
//
//        return ResponseEntity
//                .ok()
//                .headers(headers)
//                .contentType(MediaType.APPLICATION_PDF)
//                .body(new InputStreamResource(bis));
//    }
	
	public static ByteArrayInputStream KPIHistoryReport(List<KPIHistoryDTO> kpiHistoryDTOList) {
		Document document = new Document();
		ByteArrayOutputStream out = new ByteArrayOutputStream();
		try {

			PdfPTable table = new PdfPTable(4);
			table.setWidthPercentage(60);
			table.setWidths(new int[] { 3, 3, 3, 3 });

			Font headFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD);

			PdfPCell hcell;
			hcell = new PdfPCell(new Phrase("Nombre", headFont));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(hcell);

			hcell = new PdfPCell(new Phrase("Fecha Inicio", headFont));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(hcell);

			hcell = new PdfPCell(new Phrase("Fecha Fin", headFont));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(hcell);

			hcell = new PdfPCell(new Phrase("Valor", headFont));
			hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
			table.addCell(hcell);

			for (KPIHistoryDTO kpiHistoryDTO : kpiHistoryDTOList) {

				PdfPCell cell;

				cell = new PdfPCell(new Phrase(kpiHistoryDTO.getKpiIndicatorDTO().getName()));
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_CENTER);
				table.addCell(cell);

				cell = new PdfPCell(
						new Phrase(new SimpleDateFormat("dd-MM-yyyy").format(kpiHistoryDTO.getStartDate())));
				cell.setPaddingLeft(5);
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				table.addCell(cell);

				cell = new PdfPCell(new Phrase(new SimpleDateFormat("dd-MM-yyyy").format(kpiHistoryDTO.getEndDate())));
				cell.setPaddingLeft(5);
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_LEFT);
				table.addCell(cell);

				cell = new PdfPCell(new Phrase(String.valueOf(kpiHistoryDTO.getAverageValue())));
				cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
				cell.setHorizontalAlignment(Element.ALIGN_RIGHT);
				cell.setPaddingRight(5);
				table.addCell(cell);
			}

			PdfWriter.getInstance(document, out);
			document.open();
			document.add(table);
			document.close();

		} catch (DocumentException ex) {
			Logger.getLogger(ExportToPdf.class.getName()).log(Level.SEVERE, null, ex);
		}
		return new ByteArrayInputStream(out.toByteArray());
	}
}