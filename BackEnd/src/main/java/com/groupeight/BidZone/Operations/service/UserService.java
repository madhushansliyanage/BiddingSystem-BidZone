package com.groupeight.BidZone.Operations.service;

import com.groupeight.BidZone.Operations.dto.UserDTO;
import com.groupeight.BidZone.Operations.entity.User;
import com.groupeight.BidZone.Operations.repo.UserRepository;
import com.groupeight.BidZone.Operations.util.VarList;
import jakarta.transaction.Transactional;
import org.modelmapper.ModelMapper;
import org.modelmapper.TypeToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
@Transactional
public class UserService {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private UserRepository userRepository;

    public String save(UserDTO userDTO) {
        if (userRepository.existsById(userDTO.getId())) {
            return VarList.RSP_DUPLICATED;
        }
        else{
            userRepository.save(modelMapper.map(userDTO, User.class));
            return VarList.RSP_SUCCESS; // Return success
        }
    }

    public String delete(int id) {
        if(userRepository.existsById(id)){
            userRepository.deleteById(id);
            return VarList.RSP_SUCCESS;
        }
        else{
            return VarList.RSP_NO_DATA_FOUND;
        }
    }

    public String update(UserDTO userDTO) {
        if (!userRepository.existsById(userDTO.getId())) {
            userRepository.save(modelMapper.map(userDTO, User.class));
            return VarList.RSP_NO_DATA_FOUND;
        }
        else{
            return VarList.RSP_SUCCESS;
        }
    }

    public List<UserDTO> findAll() {
        List<User> users = userRepository.findAll();
        return modelMapper.map(users, new TypeToken<List<UserDTO>>(){}.getType());
    }

    public UserDTO findUserById(Integer id) {
        User user = userRepository.findUserById(id);
        return modelMapper.map(user,UserDTO.class);
    }

    public UserDTO findUserByUsernameAndPassword(String username, String password) {
        User user = userRepository.findUserByUsernameAndPassword(username, password);
        return modelMapper.map(user,UserDTO.class);
    }



    //------------------------------------------------------------------------------------------------------------
    /*public List<User> findUsersThatLikeACategory(String category){
        return userRepository.findUsersThatLikeACategory(category);
    }

    public void checkCategorySuggestion(User user, Date bidDate, String category) {
        if (!user.getLikedCategories().contains(category)) {
            GregorianCalendar calendar = new GregorianCalendar();
            calendar.setTime(bidDate);
            calendar.add(Calendar.DATE,  -10);
            Date thresholdDate = calendar.getTime();
            Integer similarBidsCount = userRepository.getSimilarBidsCount(user.getId(), thresholdDate, category);
            if (similarBidsCount >= 5) {
                if (user.getLikedCategories() != null && !user.getLikedCategories().equals("")) {
                    user.setLikedCategories(user.getLikedCategories() + "," + category);
                } else {
                    user.setLikedCategories(category);
                }
                save(user);
            }
        }
    }*/
}
