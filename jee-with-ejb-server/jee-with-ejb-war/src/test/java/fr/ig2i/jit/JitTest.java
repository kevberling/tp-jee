package fr.ig2i.jit;


import org.junit.jupiter.api.Test;

import java.util.concurrent.TimeUnit;

//@Ignore
class JitTest {

    @Test
    void jitTesting() throws InterruptedException {
        int cpt = 0;
        while(cpt < 10){
            this.doSomething();
            TimeUnit.SECONDS.sleep(1L);
            cpt++;
        }
    }

    private static void dead() {
        String bar = new String("Bar");
    }

    private void doSomething() {
        long debut = System.currentTimeMillis();
        for(int i = 0; i < 100000000; i++) {
        	dead();
        }
        System.out.println("Time : "+(System.currentTimeMillis() - debut) + " ms");
    }
}
