package com.kodilla.hibernate.invoice.dao;

import com.kodilla.hibernate.invoice.Invoice;
import com.kodilla.hibernate.invoice.Item;
import com.kodilla.hibernate.invoice.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

@SpringBootTest
public class InvoiceDaoTestSuite {

    @Autowired
    private InvoiceDao invoiceDao;

    @Autowired
    private ProductDao productDao;


    @Test
    void testInvoiceDaoSave(){
        //Given
        Product product1 = new Product("Laptop");
        Product product2 = new Product("Mouse");

        productDao.save(product1);
        productDao.save(product2);

        Item item1 = new Item(product1, new BigDecimal("3000"), 1);
        Item item2 = new Item(product2, new BigDecimal("100"), 2);

        Invoice invoice = new Invoice("INV/01/2026");
        invoice.addItem(item1);
        invoice.addItem(item2);

        //When
        invoiceDao.save(invoice);
        int invoice_id = invoice.getId();
        int invoice_id_item1 = item1.getInvoice().getId();
        int invoice_id_item2 = item2.getInvoice().getId();
        int items_on_invoice = invoice.getItems().size();


        //Then
        assertNotEquals(0, invoice_id);
        assertEquals(2, invoice.getItems().size());
        assertEquals(invoice_id, invoice_id_item1);
        assertEquals(invoice_id, invoice_id_item2);
        assertEquals(2, items_on_invoice);

        //CleanUp
        invoiceDao.deleteById(invoice_id);
        productDao.deleteById(product1.getId());
        productDao.deleteById(product2.getId());



    }
}
