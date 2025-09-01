package br.dev.ferreiras.hackerrank;

/*
a developer has created a web app that solves a complex math equation.
Due to high computational load from incoming requests the app is scaled
using multiple servers to handle requests simultaneously.
Requests are assigned to the next available server in a round-robin manner,
starting from the first server. If a server is busy, it is skipped.
If no server is free the request is dropped.
Each server has infinite computational capacity.
At the end of the day, the dev needs to identify the server that did the most computational
work for maintenance.
if multiple servers have the same highest load return a sorted list of their ids...
based on this context I need to develop a load balancing function that receives
the number of servers, a list of arrivals, and other list of loads as this:
public static List<Integer> loadBalance(int k, List<Integer> arrival, List<Integer> load){}

 */
import java.util.*;

public class LoadBalancer {

    public static List<Integer> loadBalance(int k, List<Integer> arrival, List<Integer> load) {
        if (k < 1) {
            throw new IllegalArgumentException("k must be >= 1");
        }

        int[] serverLoads = new int[k + 1];      // Processed requests counter
        int[] serverFreeAt = new int[k + 1];     // free server
        int pointer =1;                     // Round-robin pointer

        List<int[]> requests = new ArrayList<>();
        for (int i = 0; i < arrival.size(); i++) {
            requests.add(new int[] {arrival.get(i), load.get(i)});
        }

        // 🔃 Ordenar por tempo de chegada
        requests.sort(Comparator.comparingInt(r -> r[0]));

        for (int[] request : requests) {
            int time = request[0];
            int duration = request[1];
            boolean assigned = false;

            for (int j = 0; j < k; j++) {
                /*
                Handles circular traversal over servers indexed from 1 to k
                Allows starting from any server (pointer) and looping through all servers
                in round-robin order
                Ensures correct wrapping and consistent 1-based indexing
                Let's go piece by piece:
                🔹 pointer: The ID of the server we want to start from
                Always in range [1, k] (inclusive)
                🔹 j Loop index: j = 0 to k - 1
                Controls how many steps forward we go from pointer
                🔹 pointer + j - 1
                Why -1?
                Because pointer is 1-based, but % k assumes 0-based
                So we shift to 0-based by subtracting 1
                🔹 % k
                Ensures we wrap around in circular fashion
                Example: if k = 3, and pointer + j - 1 = 3, then (3 % 3) = 0 → wraps to the beginning
                🔹 +1
                After % k, we’re back to 0-based indexing
                So we convert back to 1-based by adding 1
                We loop j from 0 to 2:
                j	Calculation	                            Result
                0	((2 + 0 - 1) % 3) + 1 = (1 % 3) + 1	    2
                1	((2 + 1 - 1) % 3) + 1 = (2 % 3) + 1	    3
                2	((2 + 2 - 1) % 3) + 1 = (3 % 3) + 1	    1
                So: we check servers in order [2, 3, 1],
                 which is the correct round-robin order starting from server 2
                 */
                int serverId = ((pointer + j - 1) % k) + 1;

                if (serverFreeAt[serverId] <= time) {
                    // Free Server? → request assigned
                    serverFreeAt[serverId] = time + duration;
                    serverLoads[serverId]++;
                    pointer = (serverId % k) + 1; // Next server
                    assigned = true;
                    break;
                }
            }

            // Se nenhum servidor estiver livre, a requisição é descartada
        }

        // Descobre o valor máximo de requisições processadas
        int maxLoad = Arrays.stream(serverLoads).max().getAsInt();

        // Retorna os servidores com essa carga máxima, ordenados
        List<Integer> result = new ArrayList<>();
        for (int i = 1; i <= k; i++) {
            if (serverLoads[i] == maxLoad) {
                result.add(i);
            }
        }

        return result;
    }
}

