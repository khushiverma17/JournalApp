package com.kverma.journalApp.service;

import com.kverma.journalApp.entity.JournalEntry;
import com.kverma.journalApp.entity.User;
import com.kverma.journalApp.repository.JournalEntryRepository;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Component
public class JournalEntryService {

    @Autowired
    private JournalEntryRepository journalEntryRepository;

    @Autowired
    private UserService userService;

    @Transactional
    public void saveEntry(JournalEntry journalEntry, String userName){
        try {
            User user = userService.findByUserName(userName);
            journalEntry.setDate(LocalDateTime.now());

            JournalEntry saved = journalEntryRepository.save(journalEntry);
            // now if there comes an exception, so the journal entry has already been saved, but it will not be saved in user
            user.getJournalEntries().add(saved);
//            user.setPassword(null); // this line will make sure that next line will not be executed
            userService.saveNewUser(user);
        }catch (Exception e){
            // Transaction numbers are only allowed on a replica set member or mongos
            System.out.println(e);
            throw new RuntimeException("An error occured while saving the entry", e);
        }
    }
    public void saveEntry(JournalEntry journalEntry){
        journalEntryRepository.save(journalEntry);
    }

    public List<JournalEntry> getAll(){
        return journalEntryRepository.findAll();
    }

    public Optional<JournalEntry> findById(ObjectId id){
        return journalEntryRepository.findById(id);
    }

    @Transactional
    public boolean deleteById(ObjectId id, String userName){
        boolean removed = false;
        try {
            User user = userService.findByUserName(userName);
            removed = user.getJournalEntries().removeIf(x -> x.getId().equals(id));
            if(removed){
                userService.saveUser(user);
                journalEntryRepository.deleteById(id);
            }
        }
        catch (Exception e){
            System.out.println(e);
            throw new RuntimeException("An error occured while deleting entry");
        }
        return removed;
    }

//    public List<JournalEntry> findByUserName(String userName){
//
//    }




}


// Controller -> service -> repository