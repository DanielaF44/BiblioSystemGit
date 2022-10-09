package com.example.bibliosystem.batch;

import com.example.bibliosystem.entity.User;
import com.example.bibliosystem.entity.custom.UserPretRelance;
import com.example.bibliosystem.repository.UserPretRepository;
import com.example.bibliosystem.service.UserPretRelanceService;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.batch.item.data.RepositoryItemReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

/**
 * Classe de lecture des prêts expirés pour job de relance
 */
@Component
public class PretReader implements ItemReader<String> {

    @Autowired
    private UserPretRelanceService service;

    @Autowired
    UserPretRepository pretRepository;

    private List<UserPretRelance> userPretRelanceList;


    @BeforeStep
    public void before(StepExecution stepExecution) {


    }

    private int index = 0;

    @Override
    public String read() throws Exception, UnexpectedInputException,
            ParseException, NonTransientResourceException {

        String result;
        //userPretRelanceList = service.userPretRelances();
        userPretRelanceList = pretRepository.findUserRelance();

        if (index >= userPretRelanceList.size()) {
            return null;
        }
        result = userPretRelanceList.get(index).getEmail();
        System.out.println("MyCustomReader    : Reading data    : "+ result);
        index++;
        return result;
    }

}