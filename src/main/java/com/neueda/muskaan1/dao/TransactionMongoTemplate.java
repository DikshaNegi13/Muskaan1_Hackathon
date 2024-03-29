package com.neueda.muskaan1.dao;

import com.neueda.muskaan1.dto.*;
import com.neueda.muskaan1.entity.*;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.*;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

import static org.springframework.data.mongodb.core.aggregation.Aggregation.*;

@Repository
public class TransactionMongoTemplate  {
    Logger LOGGER = Logger.getLogger("TransactionMongoTemplate");

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
    public List<CityPopulation> getSpendingHistoryByPopulation() {
        GroupOperation groupByPopulationSumAmount = group("city").sum("amt").as("total_amt").first("city_population").as("cityPopulation");
        MatchOperation allPopulation = match(new Criteria("city").exists(true));
        ProjectionOperation includes = project("total_amt").andInclude("cityPopulation").and("city").previousOperation();
        SortOperation sortByAmtDESC = sort(Sort.by(Sort.Direction.DESC, "total_amt"));

        Aggregation aggregation = newAggregation(allPopulation, groupByPopulationSumAmount, sortByAmtDESC, includes);
        AggregationResults<CityPopulation> groupResults = mongoTemplate.aggregate(aggregation, "transaction", CityPopulation.class);
        List<CityPopulation> result = groupResults.getMappedResults();
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

    public List<AmountSpending> getSpendingHistoryByAmount(double lowValue, double highValue) {
        Criteria amountCriteria = Criteria.where("amt").gte(lowValue).lte(highValue);
        Query query = new Query(amountCriteria).with(Sort.by(Sort.Direction.DESC, "amt"));

        List<AmountSpending> amountSpendingList = new ArrayList<>();

        List<Transactions> transactions = mongoTemplate.find(query, Transactions.class);

        for (Transactions transaction : transactions) {
            AmountSpending amountSpending = new AmountSpending();
            amountSpending.setCustomerId(transaction.getCustomerId());
            amountSpending.setAmt(transaction.getAmt());
            amountSpendingList.add(amountSpending);
        }

        return amountSpendingList;
    }



    // Show a list of top merchants where the user has spent the most.
    // the getTopMerchants method aggregates transaction data by merchant and arranges the results in
    // descending order based on total spending. Users can customize the output by specifying the
    // number of top merchants to display using the optional query parameter "limit" (default 10? idk) with the /top-merchants endpoint.
    // The returned data is structured using the TopMerchant DTO.

    public List<TopMerchant> getTopMerchants(int limit) {
        GroupOperation groupByTopMerchantSumAmount = group("merchant").sum("amt").as("total_amt").first("city").as("city")
                .first("state").as("state")
                .first("city_population").as("cityPopulation");
        MatchOperation allMerchant = match(new Criteria("merchant").exists(true));
        ProjectionOperation includes = project("total_amt").andInclude("city").andInclude("state").andInclude("cityPopulation").and("merchant").previousOperation();
        SortOperation sortByAmtDESC = sort(Sort.by(Sort.Direction.DESC, "total_amt"));

        Aggregation aggregation = newAggregation(allMerchant, groupByTopMerchantSumAmount, sortByAmtDESC, includes,Aggregation.limit(limit));

        AggregationResults<TopMerchant> groupResults = mongoTemplate.aggregate(aggregation, "transaction",TopMerchant.class);
        List<TopMerchant> result = groupResults.getMappedResults();
        return result;
    }

}