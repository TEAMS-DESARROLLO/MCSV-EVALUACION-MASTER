package com.bussinesdomain.maestros.services.impl;

import com.bussinesdomain.maestros.commons.Filter;
import com.bussinesdomain.maestros.commons.IPaginationCommons;
import com.bussinesdomain.maestros.commons.PaginationModel;
import com.bussinesdomain.maestros.commons.SortModel;
import com.bussinesdomain.maestros.dto.QuestionResponseDTO;
import com.bussinesdomain.maestros.exception.ServiceException;
import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class QuestionPaginationService implements IPaginationCommons<QuestionResponseDTO> {
    private final EntityManager entityManager;
    @Override
    public Page<QuestionResponseDTO> pagination(PaginationModel pagination) {
        try {

            String sqlCount  = "SELECT count(a) " + getFrom().toString() + getFilters( pagination.getFilters()  ).toString();
            String sqlSelect = getSelect().toString() + getFrom().toString() +getFilters( pagination.getFilters()).toString() + getOrder(pagination.getSorts());

            Query queryCount = entityManager. createQuery(sqlCount);
            Query querySelect = entityManager.createQuery(sqlSelect);

            this.setParams(pagination.getFilters(), queryCount);
            this.setParams(pagination.getFilters(), querySelect);

            Long total = (long) queryCount.getSingleResult();

            querySelect.setFirstResult((pagination.getPageNumber()) * pagination.getRowsPerPage());
            querySelect.setMaxResults(pagination.getRowsPerPage());

            @SuppressWarnings("unchecked")
            List<QuestionResponseDTO> lista = querySelect.getResultList();

            PageRequest pageable = PageRequest.of(pagination.getPageNumber(), pagination.getRowsPerPage());

            Page<QuestionResponseDTO> page = new PageImpl<QuestionResponseDTO>(lista, pageable, total);

            return page;
        } catch (RuntimeException e) {
            throw new ServiceException("error al momento de generar la paginacion " + e.getMessage());
        }
    }

    @Override
    public StringBuilder getSelect() {
        StringBuilder sql = new StringBuilder("SELECT new com.bussinesdomain.maestros.dto.QuestionResponseDTO(a.idQuestion," +
                "r.idRequirement," +
                "r.title as requirementTitle," +
                "l.idLevel," +
                "l.title as levelTitle," +
                "t.idTopic," +
                "t.title as topicTitle," +
                "a.statement," +
                "a.image," +
                "a.choice01," +
                "a.choice02," +
                "a.choice03," +
                "a.choice04," +
                "a.choice05," +
                "a.choiceOK " +
                ") ");
        return sql;
    }

    @Override
    public StringBuilder getFrom() {
        StringBuilder sql = new StringBuilder(" FROM QuestionEntity a " +
                " inner join RequirementEntity r on a.requirement=r " +
                " inner join LevelEntity l on a.level=l " +
                " inner join TopicEntity t on a.topic=t  ");
        return sql;
    }

    @Override
    public StringBuilder getFilters(List<Filter> filters) {
        StringBuilder sql = new StringBuilder("where 1=1 ");

        for(Filter filtro:filters){
            if(filtro.getField().equals("idQuestion")){
                sql.append(" AND a.idQuestion = :idQuestion");
            }

            if(filtro.getField().equals("idRequirement")){
                sql.append(" AND r.idRequirement = :idRequirement ");
            }
            if(filtro.getField().equals("requirementTitle")){
                sql.append(" AND r.title LIKE :requirementTitle ");
            }

            if(filtro.getField().equals("idLevel")){
                sql.append(" AND l.idLevel = :idLevel ");
            }
            if(filtro.getField().equals("levelTitle")){
                sql.append(" AND l.title LIKE :levelTitle ");
            }

            if(filtro.getField().equals("idTopic")){
                sql.append(" AND t.idTopic = :idTopic ");
            }
            if(filtro.getField().equals("topicTitle")){
                sql.append(" AND t.topicTitle LIKE :topicTitle ");
            }

            if(filtro.getField().equals("statement")){
                sql.append(" AND a.statement LIKE :statement ");
            }
            if(filtro.getField().equals("image")){
                sql.append(" AND a.image LIKE :image ");
            }
            if(filtro.getField().equals("choice01")){
                sql.append(" AND a.choice01 LIKE :choice01 ");
            }
            if(filtro.getField().equals("choice02")){
                sql.append(" AND a.choice02 LIKE :choice02 ");
            }
            if(filtro.getField().equals("choice03")){
                sql.append(" AND a.choice03 LIKE :choice03 ");
            }
            if(filtro.getField().equals("choice04")){
                sql.append(" AND a.choice04 LIKE :choice04 ");
            }
            if(filtro.getField().equals("choice05")){
                sql.append(" AND a.choice05 LIKE :choice05 ");
            }
            if(filtro.getField().equals("choiceOK")){
                sql.append(" AND a.choiceOK LIKE :choiceOK ");
            }


        }
        return sql;
    }

    @Override
    public Query setParams(List<Filter> filters, Query query) {
        for(Filter filtro:filters){
            if(filtro.getField().equals("idQuestion")){
                query.setParameter("idQuestion",filtro.getValue() );
            }


            if(filtro.getField().equals("idRequirement")){
                query.setParameter("idRequirement",filtro.getValue() );
            }
            if(filtro.getField().equals("requirementTitle")){
                query.setParameter("requirementTitle","%"+filtro.getValue()+"%");
            }

            if(filtro.getField().equals("idLevel")){
                query.setParameter("idLevel",filtro.getValue() );
            }
            if(filtro.getField().equals("levelTitle")){
                query.setParameter("levelTitle","%"+filtro.getValue()+"%");
            }

            if(filtro.getField().equals("idTopic")){
                query.setParameter("idTopic",filtro.getValue() );
            }
            if(filtro.getField().equals("topicTitle")){
                query.setParameter("topicTitle","%"+filtro.getValue()+"%");
            }

            if(filtro.getField().equals("statement")){
                query.setParameter("statement","%"+filtro.getValue()+"%");
            }
            if(filtro.getField().equals("image")){
                query.setParameter("image","%"+filtro.getValue()+"%");
            }
            if(filtro.getField().equals("choice01")){
                query.setParameter("choice01","%"+filtro.getValue()+"%");
            }
            if(filtro.getField().equals("choice02")){
                query.setParameter("choice02","%"+filtro.getValue()+"%");
            }
            if(filtro.getField().equals("choice03")){
                query.setParameter("choice03","%"+filtro.getValue()+"%");
            }
            if(filtro.getField().equals("choice04")){
                query.setParameter("choice04","%"+filtro.getValue()+"%");
            }
            if(filtro.getField().equals("choice05")){
                query.setParameter("choice05","%"+filtro.getValue()+"%");
            }
            if(filtro.getField().equals("choiceOK")){
                query.setParameter("choiceOK",filtro.getValue() );
            }


        }
        return query;
    }

    @Override
    public StringBuilder getOrder(List<SortModel> sorts) {
        boolean flagMore = false;
        StringBuilder sql = new StringBuilder("");
        if(sorts.size() > 0){
            sql.append(" ORDER BY ");

            for(SortModel sort:sorts){
                if(sort.getColName().equals("idQuestion")){
                    if(flagMore)
                        sql.append(", ");

                    sql.append( " a.idQuestion " + sort.getSort() );
                    flagMore = true;
                }


                if(sort.getColName().equals("idRequirement")){
                    if(flagMore)
                        sql.append(", ");

                    sql.append( " r.idRequirement " + sort.getSort() );
                    flagMore = true;
                }
                if(sort.getColName().equals("requirementTitle")){
                    if(flagMore)
                        sql.append(", ");

                    sql.append( " r.title " + sort.getSort() );
                    flagMore = true;
                }

                if(sort.getColName().equals("idLevel")){
                    if(flagMore)
                        sql.append(", ");

                    sql.append( " l.idLevel " + sort.getSort() );
                    flagMore = true;
                }
                if(sort.getColName().equals("levelTitle")){
                    if(flagMore)
                        sql.append(", ");

                    sql.append( " l.title " + sort.getSort() );
                    flagMore = true;
                }

                if(sort.getColName().equals("idTopic")){
                    if(flagMore)
                        sql.append(", ");

                    sql.append( " t.idTopic " + sort.getSort() );
                    flagMore = true;
                }
                if(sort.getColName().equals("topicTitle")){
                    if(flagMore)
                        sql.append(", ");

                    sql.append( " t.title " + sort.getSort() );
                    flagMore = true;
                }

                if(sort.getColName().equals("statement")){
                    if(flagMore)
                        sql.append(", ");
                    sql.append( " a.statement " + sort.getSort() );
                    flagMore = true;
                }

                if(sort.getColName().equals("image")){
                    if(flagMore)
                        sql.append(", ");
                    sql.append( " a.image " + sort.getSort() );
                    flagMore = true;
                }

                if(sort.getColName().equals("choice01")){
                    if(flagMore)
                        sql.append(", ");
                    sql.append( " a.choice01 " + sort.getSort() );
                    flagMore = true;
                }
                if(sort.getColName().equals("choice02")){
                    if(flagMore)
                        sql.append(", ");
                    sql.append( " a.choice02 " + sort.getSort() );
                    flagMore = true;
                }
                if(sort.getColName().equals("choice03")){
                    if(flagMore)
                        sql.append(", ");
                    sql.append( " a.choice03 " + sort.getSort() );
                    flagMore = true;
                }
                if(sort.getColName().equals("choice04")){
                    if(flagMore)
                        sql.append(", ");
                    sql.append( " a.choice04 " + sort.getSort() );
                    flagMore = true;
                }
                if(sort.getColName().equals("choice05")){
                    if(flagMore)
                        sql.append(", ");
                    sql.append( " a.choice05 " + sort.getSort() );
                    flagMore = true;
                }
                if(sort.getColName().equals("choiceOK")){
                    if(flagMore)
                        sql.append(", ");
                    sql.append( " a.choiceOK " + sort.getSort() );
                    flagMore = true;
                }


            }
        }
        return sql;
    }
}
