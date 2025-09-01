package br.dev.ferreiras;

import br.dev.ferreiras.hackerrank.LoadBalancer;

import java.util.Arrays;
import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {

    public static void main(String[] args) {
        int k = 3;
        List<Integer> arrival = Arrays.asList(7, 2, 12, 5, 6, 30, 32);
        List<Integer> load    = Arrays.asList(15,10,10,10,15,10,10);

        List<Integer> result = LoadBalancer.loadBalance(k, arrival, load);
        System.out.println(result);
    }

}