package com.epam.cdp;

import javafx.util.Pair;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;

import static com.epam.cdp.ComparatorUtils.sortListByLength;
import static com.epam.cdp.ComparatorUtils.sortPairList;

public class Java7ParallelAggregator implements Aggregator {

    private static final Logger LOG = Logger.getLogger("Java7Aggregator.class");
    private static final int PROCESSORS = Runtime.getRuntime().availableProcessors();
    private Integer sum = 0;

    @Override
    public int sum(List<Integer> numbers) {
        ExecutorService executorService = Executors.newFixedThreadPool(PROCESSORS);
        List<Future<Integer>> tasks = new ArrayList<>();

        for (int i = 0; i < PROCESSORS; i++) {
            int step = i;
            tasks.add(executorService.submit(new Callable<Integer>() {
                public Integer call() {
                    int segmentSum = 0;
                    for (int j = step; j < numbers.size(); j += PROCESSORS) {
                        segmentSum += numbers.get(j);
                    }
                    return segmentSum;
                }
            }));
        }

        for (Future<Integer> task : tasks) {
            try {
                sum += task.get();
            } catch (InterruptedException | ExecutionException e) {
                LOG.log(Level.INFO, "Interrupted Exception or Execution Exception");
            }
        }

        return sum;
    }

    @Override
    public List<Pair<String, Long>> getMostFrequentWords(List<String> words, long limit) {
        ExecutorService executorService = Executors.newFixedThreadPool(PROCESSORS);
        List<Pair<String, Long>> pairList = new ArrayList<>();

        for (int i = 0; i < PROCESSORS; i++) {
            int step = i;
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    for (int j = step; j < words.size(); j += PROCESSORS) {
                        if (!pairList.contains(new Pair<>(words.get(j), (long) Collections.frequency(words, words.get(j))))) {
                            pairList.add(new Pair<>(words.get(step), (long) Collections.frequency(words, words.get(step))));
                        }
                    }
                }
            });
        }

        executorService.shutdown();
        awaitTermination(executorService);

        return sortPairList(pairList).subList(0, limit > pairList.size() ? pairList.size() : (int) limit);
    }

    @Override
    public List<String> getDuplicates(List<String> words, long limit) {
        ExecutorService executorService = Executors.newFixedThreadPool(PROCESSORS);
        List<String> listOfDuplicates = new ArrayList<>();

        for (int i = 0; i < PROCESSORS; i++) {
            int step = i;
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    for (int j = step; j < words.size(); j += PROCESSORS) {
                        if (frequency(words, words.get(j)) > 1 && !listOfDuplicates.contains(words.get(j).toUpperCase())) {
                            listOfDuplicates.add(words.get(j).toUpperCase());
                        }
                    }
                }
            });
        }

        executorService.shutdown();
        awaitTermination(executorService);

        return sortListByLength(listOfDuplicates).subList(0, limit > listOfDuplicates.size() ? listOfDuplicates.size() : (int) limit);
    }

    private void awaitTermination(ExecutorService executorService) {
        while (!executorService.isTerminated()) {
            try {
                executorService.awaitTermination(15, TimeUnit.MILLISECONDS);
            } catch (InterruptedException e) {
                LOG.log(Level.INFO, "Interrupted Exception");
            }
        }
    }

}
