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
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
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

    public String addNewListing(ListingDTO listingDTO,MultipartFile image,String username) throws IOException {

        Listing listing = modelMapper.map(listingDTO, Listing.class);

        System.out.println("image is "+image);

        if (image != null && !image.isEmpty()) {
            String imagePath = saveImageToDisk(image,username);
            System.out.println(imagePath);
            listing.setImage(imagePath);
        }

        if(listingRepository.existsById(listingDTO.getId())){
            return VarList.RSP_DUPLICATED;
        }else{
            listingRepository.save(listing);
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

    public String saveImageToDisk(MultipartFile image, String username) throws IOException {
        String path = "C:\\Users\\Listings\\" + username;
        File directory = new File(path);

        if (!directory.exists() && !directory.mkdirs()) {
            throw new IOException("Failed to create directory: " + path);
        }

        String fileName = System.currentTimeMillis() + "_" + image.getOriginalFilename();
        File targetFile = new File(directory, fileName);

        try {
            image.transferTo(targetFile);
        } catch (IOException e) {
            System.err.println("IOException: " + e.getMessage());
            throw new IOException("Failed to save file to disk", e);
        }

        return "c:/Users/Listings/" +username + "/" + fileName;
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