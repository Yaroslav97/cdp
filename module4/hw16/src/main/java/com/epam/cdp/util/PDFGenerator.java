package com.epam.cdp.util;

import com.epam.cdp.model.Ticket;
import com.lowagie.text.*;
import com.lowagie.text.pdf.PdfWriter;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.List;

public class PDFGenerator {

    public static void generatePDFEventReport(List<Ticket> list) throws DocumentException, FileNotFoundException {

        Document document = new Document();
        PdfWriter.getInstance(document, new FileOutputStream("src/main/resources/pdf/test.pdf"));

        document.open();
        Paragraph header = new Paragraph(new Chunk("Events", FontFactory.getFont(FontFactory.HELVETICA, 30)));
        document.add(header);

        for (Ticket ticket : list) {
            Paragraph by = new Paragraph(new Chunk("ID: " + ticket.getId()
                    + ", event ID: " + ticket.getEventId()
                    + ", user ID: " + ticket.getUserId()
                    + ", category: " + ticket.getCategory()
                    + ", place: " + ticket.getPlace(),
                    FontFactory.getFont(FontFactory.HELVETICA, 20)));

            document.add(by);
        }
        document.close();

    }

}
