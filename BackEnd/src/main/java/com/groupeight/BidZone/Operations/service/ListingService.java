package com.groupeight.BidZone.Operations.service;

import com.groupeight.BidZone.Operations.dto.ListingDTO;
import com.groupeight.BidZone.Operations.entity.Listing;
import com.groupeight.BidZone.Operations.repo.ListingRepository;
import com.groupeight.BidZone.Operations.util.VarList;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Transactional
public class ListingService {
    @Autowired
    private ListingRepository listingRepository;

    @Autowired
    private ModelMapper modelMapper;

    public List<ListingDTO> findAllListing() {
        List<Listing> listings = listingRepository.findAll();
        return modelMapper.map(listings,new TypeToken<List<ListingDTO>>(){}.getType());
    }

    public ListingDTO searchListingById(int id) {
        if(listingRepository.existsById(id)){
            Listing listing = listingRepository.findById(id).orElse(null);
            return modelMapper.map(listing,ListingDTO.class);
        }else{
            return null;
        }
    }

    public List<ListingDTO> findListingByDate(Date date) {
        List<Listing> listings = listingRepository.findListingsByDate(date);
        return modelMapper.map(listings,new TypeToken<List<ListingDTO>>(){}.getType());
    }

    public String addNewListing(ListingDTO listingDTO) {
        if(listingRepository.existsById(listingDTO.getId())){
            return VarList.RSP_DUPLICATED;
        }else{
            listingRepository.save(modelMapper.map(listingDTO, Listing.class));
            return VarList.RSP_SUCCESS;
        }
    }

    public String updateListing(ListingDTO listingDTO){
        if(listingRepository.existsById(listingDTO.getId())){
            listingRepository.save(modelMapper.map(listingDTO,Listing.class));
            return VarList.RSP_SUCCESS;
        }else{
            return VarList.RSP_NO_DATA_FOUND;
        }
    }

    public String deleteListingByID(int listingId){
        if (listingRepository.existsById(listingId)){
            listingRepository.deleteById(listingId);
            return VarList.RSP_SUCCESS;
        }else{
            return VarList.RSP_NO_DATA_FOUND;
        }
    }

    //-----------------------------------------------------------------------------------------------------------
    /*public List<Listing> findActiveListingsUsingLikedCategories(String categories) {
        Date date = new Date();
        if (categories != null && !categories.equals("")) {
            List<String> categoriesList = Arrays.asList(categories.split(","));
            List<Listing> preferredListings = new ArrayList<>();
            preferredListings.addAll(listingRepository.findUserPreferredActiveListings(date, categoriesList));
            preferredListings.addAll(listingRepository.findUserNotPreferredActiveListings(date, categoriesList));
            return preferredListings;
        }
        return listingRepository.findActiveListings(date);
    }

    public List<Listing> findActiveListingsByIds(List<Integer> ids) {
        return listingRepository.findActiveListingsByIds(new Date(), ids);
    }

    public List<Listing> findPastListings() {
        return listingRepository.findPastListings(new Date());
    }

    public Listing findListingById(Integer id) {
        return listingRepository.findListingById(id);
    }



    public List<String> findCategories() {
        return listingRepository.findCategories();
    }

    this service method for show suggested listing
    public List<Listing> findSuggestedListings(User user, Listing listing) {
        AgeRange userAgeRange = EnumSet.allOf(AgeRange.class)
                .stream()
                .filter(e -> user.getAge() >= e.getMin() && user.getAge() <= e.getMax())
                .findAny()
                .orElse(AgeRange.RANGE_85PLUS);
        List<Integer> sameAgeRangeUsersIds = listing.getBids()
                .stream()
                .filter(b -> b.getUser().getAge() >= userAgeRange.getMin() && b.getUser().getAge() <= userAgeRange.getMax() && !b.getUser().getId().equals(user.getId()))
                .map(Bid::getUser)
                .map(User::getId)
                .collect(Collectors.toList());
        return listingRepository.findSuggestedListings(new Date(), listing.getCategory(), listing.getId(), sameAgeRangeUsersIds);
    }*/
}
