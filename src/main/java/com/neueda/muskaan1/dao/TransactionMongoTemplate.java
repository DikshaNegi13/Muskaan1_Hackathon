package com.neueda.muskaan1.dao;

import com.neueda.muskaan1.dto.CategoryAmount;
import com.neueda.muskaan1.dto.MerchantAmount;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.*;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.stereotype.Repository;

import java.util.List;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.*;

@Repository
public class TransactionMongoTemplate {

    @Autowired
    private MongoTemplate mongoTemplate;

    public List<CategoryAmount> getSpendingHistoryByCategory() {
        GroupOperation groupByCategorySumAmount = group("category").sum("amt").as("total_amt");
        MatchOperation allCategory = match(new Criteria("category").exists(true));
        ProjectionOperation include = project("total_amt").and("category").previousOperation();
        SortOperation sortByAmt = sort(Sort.by(Sort.Direction.DESC, "total_amt"));

        Aggregation aggregationData = newAggregation(allCategory, groupByCategorySumAmount, sortByAmt, include);
        AggregationResults<CategoryAmount> groupResult = mongoTemplate.aggregate(aggregationData, "category", CategoryAmount.class);
        List<CategoryAmount> results = groupResult.getMappedResults();
        return results;


    }

    public List<MerchantAmount> getSpendingHistoryByMerchant() {
        GroupOperation groupByMerchantSumAmount = group("merchant").sum("amt").as("total_amt");
        MatchOperation allMerchant = match(new Criteria("merchant").exists(true));
        ProjectionOperation includes = project("total_amt").and("merchant").previousOperation();
        SortOperation sortByAmtDesc = sort(Sort.by(Sort.Direction.DESC, "total_amt"));

        Aggregation aggregation = newAggregation(allMerchant, groupByMerchantSumAmount, sortByAmtDesc, includes);
        AggregationResults<MerchantAmount> groupResults = mongoTemplate.aggregate(aggregation, "merchant", MerchantAmount.class);
        List<MerchantAmount> result = groupResults.getMappedResults();
        return result;

    }

}