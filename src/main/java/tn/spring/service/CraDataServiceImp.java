package tn.spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tn.spring.dao.CraDataDao;
import tn.spring.entity.CraData;


import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;




@Service
public class CraDataServiceImp {
    @Autowired
    private CraDataDao craDataDao;

    public List<CraData> getAllCraDatas() {
        return craDataDao.findAll();
    }

    public Optional<CraData> getCraDataById(Long id) {
        return craDataDao.findById(id);
    }

    public CraData createCaraData(CraData craData) {
        return craDataDao.save(craData);
    }

    public CraData updateCraData(Long id, CraData updatedComment) {
        Optional<CraData> existingComment = craDataDao.findById(id);
        if (existingComment.isPresent()) {
            CraData craData = existingComment.get();
            craData.setChoixClient(updatedComment.getChoixClient());
            craData.setChoixProjet(updatedComment.getChoixProjet());
            craData.setTextInputs(updatedComment.getTextInputs());
            return craDataDao.save(craData);
        } else {
            // Gérez le cas où le commentaire n'existe pas
            return null;
        }
    }

    public void deleteCraData(Long id) {
        craDataDao.deleteById(id);
    }

    public List<CraData> findByDate(Date date) {
        return craDataDao.findCraDataByDate(date);
    }

    public List<CraData> findByUserId(Long userId) {
        return craDataDao.findByUserId(userId);
    }

    public List<CraData> findByYearAndMonth(int year, int month) {
        return craDataDao.findByYearAndMonth(year, month);
    }

    public List<CraData> findByYear(int year) {
        return craDataDao.findByYear(year);
    }

    public List<CraData> findByMonth(int month) {
        return craDataDao.findByMonth(month);
    }


