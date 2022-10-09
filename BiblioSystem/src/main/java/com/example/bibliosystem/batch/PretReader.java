package com.example.bibliosystem.batch;

import com.example.bibliosystem.entity.custom.UserPretRelance;
import com.example.bibliosystem.service.UserPretRelanceService;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.annotation.BeforeStep;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.List;

/**
 * Classe de lecture des prêts expirés pour job de relance
 */
@Component
public class PretReader implements ItemReader<String> {

    @Autowired
    private UserPretRelanceService service;

    private List<UserPretRelance> userPretRelanceList;


    @BeforeStep
    public void before(StepExecution stepExecution) {
        userPretRelanceList = service.userPretRelances();
    }

    private int index = 0;

    @Override
    public String read() throws Exception, UnexpectedInputException,
            ParseException, NonTransientResourceException {

        String result;

        if (index >= userPretRelanceList.size()) {
            return null;
        }
        result = userPretRelanceList.get(index).getEmail();
        System.out.println("Job Relance Reader    : "+ result);
        index++;
        return result;
    }

}