package fox.hound.spring.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fox.hound.spring.models.Usuario;
import fox.hound.spring.repositories.UsuarioRepository;
import fox.hound.spring.utils.EncryptionUtil;

@Service
public class UsuarioService {
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private EncryptionUtil encript;

	public List<Usuario> getAllUsers() {
		List<Usuario> lista = new ArrayList<>();
		usuarioRepository.findAll().forEach(lista::add);
		return lista.isEmpty() ? null : lista;
	}
	
	public Usuario getUser(Long id) {
		return usuarioRepository.findOne(id);
	}

	public Usuario saveOrUpdateUser(Usuario user) {
		user.setPassword( encript.md5( user.getPassword() ) );
		Usuario u = usuarioRepository.save(user);
		return u;
	}

	public void deleteUser(Long id) {
		usuarioRepository.delete(id);
	}

}
