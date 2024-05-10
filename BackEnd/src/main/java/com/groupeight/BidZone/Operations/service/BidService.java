package com.groupeight.BidZone.Operations.service;

import com.groupeight.BidZone.Operations.dto.BidDTO;
import com.groupeight.BidZone.Operations.entity.Bid;
import com.groupeight.BidZone.Operations.repo.BidRepository;
import com.groupeight.BidZone.Operations.util.VarList;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Transactional
public class BidService {
    @Autowired
    private BidRepository bidRepository;

    @Autowired
    private ModelMapper modelMapper;

    public List<BidDTO> findAll() {
        List<Bid> bids = bidRepository.findAll();
        return modelMapper.map(bids,new TypeToken<List<BidDTO>>(){}.getType());
    }

    public BidDTO searchBidById(int id) {
        if(bidRepository.existsById(id)){
            Bid bid = bidRepository.findById(id).orElse(null);
            return modelMapper.map(bid,BidDTO.class);
        }else{
            return null;
        }
    }
    public List<BidDTO> searchBidByUserId(int userId) {
        List<Bid> bids = bidRepository.findBidByUserId(userId);
        return modelMapper.map(bids,new TypeToken<List<BidDTO>>(){}.getType());
    }

    public List<BidDTO> searchBidByListingId(int listingID) {
        List<Bid> bids = bidRepository.findBidByListingId(listingID);
        return modelMapper.map(bids,new TypeToken<List<BidDTO>>(){}.getType());
    }

    public String addNewBid(BidDTO bidDTO) {
        if(bidRepository.existsById(bidDTO.getId())){
            return VarList.RSP_DUPLICATED;
        }else{
            bidRepository.save(modelMapper.map(bidDTO, Bid.class));
            return VarList.RSP_SUCCESS;
        }
    }

    public String updateBid(BidDTO bidDTO){
        if(bidRepository.existsById(bidDTO.getId())){
            bidRepository.save(modelMapper.map(bidDTO,Bid.class));
            return VarList.RSP_SUCCESS;
        }else{
            return VarList.RSP_NO_DATA_FOUND;
        }
    }

    public String deleteBidByID(int bidId){
        if (bidRepository.existsById(bidId)){
            bidRepository.deleteById(bidId);
            return VarList.RSP_SUCCESS;
        }else{
            return VarList.RSP_NO_DATA_FOUND;
        }
    }

    //------------------------------------------------------------------------------------------------------------
    /*public List<Bid> findUserActiveBids(Integer userId) {
        return bidRepository.findUserActiveBids(userId);
    }

    public Map<String, Long> retrieveAgeRangeStatisticsPerCategory(Integer min, Integer max) {
        List<Object[]> statistics = bidRepository.retrieveAgeRangeStatisticsPerCategory(min, max);
        return extractStatisticsMap(statistics);
    }

    public Map<String, Long> retrieveCategoryStatisticsPerAgeRange(String category) {
        List<Object[]> statistics = bidRepository.retrieveCategoryStatisticsPerAgeRange(category);
        return extractStatisticsMap(statistics);
    }

    public Map<String, Long> retrieveGenderStatisticsPerCategory(String gender) {
        List<Object[]> statistics = bidRepository.retrieveGenderStatisticsPerCategory(gender);
        return extractStatisticsMap(statistics);
    }

    public Map<String, Long> retrieveCategoryStatisticsPerGender(String category) {
        List<Object[]> statistics = bidRepository.retrieveCategoryStatisticsPerGender(category);
        return extractStatisticsMap(statistics);
    }

    public Map<String, Long> retrieveCombinationStatisticsPerCategory(String gender, Integer min, Integer max) {
        List<Object[]> statistics = bidRepository.retrieveCombinationStatisticsPerCategory(gender, min, max);
        return extractStatisticsMap(statistics);
    }

    public Map<String, Long> retrieveCategoryStatisticsPerCombination(String category) {
        List<Object[]> statistics = bidRepository.retrieveCategoryStatisticsPerCombination(category);
        return extractCombinationStatisticsMap(statistics);
    }

    private Map<String, Long> extractStatisticsMap(List<Object[]> statistics) {
        Map<String, Long> statisticsMap = new HashMap<>();
        for (Object[] statistic : statistics) {
            statisticsMap.put(statistic[0].toString(), (Long) statistic[1]);
        }
        return statisticsMap;
    }

    private Map<String, Long> extractCombinationStatisticsMap(List<Object[]> statistics) {
        Map<String, Long> statisticsMap = new HashMap<>();
        for (Object[] statistic : statistics) {
            statisticsMap.put(statistic[0].toString() + " -> " + statistic[1].toString(), (Long) statistic[2]);
        }
        return statisticsMap;
    }*/
}
