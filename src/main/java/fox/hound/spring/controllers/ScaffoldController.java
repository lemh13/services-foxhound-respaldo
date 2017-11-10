
package fox.hound.spring.controllers;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("scaffold")
public class ScaffoldController {
	
//	private final Logger logger = Logger.getLogger(this.getClass());

	@RequestMapping(value="/{carpeta}/{clase}", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)
	public String getAll(@PathVariable String carpeta, @PathVariable String clase) throws IOException {
		String pathProyecto = "C:/Users/Jose/Documents/publico/spring/fox-hound-api/src/main/java/fox/hound/spring";
		
		String repository = pathProyecto + File.separator + "repositories";
		String repositoryName = repository + File.separator + clase + "Repository.java";
		
		createRepository(repositoryName, carpeta, clase);
		
		String services = pathProyecto + File.separator + "services";
		String serviceName = services + File.separator + clase + "Service.java";
		
		createService(serviceName, carpeta, clase);
		
		String controllers = pathProyecto + File.separator + "controllers";//+ File.separator + carpeta;
		String controllerName = controllers + File.separator + clase + "Controller.java";
		
		createCnotroller(controllerName, carpeta, clase);
		
		return "listo";
	}
	
	private void createCnotroller(String path, String carpeta, String clase) throws IOException {
		File f = new File(path);
		
		String claseLower = clase.toLowerCase();
		
		PrintWriter w = new PrintWriter(f);
		w.println("package fox.hound.spring.controllers;");//." + carpeta + ";");
		w.println("");
		w.println("import javax.servlet.http.HttpServletRequest;");
		w.println("import org.springframework.beans.factory.annotation.Autowired;");
		w.println("import org.springframework.http.MediaType;");
		w.println("import org.springframework.http.ResponseEntity;");
		w.println("import org.springframework.web.bind.annotation.PathVariable;");
		w.println("import org.springframework.web.bind.annotation.RequestBody;");
		w.println("import org.springframework.web.bind.annotation.RequestMapping;");
		w.println("import org.springframework.web.bind.annotation.RequestMethod;");
		w.println("import org.springframework.web.bind.annotation.RestController;");
		w.println("import fox.hound.spring.models."/* + carpeta + "."*/ + clase + ";");
		w.println("import fox.hound.spring.services." + clase + "Service;");
		w.println("import fox.hound.spring.utils.DateUtil;");
		w.println("import fox.hound.spring.utils.MessageUtil;");
		w.println("import fox.hound.spring.utils.ResponseDefault;");
		w.println("");
		w.println("@RestController");
		w.println("@RequestMapping(\"" + claseLower + "\")");
		w.println("public class " + clase + "Controller {");
		w.println("");
		w.println("\t @Autowired");
		w.println("\t private " + clase + "Service service;");
		w.println("");
		w.println("\t private Class<?> CLASE = " + clase + ".class;");
		w.println("");
		w.println("\t @RequestMapping(value=\"/buscarTodos\", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)");
		w.println("\t public ResponseEntity<?> getAll(HttpServletRequest request) {");
		w.println("\t\t return ResponseDefault.ok(service.getAll(), CLASE, ResponseDefault.PLURAL);");
		w.println("\t }");
		w.println("");
		w.println("\t @RequestMapping(value=\"/buscar/{id}\", method=RequestMethod.GET, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)");
		w.println("\t public ResponseEntity<?> getOne(@PathVariable String id, HttpServletRequest request) {");
		w.println("\t\t return ResponseDefault.ok(service.getOne(Long.valueOf(id)), CLASE, ResponseDefault.SINGULAR);");
		w.println("\t }");
		w.println("");
		w.println("\t @RequestMapping(value=\"/agregar\", method=RequestMethod.POST, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)");
		w.println("\t public ResponseEntity<?> agregar(@RequestBody " + clase + " clase, @PathVariable String id, HttpServletRequest request) {");
		w.println("\t\t clase.setFecha_creacion( DateUtil.getCurrentDate() );");
		w.println("\t\t // PENDIENTE -> @ManyToOne");
		w.println("\t\t return ResponseDefault.ok(service.saveOrUpdate(clase), CLASE, ResponseDefault.SINGULAR);");
		w.println("\t }");
		w.println("");
		w.println("\t @RequestMapping(value=\"/modificar\", method=RequestMethod.PUT, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)");
		w.println("\t public ResponseEntity<?> modificar(@RequestBody " + clase + " clase, HttpServletRequest request) {");
		w.println("\t\t return ResponseDefault.ok(service.saveOrUpdate(clase), CLASE, ResponseDefault.SINGULAR);");
		w.println("\t }");
		w.println("");
		w.println("\t @RequestMapping(value=\"/borrar/{id}\", method=RequestMethod.DELETE, produces=MediaType.APPLICATION_JSON_UTF8_VALUE)");
		w.println("\t public ResponseEntity<?> borrar(@PathVariable String id, HttpServletRequest request) {");
		w.println("\t\t service.delete(Long.valueOf(id));");
		w.println("\t\t return ResponseDefault.message(MessageUtil.ELIMINAR_REGISTRO, \"" + clase + "\");");
		w.println("\t }");
		w.println("");
		w.println("}");
		w.close();
		
		f.createNewFile();
	}

