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
        ProjectionOperation includes = project("total_amt").and("category").previousOperation();
        SortOperation sortByAmtDESC = sort(Sort.by(Sort.Direction.DESC, "total_amt"));

        Aggregation aggregation = newAggregation(allCategory, groupByCategorySumAmount, sortByAmtDESC, includes);
        AggregationResults<CategoryAmount> groupResults = mongoTemplate.aggregate(aggregation, "transaction", CategoryAmount.class);
        List<CategoryAmount> result = groupResults.getMappedResults();
        return result;


    }

    public List<MerchantAmount> getAmountForMerchant() {
        GroupOperation groupByMerchantSumAmount = group("merchant").sum("amt").as("total_amt");
        MatchOperation allMerchant = match(new Criteria("merchant").exists(true));
        ProjectionOperation includes = project("total_amt").and("merchant").previousOperation();
        SortOperation sortByAmtDesc = sort(Sort.by(Sort.Direction.DESC, "total_amt"));

        Aggregation aggregation = newAggregation(allMerchant, groupByMerchantSumAmount, sortByAmtDesc, includes);
        AggregationResults<MerchantAmount> groupResults = mongoTemplate.aggregate(aggregation, "transaction", MerchantAmount.class);
        List<MerchantAmount> result = groupResults.getMappedResults();
        return result;

    }

    public List<GenderAmount> getSpendingHistoryByGender() {
        GroupOperation groupByGenderSumAmount = group("gender").sum("amt").as("total_amt");
        MatchOperation allGender = match(new Criteria("gender").exists(true));
        ProjectionOperation includes = project("total_amt").and("gender").previousOperation();
        SortOperation sortByAmountDesc = sort(Sort.by(Sort.Direction.DESC, "total_amt"));

        Aggregation aggregation = newAggregation(allGender, groupByGenderSumAmount, sortByAmountDesc, includes);
        AggregationResults<GenderAmount> groupResults = mongoTemplate.aggregate(aggregation, "transaction", GenderAmount.class);
        List<GenderAmount> result = groupResults.getMappedResults();
        return result;
    }

    public List<JobAmount> getSpendingHistoryByJob() {
        GroupOperation groupByJobSumAmount = group("Job").sum("amt").as("total_amt");
        MatchOperation allJob = match(new Criteria("Job").exists(true));
        ProjectionOperation includes = project("total_amt").and("Job").previousOperation();
        SortOperation sortByAmtDESC = sort(Sort.by(Sort.Direction.DESC, "total_amt"));

        Aggregation aggregation = newAggregation(allJob, groupByJobSumAmount, sortByAmtDESC, includes);
        AggregationResults<JobAmount> groupResults = mongoTemplate.aggregate(aggregation, "transaction", JobAmount.class);
        List<JobAmount> result = groupResults.getMappedResults();
        return result;
    }

    public List<CityAmount> getSpendingHistoryByCity() {
        GroupOperation groupByCitySumAmount = group("city").sum("amt").as("total_amt");
        MatchOperation allCity = match(new Criteria("city").exists(true));
        ProjectionOperation includes = project("total_amt").and("city").previousOperation();
        SortOperation sortByAmtDESC = sort(Sort.by(Sort.Direction.DESC, "total_amt"));

        Aggregation aggregation = newAggregation(allCity, groupByCitySumAmount, sortByAmtDESC, includes);
        AggregationResults<CityAmount> groupResults = mongoTemplate.aggregate(aggregation, "transaction", CityAmount.class);
        List<CityAmount> result = groupResults.getMappedResults();
        return result;
    }

    public List<StateAmount> getSpendingHistoryByState() {
        GroupOperation groupByStateSumAmount = group("state").sum("amt").as("total_amt");
        MatchOperation allState = match(new Criteria("state").exists(true));
        ProjectionOperation includes = project("total_amt").and("state").previousOperation();
        SortOperation sortByAmountDesc = sort(Sort.by(Sort.Direction.DESC, "total_amt"));

        Aggregation aggregation = newAggregation(allState, groupByStateSumAmount, sortByAmountDesc, includes);
        AggregationResults<StateAmount> groupResults = mongoTemplate.aggregate(aggregation, "transaction", StateAmount.class);
        List<StateAmount> result = groupResults.getMappedResults();
        return result;
    }

    public List<AmountSpending> getSpendingHistoryByAmount() {
        GroupOperation groupByAmountSumSpending = Aggregation.group()
                .sum(ConditionalOperators.when(Criteria.where("amt").lte(500)).thenValueOf("amt").otherwise(0)).as("lowValue")
                .sum(ConditionalOperators.when(Criteria.where("amt").gt(500)).thenValueOf("amt").otherwise(0)).as("highValue");

        ProjectionOperation projectSpendingTypeAndCount = Aggregation.project()
                .and("lowValue").as("totalSpending")
                .and("highValue").as("totalSpending")
                .and(ConditionalOperators.when(Criteria.where("lowValue").gt(0)).then("Low").otherwise("High")).as("spendingType");

        GroupOperation groupBySpendingTypeSumTotalAmount = Aggregation.group("spendingType")
                .sum("totalSpending").as("total_amt");

        SortOperation sortBySpendingType = Aggregation.sort(Sort.Direction.ASC, "spendingType");

        Aggregation aggregation = Aggregation.newAggregation(
                groupByAmountSumSpending,
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
        Aggregation aggregation = Aggregation.newAggregation(
                Aggregation.group("merchant")
                        .sum("amt").as("totalSpending")
                        .first("city").as("city")
                        .first("state").as("state")
                        .first("city_population").as("cityPopulation"),
                Aggregation.sort(Sort.Direction.DESC, "totalSpending"),
                Aggregation.limit(limit)
        );


        // Execute the aggregation pipeline
        AggregationResults<TopMerchant> groupResults = mongoTemplate.aggregate(
                aggregation, "transaction", TopMerchant.class
        );

        // Get the aggregated results
        List<TopMerchant> result = groupResults.getMappedResults();
        return result;
    }


}