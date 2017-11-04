package fox.hound.spring.services;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fox.hound.spring.models.Dashboard;
import fox.hound.spring.repositories.DashboardRepository;

@Service
public class DashboardService implements ServiceGeneral<Dashboard> {
	
	@Autowired
	private DashboardRepository dashboardRepository;

	@Override
	public List<Dashboard> getAll() {
		List<Dashboard> lista = new ArrayList<>();
		dashboardRepository.findAll().forEach(lista::add);
		return lista.isEmpty() ? null : lista;
	}

	@Override
	public Dashboard getOne(Long id) {
		return dashboardRepository.findOne(id);
	}

	@Override
	public Dashboard saveOrUpdate(Dashboard clase) {
		return dashboardRepository.save(clase);
	}

	@Override
	public void delete(Long id) {
		dashboardRepository.delete(id);
	}

}
