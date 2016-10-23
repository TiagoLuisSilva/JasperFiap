package br.com.fiap.uteis;

import java.io.File;

import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.export.JRPdfExporter;

public class RelatorioUtils {


    public static String realizarExportacaoPDF(ParametrosRelVO superRelVO, JasperPrint print) throws Exception {
        String nome = superRelVO.getNomeRelatorio() + ".pdf"; 
        File pdfFile = new File(superRelVO.getDiretorioRel() + File.separator + nome);
//        File pdfFile = new File(System.getProperty("java.io.tmpdir") + File.separator + nome);
        if (pdfFile.exists()) {
            try {
                pdfFile.delete();
            } catch (Exception e) {
                throw new Exception("Erro ao exportar arquivo em PDF.");
            }
        }

        JRPdfExporter jrpdfexporter = new JRPdfExporter();
        jrpdfexporter.setParameter(JRExporterParameter.JASPER_PRINT, print);
        jrpdfexporter.setParameter(JRExporterParameter.OUTPUT_FILE, pdfFile);
        jrpdfexporter.setParameter(JRExporterParameter.CHARACTER_ENCODING, "ISO-8859-1");
        jrpdfexporter.exportReport();
        nome = null; 
        jrpdfexporter = null;
        return pdfFile.getPath();
    }
}
