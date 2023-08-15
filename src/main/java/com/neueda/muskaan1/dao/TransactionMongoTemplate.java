package com.neueda.muskaan1.dao;

import com.neueda.muskaan1.dto.*;
import com.neueda.muskaan1.entity.Transactions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.*;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
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
        ProjectionOperation includes = project("total_amt").and("category").previousOperation();
        SortOperation sortByAmtDESC = sort(Sort.by(Sort.Direction.DESC, "total_amt"));

        Aggregation aggregation= newAggregation(allCategory, groupByCategorySumAmount, sortByAmtDESC, includes);
        AggregationResults<CategoryAmount> groupResults = mongoTemplate.aggregate(aggregation, "category", CategoryAmount.class);
        List<CategoryAmount> result = groupResults.getMappedResults();
        return result;


    }

    public List<MerchantAmount> getAmountForMerchant() {
        GroupOperation groupByMerchantSumAmount = group("merchant").sum("amt").as("total_amt");
        MatchOperation allMerchant = match(new Criteria("merchant").exists(true));
        ProjectionOperation includes = project("total_amt").and("merchant").previousOperation();
        SortOperation sortByAmtDesc = sort(Sort.by(Sort.Direction.DESC, "total_amt"));

        Aggregation aggregation = newAggregation(allMerchant, groupByMerchantSumAmount, sortByAmtDesc, includes);
        AggregationResults<MerchantAmount> groupResults = mongoTemplate.aggregate(aggregation, "merchant", MerchantAmount.class);
        List<MerchantAmount> result = groupResults.getMappedResults();
        return result;

    }

    public List<Transactions> getSpendingHistoryByGender(String gender) {
        Query query =new Query();
        query.addCriteria(Criteria.where("transactions").is(gender));
        return mongoTemplate.find(query, Transactions.class);

    }

    public List<Transactions> getSpendingHistoryByState(String state) {
        Query query =new Query();
        query.addCriteria(Criteria.where("transactions").is(state));
        return mongoTemplate.find(query, Transactions.class);
    }


    public List<AmountSpending> getSpendingHistoryByAmount() {
        // Group transactions based on amount and categorize as lowValue and highValue

        GroupOperation groupBySpendingTypeSumAmount = group()
                .sum(ConditionalOperators.when(Criteria.where("amt").lte(100)).then(1).otherwise(0)).as("lowValue")
                .sum(ConditionalOperators.when(Criteria.where("amt").gt(500)).then(1).otherwise(0)).as("highValue");

        // Calculate total spending count and determine spendingType (Low or High)
        ProjectionOperation projectSpendingTypeAndCount = project()
                .and("lowValue").plus("highValue").as("totalSpending")
                .and(ConditionalOperators.when(Criteria.where("lowValue").gt(0)).then("Low").otherwise("High")).as("spendingType");

        // Group by spendingType and calculate total amount spent in each category
        GroupOperation groupBySpendingTypeSumTotalAmount = group("spendingType").sum("amt").as("total_amt");
        // Sort results by spendingType in ascending order
        SortOperation sortBySpendingType = sort(Sort.by(Sort.Direction.ASC, "spendingType"));

        // Aggregation pipeline
        Aggregation aggregation = Aggregation.newAggregation(
                groupBySpendingTypeSumAmount,
                projectSpendingTypeAndCount,
                groupBySpendingTypeSumTotalAmount,
                sortBySpendingType
        );

        AggregationResults<AmountSpending> groupResults = mongoTemplate.aggregate(aggregation, "transaction", AmountSpending.class);
        return groupResults.getMappedResults();
    }

    // Show a list of top merchants where the user has spent the most.
    // the getTopMerchants method aggregates transaction data by merchant and arranges the results in
    // descending order based on total spending. Users can customize the output by specifying the
    // number of top merchants to display using the optional query parameter "limit" (default 10? idk) with the /top-merchants endpoint.
    // The returned data is structured using the TopMerchant DTO.

    public List<TopMerchant> getTopMerchants(int limit) {
        GroupOperation groupByMerchantSumAmount = group("merchant").sum("amt").as("totalSpending");
        SortOperation sortByTotalSpendingDesc = sort(Sort.by(Sort.Direction.DESC, "totalSpending"));
        LimitOperation limitResults = limit(limit);

        Aggregation aggregation = Aggregation.newAggregation(
                groupByMerchantSumAmount,
                sortByTotalSpendingDesc,
                limitResults
        );

        AggregationResults<TopMerchant> groupResults = mongoTemplate.aggregate(aggregation, "transaction", TopMerchant.class);
        return groupResults.getMappedResults();
    }





}