package com.neueda.muskaan1.dao;

import com.neueda.muskaan1.dto.*;
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

    public List<GenderSpending> getSpendingHistoryByGender() {
        GroupOperation groupByGenderSumAmount = group("customer.gender").sum("amt").as("total_amt");
        MatchOperation allGender = match(new Criteria("customer.gender").exists(true));
        ProjectionOperation includes = project("total_amt").and("customer.gender").previousOperation();
        SortOperation sortByAmountDesc = sort(Sort.by(Sort.Direction.DESC, "total_amt"));

        Aggregation aggregation = newAggregation(allGender, groupByGenderSumAmount, sortByAmountDesc, includes);
        AggregationResults<GenderSpending> groupResults = mongoTemplate.aggregate(aggregation, "transaction", GenderSpending.class);
        List<GenderSpending> result = groupResults.getMappedResults();
        return result;
    }

    public List<StateSpending> getSpendingHistoryByState() {
        GroupOperation groupByStateSumAmount = group("state").sum("amt").as("total_amt");
        MatchOperation allState = match(new Criteria("state").exists(true));
        ProjectionOperation includes = project("total_amt").and("state").previousOperation();
        SortOperation sortByAmountDesc = sort(Sort.by(Sort.Direction.DESC, "total_amt"));

        Aggregation aggregation = newAggregation(allState, groupByStateSumAmount, sortByAmountDesc, includes);
        AggregationResults<StateSpending> groupResults = mongoTemplate.aggregate(aggregation, "transaction", StateSpending.class);
        List<StateSpending> result = groupResults.getMappedResults();
        return result;
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
        GroupOperation groupBySpendingTypeSumTotalAmount = group("spendingType").sum("amt").as("totalAmount");
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




}