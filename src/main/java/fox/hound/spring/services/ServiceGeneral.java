package fox.hound.spring.services;

import java.util.List;

public interface ServiceGeneral<T> {

	public List<T> getAll();
	public T getOne(Long id);
	public T saveOrUpdate(T clase);
	public void delete(Long id);
	
	public void deleteLogic(String id);
	
}
