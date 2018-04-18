package com.epam.cdp.controller;

import com.epam.cdp.facade.BookingFacade;
import com.epam.cdp.model.Event;
import com.epam.cdp.util.PDFGenerator;
import com.epam.cdp.util.exception_resolver.ErrorView;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_PDF_VALUE;

@Controller
@RequestMapping("/")
public class BookingController {

    private Logger log = LoggerFactory.getLogger(BookingController.class);

    @Autowired
    private BookingFacade bookingFacade;

    @RequestMapping(value = "/events/{event}", method = RequestMethod.GET)
    public ModelAndView getEventsByEventTitle(@PathVariable String event) {
        List<Event> list = bookingFacade.getEventsByTitle(event, 10, 0);
        log.info("List of events with title: " + event + ", " + list);

        ModelAndView modelAndView = new ModelAndView("index");
        modelAndView.addObject("events", list);
        return modelAndView;
    }

    @RequestMapping(value = "/user/{id}/tickets/pdf", method = RequestMethod.GET, consumes = {APPLICATION_PDF_VALUE} )
    public ResponseEntity<InputStreamResource> getPDFReport(@PathVariable long id) throws Exception {
        PDFGenerator.generatePDFEventReport(bookingFacade.getBookedTickets(bookingFacade.getUserById(id), 1, 0));
        ClassPathResource pdfFile = new ClassPathResource("pdf/test.pdf");
        log.info("Generated pdf report for user: " + id);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType("application/pdf"));
        headers.add("Access-Control-Allow-Origin", "*");
        headers.add("Access-Control-Allow-Methods", "GET, POST, PUT");
        headers.add("Access-Control-Allow-Headers", "Content-Type");
        headers.add("Content-Disposition", "filename=" + "test.pdf");
        headers.add("Cache-Control", "no-cache, no-store, must-revalidate");
        headers.add("Pragma", "no-cache");
        headers.add("Expires", "0");

        headers.setContentLength(pdfFile.contentLength());
        ResponseEntity<InputStreamResource> response = new ResponseEntity<InputStreamResource>(
                new InputStreamResource(pdfFile.getInputStream()), headers, HttpStatus.OK);
        return response;
    }

    @ErrorView(value = "error", status = HttpStatus.GONE)
    @RequestMapping("/hello")
    public ModelAndView welcome() throws Exception {
        log.error("custom HandlerExceptionResolver test");
        throw new Exception();
    }

}
