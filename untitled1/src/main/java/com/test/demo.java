package com.test;

import com.spire.pdf.FileFormat;
import com.spire.pdf.PdfDocument;

public class demo {

    public static void main(String[] args) {
        //加载PDF
        PdfDocument pdf = new PdfDocument();
        pdf.loadFromFile("D:/bengou/dmq.pdf");
//保存为Word格式
        pdf.saveToFile("D:/bengou/dmq.docx", FileFormat.DOCX);
    }
}
