package com.multiplication.rest.multiplication.service;


import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.multiplication.rest.multiplication.domain.Multiplication;
import com.multiplication.rest.multiplication.domain.MultiplicationResultAttempt;
import com.multiplication.rest.multiplication.domain.User;
import com.multiplication.rest.multiplication.repository.MultiplicationResultAttemptRepository;
import com.multiplication.rest.multiplication.repository.UserRepository;

@Service
class MultiplicationServiceImpl implements MultiplicationService {

	private RandomGeneratorService randomGeneratorService;

    private MultiplicationResultAttemptRepository attemptRepository;
    private UserRepository userRepository;
	
    @Autowired
    public MultiplicationServiceImpl(final RandomGeneratorService randomGeneratorService,
    		final MultiplicationResultAttemptRepository attemptRepository,
            final UserRepository userRepository) {
    	
        this.randomGeneratorService = randomGeneratorService;
        this.attemptRepository = attemptRepository;
        this.userRepository = userRepository;
    }

    @Override
    public Multiplication createRandomMultiplication() {
        int factorA = randomGeneratorService.generateRandomFactor();
        int factorB = randomGeneratorService.generateRandomFactor();
        return new Multiplication(factorA, factorB, null);
    }

    /*@Override
    public boolean checkAttempt(final MultiplicationResultAttempt resultAttempt) {
        return resultAttempt.getResultAttempt() ==
                resultAttempt.getMultiplication().getFactorA() *
                resultAttempt.getMultiplication().getFactorB();
    }*/
    @Transactional
    @Override

    public boolean checkAttempt(final MultiplicationResultAttempt attempt) {
        // Check if the user already exists for that alias
        Optional<User> user = userRepository.findByAlias(attempt.getUser().getAlias());

        // Avoids 'hack' attempts
        Assert.isTrue(!attempt.isCorrect(), "You can't send an attempt marked as correct!!");

        // Check if the attempt is correct
        boolean isCorrect = attempt.getResultAttempt() ==
                        attempt.getMultiplication().getFactorA() *
                        attempt.getMultiplication().getFactorB();

        MultiplicationResultAttempt checkedAttempt = new MultiplicationResultAttempt(
                user.orElse(attempt.getUser()),
                attempt.getMultiplication(),
                attempt.getResultAttempt(),
                isCorrect
        );

        // Stores the attempt
        attemptRepository.save(checkedAttempt);

        return isCorrect;
    }

	@Override
	/*public List<MultiplicationResultAttempt> getStatsForUser(String userAlias) {
		// TODO Auto-generated method stub
		return null;
	}*/
	
	
    public List<MultiplicationResultAttempt> getStatsForUser(String userAlias) {
        return attemptRepository.findTop5ByUserAliasOrderByIdDesc(userAlias);
    }
	
	
}