    public ByteArrayInputStream craPDFReport(List<CraData> craData) throws MalformedURLException, IOException {

        Document document = new Document();
        ByteArrayOutputStream out = new ByteArrayOutputStream();

        try {

            PdfWriter.getInstance(document, out);

            document.open();

            // -----------------//

            // Creates a table
            PdfPTable table6 = new PdfPTable(3);
            table6.setWidthPercentage(100);

            PdfPCell cell2 = new PdfPCell(new Phrase("Facture N°:64482"));
            cell2.setHorizontalAlignment(Element.ALIGN_CENTER);
            cell2.setBorderColor(BaseColor.WHITE);

            // Creates an image and set it as cell's content
          /*  URL resource = VenteService.class.getResource("/java.png");
            Image image = Image.getInstance(Objects.requireNonNull(resource));
            PdfPCell cell1 = new PdfPCell(image, true);
            cell1.setFixedHeight(40);
            cell1.setBorderColor(BaseColor.WHITE);
*/
            // Creates date and set it as cell's content
            PdfPCell cell3 = new PdfPCell(new Phrase("Date:"));
            cell3.setBorderColor(BaseColor.WHITE);
            cell3.setHorizontalAlignment(Element.ALIGN_CENTER);

            // Add cells to table
           // table6.addCell(cell1);
            table6.addCell(cell2);
            table6.addCell(cell3);

            document.add(table6);
            // -----------------//

            Font mainFont = FontFactory.getFont("Arial", 10, BaseColor.BLACK);
            mainFont.setColor(BaseColor.BLUE);
            Paragraph paragraph3 = new Paragraph();
            Paragraph paragraph = new Paragraph("", mainFont);

            paragraph.setAlignment(Element.ALIGN_CENTER);
            paragraph.setIndentationLeft(50);
            paragraph.setIndentationRight(10f);
            paragraph.setSpacingAfter(10);
            document.add(paragraph);

            Font mainFont1 = FontFactory.getFont("Arial", 10, BaseColor.BLACK);
            mainFont1.setColor(BaseColor.BLUE);

            Paragraph paragraph1 = new Paragraph(" ", mainFont1);

            paragraph1.setAlignment(Element.ALIGN_CENTER);
            paragraph1.setIndentationLeft(50);
            paragraph1.setIndentationRight(50);
            paragraph1.setSpacingAfter(10);
            paragraph1.setFont(mainFont1);
            document.add(paragraph1);

//********//

            Font tableHeader2 = FontFactory.getFont("Arial", 10, BaseColor.BLACK);
            Font tableBody2 = FontFactory.getFont("Arial", 50, BaseColor.WHITE);
            PdfPTable table2 = new PdfPTable(1);
            table2.setWidthPercentage(50);
            table2.setHorizontalAlignment(Element.ALIGN_RIGHT);

            PdfPCell name = new PdfPCell(new Paragraph("Client:", tableHeader2));
            name.setBorderColor(BaseColor.WHITE);
            name.setPaddingLeft(10);
            name.setHorizontalAlignment(Element.ALIGN_LEFT);
            name.setVerticalAlignment(Element.ALIGN_CENTER);
            name.setBackgroundColor(BaseColor.WHITE);
            name.setExtraParagraphSpace(5f);
            name.getImage();
            table2.addCell(name);

            PdfPCell mf = new PdfPCell(new Paragraph("MF:", tableHeader2));
            mf.setBorderColor(BaseColor.WHITE);
            mf.setPaddingLeft(10);
            mf.setHorizontalAlignment(Element.ALIGN_LEFT);
            mf.setVerticalAlignment(Element.ALIGN_CENTER);
            mf.setBackgroundColor(BaseColor.LIGHT_GRAY);
            mf.setExtraParagraphSpace(5f);
            mf.getImage();
            table2.addCell(mf);

            PdfPCell email = new PdfPCell(new Paragraph("Email:", tableHeader2));
            email.setBorderColor(BaseColor.WHITE);
            email.setPaddingLeft(10);
            email.setHorizontalAlignment(Element.ALIGN_LEFT);
            email.setVerticalAlignment(Element.ALIGN_CENTER);
            email.setBackgroundColor(BaseColor.LIGHT_GRAY);
            email.setExtraParagraphSpace(5f);
            email.getImage();
            table2.addCell(email);

            PdfPCell Numtel = new PdfPCell(new Paragraph("Num Tel:", tableHeader2));
            Numtel.setBorderColor(BaseColor.WHITE);
            Numtel.setPaddingLeft(10);
            Numtel.setHorizontalAlignment(Element.ALIGN_LEFT);
            Numtel.setVerticalAlignment(Element.ALIGN_CENTER);
            Numtel.setBackgroundColor(BaseColor.LIGHT_GRAY);
            Numtel.setExtraParagraphSpace(5f);
            Numtel.getImage();
            table2.addCell(Numtel);

            PdfPCell adresse = new PdfPCell(new Paragraph("Adresse:", tableHeader2));
            adresse.setBorderColor(BaseColor.WHITE);
            adresse.setPaddingLeft(10);
            adresse.setHorizontalAlignment(Element.ALIGN_LEFT);
            adresse.setVerticalAlignment(Element.ALIGN_CENTER);
            adresse.setBackgroundColor(BaseColor.LIGHT_GRAY);
            adresse.setExtraParagraphSpace(5f);
            adresse.getImage();
            table2.addCell(adresse);
//******//
            // --------//
            Font tableHeader3 = FontFactory.getFont("Arial", 10, BaseColor.BLACK);
            Font tableBody3 = FontFactory.getFont("Arial", 50, BaseColor.WHITE);
            PdfPTable table4 = new PdfPTable(1);
            table4.setWidthPercentage(100);

            PdfPCell name1 = new PdfPCell(new Paragraph("Sociéte: DKSOFT", tableHeader3));
            name1.setBorderColor(BaseColor.WHITE);
            name1.setPaddingLeft(10);
            name1.setHorizontalAlignment(Element.ALIGN_LEFT);
            name1.setVerticalAlignment(Element.ALIGN_CENTER);
            name1.setBackgroundColor(BaseColor.WHITE);
            name1.setExtraParagraphSpace(5f);
            name1.getImage();
            table4.addCell(name1);

            PdfPCell mf1 = new PdfPCell(new Paragraph("MF: 021365K /A/M/000", tableHeader3));
            mf1.setBorderColor(BaseColor.WHITE);
            mf1.setPaddingLeft(10);
            mf1.setHorizontalAlignment(Element.ALIGN_LEFT);
            mf1.setVerticalAlignment(Element.ALIGN_CENTER);
            mf1.setBackgroundColor(BaseColor.WHITE);
            mf1.setExtraParagraphSpace(5f);
            mf1.getImage();
            table4.addCell(mf1);

            PdfPCell email1 = new PdfPCell(new Paragraph("Email: decasoft@gmail.com", tableHeader3));
            email1.setBorderColor(BaseColor.WHITE);
            email1.setPaddingLeft(10);
            email1.setHorizontalAlignment(Element.ALIGN_LEFT);
            email1.setVerticalAlignment(Element.ALIGN_CENTER);
            email1.setBackgroundColor(BaseColor.WHITE);
            email1.setExtraParagraphSpace(5f);
            email1.getImage();
            table4.addCell(email1);

            PdfPCell Numtel1 = new PdfPCell(new Paragraph("Num Tel: 74 555 555", tableHeader3));
            Numtel1.setBorderColor(BaseColor.WHITE);
            Numtel1.setPaddingLeft(10);
            Numtel1.setHorizontalAlignment(Element.ALIGN_LEFT);
            Numtel1.setVerticalAlignment(Element.ALIGN_CENTER);
            Numtel1.setBackgroundColor(BaseColor.WHITE);
            Numtel1.setExtraParagraphSpace(5f);
            Numtel1.getImage();
            table4.addCell(Numtel1);

            PdfPCell adresse1 = new PdfPCell(
                    new Paragraph("Adresse: Rte Afrane km 2.5 Imm Rexpalce Sfax 3052", tableHeader3));
            adresse1.setBorderColor(BaseColor.WHITE);
            adresse1.setPaddingLeft(10);
            adresse1.setHorizontalAlignment(Element.ALIGN_LEFT);
            adresse1.setVerticalAlignment(Element.ALIGN_CENTER);
            adresse1.setBackgroundColor(BaseColor.WHITE);
            adresse1.setExtraParagraphSpace(5f);
            adresse1.getImage();
            table4.addCell(adresse1);

            // --------//
            PdfPTable table = new PdfPTable(7);
            table.setWidthPercentage(100);
            table.setSpacingBefore(50f);
            table.setSpacingAfter(50f);

            Font tableHeader = FontFactory.getFont("Arial", 10, BaseColor.BLACK);
            Font tableBody = FontFactory.getFont("Arial", 8, BaseColor.BLACK);

            float[] columnWidths = { 2f, 2f, 2f, 2f, 2f, 2f, 2f };
            table.setWidths(columnWidths);

            PdfPCell type = new PdfPCell(new Paragraph("Type", tableHeader));
            type.setBorderColor(BaseColor.BLACK);
            type.setPaddingLeft(10);
            type.setHorizontalAlignment(Element.ALIGN_CENTER);
            type.setVerticalAlignment(Element.ALIGN_CENTER);
            type.setBackgroundColor(BaseColor.GRAY);
            type.setExtraParagraphSpace(5f);
            table.addCell(type);

            PdfPCell Referance = new PdfPCell(new Paragraph("Referance", tableHeader));
            Referance.setBorderColor(BaseColor.BLACK);
            Referance.setPaddingLeft(10);
            Referance.setHorizontalAlignment(Element.ALIGN_CENTER);
            Referance.setVerticalAlignment(Element.ALIGN_CENTER);
            Referance.setBackgroundColor(BaseColor.GRAY);
            Referance.setExtraParagraphSpace(5f);
            table.addCell(Referance);

            PdfPCell Designation = new PdfPCell(new Paragraph("Designation", tableHeader));
            Designation.setBorderColor(BaseColor.BLACK);
            Designation.setPaddingLeft(10);
            Designation.setHorizontalAlignment(Element.ALIGN_CENTER);
            Designation.setVerticalAlignment(Element.ALIGN_CENTER);
            Designation.setBackgroundColor(BaseColor.GRAY);
            Designation.setExtraParagraphSpace(5f);
            table.addCell(Designation);

            PdfPCell PUHT = new PdfPCell(new Paragraph("PUHT", tableHeader));
            PUHT.setBorderColor(BaseColor.BLACK);
            PUHT.setPaddingLeft(10);
            PUHT.setHorizontalAlignment(Element.ALIGN_CENTER);
            PUHT.setVerticalAlignment(Element.ALIGN_CENTER);
            PUHT.setBackgroundColor(BaseColor.GRAY);
            PUHT.setExtraParagraphSpace(5f);
            table.addCell(PUHT);

            PdfPCell Qte = new PdfPCell(new Paragraph("Qte", tableHeader));
            Qte.setBorderColor(BaseColor.BLACK);
            Qte.setPaddingLeft(10);
            Qte.setHorizontalAlignment(Element.ALIGN_CENTER);
            Qte.setVerticalAlignment(Element.ALIGN_CENTER);
            Qte.setBackgroundColor(BaseColor.GRAY);
            Qte.setExtraParagraphSpace(5f);
            table.addCell(Qte);

            PdfPCell Remise = new PdfPCell(new Paragraph("Remise", tableHeader));
            Remise.setBorderColor(BaseColor.BLACK);
            Remise.setPaddingLeft(10);
            Remise.setHorizontalAlignment(Element.ALIGN_CENTER);
            Remise.setVerticalAlignment(Element.ALIGN_CENTER);
            Remise.setBackgroundColor(BaseColor.GRAY);
            Remise.setExtraParagraphSpace(5f);
            table.addCell(Remise);

            PdfPCell Montant = new PdfPCell(new Paragraph("Montant Total", tableHeader));
            Montant.setBorderColor(BaseColor.BLACK);
            Montant.setPaddingLeft(10);
            Montant.setHorizontalAlignment(Element.ALIGN_CENTER);
            Montant.setVerticalAlignment(Element.ALIGN_CENTER);
            Montant.setBackgroundColor(BaseColor.GRAY);
            Montant.setExtraParagraphSpace(5f);
            table.addCell(Montant);

      /*      for (VenteDto vente : list1) {

                PdfPCell totalValue = new PdfPCell(new Paragraph(vente.getMontant(),tableBody));
                totalValue.setBorderColor(BaseColor.BLACK);
                totalValue.setPaddingLeft(10);
                totalValue.setHorizontalAlignment(Element.ALIGN_CENTER);
                totalValue.setVerticalAlignment(Element.ALIGN_CENTER);
                totalValue.setBackgroundColor(BaseColor.WHITE);
                totalValue.setExtraParagraphSpace(5f);
                table.addCell(totalValue);

            }*/

//********//

            Font tableHeader4 = FontFactory.getFont("Arial", 10, BaseColor.BLACK);
            Font tableBody4 = FontFactory.getFont("Arial", 50, BaseColor.BLACK);
            PdfPTable table5 = new PdfPTable(1);
            table5.setWidthPercentage(50);
            table5.setHorizontalAlignment(Element.ALIGN_RIGHT);

            PdfPCell MT = new PdfPCell(new Paragraph("Total TTC", tableHeader4));
            MT.setBorderColor(BaseColor.WHITE);
            MT.setPaddingLeft(10);
            MT.setHorizontalAlignment(Element.ALIGN_LEFT);
            MT.setVerticalAlignment(Element.ALIGN_CENTER);
            MT.setBackgroundColor(BaseColor.LIGHT_GRAY);
            MT.setExtraParagraphSpace(5f);
            MT.getImage();
            table5.addCell(MT);

            PdfPCell TVA = new PdfPCell(new Paragraph("TOTAL TVA :", tableHeader4));
            TVA.setBorderColor(BaseColor.WHITE);
            TVA.setPaddingLeft(10);
            TVA.setHorizontalAlignment(Element.ALIGN_LEFT);
            TVA.setVerticalAlignment(Element.ALIGN_CENTER);
            TVA.setBackgroundColor(BaseColor.LIGHT_GRAY);
            TVA.setExtraParagraphSpace(5f);

            table5.addCell(TVA);

            PdfPCell TIMBRE = new PdfPCell(new Paragraph("DROIT DE TIMBRE : 0.600", tableHeader4));
            TIMBRE.setBorderColor(BaseColor.WHITE);
            TIMBRE.setPaddingLeft(10);
            TIMBRE.setHorizontalAlignment(Element.ALIGN_LEFT);
            TIMBRE.setVerticalAlignment(Element.ALIGN_CENTER);
            TIMBRE.setBackgroundColor(BaseColor.LIGHT_GRAY);
            TIMBRE.setExtraParagraphSpace(5f);

            table5.addCell(TIMBRE);

            PdfPCell TTC = new PdfPCell(new Paragraph("MONTANT TTC :" , tableHeader4));
            TTC.setBorderColor(BaseColor.WHITE);
            TTC.setPaddingLeft(10);
            TTC.setHorizontalAlignment(Element.ALIGN_LEFT);
            TTC.setVerticalAlignment(Element.ALIGN_CENTER);
            TTC.setBackgroundColor(BaseColor.LIGHT_GRAY);
            TTC.setExtraParagraphSpace(5f);

            table5.addCell(TTC);
            document.add(Chunk.NEWLINE);

//******//

            Font tableHeader6 = FontFactory.getFont("Arial", 10, BaseColor.BLACK);
            Font tableBody6 = FontFactory.getFont("Arial", 50, BaseColor.WHITE);
            PdfPTable table7 = new PdfPTable(1);
            table7.setWidthPercentage(100);
            table7.setHorizontalAlignment(Element.ALIGN_LEFT);

            PdfPCell LETTRE = new PdfPCell(new Paragraph("Arrété la présente Facture à la somme de :", tableHeader6));
            LETTRE.setBorderColor(BaseColor.WHITE);
            LETTRE.setPaddingLeft(10);
            LETTRE.setHorizontalAlignment(Element.ALIGN_LEFT);
            LETTRE.setVerticalAlignment(Element.ALIGN_CENTER);
            LETTRE.setBackgroundColor(BaseColor.WHITE);
            LETTRE.setExtraParagraphSpace(5f);
            table7.addCell(LETTRE);
//******//

            Font tableHeader8 = FontFactory.getFont("Arial", 10, BaseColor.BLACK);
            Font tableBody8 = FontFactory.getFont("Arial", 50, BaseColor.WHITE);
            PdfPTable table8 = new PdfPTable(1);
            table8.setWidthPercentage(50);
            table8.setHorizontalAlignment(Element.ALIGN_RIGHT);

            PdfPTable table10 = new PdfPTable(1);
            table10.setWidthPercentage(50);
            table10.setHorizontalAlignment(Element.ALIGN_LEFT);


            PdfPCell validationPrestataire = new PdfPCell(new Paragraph("Cachet Et Signature", tableHeader6));
            validationPrestataire.setBorderColor(BaseColor.BLACK);
            validationPrestataire.setPaddingRight(10);
            validationPrestataire.setHorizontalAlignment(Element.ALIGN_CENTER);
            validationPrestataire.setVerticalAlignment(Element.ALIGN_CENTER);
            validationPrestataire.setBackgroundColor(BaseColor.WHITE);
            validationPrestataire.setExtraParagraphSpace(5f);
            table8.addCell(validationPrestataire);

            PdfPCell signaturePrestataire = new PdfPCell(new Paragraph("", tableHeader6));
            signaturePrestataire.setBorderColor(BaseColor.BLACK);
            signaturePrestataire.setPaddingLeft(10);
            signaturePrestataire.setFixedHeight(120);
            signaturePrestataire.setHorizontalAlignment(Element.ALIGN_LEFT);
            signaturePrestataire.setVerticalAlignment(Element.ALIGN_CENTER);
            signaturePrestataire.setBackgroundColor(BaseColor.WHITE);
            signaturePrestataire.setExtraParagraphSpace(5f);
            table8.addCell(signaturePrestataire);

            PdfPCell validationClient = new PdfPCell(new Paragraph("Cachet Et Signature", tableHeader6));
            validationClient.setBorderColor(BaseColor.BLACK);
            validationClient.setPaddingRight(10);
            validationClient.setHorizontalAlignment(Element.ALIGN_CENTER);
            validationClient.setVerticalAlignment(Element.ALIGN_CENTER);
            validationClient.setBackgroundColor(BaseColor.WHITE);
            validationClient.setExtraParagraphSpace(5f);
            table10.addCell(validationClient);

            PdfPCell signatureClient = new PdfPCell(new Paragraph("", tableHeader6));
            signatureClient.setBorderColor(BaseColor.BLACK);
            signatureClient.setPaddingLeft(10);
            signatureClient.setFixedHeight(120);
            signatureClient.setHorizontalAlignment(Element.ALIGN_LEFT);
            signatureClient.setVerticalAlignment(Element.ALIGN_CENTER);
            signatureClient.setBackgroundColor(BaseColor.WHITE);
            signatureClient.setExtraParagraphSpace(5f);
            table10.addCell(signatureClient);
//******//

            Font tableHeader9 = FontFactory.getFont("Arial", 10, BaseColor.BLACK);
            Font tableBody9 = FontFactory.getFont("Arial", 50, BaseColor.WHITE);
            PdfPTable table9 = new PdfPTable(1);
            table9.setWidthPercentage(100);
            table9.setHorizontalAlignment(Element.ALIGN_LEFT);

            PdfPCell FOOTER = new PdfPCell(new Paragraph(
                    "Code TVA : 1238838K/A/M/000  RIB AMEN BANK : 07072 013 010 1101 378 56  ",
                    tableHeader6));
            FOOTER.setBorderColor(BaseColor.WHITE);
            FOOTER.setFixedHeight(20);
            FOOTER.setPaddingLeft(10);
            FOOTER.setHorizontalAlignment(Element.ALIGN_LEFT);
            FOOTER.setVerticalAlignment(Element.ALIGN_CENTER);
            FOOTER.setBackgroundColor(BaseColor.WHITE);
            FOOTER.setExtraParagraphSpace(5f);
            table9.addCell(FOOTER);
//******//

            document.addTitle("Facture N°: 455666");
            document.addCreationDate();
            document.add(table4);
            document.add(table2);

            document.add(table);
            document.add(table5);
            document.add(Chunk.NEWLINE);
            document.add(table7);
            document.add(Chunk.NEWLINE);


            document.add(table8);
            document.add(Chunk.NEWLINE);
            document.add(Chunk.NEWLINE);
            document.add(table10);
            document.add(table9);


            document.close();
        } catch (DocumentException e) {
           // logger.error(e.toString());
        }

        return new ByteArrayInputStream(out.toByteArray());
    }

}
