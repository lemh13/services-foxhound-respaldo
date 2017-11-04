package fox.hound.spring.report;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import fox.hound.spring.models.Usuario;
import fox.hound.spring.services.UsuarioService;
import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.data.JRBeanCollectionDataSource;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.xml.JRXmlLoader;

@RestController
public class ReportController {
	
	@Autowired
	private UsuarioService usuarioService;
	
	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@RequestMapping(value="/simplePDF", produces = "application/pdf")
	public @ResponseBody void simpleReport(HttpServletResponse response) {
		
		try {
			InputStream jasperSteam = this.getClass().getResourceAsStream("/report/report1.jrxml");
			JasperDesign desing = JRXmlLoader.load(jasperSteam);
			JasperReport report = JasperCompileManager.compileReport(desing);
			
			Map<String, Object> parameterMap = new HashMap<>();
			
			List<Usuario> usuarios = usuarioService.getAllUsers();
			JRDataSource jrDataSource = new JRBeanCollectionDataSource(usuarios);
			
			parameterMap.put("dataSource", jrDataSource);

			JasperPrint jasperPrint = JasperFillManager.fillReport(report, parameterMap, jrDataSource);
			response.addHeader("Content-disposition", "attachment;filename=usuario.pdf");
            response.setContentType("application/pdf");

			final OutputStream outputStream = response.getOutputStream();
			JasperExportManager.exportReportToPdfStream(jasperPrint, outputStream);
			
		} catch (JRException | IOException e) {
			logger.info("Error load report");
		}
	}
	
}