	private void createService(String path, String carpeta, String clase) throws IOException {
		File f = new File(path);
		
		PrintWriter w = new PrintWriter(f);
		w.println("package fox.hound.spring.services;");
		w.println("");
		w.println("import java.util.ArrayList;");
		w.println("import java.util.List;");
		w.println("import org.springframework.beans.factory.annotation.Autowired;");
		w.println("import org.springframework.stereotype.Service;");
		w.println("import fox.hound.spring.models."/* + carpeta + "."*/ + clase + ";");
		w.println("import fox.hound.spring.repositories." + clase + "Repository;");
		w.println("import fox.hound.spring.utils.DateUtil;");
		w.println("");
		w.println("@Service");
		w.println("public class " + clase + "Service implements ServiceGeneral<" + clase + "> {");
		w.println("");
		w.println("\t @Autowired");
		w.println("\t private " + clase + "Repository repository;");
		w.println("");
		w.println("\t @Autowired");
		w.println("\t private EstatusService estatusService;");
		w.println("");
		w.println("\t @Override");
		w.println("\t public List<" + clase + "> getAll() {");
		w.println("\t\t List<" + clase + "> lista = new ArrayList<>();");
		w.println("\t\t repository.findAll().forEach(lista::add);");
		w.println("\t\t return lista;");
		w.println("\t }");
		w.println("");
		w.println("\t @Override");
		w.println("\t public " + clase + " getOne(Long id) {");
		w.println("\t\t return repository.findOne(id);");
		w.println("\t }");
		w.println("");
		w.println("\t @Override");
		w.println("\t public " + clase + " saveOrUpdate(" + clase + " clase) {");
		w.println("\t\t if (clase.getId() != null) {");
		w.println("\t\t\t " + clase + " claseAux = getOne( clase.getId() );");
		w.println("\t\t\t clase.setFecha_creacion( claseAux.getFecha_creacion() );");
		w.println("\t\t }");
		w.println("\t\t clase.setFecha_modificacion( DateUtil.getCurrentDate() );");
		w.println("\t\t clase.setEstatus( estatusService.getOne(clase.getEstatus().getId() ) );");
		w.println("\t\t return repository.save(clase);");
		w.println("\t }");
		w.println("");
		w.println("\t @Override");
		w.println("\t public void delete(Long id) {");
		w.println("\t\t repository.delete(id);");
		w.println("\t }");
		w.println("}");
		w.close();
		
		f.createNewFile();
	}
	
	private void createRepository(String path, String carpeta, String clase) throws IOException {
		File f = new File(path);
		
		PrintWriter w = new PrintWriter(f);
		w.println("package fox.hound.spring.repositories;");
		w.println("");
		w.println("import org.springframework.data.repository.CrudRepository;");
		w.println("import fox.hound.spring.models."/* + carpeta + "."*/ + clase + ";");
		w.println("");
		w.println("//https://docs.spring.io/spring-data/jpa/docs/1.6.0.RELEASE/reference/html/jpa.repositories.html#jpa.query-methods");
		w.println("public interface " + clase + "Repository extends CrudRepository<" + clase + ", Long> {");
		w.println("");
		w.println("}");
		w.close();
		
		f.createNewFile();
	}

}
