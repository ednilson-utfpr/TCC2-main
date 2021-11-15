package br.edu.utfpr.pb.tcc.service;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.view.JasperViewer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ResourceUtils;

import java.io.*;
import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;

@Service
public class JasperService {

    private static final String JASPER_DIRETORIO = "classpath:jasper/";
    private static final String JASPER_PREFIXO = "produtos-";
    private static final String JASPER_SUFIXO = ".jasper";

    @Autowired
    private Connection connection;

    private Map<String, Object> params = new HashMap<>();

    public void addParams(String key, Object value) {
        this.params.put(key, value);
    }

    public byte[] exportarPDF(String code) {
        byte[] bytes = null;
        try {
            File file = ResourceUtils.getFile(JASPER_DIRETORIO.concat(JASPER_PREFIXO).concat(code).concat(JASPER_SUFIXO));
            JasperPrint print = JasperFillManager.fillReport(file.getAbsolutePath(), params, connection);
            bytes = JasperExportManager.exportReportToPdf(print);
        } catch (FileNotFoundException | JRException e) {
            e.printStackTrace();
        }
        return bytes;
    }
    //fim

    public void abrirPontoJasper(String jasperfile, Connection connection) {
        try {
            InputStream is = getClass().getClassLoader().getResourceAsStream(jasperfile);
            JasperPrint print = JasperFillManager.fillReport(is, this.params, connection);
            JasperViewer viewer = new JasperViewer(print);
            viewer.setVisible(true);
        } catch (JRException e) {
            e.printStackTrace();
        }
    }

    //JASPER VIEW
    public void abrirJasperViewer(String jrxml, Connection connection) {
        JasperReport report = compilarjrxml(jrxml);
        try {
            JasperPrint print = JasperFillManager.fillReport(report, this.params, connection);
            JasperViewer viewer = new JasperViewer(print);
            viewer.setVisible(true);
        } catch (JRException e) {
            e.printStackTrace();
        }
    }

    public void exportarParaPDF(String jrxml, Connection connection, String saida) {
        JasperReport report = compilarjrxml(jrxml);
        try {
            OutputStream out = new FileOutputStream(saida);
            JasperPrint print = JasperFillManager.fillReport(report, this.params, connection);
            JasperExportManager.exportReportToPdfStream(print, out);

        } catch (JRException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }


    private JasperReport compilarjrxml(String arquivo) {
        InputStream is = getClass().getClassLoader().getResourceAsStream(arquivo);
        try {
            return JasperCompileManager.compileReport(is);
        } catch (JRException e) {
            e.printStackTrace();
        }
        return null;

    }

}
