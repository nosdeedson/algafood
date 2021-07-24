package com.ejs.algaworksCurso.infrastructure.repository.service;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.springframework.stereotype.Repository;

import com.ejs.algaworksCurso.api.model.dto.VendaDiariaDTO;
import com.ejs.algaworksCurso.domain.model.Pedido;
import com.ejs.algaworksCurso.domain.model.StatusPedido;
import com.ejs.algaworksCurso.domain.model.filter.VendaDiariaFilter;
import com.ejs.algaworksCurso.domain.services.VendaQueryService;

@Repository
public class VendaQueryServiceImpl  implements VendaQueryService {

	@PersistenceContext
	private EntityManager manager;
	
	@Override
	public List<VendaDiariaDTO> consultarVendasDiarias(VendaDiariaFilter filtro) {

		var builder = manager.getCriteriaBuilder();
		var query = builder.createQuery(VendaDiariaDTO.class);
		var root = query.from(Pedido.class);
		List<Predicate> predicates = new ArrayList<>();
		
		predicates.add(root.get("status").in(StatusPedido.CONFIRMADO, StatusPedido.ENTREGUE));
		
		this.gerarFiltros(predicates, builder, root, filtro);
		
		var selection = builder.construct(VendaDiariaDTO.class,
				root.get("dataCriacao").alias("data"),
				builder.count(root.get("id")).alias("quantidadeVendas"),
				builder.sum(root.get("valorTotal")).alias("totalFaturado"));
		
		query.where(predicates.toArray(new Predicate[0]));
		
		query.select(selection);
		query.groupBy(root.get("dataCriacao"));
		var result = manager.createQuery(query);
		
		return result.getResultList();
	}

	private void gerarFiltros( List<Predicate> predicates, CriteriaBuilder builder, Root<?> root, VendaDiariaFilter filtro) {
		if (filtro.getDataCriacaoInicio() != null) {
			predicates.add(builder.greaterThanOrEqualTo(root.get("dataCriacao"), filtro.getDataCriacaoInicio()));
		}if (filtro.getDataCriacaoFim() != null) {
			predicates.add(builder.lessThanOrEqualTo(root.get("dataCriacao"), filtro.getDataCriacaoFim()));
		}if( filtro.getRestauranteId() !=  null) {
			predicates.add(builder.equal(root.get("restaurante").get("id"), filtro.getRestauranteId()));
		}
	}
	
	
}
