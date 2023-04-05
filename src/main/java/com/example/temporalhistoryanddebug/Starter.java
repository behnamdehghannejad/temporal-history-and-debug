package com.example.temporalhistoryanddebug;


import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
@RequiredArgsConstructor
@Log4j2
public class Starter implements CommandLineRunner {

    private final MainWorker mainWorker;

    @Override
    public void run(String... args) throws IOException, InterruptedException {
        mainWorker.startMainWorker();
    }
}
