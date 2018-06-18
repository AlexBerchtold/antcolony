package py.edu.facitec.ant.dao;

import java.util.List;

import py.edu.facitec.ant.entidades.Materia;

public class MateriaDao extends GenericDao<Materia> {

	public MateriaDao() {
		super(Materia.class);
		// TODO Auto-generated constructor stub
	}
	
	public List<Materia> recuperarPorFiltro(String filtro){
		instanciarCriteria();
		criteriaQuery.where( builder.like(builder.lower(root.<String>get("dia")), "%"+filtro.toLowerCase()+"%"));
		criteriaQuery.orderBy(builder.asc(root.<Integer>get("id")));
		lista = session.createQuery(criteriaQuery).getResultList();
		cerrar();
		return lista;
	}

}
