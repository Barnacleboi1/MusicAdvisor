package advisor;

import java.util.List;
import java.util.Scanner;

public class PagePrinter {
    public static int entriesPerPage = 5;
    int totalPages;
    int currentPage;
    int startObject;
    int endObject;
    public void print(List<Output> outputList) {
        if (outputList == null) {
            return;
        }
        currentPage = 1;
        startObject = 0;
        endObject = entriesPerPage;
        if (outputList.size() % entriesPerPage > 0) {
            totalPages = outputList.size() / entriesPerPage + 1;
        } else {
            totalPages = outputList.size() / entriesPerPage;
        }


        Scanner scanner = new Scanner(System.in);
        printObjects(outputList);

        loop: while (true) {
            String input = scanner.nextLine();
            switch (input) {
                case "prev" -> {
                    if (currentPage == 1) {
                        System.out.println("No more pages");
                    } else {
                        currentPage--;
                        startObject = Math.max(startObject - entriesPerPage, 0);
                        endObject = Math.max(endObject - entriesPerPage, 1);
                        printObjects(outputList);
                    }
                }
                case "next" -> {
                    if (currentPage == totalPages) {
                        System.out.println("No more pages");
                    } else {
                        currentPage++;
                        startObject = Math.min(startObject + entriesPerPage, outputList.size() - 1);
                        endObject = Math.min(endObject + entriesPerPage, outputList.size());
                        printObjects(outputList);
                    }
                }
                case "exit" -> {
                    break loop;
                }
            }

        }
    }
    private void printObjects(List<Output> outputList) {
        for (int i = startObject; i < endObject; i++) {
            System.out.printf("%s", outputList.get(i).toString());
        }
        System.out.printf("---PAGE %d OF %d---\n", currentPage, totalPages);
    }
}
